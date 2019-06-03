package com.nursing.client;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.nursing.client.delegate.TestDelegateInventory;
import com.nursing.client.delegate.TestDelegateMedicine;
import com.nursing.client.delegate.TestDelegatePatient;
import com.nursing.client.delegate.TestDelegateSupply;
import com.nursing.client.delegate.TestDelegateUrgencyAttention;

@RunWith(Suite.class)
@SuiteClasses(value = {TestDelegateInventory.class, TestDelegateMedicine.class, 
		TestDelegatePatient.class, TestDelegateSupply.class,
		TestDelegateUrgencyAttention.class})
public class ApplicationTests {


}
