package org.uteq.backend.usuario.repository;
import org.springframework.stereotype.Repository;
import org.uteq.backend.usuario.entity.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public abstract class UsuarioRepositoryImpl implements  IUsuarioRepository{
    @Override
    public Usuario validateAndSave(Usuario usuario) {
        System.out.println("Guardando información");
        return usuario;
    }
    public Usuario searchByUsername(String username) {
        return null;
    }

    @Override
    public Optional<Usuario> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Boolean existsByUsername(String username) {
        return null;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Usuario> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Usuario> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteInBatch(Iterable<Usuario> entities) {
        IUsuarioRepository.super.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Usuario> entities) {
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Usuario getOne(Long aLong) {
        return null;
    }

    @Override
    public Usuario getById(Long aLong) {
        return null;
    }

    @Override
    public Usuario getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Usuario> List<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Usuario> List<S> findAll(org.springframework.data.domain.Example<S> example) {
        return List.of();
    }
}
