package com.Lavanderia.Lavanderia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Lavanderia.Lavanderia.client.InventarioClient;
import com.Lavanderia.Lavanderia.model.Lavanderia;
import com.Lavanderia.Lavanderia.repository.LavanderiaRepository;


@Service
public class LavanderiaService {
    

    @Autowired
    private LavanderiaRepository lavanderiaRepository;

    public List<Lavanderia> obtenerTodos() {
        return lavanderiaRepository.findAll();
    }
    public Lavanderia obtenerPorId(Integer id) {
    return lavanderiaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el registro con ID: " + id));
    }
    
    @Autowired
    private InventarioClient inventarioClient;


    public Lavanderia registrarRetorno(Integer id, Integer cantidadRecibida) {
    // 1. Buscamos el registro de la lavandería (ej: el 21)
    Lavanderia registro = lavanderiaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Registro de lavandería no encontrado"));

    // 2. Llamada al micro de Inventario para SUMAR stock usando el ID de la prenda guardado
    inventarioClient.llamarAumento(registro.getPrendaId(), cantidadRecibida);

    // 3. Actualizamos datos locales
    registro.setCantidadRecibida(cantidadRecibida);
    registro.setEstadoRopa("FINALIZADO");
    registro.setFechaDeRetorno(LocalDateTime.now());
    
    return lavanderiaRepository.save(registro);
    }

    // Dentro de registrarEnvio en LavanderiaService
    public Lavanderia registrarEnvio(Lavanderia registro, Integer cantidadADescontar) {
    // 1. Validar que el ID no sea nulo antes de llamar a Inventario
    if (registro.getPrendaId() == null) {
        throw new RuntimeException("El ID de la prenda es obligatorio para el descuento.");
    }
    try {
        // 2. Llamada automática usando el ID del JSON (ej: 47)
        inventarioClient.llamarDescuento(registro.getPrendaId(), cantidadADescontar);
        registro.setCantidadEnviada(cantidadADescontar);
    } catch (Exception e) {
        throw new RuntimeException("Error en comunicación con Inventario: " + e.getMessage());
    }
    registro.setEstadoRopa("PROCESANDO");

    return lavanderiaRepository.save(registro);
    }




}