package dev.cristinagom.exception;

/**
 * Excepciones de productos
 */
public abstract class ProductosException extends Exception {
    
    public ProductosException(String message) {
        super(message);
    }

    /**
     * Excepción de almacenamiento de productos
     */
    public static class ProductosStorageException extends ProductosException {
        public ProductosStorageException(String message) {
            super(message);
        }
    }

    /**
     * Excepción de producto no encontrado
     */
    public static class ProductoNotFoundException extends ProductosException {
        public ProductoNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Excepción de producto inválido
     */
    public static class ProductoInvalidoException extends ProductosException {
        public ProductoInvalidoException(String message) {
            super(message);
        }
    }
}
