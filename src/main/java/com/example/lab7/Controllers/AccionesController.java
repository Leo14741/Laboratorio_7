package com.example.lab7.Controllers;

import com.example.lab7.Entitys.Accione;
import com.example.lab7.Entitys.Usuario;
import com.example.lab7.Repositorys.AccioneRepository;
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
@RequestMapping("acciones")
public class AccionesController {
    final AccioneRepository accioneRepository;

    public AccionesController(AccioneRepository accioneRepository) {
        this.accioneRepository = accioneRepository;
    }

    @GetMapping("/listar")
    public List<Accione> listarAcciones(){ return accioneRepository.findAll();}

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> guardarAccion(
            @RequestBody Accione accion) {

        HashMap<String, Object> responseJson = new HashMap<>();

        accioneRepository.save(accion);
        responseJson.put("idCreado",accion.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionException(HttpServletRequest request) {
        HashMap<String, String> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar una accion");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }
}
