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

import com.nursing.client.delegate.services.UrgencyAttentionDelegate;
import com.nursing.client.model.Patient;
import com.nursing.client.model.UrgencyAttention;

/**
 * TestDelegateMedicine
 */
@RunWith(MockitoJUnitRunner.class)
public class TestDelegateUrgencyAttention {

	@Mock
	private RestTemplate template;

	@InjectMocks
	@Autowired
	private UrgencyAttentionDelegate delegate;

	
	private Patient patient;
	
	private UrgencyAttention expected;

	private String url;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		ReflectionTestUtils.setField(delegate, "host", "nursing-rest.herokuapp.com");
		ReflectionTestUtils.setField(delegate, "protocol", "https");
		ReflectionTestUtils.setField(delegate, "hostBasepath", "api");

		patient =  new Patient("123", "miguel", "torres");
		expected = new UrgencyAttention(patient, LocalDate.now(), "general-description", "procedure", true);

		url = "https://nursing-rest.herokuapp.com/api";
	}

	@Test
	public void testSaveUrgencyAttention() {

		ResponseEntity<UrgencyAttention> response = new ResponseEntity<UrgencyAttention>(expected, HttpStatus.ACCEPTED);

		when(template.postForEntity(url + "/urgency", expected, UrgencyAttention.class)).thenReturn(response);

		UrgencyAttention actual = delegate.save(expected);

		assertEquals(expected.getConsecutive(), actual.getConsecutive());
		assertEquals(expected.getDate(),actual.getDate());
		assertEquals(expected.getForwarded(), actual.getForwarded());
		assertEquals(expected.getForwardedPlace(),actual.getForwardedPlace());
		assertEquals(expected.getPatient().getDocument(),actual.getPatient().getDocument());
		assertEquals(expected.getProcedure(),actual.getProcedure());
		assertEquals(expected.getGeneralDescription(),actual.getGeneralDescription());

	}

	@Test(expected = Exception.class)
	public void testSaveExistUrgencyAttention() {

		ResponseEntity<UrgencyAttention> response = new ResponseEntity<UrgencyAttention>(expected, HttpStatus.PRECONDITION_FAILED);

		when(template.postForEntity(url + "/urgency", expected, UrgencyAttention.class)).thenReturn(response);

		delegate.save(expected);

		fail();

	}

	@Test
	public void testGetUrgencyAttention() throws URISyntaxException {

		ResponseEntity<UrgencyAttention> response = new ResponseEntity<UrgencyAttention>(expected, HttpStatus.ACCEPTED);


		UrgencyAttention ur = expected;
		ur.setConsecutive(1l);
		URI uri = new URI(url+"/urgencies?id="+ur.getConsecutive());
		when(template.getForEntity(uri, UrgencyAttention.class)).thenReturn(response);
		
		UrgencyAttention actual = delegate.get(ur.getConsecutive());

		assertEquals(expected.getDate(),actual.getDate());
		assertEquals(expected.getForwarded(), actual.getForwarded());
		assertEquals(expected.getForwardedPlace(),actual.getForwardedPlace());
		assertEquals(expected.getPatient().getDocument(),actual.getPatient().getDocument());
		assertEquals(expected.getProcedure(),actual.getProcedure());
		assertEquals(expected.getGeneralDescription(),actual.getGeneralDescription());

	}

	@Test(expected = Exception.class)
	public void testGetNonExistUrgencyAttention() throws URISyntaxException {
		
		UrgencyAttention ur = expected;
		ur.setConsecutive(1l);
		ResponseEntity<UrgencyAttention> response = new ResponseEntity<UrgencyAttention>(expected, HttpStatus.PRECONDITION_FAILED);
		
		URI uri = new URI(url+"/urgencies?id="+ur.getConsecutive());

		when(template.getForEntity(uri, UrgencyAttention.class)).thenReturn(response);
		delegate.get(ur.getConsecutive());

		fail();

	}
	
	@Test(expected = IllegalArgumentException.class)
    public void testGetNullId() {
    	delegate.get(null);
    }

	@Test
	public void testUpdateUrgencyAttention() {
		UrgencyAttention ur = expected;
		ur.setConsecutive(1l);
		delegate.update(ur);
		verify(template).put(url + "/urgency", ur, UrgencyAttention.class);
		assertTrue(true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateNonValidUrgencyAttention() {
		UrgencyAttention m = new UrgencyAttention();
		
		delegate.update(m);
		verifyZeroInteractions(template);
	}

	@Test
	public void testDeleteUrgencyAttention() throws URISyntaxException {

		UrgencyAttention ur = expected;
		ur.setConsecutive(1l);
		URI uri = new URI(url+"/urgency?id=id");

		delegate.delete(ur.getConsecutive());
		verify(template).delete(uri);
		assertTrue(true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNullId() {
		delegate.delete(null);
		verifyZeroInteractions(template);

	}
}