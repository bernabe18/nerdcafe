package com.techlab.nerdcafee.controller;
import com.techlab.nerdcafee.model.Cliente;
import com.techlab.nerdcafee.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

private final ClienteService clienteService;

public ClienteController(ClienteService clienteService) {
	this.clienteService = clienteService;
}

@PostMapping
public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
	return ResponseEntity.ok(clienteService.crearCliente(cliente));
}

@GetMapping("/{id}")
public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id) {
	Cliente cliente = clienteService.obtenerPorId(id);
	return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
}

@GetMapping
public ResponseEntity<List<Cliente>> listarCliente() {
	return ResponseEntity.ok(clienteService.listarCliente());
}

@PutMapping("/{id}")
public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
	Cliente actualizado = clienteService.actualizarCliente(id, cliente);
	return  ResponseEntity.ok(actualizado);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
	clienteService.eliminarCliente(id);
	return ResponseEntity.noContent().build();
}
}
