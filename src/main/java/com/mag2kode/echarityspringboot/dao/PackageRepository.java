package com.mag2kode.echarityspringboot.dao;

import com.mag2kode.echarityspringboot.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "deliveries", path = "package")
@Repository
public interface PackageRepository extends JpaRepository<Delivery, Long>{
}
