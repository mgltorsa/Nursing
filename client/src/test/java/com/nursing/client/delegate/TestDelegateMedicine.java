package com.nursing.client.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

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

import com.nursing.client.delegate.services.MedicineDelegate;
import com.nursing.client.model.Medicine;

/**
 * TestDelegateMedicine
 */
@RunWith(MockitoJUnitRunner.class)
public class TestDelegateMedicine {

	@Mock
	private RestTemplate template;

	@InjectMocks
	@Autowired
	private MedicineDelegate delegate;

	private Medicine expected;

	private String url;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		ReflectionTestUtils.setField(delegate, "host", "nursing-rest.herokuapp.com");
		ReflectionTestUtils.setField(delegate, "protocol", "https");
		ReflectionTestUtils.setField(delegate, "hostBasepath", "api");

		expected = new Medicine(1l, "name", "genericName", "laboratory", "indications");

		url = "https://nursing-rest.herokuapp.com/api";
	}

	@Test
	public void testSaveMedicine() {

		ResponseEntity<Medicine> response = new ResponseEntity<Medicine>(expected, HttpStatus.ACCEPTED);

		when(template.postForEntity(url + "/medicine", expected, Medicine.class)).thenReturn(response);

		Medicine actual = delegate.save(expected);

		assertEquals(expected.getConsecutive(), actual.getConsecutive());
		assertEquals(expected.getAdministrationType(), actual.getAdministrationType());
		assertEquals(expected.getContraIndications(), actual.getContraIndications());
		assertEquals(expected.getGenericName(), actual.getGenericName());
		assertEquals(expected.getIndications(), actual.getIndications());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getLaboratory(), actual.getLaboratory());

	}

	@Test(expected = Exception.class)
	public void testSaveExistMedicine() {

		ResponseEntity<Medicine> response = new ResponseEntity<Medicine>(expected, HttpStatus.PRECONDITION_FAILED);

		when(template.postForEntity(url + "/medicine", expected, Medicine.class)).thenReturn(response);

		delegate.save(expected);

		fail();

	}

	@Test
	public void testGetMedicine() {

		ResponseEntity<Medicine> response = new ResponseEntity<Medicine>(expected, HttpStatus.ACCEPTED);

		when(template.getForEntity(url + "/medicines", Medicine.class, expected.getConsecutive())).thenReturn(response);

		Medicine actual = delegate.get(expected.getConsecutive());

		assertEquals(expected.getConsecutive(), actual.getConsecutive());
		assertEquals(expected.getAdministrationType(), actual.getAdministrationType());
		assertEquals(expected.getContraIndications(), actual.getContraIndications());
		assertEquals(expected.getGenericName(), actual.getGenericName());
		assertEquals(expected.getIndications(), actual.getIndications());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getLaboratory(), actual.getLaboratory());

	}

	@Test(expected = Exception.class)
	public void testGetNonExistMedicine() {
		
		ResponseEntity<Medicine> response = new ResponseEntity<Medicine>(expected, HttpStatus.PRECONDITION_FAILED);
		when(template.getForEntity(url + "/medicines", Medicine.class, expected.getConsecutive())).thenReturn(response);

		delegate.get(expected.getConsecutive());

		fail();

	}
	
	@Test(expected = IllegalArgumentException.class)
    public void testGetNullId() {
    	delegate.get(null);
    }

	@Test
	public void testUpdateMedicine() {
		Medicine medicine = expected;
		medicine.setConsecutive(1l);
		delegate.update(medicine);
		verify(template).put(url + "/medicine", medicine);
		assertTrue(true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateNonValidMedicine() {
		Medicine m = new Medicine();
		
		delegate.update(m);
		verifyZeroInteractions(template);
	}

	@Test
	public void testDeleteMedicine() {

		Medicine medicine = expected;
		medicine.setConsecutive(1l);
		delegate.delete(medicine.getConsecutive());
		verify(template).delete(url + "/medicine", medicine.getConsecutive());
		assertTrue(true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNullId() {
		delegate.delete(null);
		verifyZeroInteractions(template);

	}
}