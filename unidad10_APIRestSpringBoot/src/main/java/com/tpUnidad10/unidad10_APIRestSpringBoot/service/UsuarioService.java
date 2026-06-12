package com.tpUnidad10.unidad10_APIRestSpringBoot.service;

import com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.usuario.UsuarioCreate;
import com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.usuario.UsuarioDto;
import com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.usuario.UsuarioEdit;

import java.util.List;

public interface UsuarioService {
    public UsuarioDto save(UsuarioCreate usuarioCreate);
    public UsuarioDto findById(Long id);
    public UsuarioDto findByMail(String mail); // TP10 - Firma del método findByMail para que devuelva el DTO correspondiente.
    public List<UsuarioDto> findAll();
    public UsuarioDto update(UsuarioEdit usuarioEdit, Long idUsuario);
    public void deleteById(Long id);
}
