package com.techlab.nerdcafee.service.ServiceImpl;
import com.techlab.nerdcafee.model.ItemPedido;
import com.techlab.nerdcafee.model.Producto;
import com.techlab.nerdcafee.repository.ItemPedidoRepository;
import com.techlab.nerdcafee.service.ItemPedidoService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

private final ItemPedidoRepository itemPedidoRepository;

public ItemPedidoServiceImpl(ItemPedidoRepository itemPedidoRepository) {
	this.itemPedidoRepository = itemPedidoRepository;
}

@Override
public ItemPedido crearItemPedido(ItemPedido itemPedido) {
	
	return itemPedidoRepository.save(itemPedido);
}

@Override
public void eliminarItemPedido(Long id) {
	itemPedidoRepository.deleteById(id);
}

@Override
public ItemPedido obtenerPorId(Long id) {
	return itemPedidoRepository.findById(id).orElse(null);
}

@Override
public List<ItemPedido> listarItemPedido() {
	return itemPedidoRepository.findAll();
}
}
