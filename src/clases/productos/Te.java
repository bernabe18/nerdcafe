package clases.productos;

public class Te extends Producto{

public Te(String nombre, double precio, int stock) {
	super(nombre, precio, stock);
}

@Override
public double calcularPrecioFinal() {
	return getPrecio();
}

@Override
public String toString() {
	return "[Té] " + super.toString();
}


}
