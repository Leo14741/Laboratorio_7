package com.example.lab7.Controllers;

import com.example.lab7.Entitys.Solicitude;
import com.example.lab7.Entitys.Usuario;
import com.example.lab7.Repositorys.SolicitudeRepository;
import com.example.lab7.Repositorys.UsuarioRepository;
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
@RequestMapping("users")
public class UserController {
    final UsuarioRepository usuarioRepository;
    final SolicitudeRepository solicitudeRepository;

    public UserController(UsuarioRepository usuarioRepository, SolicitudeRepository solicitudeRepository) {
        this.usuarioRepository = usuarioRepository;
        this.solicitudeRepository = solicitudeRepository;
    }

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @PostMapping("/crear")
    public ResponseEntity<HashMap<String, Object>> guardarUsuario(
            @RequestBody Usuario usuario){

        HashMap<String, Object> responseJson = new HashMap<>();

        usuarioRepository.save(usuario);
        responseJson.put("id creado",usuario.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionException(HttpServletRequest request) {
        HashMap<String, String> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un usuario");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }


}
