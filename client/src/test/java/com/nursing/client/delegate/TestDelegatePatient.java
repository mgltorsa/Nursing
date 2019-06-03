package com.nursing.client.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;

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

import com.nursing.client.delegate.services.PatientDelegate;
import com.nursing.client.model.Patient;

/**
 * TestDelegateMedicine
 */
@RunWith(MockitoJUnitRunner.class)
public class TestDelegatePatient {

	@Mock
	private RestTemplate template;

	@InjectMocks
	@Autowired
	private PatientDelegate delegate;

	private Patient expected;

	private String url;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		ReflectionTestUtils.setField(delegate, "host", "nursing-rest.herokuapp.com");
		ReflectionTestUtils.setField(delegate, "protocol", "https");
		ReflectionTestUtils.setField(delegate, "hostBasepath", "api");

		expected = new Patient("123", "miguel", "torres");

		url = "https://nursing-rest.herokuapp.com/api";
	}

	@Test
	public void testSavePatient() {

		ResponseEntity<Patient> response = new ResponseEntity<Patient>(expected, HttpStatus.ACCEPTED);

		when(template.postForEntity(url + "/patient", expected, Patient.class)).thenReturn(response);

		Patient actual = delegate.save(expected);

		assertEquals(expected.getDocument(), actual.getDocument());
		assertEquals(expected.getAcademicDependency(), actual.getAcademicDependency());
		assertEquals(expected.getAcademicProgram(),actual.getAcademicProgram());
		assertEquals(expected.getAttentions(), actual.getAttentions());
		assertEquals(expected.getNames(),actual.getNames());
		assertEquals(expected.getLastnames(),actual.getLastnames());
		assertEquals(expected.getState(),actual.getState());

	}

	@Test(expected = Exception.class)
	public void testSaveExistPatient() {

		ResponseEntity<Patient> response = new ResponseEntity<Patient>(expected, HttpStatus.PRECONDITION_FAILED);

		when(template.postForEntity(url + "/patient", expected, Patient.class)).thenReturn(response);

		delegate.save(expected);

		fail();

	}

	@Test
	public void testGetPatient() throws URISyntaxException {

		ResponseEntity<Patient> response = new ResponseEntity<Patient>(expected, HttpStatus.ACCEPTED);

		URI uri = new URI(url+"/patients?document="+expected.getDocument());

		when(template.getForEntity(uri, Patient.class)).thenReturn(response);

		Patient actual = delegate.get(expected.getDocument());

		assertEquals(expected.getDocument(), actual.getDocument());
		assertEquals(expected.getAcademicDependency(), actual.getAcademicDependency());
		assertEquals(expected.getAcademicProgram(),actual.getAcademicProgram());
		assertEquals(expected.getAttentions(), actual.getAttentions());
		assertEquals(expected.getNames(),actual.getNames());
		assertEquals(expected.getLastnames(),actual.getLastnames());
		assertEquals(expected.getState(),actual.getState());

	}

	@Test(expected = Exception.class)
	public void testGetNonExistPatient() throws URISyntaxException {
		
		ResponseEntity<Patient> response = new ResponseEntity<Patient>(expected, HttpStatus.PRECONDITION_FAILED);
		
		URI uri = new URI(url+"/patients?document="+expected.getDocument());

		when(template.getForEntity(uri, Patient.class)).thenReturn(response);

		delegate.get(expected.getDocument());

		fail();

	}
	
	@Test(expected = IllegalArgumentException.class)
    public void testGetNullId() {
    	delegate.get(null);
    }

	@Test
	public void testUpdatePatient() {
		delegate.update(expected);
		verify(template).put(url + "/patient?id="+expected.getDocument(), expected);
		assertTrue(true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateNonValidPatient() {
		Patient m = new Patient();
		
		delegate.update(m);
		verifyZeroInteractions(template);
	}

	@Test
	public void testDeletePatient() throws URISyntaxException {

		Patient patient = expected;
		delegate.delete(patient.getDocument());
		URI uri = new URI(url+"/patient?id="+patient.getDocument());

		verify(template).delete(uri);
		assertTrue(true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNullId() {
		delegate.delete(null);
		verifyZeroInteractions(template);

	}
}