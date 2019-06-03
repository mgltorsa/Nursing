package com.nursing.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import com.nursing.client.delegate.Delegate;
import com.nursing.client.model.Medicine;
import com.nursing.client.model.Patient;



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
//		 Patient pe = app.delegate.get("1107527450",Patient.class);
//		 System.out.println(pe);
		 
		
	}
	
	@Bean
	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
