package com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.pedido;

import com.tpUnidad10.unidad10_APIRestSpringBoot.entities.Pedido;
import com.tpUnidad10.unidad10_APIRestSpringBoot.enums.Estado;
import com.tpUnidad10.unidad10_APIRestSpringBoot.enums.FormaPago;

public record PedidoEdit(
        Estado estado,
        FormaPago formaPago
) {
    public void applyTo(Pedido pedido) {
        if (this.estado != null) {
            pedido.setEstado(this.estado);
        }
        if (this.formaPago != null) {
            pedido.setFormaPago(this.formaPago);
        }
    }
}
