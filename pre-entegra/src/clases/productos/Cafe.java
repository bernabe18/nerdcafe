package clases.productos;

public class Cafe extends Producto{


public Cafe(String nombre, double precio, int stock) {
	super(nombre, precio, stock);
}

@Override
public double calcularPrecioFinal() {
	return getPrecio();
}

@Override
public String toString() {
	return "[Café] " + super.toString(); }

}
