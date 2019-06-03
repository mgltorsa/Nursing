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

import com.nursing.client.delegate.services.SupplyDelegate;
import com.nursing.client.model.Medicine;
import com.nursing.client.model.Patient;
import com.nursing.client.model.Supply;

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

/**
 * TestDelegateSupply
 */

@RunWith(MockitoJUnitRunner.class)
public class TestDelegateSupply {

    @Mock
    private RestTemplate template;

    @InjectMocks
    @Autowired
    private SupplyDelegate delegate;


    private Supply expected;

    private Medicine medicine;

    private Patient patient;

    private String url;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(delegate, "host", "nursing-rest.herokuapp.com");
        ReflectionTestUtils.setField(delegate, "protocol", "https");
        ReflectionTestUtils.setField(delegate, "hostBasepath", "api");

        patient = new Patient("123", "miguel", "torres");
        medicine = new Medicine(1l, "name", "genericName", "laboratory", "indications");
        expected = new  Supply(medicine , 10, patient, LocalDate.now() , "pathology");
     
        url= "https://nursing-rest.herokuapp.com/api";
    }

    
   
    @Test
    public void testSaveSupply(){
        

        ResponseEntity<Supply> response =  new ResponseEntity<Supply>(expected, HttpStatus.ACCEPTED);
        
        when(template.postForEntity(url+"/supply",expected, Supply.class)).thenReturn(response);

        Supply actual = delegate.save(expected);
        
        assertEquals(expected.getConsecutive() , actual.getConsecutive());
        assertEquals(expected.getDate(), actual.getDate());
        assertEquals(expected.getMedicine().getConsecutive() , actual.getMedicine().getConsecutive());
        assertEquals(expected.getObservations(), actual.getObservations());
        assertEquals(expected.getPathology(), actual.getPathology());
        assertEquals(expected.getPatient().getDocument(), actual.getPatient().getDocument());

        
    }

    @Test(expected = Exception.class)
    public void testSaveExistSupply(){

        when(template.postForEntity(url+"/supply", expected, Supply.class)).thenReturn(new ResponseEntity<Supply>(expected, HttpStatus.PRECONDITION_FAILED));
        
        delegate.save(expected);

        fail();

    }

    @Test
    public void testGetSupply() throws URISyntaxException{
    	
    	Supply supply = expected;
    	
    	supply.setConsecutive(1l);
    	
        ResponseEntity<Supply> response =  new ResponseEntity<Supply>(expected, HttpStatus.ACCEPTED);

        URI uri = new URI(url+"/supplies?id="+supply.getConsecutive());

        when(template.getForEntity(uri, Supply.class)).thenReturn(response);

        Supply actual = delegate.get(supply.getConsecutive());
        
        assertEquals(expected.getDate(), actual.getDate());
        assertEquals(expected.getMedicine().getConsecutive() , actual.getMedicine().getConsecutive());
        assertEquals(expected.getObservations(), actual.getObservations());
        assertEquals(expected.getPathology(), actual.getPathology());
        assertEquals(expected.getPatient().getDocument(), actual.getPatient().getDocument());        

    }
    
//    @Test(expected = Exception.class)
//	public void testGetNonExistSupply() throws URISyntaxException {
//		
//		Supply supply = expected;
//		supply.setConsecutive(1l);
//		ResponseEntity<Supply> response = new ResponseEntity<Supply>(supply, HttpStatus.PRECONDITION_FAILED);
//		
//		URI uri = new URI(url+"/supplies?id="+supply.getConsecutive());
//
//		when(template.getForEntity(uri, Supply.class)).thenReturn(response);
//		
//		delegate.get(supply.getConsecutive());
//
//		fail();
//
//	}
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetNullId() {
    	delegate.get(null);
    }
    
    
    
    
    @Test
    public void testUpdateSupply() {
    	Supply supply = expected;
    	supply.setConsecutive(1l);
    	delegate.update(supply);
    	verify(template).put(url+"/supply",supply);
    	assertTrue(true);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNonValidSupply() {
    	delegate.update(expected);
    	verifyZeroInteractions(template);
    }
    
    @Test
    public void testDeleteSupply() throws URISyntaxException {
    	
    	Supply supply = expected;
    	supply.setConsecutive(1l);
    	delegate.delete(supply.getConsecutive());
		URI uri = new URI(url+"/supply?id="+supply.getConsecutive());

    	verify(template).delete(uri);
    	assertTrue(true);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNullId() {
    	delegate.delete(expected.getConsecutive());
    	verifyZeroInteractions(template);
    	
    }
    

    
}