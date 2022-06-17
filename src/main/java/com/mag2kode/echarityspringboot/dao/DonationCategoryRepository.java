package com.mag2kode.echarityspringboot.dao;

import com.mag2kode.echarityspringboot.entity.Donation;
import com.mag2kode.echarityspringboot.entity.DonationCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "donationCategory", path = "donation-category")
public interface DonationCategoryRepository extends JpaRepository <DonationCategory, Long> {
    @Query("select dc from DonationCategory dc")
    Page<Donation> getAll(Pageable pageable);
}
