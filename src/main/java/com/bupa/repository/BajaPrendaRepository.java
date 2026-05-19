package com.bupa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bupa.model.BajaPrenda;

@Repository
public interface BajaPrendaRepository extends JpaRepository<BajaPrenda, Integer> {

}
