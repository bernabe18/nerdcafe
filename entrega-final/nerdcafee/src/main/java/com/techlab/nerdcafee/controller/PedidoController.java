package com.techlab.nerdcafee.controller;
import com.techlab.nerdcafee.model.Cliente;
import com.techlab.nerdcafee.model.ItemPedido;
import com.techlab.nerdcafee.model.Pedido;
import com.techlab.nerdcafee.model.Producto;
import com.techlab.nerdcafee.service.ClienteService;
import com.techlab.nerdcafee.service.PedidoService;
import com.techlab.nerdcafee.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedido")
@CrossOrigin(origins = "*")
public class PedidoController {

private final PedidoService pedidoService;
@Autowired
private ClienteService clienteService;
@Autowired
private ProductoService productoService;


public PedidoController(PedidoService pedidoService) {
	this.pedidoService = pedidoService;
}

@PostMapping
public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
	return ResponseEntity.ok(pedidoService.crearPedido(pedido));
}

@GetMapping("/{id}")
public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
	Pedido pedido = pedidoService.obtenerPorId(id);
	return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
}

@GetMapping
public ResponseEntity<List<Pedido>> listarPedido() {
	return ResponseEntity.ok(pedidoService.listarPedido());
}

@PutMapping("/{id}")
public ResponseEntity<Pedido> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
	Pedido actualizado = pedidoService.actualizarPedido(id, pedido);
	return  ResponseEntity.ok(actualizado);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
	pedidoService.eliminarPedido(id);
	return ResponseEntity.noContent().build();
}


@PostMapping("/cliente/{clienteId}")
public ResponseEntity<?> crearPedidoCliente(
		@PathVariable Long clienteId,
		@RequestBody List<ItemPedido> items) {
	
	Cliente cliente = clienteService.obtenerPorId(clienteId);
	if (cliente == null) {
		return ResponseEntity.badRequest().body("Cliente no encontrado");
	}
	
	Pedido pedido = new Pedido();
	pedido.setCliente(cliente);
	
	double total = 0;
	
	for (ItemPedido item : items) {
		
		// Cargar producto REAL desde la DB
		Producto productoReal = productoService.obtenerPorId(
				item.getProducto().getId()
		);
		
		if (productoReal == null) {
			return ResponseEntity.badRequest()
					.body("Producto no encontrado: " + item.getProducto().getId());
		}
		
		// Validar stock
		if (productoReal.getStock() < item.getCantidad()) {
			return ResponseEntity.badRequest()
					.body("Stock insuficiente para: " + productoReal.getNombre());
		}
		
		// Descontar stock
		productoReal.setStock(productoReal.getStock() - item.getCantidad());
		productoService.actualizarProducto(productoReal.getId(), productoReal);
		
		// Asignar producto REAL y el pedido
		item.setProducto(productoReal);
		item.setPedido(pedido);
		
		total += productoReal.getPrecio() * item.getCantidad();
	}
	
	pedido.setItems(items);
	pedido.setTotal(total);
	
	return ResponseEntity.ok(
			pedidoService.crearPedido(pedido)
	);
}


@GetMapping("/cliente/{clienteId}")
public ResponseEntity<?> obtenerPedidosPorCliente(@PathVariable Long clienteId) {
	return ResponseEntity.ok(pedidoService.obtenerPedidosPorCliente(clienteId));
}
}
