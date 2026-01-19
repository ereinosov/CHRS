package org.uteq.backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uteq.backend.entities.usuario; // Asegúrate que tu entidad sea con 'U' mayúscula
import org.uteq.backend.service.UsuarioService;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Validar que lleguen datos
            if (request.getUsuarioApp() == null || request.getClaveApp() == null) {
                response.put("success", false);
                response.put("error", "Faltan datos (usuarioApp o claveApp)");
                return ResponseEntity.badRequest().body(response);
            }

            // 1. Buscar usuario
            Optional<usuario> usuarioOpt = usuarioService.buscarPorUsuarioApp(request.getUsuarioApp());

            if (usuarioOpt.isEmpty()) {
                response.put("success", false);
                response.put("error", "Usuario no encontrado");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            usuario usuario = usuarioOpt.get();

            // 2. Verificar contraseña
            if (!usuario.getClaveApp().equals(request.getClaveApp())) {
                response.put("success", false);
                response.put("error", "Contraseña incorrecta");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            // 3. ¡Login Exitoso! Construimos la respuesta
            response.put("success", true);
            response.put("usuario", usuario.getUsuarioApp());

            // --- ESTO ES LO IMPORTANTE ---
            // Enviamos el número de rol (3, 2 o 1)
            response.put("id_rol", usuario.getId_rol());
            // -----------------------------

            response.put("id", usuario.getId_usuario());

            // Agregamos también el nombre del rol por si acaso (admin, postulante, etc)
            response.put("rol", usuario.getRol());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("error", "Error interno: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

// Clase auxiliar para recibir los datos (Mapea 'username' a 'usuarioApp' por si acaso)
class LoginRequest {
    @JsonProperty("usuarioApp")
    private String usuarioApp;

    @JsonProperty("claveApp")
    private String claveApp;

    // Estos métodos permiten que funcione aunque Angular mande "username"
    @JsonProperty("username")
    private void setUsername(String username) { this.usuarioApp = username; }

    @JsonProperty("password")
    private void setPassword(String password) { this.claveApp = password; }

    public String getUsuarioApp() { return usuarioApp; }
    public void setUsuarioApp(String usuarioApp) { this.usuarioApp = usuarioApp; }
    public String getClaveApp() { return claveApp; }
    public void setClaveApp(String claveApp) { this.claveApp = claveApp; }
}