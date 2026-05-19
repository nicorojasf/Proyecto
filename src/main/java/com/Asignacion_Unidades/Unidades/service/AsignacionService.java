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

    @Autowired
    private InventarioClient inventarioClient;

    @Autowired
    private LavanderiaClient lavanderiaClient;

    public List<AsignacionUnidad> obtenerTodas() {
        return asignacionRepository.findAll();
    }

    
    public String chequearEstadoRopa(Integer registroId) {
        LavanderiaDTO registro = lavanderiaClient.consultarEstado(registroId);
        return "El pedido " + registroId + " está en estado: " + registro.getEstadoRopa();
    }

    public AsignacionUnidad realizarAsignacionInicial(Integer prendaId, Integer cantidad, String area) {
        InventarioDTO prenda = inventarioClient.obtenerPrendaPorId(prendaId);
        System.out.println("Asignando la prenda: " + prenda.getNombrePrenda());
        try {
            inventarioClient.llamarDescuento(prendaId, cantidad);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo descontar stock: " + e.getMessage());
        }
        AsignacionUnidad nuevaAsignacion = new AsignacionUnidad();
        nuevaAsignacion.setIdUnidad(prendaId);
        nuevaAsignacion.setAreaAsignada(area);
        nuevaAsignacion.setStockUnidad(cantidad);
        nuevaAsignacion.setEstadoUnidad("ASIGNADA");
        return asignacionRepository.save(nuevaAsignacion);
    }

    
    public LavanderiaDTO enviarRopaSucia(Integer prendaId, Integer cantidad, String area) {
    List<AsignacionUnidad> asignaciones = asignacionRepository.findByIdUnidadAndAreaAsignada(prendaId, area);
    
    if (asignaciones.isEmpty()) {
        throw new RuntimeException("No existe asignación para la prenda " + prendaId + " en el área " + area);
    }
    
    AsignacionUnidad asignacion = asignaciones.get(0);
    
    if (asignacion.getStockUnidad() < cantidad) {
        throw new RuntimeException("Stock insuficiente en " + area + ". Disponible: " + asignacion.getStockUnidad());
    }
    
    asignacion.setStockUnidad(asignacion.getStockUnidad() - cantidad);
    asignacion.setEstadoUnidad("EN LAVANDERIA");
    
    asignacionRepository.save(asignacion);
    
    LavanderiaDTO peticion = new LavanderiaDTO();
    peticion.setPrendaId(prendaId);
    peticion.setCantidadEnviada(cantidad);
    peticion.setArea(area);
    
    return lavanderiaClient.solicitarServicio(peticion, cantidad);
    }

}
