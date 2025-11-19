package clases.pedidos;

import clases.productos.Producto;

public class ItemPedido {


private Producto producto;
private int cantidad;

public ItemPedido(Producto producto, int cantidad) {
	this.producto = producto;
	this.cantidad = cantidad;
}

public double getSubtotal() {
	return producto.calcularPrecioFinal() * cantidad;
}

@Override
public String toString() {
	return producto.getNombre() + " x" + cantidad + " = $" + getSubtotal();
}

}
