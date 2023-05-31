package com.example.lab7.Controllers;

import com.example.lab7.Entitys.Solicitude;
import com.example.lab7.Repositorys.SolicitudeRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@Controller
@RequestMapping("solicitudes")
public class SolicitudesController {

    final SolicitudeRepository solicitudeRepository;

    public SolicitudesController(SolicitudeRepository solicitudeRepository) {
        this.solicitudeRepository = solicitudeRepository;
    }

    @GetMapping("/registro")
    public List<Solicitude> listarSolicitudes() {return solicitudeRepository.findAll();}

    @PostMapping("/aprobarSolicitud")
    public ResponseEntity<HashMap<String, Object>> guardarSolicitudes(
            @RequestBody Solicitude solicitud) {

        HashMap<String, Object> responseJson = new HashMap<>();

        if(solicitud.getSolicitudEstado().equals("")){
            solicitud.setSolicitudEstado("pendiente");
        }
        if(solicitud.getSolicitudEstado().equals("pendiente")){
            solicitudeRepository.save(solicitud);
            responseJson.put("Monto solicitado", solicitud.getSolicitudMonto());
            responseJson.put("id",solicitud.getId());
        }
        else
            responseJson.put("Solicitud ya atendida", solicitud.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }



    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionException(HttpServletRequest request) {
        HashMap<String, String> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un solicitud");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }

}
