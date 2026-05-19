package com.bupa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bupa.client.InventarioClient;
import com.bupa.client.LavanderiaClient;
import com.bupa.client.UsuarioClient;
import com.bupa.dto.InventarioDTO;
import com.bupa.dto.LavanderiaDTO;
import com.bupa.dto.UsuarioDTO;
import com.bupa.model.Alerta;
import com.bupa.repository.AlertaRepository;

@Service
public class AlertaService {
    
    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private InventarioClient inventarioClient;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private LavanderiaClient lavanderiaClient;

    public Alerta registrarNuevaAlerta(Alerta alerta) {
        //1. integracion segun datos del modelo procedente
        try{
            if ("INVENTARIO".equalsIgnoreCase(alerta.getModuloEmisor()) && alerta.getEntidadReferenciaId() != null) {
            ResponseEntity<InventarioDTO> response = inventarioClient.obtenerPrendaPorId(alerta.getEntidadReferenciaId());
            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                InventarioDTO prenda = response.getBody();

                alerta.setDescripcion(alerta.getDescripcion()+" | Prenda Afectada: "+ prenda.getNombrePrenda()+ "(Stock: "+ prenda.getStockActual()+ ")");
            }
        } else if ("LAVANDERIA".equalsIgnoreCase(alerta.getModuloEmisor()) && alerta.getEntidadReferenciaId() != null) {
            ResponseEntity<LavanderiaDTO> response = lavanderiaClient.obtenerProcesoLavado(alerta.getEntidadReferenciaId());
            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
                LavanderiaDTO lavado = response.getBody();

                alerta.setDescripcion(alerta.getDescripcion()+ " | Estado de Carga Actual: "+ lavado.getEstado());
            }
        }
        //2. Integracion y Auditoria
        else if ("AUDITORIA".equalsIgnoreCase(alerta.getModuloEmisor())){
            alerta.setDescripcion(alerta.getDescripcion()+ " | [Verificado por Modulo Descarte]");
        }
        if (alerta.getOperadorResponsable() != null) {
            ResponseEntity<UsuarioDTO> userResponse = usuarioClient.obtenerPorUsername(alerta.getOperadorResponsable());
            if (userResponse.getStatusCode().is2xxSuccessful() && userResponse.getBody() != null){
                UsuarioDTO usuario = userResponse.getBody();
                if(!usuario.isActivo()){
                    alerta.setDescripcion(alerta.getDescripcion()+ " | ¡ALERTA CRITICA: El operador responsable esta inactivo!");
                }
            }else {
                alerta.setOperadorResponsable(alerta.getOperadorResponsable()+ " (No registrado)");
            }
        }
    } catch (Exception e) {
        //Si un micro externo esta caido
        alerta.setDescripcion(alerta.getDescripcion()+ "[Nota: No se puede conectar para corroborar datos]");
    }
    return alertaRepository.save(alerta);
    }

    public List<Alerta> listarAlertasActivas(){
        return alertaRepository.findByResueltaFalseOrderByFechaIncidenteDesc();
    }

    public List<Alerta> listarAlertasActivasPorModulo(String modulo){
        return alertaRepository.findByModuloEmisorAndResueltaFalse(modulo);
    }

    public Alerta cerrarAlerta(Integer id) {
        Alerta alerta = alertaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe ninguna contingencia activa con el ID: "+ id));
        alerta.setResuelta(true);
        return alertaRepository.save(alerta);
    }
}
