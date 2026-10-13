package dev.cristinagom.service;

import dev.cristinagom.model.Producto;

import java.util.List;

/**
 * Interfaz del servicio de productos
 */
public interface ProductoService {
    
    /**
     * Lee productos desde un archivo
     * @param filePath Ruta del archivo
     * @return Lista de productos
     */
    List<Producto> readFromFile(String filePath);
    
    /**
     * Escribe productos a un archivo
     * @param filePath Ruta del archivo
     * @param productos Lista de productos
     */
    void writeToFile(String filePath, List<Producto> productos);
    
    /**
     * Importa productos desde un archivo y los almacena en el repositorio
     * @param filePath Ruta del archivo
     */
    void importFromFile(String filePath);
    
    /**
     * Exporta productos del repositorio a un archivo
     * @param filePath Ruta del archivo
     */
    void exportToFile(String filePath);
    
    /**
     * Obtiene todos los productos
     * @return Lista de productos
     */
    List<Producto> getAll();
    
    /**
     * Obtiene un producto por su id
     * @param id Identificador del producto
     * @return Producto
     */
    Producto getById(int id);
    
    /**
     * Guarda un producto
     * @param producto Producto a guardar
     * @return Producto guardado
     */
    Producto save(Producto producto);
    
    /**
     * Actualiza un producto
     * @param id Identificador del producto
     * @param producto Producto con los datos actualizados
     * @return Producto actualizado
     */
    Producto update(int id, Producto producto);
    
    /**
     * Elimina un producto
     * @param id Identificador del producto
     * @return Producto eliminado
     */
    Producto delete(int id);
}
