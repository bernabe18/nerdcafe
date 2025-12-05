package com.techlab.nerdcafee.service.ServiceImpl;
import com.techlab.nerdcafee.model.Cliente;
import com.techlab.nerdcafee.model.ItemPedido;
import com.techlab.nerdcafee.model.Pedido;
import com.techlab.nerdcafee.model.Producto;
import com.techlab.nerdcafee.repository.ClienteRepository;
import com.techlab.nerdcafee.repository.PedidoRepository;
import com.techlab.nerdcafee.repository.ProductoRepository;
import com.techlab.nerdcafee.service.PedidoService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class PedidoServiceImpl implements PedidoService {

private final PedidoRepository pedidoRepository;
private final ClienteRepository clienteRepository;
private final ProductoRepository productoRepository;

public PedidoServiceImpl(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProductoRepository productoRepository) {
	this.pedidoRepository = pedidoRepository;
	this.clienteRepository = clienteRepository;
	this.productoRepository = productoRepository;
}

@Override
public List<Pedido> listarPedido() {
	return pedidoRepository.findAll();
}

@Override
public Pedido obtenerPorId(Long id) {
	return pedidoRepository.findById(id).orElse(null);
}

@Override
public Pedido crearPedido(Pedido pedido) {
	return pedidoRepository.save(pedido);
}

@Override
public Pedido actualizarPedido(Long id, Pedido datos) {
	
	Pedido pedido = pedidoRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
	

	if (datos.getCliente() != null && datos.getCliente().getId() != null) {
		
		Cliente cliente = clienteRepository.findById(datos.getCliente().getId())
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
		
		pedido.setCliente(cliente);
		
		// Mantener sincronizada la relación inversa
		if (!cliente.getPedidos().contains(pedido)) {
			cliente.getPedidos().add(pedido);
		}
	}
	
	if (datos.getItems() != null) {
		
		// Elimina ítems anteriores
		pedido.getItems().clear();
		
		for (ItemPedido item : datos.getItems()) {
			
			Producto producto = productoRepository.findById(item.getProducto().getId())
					.orElseThrow(() -> new RuntimeException("Producto no encontrado"));
			
			ItemPedido nuevoItem = new ItemPedido();
			nuevoItem.setProducto(producto);
			nuevoItem.setCantidad(item.getCantidad());
			nuevoItem.setPedido(pedido);
			
			pedido.getItems().add(nuevoItem);
		}
	}
	// Actualizar fecha
	pedido.setFecha(LocalDateTime.now());
	
	// Recalcular total
	double total = pedido.getItems().stream()
			.mapToDouble(i -> i.getProducto().getPrecio() * i.getCantidad())
			.sum();
	pedido.setTotal(total);
	
	return pedidoRepository.save(pedido);
}

@Override
public void eliminarPedido(Long id) {
	pedidoRepository.deleteById(id);
}

@Override
public List<Pedido> obtenerPedidosPorCliente(Long clienteId) {
	return pedidoRepository.findByClienteId(clienteId);
}
}
