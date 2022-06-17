package com.mag2kode.echarityspringboot;

import com.mag2kode.echarityspringboot.dao.DonationCategoryRepository;
import com.mag2kode.echarityspringboot.dao.DonationRepository;
import com.mag2kode.echarityspringboot.entity.Donation;
import com.mag2kode.echarityspringboot.entity.DonationCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.IterableResult;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ECharitySpringBootApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DonationIntegrationTest{

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port = 8080;

    @Autowired
    DonationRepository donationRepository;

    @Autowired
    DonationCategoryRepository donationCategoryRepository;

    @Test
    public void contextLoads() {
        assertThat(donationRepository)
                .isNotNull();
        assertThat(donationCategoryRepository)
                .isNotNull();
    }

    @Test
    public void givenGetDonationsApiCall_whenDonationListRetrieved_thenSizeMatchAndListContainsDonationNames() {
        ResponseEntity<Donation[]> responseEntity =
                restTemplate.getForEntity("http://localhost:" + port + "/api/donations", Donation[].class);

        Donation[] donations = responseEntity.getBody();
        assertThat(donations).hasSize(7);

        assertThat(donations).containsExactly().anyMatch(d -> d.getName().equals(""));
    }

    @Test
    public void givenGetDonationCategoryApiCall_whenDonationCategoryListRetrieved_thenSizeMatchAndListContainsDonationCategoryNames() {
        ResponseEntity<DonationCategory[]> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/api/donation-category", DonationCategory[].class);

        DonationCategory[] donationCategories = responseEntity.getBody();
        assertThat(donationCategories)
                .hasSize(7);

        assertThat(donationCategories).containsExactly().anyMatch(d -> d.getCategoryName().equals(""));
        assertThat(donationCategories).containsExactly().anyMatch(d -> d.getId().equals(""));
        assertThat(donationCategories).allMatch(Objects::nonNull);
    }

    /*@Test
    public void givenPostNeed_whenBodyRequestMatcherJson_thenResponseContainsEqualObjectProperties() {
        final ResponseEntity<Need> postResponse = restTemplate.postForEntity("http://localhost:" + port + "/api/needs", prepareNeedForm(), Need.class);
        Need need = postResponse.getBody();
        Assertions
                .assertThat(postResponse.getStatusCode())
                .isEqualByComparingTo(HttpStatus.CREATED);

        assertThat(need, hasProperty("status", is("ASKED")));
        assertThat(need.getNeedItem(), hasItem(hasProperty("quantity", is(2))));
    }*/

}
