package com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.pedido;

import com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.detallePedido.DetallePedidoCreate;
import com.tpUnidad10.unidad10_APIRestSpringBoot.entities.Pedido;
import com.tpUnidad10.unidad10_APIRestSpringBoot.enums.Estado;
import com.tpUnidad10.unidad10_APIRestSpringBoot.enums.FormaPago;

import java.time.LocalDate;
import java.util.List;

public record PedidoCreate(
        Estado estado,
        FormaPago formaPago,
        List<DetallePedidoCreate> detalles
) {
    public Pedido toEntity() {
        return Pedido.builder()
                .fecha(LocalDate.now())
                .estado(this.estado != null ? this.estado : Estado.PENDIENTE)
                .formaPago(this.formaPago)
                .build();
    }
}
