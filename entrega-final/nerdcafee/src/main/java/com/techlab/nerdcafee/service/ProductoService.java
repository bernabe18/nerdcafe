package com.techlab.nerdcafee.service;
import com.techlab.nerdcafee.model.Producto;
import org.springframework.stereotype.Service;
import java.util.List;



public interface ProductoService {


Producto crearProducto(Producto producto);

Producto actualizarProducto(Long id, Producto producto);

void eliminarProducto(Long id);

Producto obtenerPorId(Long id);

List<Producto> listarProductos();
}
