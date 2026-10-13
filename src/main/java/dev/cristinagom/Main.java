package dev.cristinagom;

import dev.cristinagom.model.Producto;
import dev.cristinagom.repository.ProductosRepositoryImpl;
import dev.cristinagom.service.ProductoService;
import dev.cristinagom.service.ProductoServiceImpl;
import dev.cristinagom.storage.ProductosStorageCsv;
import dev.cristinagom.utils.Utils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hola Productos");

        // Creamos el servicio de productos inyectando sus dependencias: repositorio y almacenamiento
        ProductoService service = new ProductoServiceImpl(
                new ProductosStorageCsv(),
                new ProductosRepositoryImpl()
        );

        // Importar los productos desde un CSV
        service.importFromFile("data/products.csv");

        // Obtener todos los productos
        List<Producto> productos = service.getAll();

        // 1. Todos los productos
        System.out.println();
        System.out.println("Todos los productos:");
        productos.forEach(System.out::println);

        // 2. Nombre de los productos
        System.out.println();
        System.out.println("Nombre de los productos:");
        productos.forEach(producto -> System.out.println(producto.getNombre()));

        // 3. Nombre de los productos cuyo stock es menor que 10
        System.out.println("Productos con un stock menor a 10: ");
        productos.stream().filter(producto -> producto.getUnidadesEnStock() < 10)
                .map(Producto::getNombre)
                .forEach(System.out::println); 

        // 4. Obtén el número de proveedores existentes
        long numeroProveedores = productos.stream()
                .map(Producto::getIdProveedor)
                .distinct()
                .count();
        System.out.println("Numero de proveedores totales: " + numeroProveedores);

        // 5. Por cada proveedor, obtener el número de productos
        Map<Integer, Integer> numeroProductosProveedor = productos.stream()
                .collect(Collectors.groupingBy(Producto::getIdProveedor, Collectors.summingInt(producto -> 1)));

        System.out.println("Número de productos por proveedor:");
        numeroProductosProveedor.forEach((proveedor, cantidad) ->
                System.out.println("Proveedor: " + proveedor + " - Número de productos: " + cantidad));

        // 6. Obtener el producto más caro
        String productoMasCaro = productos.stream()
                .max(Comparator.comparing(Producto::getPrecioUnidad))
                .map(Producto::getNombre)
                .orElse("No hay productos");
        System.out.println("Producto mas caro: " + productoMasCaro);

        // 7. Obtener los proveedores que tengan más de 5 productos
        System.out.println();
        System.out.println("Proveedores con más de 5 productos:");
        Map<Integer, List<Producto>> proveedoresConMasProductos = productos.stream()
                .collect(Collectors.groupingBy(Producto::getIdProveedor))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() > 5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        proveedoresConMasProductos.forEach((proveedor, productosProveedor) -> 
                System.out.println("Proveedor: " + proveedor + " - Número de productos: " + productosProveedor.size()));

        // 8. Obtener el proveedor con la mayor cantidad de productos
        System.out.println("Proveedor con mayor cantidad de productos: ");
        Optional<Map.Entry<Integer, Long>> proveedorConMayorCantidad = productos.stream()
                .collect(Collectors.groupingBy(Producto::getIdProveedor, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        System.out.println(
                proveedorConMayorCantidad
                        .map(entry -> "Proveedor: " + entry.getKey() + ", Número de productos: " + entry.getValue())
                        .orElse("No hay productos")
        );

        // 9. Categorías y número de productos por categoría
        Map<Integer, Long> numeroProductosPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(Producto::getIdCategoria, Collectors.counting()));

        System.out.println("Número de productos por categoría:");
        numeroProductosPorCategoria.forEach((categoria, cantidad) ->
                System.out.println("Categoría: " + categoria + " - Número de productos: " + cantidad));

        service.exportToFile("data/products_back.csv");
    }
}