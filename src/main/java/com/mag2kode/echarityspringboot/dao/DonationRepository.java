package com.mag2kode.echarityspringboot.dao;

import com.mag2kode.echarityspringboot.entity.Donation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("http://localhost:4200")
//@RepositoryRestResource(collectionResourceRel = "donations", path = "donations")
@Repository
public interface DonationRepository extends JpaRepository<Donation, Long>{
    Page<Donation> findByCategoryIdId(@RequestParam("id") Long id, Pageable pageable);
    Page<Donation> findBySize(@RequestParam("s") Long id, Pageable pageable);
    Page<Donation> findByNameContaining(@RequestParam("name") String name, Pageable pageable);

}
