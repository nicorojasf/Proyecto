package com.Inventario.Inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Inventario.Inventario.model.Prendas;

public interface PrendasRepository extends JpaRepository<Prendas, Integer> {

    List<Prendas> findById(Long id);



}
