package com.techlab.nerdcafee.service;
import com.techlab.nerdcafee.model.ItemPedido;
import java.util.List;


public interface ItemPedidoService {

ItemPedido crearItemPedido(ItemPedido itemPedido);

void eliminarItemPedido(Long id);

ItemPedido obtenerPorId(Long id);

List<ItemPedido> listarItemPedido();
}
