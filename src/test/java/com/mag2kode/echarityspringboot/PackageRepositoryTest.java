package com.mag2kode.echarityspringboot;


import com.mag2kode.echarityspringboot.dao.DonationCategoryRepository;
import com.mag2kode.echarityspringboot.dao.DonationRepository;
import com.mag2kode.echarityspringboot.dto.Package;
import com.mag2kode.echarityspringboot.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = MOCK)
@AutoConfigureMockMvc
public class PackageRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests{

    //@Autowired
    public MockMvc mockMvc;

    private TestEntityManager testEntityManager;

    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port = 8080;

    @MockBean
    private DonationCategoryRepository donationCategoryRepository;


    /*@Test
    public void givenDonation_whenGetDonations_thenReturnJsonArray()
            throws Exception {

        String text = UUID.randomUUID().toString();
        Donation mockDonation = mock(Donation.class);
        mockDonation.setName(text);

        List<Donation> allDonations = Arrays.asList(mockDonation);
        Pageable listOfDonations = PageRequest.ofSize(10);

        given(packageRepository.findByNameContaining(text, listOfDonations)).willReturn((Page<Donation>) allDonations);
        when(packageRepository.findByNameContaining(text, listOfDonations)).thenReturn((Page<Donation>) listOfDonations.first());

        mockMvc.perform(get("/api/donations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect((ResultMatcher) jsonPath("$[0].name").value(mockDonation.getName()));
    }*/

    //@Test
    public void givenPostNeed_whenBodyRequestMatcherJson_thenResponseContainsEqualObjectProperties() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Package pack = new Package();
        Address mockAddr = mock(Address.class);
        pack.setAddress(mockAddr);
        pack.setDelivery(mock(Delivery.class));
        pack.setReceiver(mock(Receiver.class));
        pack.setVolunteer(mock(Volunteer.class));
        Set<DeliveryItem> mockItems = new HashSet<>();
        mockItems.add(mock(DeliveryItem.class));
        pack.setDeliveryItems(mockItems);

        HttpEntity<Package> requestEntity = new HttpEntity<>(pack, headers);
        ResponseEntity<Package> responseEntity =
                restTemplate.postForEntity("http://localhost:" + port + "/api/package", requestEntity, Package.class);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful());
    }

}
