package org.uteq.backend.usuario.repository;
import org.springframework.stereotype.Repository;
import org.uteq.backend.usuario.entity.Usuario;
@Repository
public class UsuarioRepositoryImpl implements  IUsuarioRepository{
    @Override
    public Usuario validateAndSave(Usuario usuario) {
        System.out.println("Guardando informacion");
        return usuario;
    }
}
