package dev.cristinagom.repository;

import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD
 * @param <T> Tipo de entidad
 * @param <ID> Tipo del identificador
 */
public interface CrudRepository<T, ID> {
    
    /**
     * Obtiene todas las entidades
     * @return Lista de entidades
     */
    List<T> getAll();
    
    /**
     * Obtiene una entidad por su id
     * @param id Identificador de la entidad
     * @return La entidad o null si no se encuentra
     */
    T getById(ID id);
    
    /**
     * Guarda una entidad
     * @param item La entidad a guardar
     * @return La entidad guardada
     */
    T save(T item);
    
    /**
     * Actualiza una entidad
     * @param id Identificador de la entidad
     * @param item La entidad con los datos actualizados
     * @return La entidad actualizada o null si no se encuentra
     */
    T update(ID id, T item);
    
    /**
     * Elimina una entidad
     * @param id Identificador de la entidad
     * @return La entidad eliminada o null si no se encuentra
     */
    T delete(ID id);
}
