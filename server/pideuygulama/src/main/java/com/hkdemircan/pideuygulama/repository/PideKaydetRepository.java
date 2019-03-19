package com.hkdemircan.pideuygulama.repository;

import com.hkdemircan.pideuygulama.model.PideKaydet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PideKaydetRepository extends JpaRepository<PideKaydet, Long> {
    List<PideKaydet> findAllByEmail(String email);
    PideKaydet findFirstById(int id);
}
