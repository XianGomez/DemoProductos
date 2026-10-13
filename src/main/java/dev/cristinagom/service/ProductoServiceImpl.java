package dev.cristinagom.service;

import dev.cristinagom.exception.ProductosException;
import dev.cristinagom.model.Producto;
import dev.cristinagom.repository.ProductosRepository;
import dev.cristinagom.repository.ProductosRepositoryImpl;
import dev.cristinagom.storage.ProductosStorage;
import dev.cristinagom.validator.ProductoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * Implementación del servicio de productos
 * Esta clase implementa la interfaz ProductoService para gestionar productos.
 */
public class ProductoServiceImpl implements ProductoService {
    private final Logger logger = LoggerFactory.getLogger(ProductosRepositoryImpl.class.getName());

    private final ProductosStorage storage;
    private final ProductosRepository repository;

    /**
     * Constructor del servicio de productos
     * @param storage Almacenamiento de productos, para leer y escribir en ficheros
     * @param repository Repositorio de productos, para almacenar y gestionar productos en memoria
     */
    public ProductoServiceImpl(ProductosStorage storage, ProductosRepository repository) {
        this.storage = storage;
        this.repository = repository;
    }

    /**
     * Lee los productos de un fichero
     * @param filePath Ruta del fichero
     * @return Lista de productos
     */
    @Override
    public List<Producto> readFromFile(String filePath) {
        logger.info("Leyendo productos de fichero: " + filePath);
        return storage.readFromFile(new File(filePath));
    }

    /**
     * Escribe los productos en un fichero
     * @param filePath Ruta del fichero
     * @param productos Lista de productos
     */
    @Override
    public void writeToFile(String filePath, List<Producto> productos) {
        logger.info("Escribiendo productos en fichero: " + filePath);
        storage.writeToFile(new File(filePath), productos);
    }

    /**
     * Importa los productos de un fichero y los almacena en el repositorio
     * @param filePath Ruta del fichero
     */
    @Override
    public void importFromFile(String filePath) {
        logger.info("Importando productos de fichero: " + filePath);
        List<Producto> productos = readFromFile(filePath);
        productos.forEach(repository::save);
    }

    /**
     * Exporta los productos del repositorio a un fichero
     * @param filePath Ruta del fichero
     */
    @Override
    public void exportToFile(String filePath) {
        logger.info("Exportando productos a fichero: " + filePath);
        writeToFile(filePath, repository.getAll());
    }

    /**
     * Obtiene todos los productos
     * @return Lista de productos
     */
    @Override
    public List<Producto> getAll() {
        logger.info("Obteniendo todos los productos");
        return repository.getAll();
    }

    /**
     * Obtiene un producto por su id
     * @param id Identificador del producto
     * @return Producto con el id especificado
     * @throws RuntimeException si el producto no se encuentra
     */
    @Override
    public Producto getById(int id) {
        logger.info("Obteniendo producto con id: " + id);
        Producto producto = repository.getById(id);
        if (producto == null) {
            throw new RuntimeException(new ProductosException.ProductoNotFoundException(
                "Producto no encontrado con id: " + id));
        }
        return producto;
    }

    /**
     * Guarda un producto
     * @param producto Producto a guardar
     * @return Producto guardado
     * @throws RuntimeException si el producto no es válido
     */
    @Override
    public Producto save(Producto producto) {
        logger.info("Guardando producto: " + producto);
        try {
            ProductoValidator.validate(producto); // Validamos el producto
            return repository.save(producto); // Guardamos el producto
        } catch (ProductosException.ProductoInvalidoException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Actualiza un producto por su id
     * @param id Identificador del producto
     * @param producto Producto a actualizar
     * @return Producto actualizado
     * @throws RuntimeException si el producto no se encuentra o no es válido
     */
    @Override
    public Producto update(int id, Producto producto) {
        //logger.info("Actualizando producto con id: " + id);
        try {
            ProductoValidator.validate(producto); // Validamos el producto
            Producto productoActualizado = repository.update(id, producto); // Actualizamos el producto
            if (productoActualizado == null) {
                throw new RuntimeException(new ProductosException.ProductoNotFoundException(
                    "Producto no encontrado con id: " + id));
            }
            return productoActualizado;
        } catch (ProductosException.ProductoInvalidoException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Borra un producto por su id
     * @param id Identificador del producto
     * @return Producto borrado
     * @throws RuntimeException si el producto no se encuentra
     */
    @Override
    public Producto delete(int id) {
        //logger.info("Borrando producto con id: " + id);
        Producto producto = repository.delete(id);
        if (producto == null) {
            throw new RuntimeException(new ProductosException.ProductoNotFoundException(
                "Producto no encontrado con id: " + id));
        }
        return producto;
    }
}
