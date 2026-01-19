package org.uteq.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uteq.backend.entities.usuario;
import org.uteq.backend.service.UsuarioService;

import java.util.List;

@RestController  // Indica que es un controlador REST
@RequestMapping("/api/usuarios")  // Ruta base: /api/usuarios
@CrossOrigin(origins = "http://localhost:4200")  // Permitir peticiones desde cualquier origen (para desarrollo)
public class UsuarioController {

    @Autowired  // Inyecta el servicio
    private UsuarioService usuarioService;

    // GET /api/usuarios - Listar todos
    @GetMapping
    public ResponseEntity<List<usuario>> listarTodos() {
        List<usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    // GET /api/usuarios/{id} - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<usuario> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/usuarios - Crear nuevo
    @PostMapping
    public ResponseEntity<usuario> crear(@RequestBody usuario usuario) {
        try {
            usuario nuevoUsuario = usuarioService.crear(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /api/usuarios/{id} - Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<usuario> actualizar(
            @PathVariable Long id,
            @RequestBody usuario usuario) {
        try {
            usuario usuarioActualizado = usuarioService.actualizar(id, usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/usuarios/{id} - Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}