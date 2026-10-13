package dev.cristinagom.utils;

/**
 * Utilidades para el proyecto
 */
public class Utils {
    
    /**
     * Redondea un número decimal a un número específico de decimales
     * @param value El valor a redondear
     * @param decimals El número de decimales
     * @return El valor redondeado
     */
    public static double roundTo(double value, int decimals) {
        double factor = Math.pow(10.0, decimals);
        return Math.round(value * factor) / factor;
    }
}
