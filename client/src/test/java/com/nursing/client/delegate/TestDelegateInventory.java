package com.nursing.client.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.nursing.client.delegate.services.InventaryMedicineDelegate;
import com.nursing.client.model.InventoryMedicine;
import com.nursing.client.model.Medicine;

/**
 * TestDelegateMedicine
 */
@RunWith(MockitoJUnitRunner.class)
public class TestDelegateInventory {

	@Mock
	private RestTemplate template;

	@InjectMocks
	@Autowired
	private InventaryMedicineDelegate delegate;

	private Medicine medicine;
	
	private InventoryMedicine expected;

	private String url;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		ReflectionTestUtils.setField(delegate, "host", "nursing-rest.herokuapp.com");
		ReflectionTestUtils.setField(delegate, "protocol", "https");
		ReflectionTestUtils.setField(delegate, "hostBasepath", "api");

		medicine = new Medicine(1l, "name", "genericName", "laboratory", "indications");
		expected = new InventoryMedicine(medicine, 10, "ubication", LocalDate.now());
		expected.setMedicine(medicine);

		url = "https://nursing-rest.herokuapp.com/api";
	}

	@Test
	public void testSaveInventory() throws URISyntaxException {

		ResponseEntity<InventoryMedicine> response = new ResponseEntity<InventoryMedicine>(expected, HttpStatus.ACCEPTED);

		URI uri = new URI(url + "/inventory");

		when(template.postForEntity(uri, expected, InventoryMedicine.class)).thenReturn(response);

		InventoryMedicine actual = delegate.save(expected);

		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getAvailableQuantity(),actual.getAvailableQuantity());
		assertEquals(expected.getExpirationDate(),actual.getExpirationDate());
		assertEquals(expected.getMedicine().getConsecutive(),actual.getMedicine().getConsecutive());
		assertEquals(expected.getUbication(),actual.getUbication());

	}

	@Test(expected = Exception.class)
	public void testSaveExistInventory() throws URISyntaxException {

		ResponseEntity<InventoryMedicine> response = new ResponseEntity<InventoryMedicine>(expected, HttpStatus.PRECONDITION_FAILED);

		URI uri = new URI(url+"/inventory");

		when(template.postForEntity(uri, expected, InventoryMedicine.class)).thenReturn(response);

		delegate.save(expected);

		fail();

	}

	@Test
	public void testGetInventory() throws URISyntaxException {

		
		InventoryMedicine inventory = expected;
		inventory.setId(1l);
		
		URI uri = new URI(url+"/inventories?id="+inventory.getId());

		ResponseEntity<InventoryMedicine> response = new ResponseEntity<InventoryMedicine>(inventory, HttpStatus.ACCEPTED);

		when(template.getForEntity(uri, InventoryMedicine.class)).thenReturn(response);

		InventoryMedicine actual = delegate.get(inventory.getId());

		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getAvailableQuantity(),actual.getAvailableQuantity());
		assertEquals(expected.getExpirationDate(),actual.getExpirationDate());
		assertEquals(expected.getMedicine().getConsecutive(),actual.getMedicine().getConsecutive());
		assertEquals(expected.getUbication(),actual.getUbication());

	}

	@Test(expected = Exception.class)
	public void testGetNonExistInventory() throws URISyntaxException {
		
		InventoryMedicine inventory = expected;
		inventory.setId(1l);
		URI uri = new URI(url+"/inventories?id="+inventory.getId());

		ResponseEntity<InventoryMedicine> response = new ResponseEntity<InventoryMedicine>(inventory, HttpStatus.PRECONDITION_FAILED);
		when(template.getForEntity(uri, InventoryMedicine.class)).thenReturn(response);

		delegate.get(inventory.getId());

		fail();

	}
	
	@Test(expected = IllegalArgumentException.class)
    public void testGetNullId() {
    	delegate.get(null);
    }

	@Test
	public void testUpdateInventory() {
		InventoryMedicine inventory = expected;
		inventory.setId(1l);
		delegate.update(inventory);
		verify(template).put(url + "/inventory", inventory);
		assertTrue(true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateNonValidInventory() {
		InventoryMedicine m = new InventoryMedicine();
		
		delegate.update(m);
		verifyZeroInteractions(template);
	}

	@Test
	public void testDeleteInventory() throws URISyntaxException {

		InventoryMedicine inventory = expected;
		inventory.setId(1l);
		delegate.delete(inventory.getId());
		URI uri = new URI(url+"/inventory?id="+1l);

		verify(template).delete(uri);
		assertTrue(true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNullId() {
		delegate.delete(null);
		verifyZeroInteractions(template);

	}
}