package com.mag2kode.echarityspringboot;

import com.mag2kode.echarityspringboot.dao.DonationCategoryRepository;
import com.mag2kode.echarityspringboot.dao.DonationRepository;
import com.mag2kode.echarityspringboot.entity.Donation;
import org.junit.jupiter.api.Test;
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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ECharitySpringBootApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DonationIntegrationTest{

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

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
        ResponseEntity<Iterable<Donation>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/donations", HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Donation>>() {
        });

        Iterable<Donation> donations = responseEntity.getBody();
        assertThat(donations).hasSize(7);

        assertThat(donations).containsExactly().anyMatch(d -> d.getName().equals(""));
    }

    @Test
    public void givenGetDonationCategoryApiCall_whenDonationCategoryListRetrieved_thenSizeMatchAndListContainsDonationCategoryNames() {
        ResponseEntity<Iterable<Donation>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/donations", HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Donation>>() {
        });

        Iterable<Donation> donations = responseEntity.getBody();
        assertThat(donations)
                .hasSize(7);

        assertThat(donations).containsExactly().anyMatch(d -> d.getName().equals(""));
        assertThat(donations).containsExactly().anyMatch(d -> d.getCategoryId().equals(""));
        assertThat(donations).allMatch(Objects::nonNull);
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
