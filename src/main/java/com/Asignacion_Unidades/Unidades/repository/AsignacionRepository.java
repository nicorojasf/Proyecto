package com.Asignacion_Unidades.Unidades.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Asignacion_Unidades.Unidades.model.AsignacionUnidad;

@Repository
public interface AsignacionRepository extends JpaRepository<AsignacionUnidad,Integer>{

    List<AsignacionUnidad> findByIdUnidad(Integer idUnidad);

    List<AsignacionUnidad> findByAreaAsignada(String areaAsignada);

    List<AsignacionUnidad> findByIdUnidadAndAreaAsignada(Integer idUnidad, String areaAsignada);

}   
