package dev.cristinagom.validator;

import dev.cristinagom.exception.ProductosException;
import dev.cristinagom.model.Producto;

/**
 * Validador de productos
 */
public class ProductoValidator {

    /**
     * Valida un producto
     * @param producto El producto a validar
     * @throws ProductosException.ProductoInvalidoException si el producto no es válido
     */
    public static void validate(Producto producto) throws ProductosException.ProductoInvalidoException {
        // El nombre no esté en blanco
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new ProductosException.ProductoInvalidoException("El nombre no puede estar en blanco");
        }
        
        // La cantidad por unidad no esté en blanco
        if (producto.getCantidadPorUnidad() == null || producto.getCantidadPorUnidad().trim().isEmpty()) {
            throw new ProductosException.ProductoInvalidoException("La cantidad por unidad no puede estar en blanco");
        }
        
        // El precio unitario sea mayor a cero
        if (producto.getPrecioUnidad() <= 0) {
            throw new ProductosException.ProductoInvalidoException("El precio unitario no puede ser negativo");
        }
    }
}
