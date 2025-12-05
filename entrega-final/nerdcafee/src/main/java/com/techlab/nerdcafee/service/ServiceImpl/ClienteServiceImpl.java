package com.techlab.nerdcafee.service.ServiceImpl;
import com.techlab.nerdcafee.model.Cliente;
import com.techlab.nerdcafee.repository.ClienteRepository;
import com.techlab.nerdcafee.service.ClienteService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

private final ClienteRepository clienteRepository;

public ClienteServiceImpl(ClienteRepository clienteRepository) {
	this.clienteRepository = clienteRepository;
}

@Override
public Cliente crearCliente(Cliente cliente) {
	return clienteRepository.save(cliente);
}

@Override
public Cliente actualizarCliente(Long id, Cliente updatedCliente) {
	Cliente cliente = clienteRepository.findById(id).orElse(null);
	if (cliente == null){
		return null;
	}
	cliente.setNombre(updatedCliente.getNombre());
	cliente.setEmail(updatedCliente.getEmail());
	
	return clienteRepository.save(cliente);
}

@Override
public void eliminarCliente(Long id) {
	clienteRepository.deleteById(id);
}

@Override
public Cliente obtenerPorId(Long id) {
	return clienteRepository.findById(id).orElse(null);
}

@Override
public List<Cliente> listarCliente() {
	return clienteRepository.findAll();
}
}
