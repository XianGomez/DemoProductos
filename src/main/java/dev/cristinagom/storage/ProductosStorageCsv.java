package dev.cristinagom.storage;

import dev.cristinagom.exception.ProductosException;
import dev.cristinagom.model.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

/**
 * Almacenamiento de productos en CSV
 * Esta clase implementa la interfaz ProductosStorage para almacenar y leer productos en un fichero CSV.
 */
public class ProductosStorageCsv implements ProductosStorage {

    private final Logger logger = LoggerFactory.getLogger(ProductosStorageCsv.class.getName());
    /**
     * Lee los productos de un fichero CSV
     * @param file Fichero CSV
     * @return Lista de productos
     * @throws ProductosException.ProductosStorageException
     */
    @Override
    public List<Producto> readFromFile(File file) {
        logger.debug("Leyendo productos de fichero CSV: " + file);
        
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            logger.error("El fichero no existe, o no es un fichero o no se puede leer: " + file);
            throw new RuntimeException(new ProductosException.ProductosStorageException(
                "El fichero no existe, o no es un fichero o no se puede leer: " + file));
        }

        try {
            return Files.lines(file.toPath())
                    .skip(1) // Saltamos la cabecera
                    .map(line -> line.split(","))
                    .map(this::trimArray)
                    .map(this::createProducto)
                    .toList();
        } catch (IOException e) {
            logger.error("Error leyendo el fichero: " + file);
          //  throw new RuntimeException(new ProductosException.ProductosStorageException(
          //      "Error leyendo el fichero: " + file), e);
        }
        return List.of();
    }

    /**
     * Escribe los productos en un fichero CSV
     * @param file Fichero CSV
     * @param productos Lista de productos
     * @throws ProductosException.ProductosStorageException
     */
    @Override
    public void writeToFile(File file, List<Producto> productos) {
        logger.debug("Escribiendo productos en fichero CSV: " + file);
        
        if (!file.getParentFile().exists() || !file.getParentFile().isDirectory()) {
            logger.error("El directorio padre del fichero no existe: " + file.getParentFile().getAbsolutePath());
            throw new RuntimeException(new ProductosException.ProductosStorageException(
                "El directorio padre del fichero no existe: " + file.getParentFile().getAbsolutePath()));
        }

        try {
            StringBuilder content = new StringBuilder();
            content.append("productID,productName,supplierID,categoryID,quantityPerUnit,unitPrice,unitsInStock,unitsOnOrder,reorderLevel,discontinued\n");
            
            for (Producto producto : productos) {
                content.append(String.format("%d,%s,%d,%d,%s,%.2f,%d,%d,%d,%b\n",
                    producto.getId(),
                    producto.getNombre(),
                    producto.getIdProveedor(),
                    producto.getIdCategoria(),
                    producto.getCantidadPorUnidad(),
                    producto.getPrecioUnidad(),
                    producto.getUnidadesEnStock(),
                    producto.getUnidadesEnPedido(),
                    producto.getNivelDeReabastecimiento(),
                    producto.isDescontinuado()));
            }
            
            Files.writeString(file.toPath(), content.toString());
        } catch (IOException e) {
            logger.error("Error escribiendo el fichero: " + file);
      //      throw new RuntimeException(new ProductosException.ProductosStorageException(
      //          "Error escribiendo el fichero: " + file), e);
        }
    }

    /**
     * Elimina espacios en blanco de los elementos del array
     * @param array Array de strings
     * @return Array con elementos sin espacios en blanco
     */
    private String[] trimArray(String[] array) {
        return Stream.of(array).map(String::trim).toArray(String[]::new);
    }

    /**
     * Crea un producto a partir de un array de strings
     * @param data Array con los datos del producto
     * @return Producto creado
     */
    private Producto createProducto(String[] data) {
        return new Producto(
            Integer.parseInt(data[0]),
            data[1],
            Integer.parseInt(data[2]),
            Integer.parseInt(data[3]),
            data[4],
            Double.parseDouble(data[5]),
            Integer.parseInt(data[6]),
            Integer.parseInt(data[7]),
            Integer.parseInt(data[8]),
            Boolean.parseBoolean(data[9])
        );
    }
}
