package com.Inventario.Inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Inventario.Inventario.model.Prendas;
import com.Inventario.Inventario.repository.PrendasRepository;

import jakarta.transaction.Transactional;

@Service
public class PrendasService {

    @Autowired
    private PrendasRepository prendasRepository;

    public List<Prendas> obtenerTodas(){
        return prendasRepository.findAll();
    }

    public Prendas guardar(Prendas prendas){
        return prendasRepository.save(prendas);
    }

    public Prendas obtenerPorId(Integer id) {  
    return prendasRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró la prenda con ID: " + id));
    }


    @Transactional
    public void descontarStock(Integer id, Integer cantidad) {
    Prendas prenda = prendasRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró la prenda con ID: " + id));

    // VALIDACIÓN: Si el stock actual es menor a lo solicitado, lanzamos error
    if (prenda.getStockActual() < cantidad) {
        throw new RuntimeException("Stock insuficiente en Inventario. Disponible: " + prenda.getStockActual());
    }
    prenda.setStockActual(prenda.getStockActual() - cantidad);
    prendasRepository.save(prenda);
    }

    @Transactional
    public void aumentarStock(Integer id, Integer cantidad) {
    Prendas prenda = prendasRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró la prenda con ID: " + id));
    
    prenda.setStockActual(prenda.getStockActual() + cantidad);
    prendasRepository.save(prenda);
}
}   
