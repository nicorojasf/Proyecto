package com.bupa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bupa.client.InventarioClient;
import com.bupa.dto.InventarioDTO;
import com.bupa.model.BajaPrenda;
import com.bupa.repository.BajaPrendaRepository;

import jakarta.transaction.Transactional;

@Service
public class AuditoriaService {
    
    @Autowired
    private BajaPrendaRepository bajaRepo;

    @Autowired
    private InventarioClient inventarioClient;

    public List<BajaPrenda> listarTodas() {
        //1. Validar que la prenda existe en Inventario
        return bajaRepo.findAll();
    }

    @Transactional
    public BajaPrenda procesarDescarte(BajaPrenda descarte) {
        //1. Valida existencia modulo 1
        ResponseEntity<InventarioDTO> response = inventarioClient.obtenerPorId(descarte.getPrendaId());

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null){
            throw new RuntimeException("Operacion abortada: La prenda con ID "+ descarte.getPrendaId()+" no existe en Inventario central.");
        }
        
        InventarioDTO prendaInfo = response.getBody();

        //2. Comprobar si hay stock antes de procesar
        if (prendaInfo.getStockActual() <descarte.getCantidad()){
            throw new RuntimeException("ERROR: Stock insuficiente en Inventario para dar baja. Stockactual disponible: "+ prendaInfo.getStockActual()+", cantidad solicitada: "+ descarte.getCantidad());
        }

        //3. Modifica stock directamente en Inventario
        inventarioClient.descontarStock(descarte.getPrendaId(), descarte.getCantidad());

        //4. Guarda registro  en auditoria
        return bajaRepo.save(descarte);
    }
}
