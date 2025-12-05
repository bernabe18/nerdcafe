package com.techlab.nerdcafee.service.ServiceImpl;
import com.techlab.nerdcafee.model.Producto;
import com.techlab.nerdcafee.repository.ProductoRepository;
import com.techlab.nerdcafee.service.ProductoService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

private final ProductoRepository productoRepository;

public ProductoServiceImpl(ProductoRepository productoRepository) {
	this.productoRepository = productoRepository;
}

@Override
public Producto crearProducto(Producto producto) {
	return productoRepository.save(producto);
}

@Override
public Producto actualizarProducto(Long id, Producto updatedProducto) {
	Producto producto = productoRepository.findById(id).orElse(null);
	if (producto == null) return null;
	
	producto.setNombre(updatedProducto.getNombre());
	producto.setPrecio(updatedProducto.getPrecio());
	producto.setDescripcion(updatedProducto.getDescripcion());
	producto.setStock(updatedProducto.getStock());
	producto.setCategoria(updatedProducto.getCategoria());
	
	return productoRepository.save(producto);
}

@Override
public void eliminarProducto(Long id) {
	productoRepository.deleteById(id);
}

@Override
public Producto obtenerPorId(Long id) {
	return productoRepository.findById(id).orElse(null);
}

@Override
public List<Producto> listarProductos() {
	return productoRepository.findAll();
}
}
