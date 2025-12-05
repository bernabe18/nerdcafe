package com.techlab.nerdcafee.service;
import com.techlab.nerdcafee.model.Cliente;
import java.util.List;


public interface ClienteService {

Cliente crearCliente(Cliente cliente);

Cliente actualizarCliente(Long id, Cliente cliente);

void eliminarCliente(Long id);

Cliente obtenerPorId(Long id);

List<Cliente> listarCliente();

}
