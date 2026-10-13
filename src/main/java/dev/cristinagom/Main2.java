package dev.cristinagom;

import dev.cristinagom.model.Producto;
import dev.cristinagom.repository.ProductosRepositoryImpl;
import dev.cristinagom.service.ProductoService;
import dev.cristinagom.service.ProductoServiceImpl;
import dev.cristinagom.storage.ProductosStorageCsv;
import dev.cristinagom.storage.ProductosStorageJson;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main2 {
    public static void main(String[] args) {
        System.out.println("Hola Productos");

        // Creamos el servicio de productos inyectando sus dependencias: repositorio y almacenamiento
        ProductoService service = new ProductoServiceImpl(
                new ProductosStorageJson(),
                new ProductosRepositoryImpl()
        );

        // Importar los productos desde un JSON
        service.importFromFile("data/products.json");

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


        service.save(new Producto(100, "Leche de cabra"));
        // 3. Nombre de los productos cuyo stock es menor que 10

        // 4. Obtén el número de proveedores existentes

        // 5. Por cada proveedor, obtener el número de productos

        // 6. Obtener el producto más caro

        // 7. Obtener los proveedores que tengan más de 5 productos

        // 8. Obtener el proveedor con la mayor cantidad de productos

        // 9. Categorías y número de productos por categoría


        service.exportToFile("data/products_back.json");
    }
}
