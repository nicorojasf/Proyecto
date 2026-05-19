package com.bupa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bupa.model.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Integer> {
    List<Alerta> findByResueltaFalseOrderByFechaIncidenteDesc();
    List<Alerta> findByModuloEmisorAndResueltaFalse(String moduloEmisor);
    List<Alerta> findByNivelCriticoAndResueltaFalse(String nivelCritico);
}
