package dev.cristinagom.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.cristinagom.utils.Utils;

import java.util.Objects;

/**
 * Producto de la tienda
 */
public class Producto {
    @JsonProperty("id")
    private final int id;
    @JsonProperty("nombre")
    private final String nombre;
    @JsonProperty("idProveedor")
    private final int idProveedor;
    @JsonProperty("idCategoria")
    private final int idCategoria;
    @JsonProperty("cantidadPorUnidad")
    private final String cantidadPorUnidad;
    @JsonProperty("precioUnidad")
    private final double precioUnidad;
    @JsonProperty("unidadesEnStock")
    private final int unidadesEnStock;
    @JsonProperty("unidadesEnPedido")
    private final int unidadesEnPedido;
    @JsonProperty("nivelDeReabastecimiento")
    private final int nivelDeReabastecimiento;
    @JsonProperty("descontinuado")
    private final boolean descontinuado;

    /**
     * Constructor del producto
     * @param id Identificador del producto
     * @param nombre Nombre del producto
     * @param idProveedor Identificador del proveedor
     * @param idCategoria Identificador de la categoría
     * @param cantidadPorUnidad Cantidad por unidad
     * @param precioUnidad Precio por unidad
     * @param unidadesEnStock Unidades en stock
     * @param unidadesEnPedido Unidades en pedido
     * @param nivelDeReabastecimiento Nivel de reabastecimiento
     * @param descontinuado Descontinuado o no
     */
    @JsonCreator
    public Producto(
            @JsonProperty("id") int id,
            @JsonProperty("nombre") String nombre,
            @JsonProperty("idProveedor") int idProveedor,
            @JsonProperty("idCategoria") int idCategoria,
            @JsonProperty("cantidadPorUnidad") String cantidadPorUnidad,
            @JsonProperty("precioUnidad") double precioUnidad,
            @JsonProperty("unidadesEnStock") int unidadesEnStock,
            @JsonProperty("unidadesEnPedido") int unidadesEnPedido,
            @JsonProperty("nivelDeReabastecimiento") int nivelDeReabastecimiento,
            @JsonProperty("descontinuado") boolean descontinuado
    ) {
        this.id = id;
        this.nombre = nombre;
        this.idProveedor = idProveedor;
        this.idCategoria = idCategoria;
        this.cantidadPorUnidad = cantidadPorUnidad != null ? cantidadPorUnidad : "N/A";
        this.precioUnidad = precioUnidad;
        this.unidadesEnStock = unidadesEnStock;
        this.unidadesEnPedido = unidadesEnPedido;
        this.nivelDeReabastecimiento = nivelDeReabastecimiento;
        this.descontinuado = descontinuado;
    }

    public Producto (int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.idProveedor = 1;
        this.idCategoria = 1;
        this.cantidadPorUnidad = "N/A";
        this.precioUnidad = 1;
        this.unidadesEnStock = 1;
        this.unidadesEnPedido = 0;
        this.nivelDeReabastecimiento = 0;
        this.descontinuado = false;
    }


    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getCantidadPorUnidad() {
        return cantidadPorUnidad;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public int getUnidadesEnStock() {
        return unidadesEnStock;
    }

    public int getUnidadesEnPedido() {
        return unidadesEnPedido;
    }

    public int getNivelDeReabastecimiento() {
        return nivelDeReabastecimiento;
    }

    public boolean isDescontinuado() {
        return descontinuado;
    }

    /**
     * Crea una copia del producto con un nuevo id
     * @param newId El nuevo id
     * @return Una nueva instancia de Producto con el id actualizado
     */
    public Producto withId(int newId) {
        return new Producto(newId, nombre, idProveedor, idCategoria, cantidadPorUnidad, 
                           precioUnidad, unidadesEnStock, unidadesEnPedido, 
                           nivelDeReabastecimiento, descontinuado);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id &&
               idProveedor == producto.idProveedor &&
               idCategoria == producto.idCategoria &&
               Double.compare(producto.precioUnidad, precioUnidad) == 0 &&
               unidadesEnStock == producto.unidadesEnStock &&
               unidadesEnPedido == producto.unidadesEnPedido &&
               nivelDeReabastecimiento == producto.nivelDeReabastecimiento &&
               descontinuado == producto.descontinuado &&
               Objects.equals(nombre, producto.nombre) &&
               Objects.equals(cantidadPorUnidad, producto.cantidadPorUnidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, idProveedor, idCategoria, cantidadPorUnidad, 
                           precioUnidad, unidadesEnStock, unidadesEnPedido, 
                           nivelDeReabastecimiento, descontinuado);
    }

    @Override
    public String toString() {
        return "Producto(" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", idProveedor=" + idProveedor +
                ", idCategoria=" + idCategoria +
                ", cantidadPorUnidad='" + cantidadPorUnidad + '\'' +
                ", precioUnidad=" + Utils.roundTo(precioUnidad, 2) +
                ", unidadesEnStock=" + unidadesEnStock +
                ", unidadesEnPedido=" + unidadesEnPedido +
                ", nivelDeReabastecimiento=" + nivelDeReabastecimiento +
                ", descontinuado=" + descontinuado +
                ')';
    }
}
