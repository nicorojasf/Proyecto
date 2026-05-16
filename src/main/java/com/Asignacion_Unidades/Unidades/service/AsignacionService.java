package com.Asignacion_Unidades.Unidades.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Asignacion_Unidades.Unidades.client.InventarioClient;
import com.Asignacion_Unidades.Unidades.client.LavanderiaClient;
import com.Asignacion_Unidades.Unidades.dto.InventarioDTO;
import com.Asignacion_Unidades.Unidades.dto.LavanderiaDTO;
import com.Asignacion_Unidades.Unidades.model.AsignacionUnidad;
import com.Asignacion_Unidades.Unidades.repository.AsignacionRepository;


@Service
public class AsignacionService {

    @Autowired
    private AsignacionRepository asignacionRepository;

    // Listar todas las asignaciones
    public List<AsignacionUnidad> obtenerTodas() {
        return asignacionRepository.findAll();
    }

    @Autowired
    private LavanderiaClient lavanderiaClient;

    public String chequearEstadoRopa(Integer registroId) {
        // Obtenemos la información del registro 21, por ejemplo
        LavanderiaDTO registro = lavanderiaClient.consultarEstado(registroId);
        return "El pedido " + registroId + " está en estado: " + registro.getEstadoRopa();
    }

    @Autowired
    private InventarioClient inventarioClient;
    public AsignacionUnidad realizarAsignacionInicial(Integer prendaId, Integer cantidad, String area) {
    InventarioDTO prenda = inventarioClient.obtenerPrendaPorId(prendaId);
    System.out.println("Asignando la prenda: " + prenda.getNombrePrenda());
    inventarioClient.llamarDescuento(prendaId, cantidad);
    inventarioClient.llamarDescuento(prendaId, cantidad);
    AsignacionUnidad nuevaAsignacion = new AsignacionUnidad();
    
    nuevaAsignacion.setIdUnidad(prendaId); 
    nuevaAsignacion.setStockUnidad(cantidad);
    nuevaAsignacion.setAreaAsignada(area);
    nuevaAsignacion.setEstadoUnidad("ASIGNADA");
    
    return asignacionRepository.save(nuevaAsignacion);
    }

    public LavanderiaDTO enviarRopaSucia(Integer prendaId, Integer cantidad) {
        LavanderiaDTO peticion = new LavanderiaDTO();
        peticion.setPrendaId(prendaId);
        peticion.setCantidadEnviada(cantidad);
        return lavanderiaClient.solicitarServicio(peticion, cantidad);
    }


    // En AsignacionService.java
    public AsignacionUnidad recibirRopaLimpia(Integer prendaId, Integer cantidad, String area) {
    // Buscamos la asignación actual de esa unidad y prenda
        List<AsignacionUnidad> asignaciones = asignacionRepository.findByIdUnidadAndAreaAsignada(prendaId, area);
        if (asignaciones.isEmpty()) {
            throw new RuntimeException("No existe una asignación previa para esta área");
        }
        AsignacionUnidad asignacion = asignaciones.get(0);

    // Sumamos la ropa que vuelve limpia al stock de la unidad
        asignacion.setStockUnidad(asignacion.getStockUnidad() + cantidad);
    
        return asignacionRepository.save(asignacion);
    }


}
