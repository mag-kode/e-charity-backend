package com.mag2kode.echarityspringboot;

import com.mag2kode.echarityspringboot.dao.DonationRepository;
import com.mag2kode.echarityspringboot.entity.Donation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.slf4j.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = MOCK)
@AutoConfigureMockMvc
@TestPropertySource(
		locations = "classpath:application-integrationtest.properties")
class ECharitySpringBootApplicationTests {
	private static Logger logger = LoggerFactory.getLogger(ECharitySpringBootApplicationTests.class);


	private MockMvc mvc;

	private DonationRepository repository;

	@BeforeAll
	static void setup() {
		logger.info("@BeforeAll - executes once before all test methods in this class");
	}

	@BeforeEach
	void init() {
		logger.info("@BeforeEach - executes before each test method in this class");
	}

	@Test
	void contextLoads() {
	}

}
