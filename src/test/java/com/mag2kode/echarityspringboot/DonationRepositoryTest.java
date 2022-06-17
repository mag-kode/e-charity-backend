package com.mag2kode.echarityspringboot;


import com.mag2kode.echarityspringboot.dao.DonationCategoryRepository;
import com.mag2kode.echarityspringboot.dao.DonationRepository;
import com.mag2kode.echarityspringboot.entity.Donation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Equals;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
@DataJpaTest
@AutoConfigureMockMvc
public class DonationRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests{

    @Autowired
    public MockMvc mockMvc;

    private TestEntityManager testEntityManager;

    @Autowired
    private DonationRepository donationRepository;

    @MockBean
    private DonationCategoryRepository donationCategoryRepository;

    @Test
    public void whenFindByName_thenReturnDonations() {
        Donation d = new Donation();
        d.setName("Test donation item");
        testEntityManager.persist(d);
        testEntityManager.flush();
        Pageable listOfDonations = PageRequest.ofSize(10);

        Iterable<Donation> result = donationRepository.findByNameContaining(d.getName(), listOfDonations);

        assertThat(result).hasSize(1);
        result.forEach(r -> assertThat(r.getName()).isEqualTo(d.getName()));
    }

    @Test
    void simpleTest() {
        String text = UUID.randomUUID().toString();
        Donation mockDonation = mock(Donation.class);
        mockDonation.setName(text);
        donationRepository.save(mockDonation);
        Pageable listOfDonations = PageRequest.ofSize(10);
        Iterable<Donation> result = donationRepository.findByNameContaining(text, listOfDonations);
        Assert.assertEquals(1, result.iterator().next().getName());
        Assert.assertEquals(text, result.iterator().next().getName());
    }

    @Test
    public void givenDonation_whenGetDonations_thenReturnJsonArray()
            throws Exception {

        String text = UUID.randomUUID().toString();
        Donation mockDonation = mock(Donation.class);
        mockDonation.setName(text);

        List<Donation> allDonations = Arrays.asList(mockDonation);
        Pageable listOfDonations = PageRequest.ofSize(10);

        given(donationRepository.findByNameContaining(text, listOfDonations)).willReturn((Page<Donation>) allDonations);
        when(donationRepository.findByNameContaining(text, listOfDonations)).thenReturn((Page<Donation>) listOfDonations.first());

        mockMvc.perform(get("/api/donations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect((ResultMatcher) jsonPath("$[0].name").value(mockDonation.getName()));
    }

}
