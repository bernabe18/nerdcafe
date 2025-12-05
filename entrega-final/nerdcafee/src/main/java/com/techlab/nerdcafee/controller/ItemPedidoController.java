package com.techlab.nerdcafee.controller;
import com.techlab.nerdcafee.model.ItemPedido;
import com.techlab.nerdcafee.service.ItemPedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/itempedido")
//para cuando conecte el front end
@CrossOrigin(origins = "*")
public class ItemPedidoController {


private final ItemPedidoService itemPedidoService;

public ItemPedidoController(ItemPedidoService itemPedidoService) {
	this.itemPedidoService = itemPedidoService;
}

@PostMapping
public ResponseEntity<ItemPedido> crearItemPedido(@RequestBody ItemPedido item) {
	return ResponseEntity.ok(itemPedidoService.crearItemPedido(item));
}

@GetMapping("/{id}")
public ResponseEntity<ItemPedido> obtenerPorId(@PathVariable Long id) {
	ItemPedido item = itemPedidoService.obtenerPorId(id);
	return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
}

@GetMapping()
public ResponseEntity<List<ItemPedido>> listarItemPedido() {
	return ResponseEntity.ok(itemPedidoService.listarItemPedido());
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminarItemPedido(@PathVariable Long id) {
	itemPedidoService.eliminarItemPedido(id);
	return ResponseEntity.noContent().build();
}
}
