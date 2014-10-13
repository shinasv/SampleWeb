package com.aitrich.smsapi.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <pre>
 * Define basic CRUD operations for every entity in the system.
 * 
 * @param <T> the entity class related to this DAO.
 * @param <ID> the type of the field that represents the entity class' primary key.
 * 
 * <h5>&copy;2012 Aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Shaheer
 * @since : 25 April 2013
 * @version : 1.0
 * 
 * 
 */
public interface GenericDAO<T, ID extends Serializable> extends Serializable {
	/**
	 * Save or updates the collection of entities.
	 * 
	 * @param entities
	 *            collection of entities
	 */
	public void saveOrUpdateAll(Collection<T> entities);

	/**
	 * Save or Update an entity.
	 * 
	 * @param entity
	 *            entity
	 */
	public void saveOrUpdate(T entity);

	/**
	 * Update an entity.
	 * 
	 * @param entity
	 *            entity to update
	 */
	public void update(T entity);

	/**
	 * Persist an entity.
	 * 
	 * @param entity
	 *            entity
	 * @return id of persisted entity
	 */
	public ID save(T entity);

	/**
	 * Delete an entity.
	 * 
	 * @param entity
	 *            entity to delete
	 */
	public void delete(T entity);

	/**
	 * Delete an entity.
	 * 
	 * @param entityId
	 *            entity identifier
	 */
	public void delete(ID entityId);

	/**
	 * Delete all entities of type T.
	 * 
	 * @param entityId
	 *            entity identifier
	 */
	public void deleteAll();

	/**
	 * Hard delete the collection of entities.
	 * 
	 * @param entities
	 *            - collection of entities.
	 */
	public void deleteAll(Collection<T> entities);

	/**
	 * Persists the detached object
	 * 
	 * @param entity
	 *            - entity to be persisted
	 * @return - merged entity
	 */
	public T merge(T entity);

	/**
	 * Returns a list of entities for the specified entity class.
	 * 
	 * @return list of all entities in database
	 */
	public List<T> findAll();

	/**
	 * Find an entity by its PK.
	 * 
	 * @param entityId
	 *            entity identifier
	 * @return entity
	 */
	public T findByPrimaryKey(ID entityId);

	/**
	 * Commits all unsaved changes to database
	 */
	public void flush();

	/**
	 * Refreshes persistent entity from database.
	 * 
	 * @param entity
	 *            entity to be refreshed
	 */
	public void refresh(T entity);

	/**
	 * Initializes lazy object wrapped by proxy
	 * 
	 * @param proxy
	 *            proxy
	 */
	public void initProxy(Object proxy);

}
