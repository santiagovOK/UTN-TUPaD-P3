package com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.detallePedido;

import com.tpUnidad10.unidad10_APIRestSpringBoot.entities.DetallePedido;

public record DetallePedidoDto(
        Long id,
        int cantidad,
        Double subtotal,
        String nombreProducto
) {
    public static DetallePedidoDto toDto(DetallePedido detalle) {
        return new DetallePedidoDto(
                detalle.getId(),
                detalle.getCantidad(),
                detalle.getSubtotal(),
                detalle.getProducto() != null ? detalle.getProducto().getNombre() : null
        );
    }
}
