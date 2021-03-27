package com.kubel.repo;

import com.kubel.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad,Long>, JpaSpecificationExecutor<Ad> {
    List<Ad> findAllByActiveTrue();
}
