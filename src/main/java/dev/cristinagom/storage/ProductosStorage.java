package dev.cristinagom.storage;

import dev.cristinagom.model.Producto;

import java.io.File;
import java.util.List;

/**
 * Interfaz para el almacenamiento de productos
 */
public interface ProductosStorage {
    
    /**
     * Lee productos desde un archivo
     * @param file El archivo a leer
     * @return Lista de productos
     */
    List<Producto> readFromFile(File file);
    
    /**
     * Escribe productos a un archivo
     * @param file El archivo donde escribir
     * @param productos Lista de productos a escribir
     */
    void writeToFile(File file, List<Producto> productos);
}
