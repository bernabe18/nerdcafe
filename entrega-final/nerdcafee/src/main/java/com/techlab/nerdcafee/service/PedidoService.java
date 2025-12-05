package com.techlab.nerdcafee.service;
import com.techlab.nerdcafee.model.Pedido;
import org.springframework.stereotype.Service;
import java.util.List;


public interface PedidoService {

Pedido crearPedido(Pedido pedido);

Pedido actualizarPedido(Long id, Pedido pedido);

void eliminarPedido(Long id);

Pedido obtenerPorId(Long id);

List<Pedido> listarPedido();

List<Pedido> obtenerPedidosPorCliente(Long clienteId);
}
