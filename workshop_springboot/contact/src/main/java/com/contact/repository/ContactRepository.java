package com.contact.repository;

/**
 * Importacion de librerias y paquetes
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.contact.model.Contact;

/**
 * la Interface ContactRepository, se extiende JpaRepository que es un repositorio que contiene operaciones 
 * CRUD básicas y también API para paginación y clasificación
 * 
 * @author LORENA FAJARDO
 * @version 1.0
 */
public interface ContactRepository extends JpaRepository <Contact, Integer>{
	
	/**
	 * @Query Consulta de SQL en la cual se solicitara todos los contactos que tengan la variable
	 *        deleted igual a false, es decir que no se han eliminado. Estos contactos se almacenan 
	 *        en una lista
	 */
	@Query(value = "SELECT * FROM contact  WHERE contact.deleted = false", nativeQuery = true)
    List<Contact> findAllByDeleted();

}
