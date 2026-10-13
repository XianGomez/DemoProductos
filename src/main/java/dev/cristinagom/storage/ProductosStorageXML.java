package dev.cristinagom.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dev.cristinagom.exception.ProductosException;
import dev.cristinagom.model.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Almacenamiento de productos en JSON
 * Esta clase implementa la interfaz ProductosStorage para almacenar y leer productos en un fichero JSON.
 */
public class ProductosStorageXML implements ProductosStorage {

    private final Logger logger = LoggerFactory.getLogger(ProductosStorageXML.class.getName());
    /**
     * Lee los productos de un fichero CSV
     * @param file Fichero CSV
     * @return Lista de productos
     * @throws ProductosException.ProductosStorageException
     */
    @Override
    public List<Producto> readFromFile(File file) {
        logger.debug("Leyendo productos de fichero XML: " + file);
        
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            logger.error("El fichero no existe, o no es un fichero o no se puede leer: " + file);
            throw new RuntimeException(new ProductosException.ProductosStorageException(
                "El fichero no existe, o no es un fichero o no se puede leer: " + file));
        }

        try {
            XmlMapper xmlMapper = new XmlMapper();

            List<Producto> listaProductos = xmlMapper.readValue(
                    file,
                    new TypeReference<List<Producto>>() {}
            );
//            listaProductos.forEach(System.out::println);
//            Producto firstProduct = listaProductos.get(0);
//            System.out.println(firstProduct);
            return listaProductos;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    /**
     * Escribe los productos en un fichero XML
     * @param file Fichero XML
     * @param productos Lista de productos
     * @throws ProductosException.ProductosStorageException
     */
    @Override
    public void writeToFile(File file, List<Producto> productos) {
        logger.debug("Escribiendo productos en fichero XML: " + file);

        if (!file.getParentFile().exists() || !file.getParentFile().isDirectory()) {
            logger.error("El directorio padre del fichero no existe: " + file.getParentFile().getAbsolutePath());
            throw new RuntimeException(new ProductosException.ProductosStorageException(
                    "El directorio padre del fichero no existe: " + file.getParentFile().getAbsolutePath()));
        }

        try {
            XmlMapper xmlMapper = new XmlMapper();

            // Creamos un Map donde la clave será la etiqueta de los hijos
            Map<String, List<Producto>> map = new HashMap<>();
            map.put("producto", productos);

            // Escribimos XML con nodo raíz <productos>
            xmlMapper.writer().withRootName("productos").writeValue(file, map);

        } catch (IOException e) {
            logger.error("Error escribiendo el fichero: " + file);
            //      throw new RuntimeException(new ProductosException.ProductosStorageException(
            //          "Error escribiendo el fichero: " + file), e);
        }
    }


}
