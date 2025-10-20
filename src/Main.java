import clases.clientes.Cliente;
import clases.pedidos.Pedido;
import clases.productos.Cafe;
import clases.productos.Producto;
import clases.productos.Te;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
public static final List<Producto> productos = new ArrayList<>();
public static final List<Cliente> clientes = new ArrayList<>();
private static final List<Pedido> pedidos = new ArrayList<>();
public static final Scanner scanner = new Scanner(System.in);


public static void main(String[] args) {
	int opcion;
	do {
		mostrarMenu();
		opcion = scanner.nextInt();
		scanner.nextLine();
		
		switch (opcion) {
			case 1 -> registrarCliente();
			case 2 -> agregarProducto();
			case 3 -> listarProductos();
			case 4 -> buscarActualizarProducto();
			case 5 -> eliminarProducto();
			case 6 -> crearPedido();
			case 7 -> listarPedidos();
			case 8 -> System.out.println(" Saliendo del sistema...");
			default -> System.out.println(" Opción inválida.");
		}
	} while (opcion != 8);
}

public static void mostrarMenu() {
	System.out.println("\n===== SISTEMA DE GESTIÓN - TECHLAB =====");
	System.out.println("1) Registrar cliente");
	System.out.println("2) Agregar producto");
	System.out.println("3) Listar productos");
	System.out.println("4) Buscar/Actualizar producto");
	System.out.println("5) Eliminar producto");
	System.out.println("6) Crear un pedido");
	System.out.println("7) Listar pedidos");
	System.out.println("8) Salir");
	System.out.print("Elija una opción: ");
}

public static void registrarCliente() {
	System.out.print("Nombre del cliente: ");
	String nombre = scanner.nextLine();
	System.out.print("Email del cliente: ");
	String email = scanner.nextLine();
	clientes.add(new Cliente(nombre, email));
	System.out.println(" Cliente registrado.");
}

public static void agregarProducto() {
	String respuesta="";
	do{
		System.out.print("Tipo de producto (1-Café, 2-Té): ");
		int tipo = scanner.nextInt();
		if(tipo>2 ){
			System.out.println("opcion incorrecta");
		}else {
			scanner.nextLine();
			System.out.print("Nombre: ");
			String nombre = scanner.nextLine();
			System.out.print("Precio: ");
			double precio = scanner.nextDouble();
			System.out.print("Stock: ");
			int stock = scanner.nextInt();
			Producto producto = (tipo == 1) ? new Cafe(nombre, precio, stock) : new Te(nombre, precio, stock);
			System.out.println(" Producto agregado: " + producto);
			productos.add(producto);
		}
		System.out.println("Desea seguir cargando Productos s/n");
		respuesta =scanner.next();
	}while(respuesta.equals("s"));
	System.out.println("Muchas Gracias");
}

public static void listarProductos() {
	if (productos.isEmpty()) {
		System.out.println(" No hay productos registrados.");
	}
	else{
		productos.forEach(System.out::println);
	}
}


public static void buscarActualizarProducto() {
	System.out.print("Ingrese ID del producto: ");
	int id = scanner.nextInt();
	scanner.nextLine();
	
	Producto encontrado = null;
	
	for (Producto p : productos) {
		if (p.getId() == id) {
			encontrado = p;
			System.out.println("Producto encontrado: " + encontrado);
		}
	}
	System.out.println("Desea Editar El Producto s/n");
	String respuesta=scanner.nextLine();
	if (respuesta.equals("s")) {
		
		System.out.print("Nuevo nombre (ENTER para no cambiar): ");
		String nombre = scanner.nextLine();
		
		if (!nombre.isBlank()) {
			encontrado.setNombre(nombre);
		}
		
		System.out.print("Nuevo precio (0 para no cambiar): ");
		double precio = scanner.nextDouble();
		if (precio > 0){
			encontrado.setPrecio(precio);
		}
		
		System.out.print("Nuevo stock (-1 para no cambiar): ");
		int stock = scanner.nextInt();
		if (stock >= 0) {
			encontrado.setStock(stock);
		}
		
		System.out.println(" Producto actualizado: " + encontrado);
		
		
	} else if (respuesta.equals("n")) {
		System.out.println("muchas gracias");
	} else {
		System.out.println(" No se encontró producto con ID " + id);
	}
}

public static void eliminarProducto() {
	listarProductos();
	System.out.print("Ingrese ID del producto a eliminar: ");
	int id = scanner.nextInt();
	System.out.println("Esta seguro que quiere eliminar el producto (s / n) ");
	String respuesta= scanner.next();
	if (respuesta.equals("s")){
		productos.removeIf(p -> p.getId() == id);
		System.out.println(" Producto eliminado.");
	}else if(respuesta.equals("n")) {
		System.out.println("El producto no fue elimanado");
	}
	
}

public static void crearPedido() {
	if (clientes.isEmpty()) {
		System.out.println(" No hay clientes registrados.");
		return;
	}
	
	System.out.println("Seleccione el cliente:");
	
	for (int i = 0; i < clientes.size(); i++){
		System.out.println((i+1) + ") " + clientes.get(i));
	}
	
	int opcionCliente = scanner.nextInt(); scanner.nextLine();
	Cliente cliente = clientes.get(opcionCliente-1);
	
	Pedido pedido = new Pedido(cliente);
	String continuar;
	
	do {
		listarProductos();
		System.out.print("ID producto: ");
		int id = scanner.nextInt();
		System.out.print("Cantidad: ");
		int cantidad = scanner.nextInt(); scanner.nextLine();
		
		Producto producto = null;
		for (Producto p : productos){
			if (p.getId() == id) {
				producto = p;
			}
		}
		if (producto != null) {
			pedido.agregarItem(producto, cantidad);
		}
		else{
			System.out.println(" Producto no encontrado.");
		}
		
		System.out.print("Agregar otro producto? (s/n): ");
		continuar = scanner.nextLine();
	} while (continuar.equalsIgnoreCase("s"));
	
	pedidos.add(pedido);
	System.out.println(" Pedido creado:\n" + pedido);
}


public static void listarPedidos() {
	if (pedidos.isEmpty()){
		System.out.println(" No hay pedidos.");
	}
	else{
		
		for (Pedido pedido: pedidos){
			System.out.println("******************");
			System.out.println(pedido);
			System.out.println("******************");
		}
		
	}
}

}