package com.Lavanderia.Lavanderia.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.Lavanderia.Lavanderia.model.Lavanderia;
import java.util.List;


public interface LavanderiaRepository extends JpaRepository<Lavanderia, Integer> {
    List<Lavanderia> findAll();
}
