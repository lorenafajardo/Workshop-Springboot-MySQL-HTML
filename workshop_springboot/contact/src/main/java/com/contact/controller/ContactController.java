package com.contact.controller;

/**
 * Importacion de java util para trabajar con listas y Optional, que es un objeto contenedor que puede contener o no un valor no nulo 
 */
import java.util.List;
import java.util.Optional;

/**
 * Importacion de herramientas de org.springframework para trabajar la aplicacion bajo el modelo MVC, mediante anotaciones.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Importacion de las clases contenidas en los paquetes model y repository, creados para el funcioamiento de la aplicacion bajo MVC 
 */
import com.contact.model.Contact;
import com.contact.repository.ContactRepository;

/**
 * @Description La clase ContactController contiene todas las funciones para ejecutar el CRUD, con las anotaciones.
 * @author LORENA FAJARDO
 * @version 1.0
 */

/**
 * @Controller: Indica que la clase ContactController es un controlador.
 */
@Controller
public class ContactController {

	@Autowired
	private ContactRepository contactRepository;

	/**
	 * @GetMapping Request que permite relacionar la Url para ejecutar el método
	 *             main que mostrara todos los contactos activos y edireccionara
	 *             al usuario a la pagina principal, que contiene todos los
	 *             usuarios creados y las opciones para crear, eliminar y
	 *             editarlos
	 * @param model     :Modelo de contacto
	 * @return index    :HTML de la pagina principal
	 */
	@GetMapping({ "/", "" })
	public String main(Model model) {
		List<Contact> contacts = contactRepository.findAllByDeleted();
		model.addAttribute("contacts", contacts);
		return "index";
	}

	/**
	 * @GetMapping Request Get, solicita el metodo formCreate que redirecciona
	 *             al formulario de creacion de contacto, donde el usuario podra
	 *             escribir la informacion del modelo contact
	 * @param model     :Modelo de contacto
	 * @return create   :HTML que contiene el formulario para crear un contacto
	 */
	@GetMapping("/create")
	public String formCreate(Model model) {
		model.addAttribute("contact", new Contact());
		return "create";
	}

	/**
	 * @PostMapping Request Post, permite ejecutar el metodo createContact y
	 *              enviar la informacion del contacto capturada en el
	 *              formulario, guardarla en la lista de ContactRepository y
	 *              base de datos.
	 * @param model         :Modelo de contacto
	 * @return redirect:/   :Redirecciona a la pagina principal
	 */
	@PostMapping("/create")
	public String createContact(Contact contact, RedirectAttributes redirect) {
		contactRepository.save(contact);
		redirect.addFlashAttribute("msgExito", "El contacto ha sido agregado");
		return "redirect:/";
	}

	/**
	 * @GettMapping Request Get, permite uilizar el metodo showFormEdit para
	 *              traer la inforrmacion del usuario que se quiera editar segun
	 *              el id enviado
	 * @param id      :Id del usuario seleccionado para editar
	 * @param model   :Modelo de contacto
	 * @return create HTML del formulario
	 */
	@GetMapping("/{id}/edit")
	public String showFormEdit(@PathVariable Integer id, Model model) {
		Contact contact = contactRepository.getById(id);
		model.addAttribute("contact", contact);
		return "create";
	}

	/**
	 * @PostMapping Request Post, permite uilizar el metodo updateContact para
	 *              modificar la informacion del usuario y guardarla
	 * @param id      :Id del usuario seleccionado para editar
	 * @param model   : Modelo de contacto
	 * @return create HTML del formulario
	 */
	@PostMapping("/{id}/edit")
	public String updateContact(@PathVariable Integer id, Contact contact,
			RedirectAttributes redirect, Model model) {
		Contact contactSaved = contactRepository.getById(id);

		contactSaved.setName(contact.getName());
		contactSaved.setEmail(contact.getEmail());
		contactSaved.setPhone(contact.getPhone());
		contactSaved.setBirth_date(contact.getBirth_date());

		contactRepository.save(contactSaved);
		redirect.addFlashAttribute("msgExito",
				"El contacto ha sido actualizado correctamente");
		return "redirect:/";
	}

	/**
	 * @GetMapping Borrado Fisico, mediante el metodo contactPhysicalDelete se
	 *             solicita la informacion de los contactos
	 * @param id              :Id del usuario a eliminar
	 * @param model           :Modelo del contacto
	 * @return physicalDelete :HTML de confirmacion de elimiacion
	 */
	@GetMapping("/{id}/physicalDelete")
	public String contactPhysicalDelete(@PathVariable Integer id, Model model) {
		Contact contact = contactRepository.getById(id);
		model.addAttribute("contact", contact);
		return "physicalDelete";
	}

	/**
	 * @PostMapping Borrado Fisico, mediante el metodo PhysicalDelete se enviar la solicitud del contacto a eliminar
	 * @param id            :Id del usuario a eliminar
	 * @param model         :Modelo del contacto
	 * @return redirect:/   :Redirecciona a la pagina principal
	 */
	@PostMapping("/{id}/physicalDelete")
	public String physicalDelete(@PathVariable Integer id,
			RedirectAttributes redirect, Model model) {
		Contact contact = contactRepository.getById(id);
		contactRepository.delete(contact);
		redirect.addFlashAttribute("msgExito",
				"El contacto ha sido eliminado correctamente");
		return "redirect:/";
	}

	/**
	 * @GetMapping Borrado logico, mediante el metodo contactLogicDelete se
	 *             solicita la informacion de los contactos
	 * @param id      :Id del usuario a eliminar
	 * @param model   : Modelo del contacto
	 * @return delete : HTML de confirmacion de elimiacion
	 */
	@GetMapping("/{id}/logicDelete")
	public String contactLogicDelete(@PathVariable Integer id, Model model) {
		Contact contact = contactRepository.getById(id);
		model.addAttribute("contact", contact);
		return "delete";

	}

	/**
	 * @PostMapping Borrado logico, mediante el metodo logicDelete se enviar la
	 *              solicitud del cambio de valor de la vaiable deleted a true
	 * @param id           :Id del usuario a eliminar
	 * @param model        : Modelo del contacto
	 * @return redirect:/  :Redirecciona a la pagina principal
	 */
	@PostMapping("/{id}/logicDelete")
	public String logicDelete(@PathVariable Integer id, Model model) {
		Optional<Contact> opt = this.contactRepository.findById(id);
		Contact contact = opt.get();
		contact.setDeleted(!contact.getDeleted());
		this.contactRepository.save(contact);
		return "redirect:/";

	}

	/**
	 * Metodo findAllByDeleted, almacena los contactos que hann sido eliminados
	 * segun el borrado logico
	 * 
	 * @param id : Id del contacto
	 * @return entities : Lista de contactos eliminados
	 */
	public List<Contact> findAllByDeleted(Integer id) {
		List<Contact> entities = this.contactRepository.findAllByDeleted();
		return entities;
	}
}
