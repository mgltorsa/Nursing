package com.nursing.client;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import com.nursing.client.delegate.Delegate;
import com.nursing.client.model.InventoryMedicine;
import com.nursing.client.model.Medicine;
import com.nursing.client.model.Patient;
import com.nursing.client.model.Supply;



@SpringBootApplication
@Configuration
public class Application {
	

	@Autowired
	private Delegate delegate;

	public static void main(String[] args) {
		 Application app = SpringApplication.run(Application.class, args).getBean(Application.class);
//		 Patient patient = new Patient("1107527450","Miguel","Torres");
//		 Patient pa = app.delegate.save(patient, Patient.class);
//		 System.out.println(pa);
//		 Medicine medicine = app.delegate.get(1l, Medicine.class);
//		 System.out.println(medicine);
//		 Patient pe = app.delegate.get("1",Patient.class);
//		 System.out.println(pe);
//		 Medicine m = new Medicine(7l, "name-7", "generic-7", "lab-7", "ind-7");
//		 app.delegate.save(m, Medicine.class);
//		 Patient p = new Patient("test-d", "test-n", "test-l");
//		 app.delegate.save(p, Patient.class);
//		 Medicine m = new Medicine(7l, "test-7", "generic-7", "lab-7", "indi7");
//		 app.delegate.save(m, Medicine.class);
//		 Supply supply = new Supply(m,10,p,LocalDate.now(),"test-k");		 
//		 app.delegate.save(supply, Supply.class);
//		 InventoryMedicine inventory = app.delegate.get(1l, InventoryMedicine.class);
//		 System.out.println(inventory);
		 		
	}
	
	@Bean
	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
