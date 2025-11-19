package clases.pedidos;

import java.util.ArrayList;
import java.util.List;

import clases.clientes.Cliente;
import clases.productos.Producto;


public class Pedido {


private int id;
private static int contadorPedido = 0;
public Cliente cliente;

private List<ItemPedido> items;


public Pedido(Cliente cliente) {
	this.id= ++contadorPedido;
	this.cliente = cliente;
	this.items = new ArrayList<>();
}

public void agregarItem(Producto producto, int cantidad) {
	if (producto.tieneStock(cantidad)) {
		items.add(new ItemPedido(producto, cantidad));
		producto.reducirStock(cantidad);
	} else {
		System.out.println("Stock insuficiente para " + producto.getNombre());
	}
}

public double calcularTotal() {
	double total = 0;
	for (ItemPedido item : items) {
		total += item.getSubtotal();
	}
	return total;
}

@Override
public String toString() {
	StringBuilder sb = new StringBuilder( this.id +" - Pedido de " + cliente + "\n");
	
	for (ItemPedido item : items){
		sb.append(item).append("\n");
	}
	sb.append("Total: $").append(calcularTotal());
	return sb.toString();
}


}
