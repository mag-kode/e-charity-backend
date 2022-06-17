package com.mag2kode.echarityspringboot;

import com.mag2kode.echarityspringboot.entity.Donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.*;

@SpringBootApplication(scanBasePackages={"com.mag2kode.echarityspringboot"})
public class ECharitySpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECharitySpringBootApplication.class, args);
	}

}
