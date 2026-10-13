package dev.cristinagom.repository;

import dev.cristinagom.model.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementación del repositorio de productos
 * Esta clase implementa la interfaz ProductosRepository para almacenar y gestionar productos en memoria.
 */
public class ProductosRepositoryImpl implements ProductosRepository {
    private final Logger logger = LoggerFactory.getLogger(ProductosRepositoryImpl.class.getName());

    private final Map<Integer, Producto> productos = new HashMap<>();

    /**
     * Obtiene todos los productos
     * @return Lista de productos
     */
    @Override
    public List<Producto> getAll() {
        logger.debug("Obteniendo todos los productos");
        return productos.values().stream().toList();
    }

    /**
     * Obtiene un producto por su id
     * @param id Identificador del producto
     * @return Producto o null si no se encuentra
     */
    @Override
    public Producto getById(Integer id) {
        logger.debug("Obteniendo producto con id: " + id);
        return productos.get(id);
    }

    /**
     * Guarda un producto
     * @param item Producto a guardar
     * @return Producto guardado
     */
    @Override
    public Producto save(Producto item) {
        logger.debug("Guardando producto: " + item);
        // Generamos un id
        int id = productos.keySet().stream().mapToInt(Integer::intValue).max().orElse(0) + 1;
        Producto productoConId = item.withId(id);
        productos.put(id, productoConId);
        return productoConId;
    }

    /**
     * Actualiza un producto
     * @param id Identificador del producto
     * @param item Producto a actualizar
     * @return Producto actualizado o null si no se encuentra
     */
    @Override
    public Producto update(Integer id, Producto item) {
        logger.debug("Actualizando producto con id: " + id);
        return productos.put(id, item);
    }

    /**
     * Borra un producto
     * @param id Identificador del producto
     * @return Producto borrado o null si no se encuentra
     */
    @Override
    public Producto delete(Integer id) {
        logger.debug("Borrando producto con id: " + id);
        return productos.remove(id);
    }
}
