package com.nursing.client;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nursing.client.demo.Car;
import com.nursing.client.demo.DemoDelegate;

@SpringBootApplication
public class Application {
	
	private static DemoDelegate delegate = new DemoDelegate();

	public static void main(String[] args) {
		Car car = delegate.getCar(1);
		System.out.println("the car -> "+car);
	}

}
