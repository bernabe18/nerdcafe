package clases.productos;

public abstract class  Producto {


private static int contadorProductos = 0;
private int id;
private String nombre;
private double precio;
private int stock;

public Producto(String nombre, double precio, int stock) {
	this.id = ++contadorProductos;
	this.nombre = nombre;
	this.precio = precio;
	this.stock = stock;
}

protected Producto() {
}

public int getId() {
	return id;
}
public String getNombre() {
	return nombre;
}

public double getPrecio() {
	return precio;
}

public int getStock() {
	return stock;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}
public void setPrecio(double precio) {
	if (precio>0){
		this.precio = precio;
	}else {
		System.out.println("el precio tiene que ser mayor a 0");
	}
	
}

public void setStock(int stock) {
	this.stock = stock;
}

public boolean tieneStock(int cantidad) {
	return stock >= cantidad;
}

public void reducirStock(int cantidad){
	if (tieneStock(cantidad)) {
		stock -= cantidad;
	}
}

public abstract double calcularPrecioFinal();

@Override
public String toString() {
	return "ID: " + id + " | " + nombre + " | $" + precio + " | Stock: " + stock;
}


}
