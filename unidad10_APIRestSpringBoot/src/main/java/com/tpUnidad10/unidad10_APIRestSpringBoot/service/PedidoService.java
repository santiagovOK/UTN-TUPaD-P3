package com.tpUnidad10.unidad10_APIRestSpringBoot.service;

import com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.pedido.PedidoCreate;
import com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.pedido.PedidoDto;
import com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.pedido.PedidoEdit;

import java.util.List;

public interface PedidoService {
    public PedidoDto save(PedidoCreate pedidoCreate);
    public PedidoDto findById(Long id);
    public List<PedidoDto> findAll();
    public PedidoDto update(PedidoEdit pedidoEdit, Long idPedido);
    public void deleteById(Long id);
}
