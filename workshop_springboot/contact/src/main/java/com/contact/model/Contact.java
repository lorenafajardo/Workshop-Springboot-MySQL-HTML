package com.contact.model;

/**
 * Impotacion de libreria time, la cual permite utilizar el tipo de dato LocalDate
 */
import java.time.LocalDate;

/**
 * Impotacion del paquete javax, para utilizar el tipo de dato LocalDate
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 *Importacion de herramientas de org.springframework para realizar anotaciones.
 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * La clase Contact contiene la definicion de variables a utilizar en la aplicacion y sus respectivos metodos
 * getters and setters para acceder a ellas
 * 
 * @author LORENA FAJARDO
 * @version 1.0
 */
@Entity
public class Contact {

	/**
	 * Declaracion de variables
	 * @id         : Numero de identificacion de cada contacto
	 * @name       : Nombre del contacto
	 * @email      : Email del contacto
	 * @phone      : Telefono
	 * @birth_date : Fecha de nacimiento del contacto
	 * @deleted    : Variable definida para el borrado logico. Por defecto su valor es falso y camiara a 
	 *               true si el usuario decide eliminarlo 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Campo obligatorio")
	private String name;

	@NotBlank(message = "Campo obligatorio")
	private String email;

	@NotBlank(message = "Campo obligatorio")
	private String phone;

	@DateTimeFormat(iso = ISO.DATE)
	@Past
	private LocalDate birth_date;
	
	private Boolean deleted = false;
	
	
	/**
	 * Declaracion de metodos get annd set, para cada variable definida anteriormente
	 */
	
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(LocalDate birth_date) {
		this.birth_date = birth_date;
	}

	public Contact() {
		super();
	}

}
