package org.uteq.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uteq.backend.entities.usuario;
import org.uteq.backend.repository.UsuarioRepository;
import org.uteq.backend.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@Service  // Indica que es un servicio
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired  // Inyección de dependencias
    private UsuarioRepository usuarioRepository;

    @Override
    public List<usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public usuario crear(usuario usuario) {
        // Validaciones de negocio
        if (existeUsuarioBd(usuario.getUsuarioBd())) {
            throw new RuntimeException("El usuario BD ya existe");
        }

        // Guardar en BD
        return usuarioRepository.save(usuario);
    }

    @Override
    public usuario actualizar(Long id, usuario usuarioActualizado) {
        // Buscar el usuario existente
        usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualizar campos
        usuarioExistente.setUsuarioBd(usuarioActualizado.getUsuarioBd());
        usuarioExistente.setClaveBd(usuarioActualizado.getClaveBd());
        usuarioExistente.setUsuarioApp(usuarioActualizado.getUsuarioApp());
        usuarioExistente.setClaveApp(usuarioActualizado.getClaveApp());

        // Guardar cambios
        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<usuario> buscarPorUsuarioBd(String usuarioBd) {
        return usuarioRepository.findByUsuarioBd(usuarioBd);
    }

    @Override
    public Optional<usuario> buscarPorUsuarioApp(String usuarioApp) {
        return usuarioRepository.findByUsuarioApp(usuarioApp);
    }

    @Override
    public boolean existeUsuarioBd(String usuarioBd) {
        return usuarioRepository.existsByUsuarioBd(usuarioBd);
    }

    @Override
    public boolean existeUsuarioApp(String usuarioApp) {
        return usuarioRepository.existsByUsuarioApp(usuarioApp);
    }
}