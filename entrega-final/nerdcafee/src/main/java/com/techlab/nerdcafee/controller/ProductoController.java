package com.techlab.nerdcafee.controller;
import com.techlab.nerdcafee.model.Producto;
import com.techlab.nerdcafee.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
private final ProductoService productoService;

public ProductoController(ProductoService productoService) {
	this.productoService = productoService;
}

@PostMapping
public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
	return ResponseEntity.ok(productoService.crearProducto(producto));
}

@GetMapping("/{id}")
public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
	Producto producto = productoService.obtenerPorId(id);
	return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
}

@GetMapping
public ResponseEntity<List<Producto>> listarProductos() {
	return ResponseEntity.ok(productoService.listarProductos());
}

@PutMapping("/{id}")
public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
	Producto actualizado = productoService.actualizarProducto(id, producto);
	return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
	productoService.eliminarProducto(id);
	return ResponseEntity.noContent().build();
}
}
