package com.kikia.itacon.controllers.institution;

import java.security.Principal;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kikia.itacon.domain.Institution;
import com.kikia.itacon.domain.User;
import com.kikia.itacon.dto.InstitutionDTO;
import com.kikia.itacon.dto.UserDTO;
import com.kikia.itacon.services.InstitutionService;
import com.kikia.itacon.services.UserService;

@Controller
@RequestMapping("/institution")
public class InstitutionController {

	private InstitutionService institutionService;
	private UserService userService;
	private ModelMapper modelMapper;
	private final String institutionsListViewValue = "institutions/institutions";

	@Autowired
	public void InstituteController(InstitutionService institutionService, UserService userService,
			ModelMapper modelMapper) {
		this.institutionService = institutionService;
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@InitBinder("form")
	public void initBinder(WebDataBinder binder) {

	}

	/**
	 * That method provide initial resources to the view. It lists all
	 * institutions available. And, it set up resource for POST (add) method.
	 * 
	 * @param model
	 *            -
	 * @param principal
	 *            - it provides resources of logged user
	 * @return - returns a view html that must be presented
	 */
	@GetMapping(value = "/institutions")
	public String institutions(Model model, final Principal principal) {
		User user = userService.findByUsername(principal.getName());
		String viewValue = null;
		UserDTO userDTO = convertToDTO(user);

		if (userDTO != null) {
			model.addAttribute("userDTO", userDTO);

			model.addAttribute("institutionDTO", new InstitutionDTO());
			Iterable<Institution> institutions = institutionService.listAllInstitutions();
			Stream<Institution> institutionsStream = StreamSupport.stream(institutions.spliterator(), false);

			model.addAttribute("institutionsDTO",
					institutionsStream.map(institution -> convertToDTO(institution)).collect(Collectors.toList()));
			viewValue = institutionsListViewValue;
		} else {
			viewValue = "unexpected_error";
		}
		return viewValue;
	}

	/**
	 * 
	 * @param model
	 * @param principal
	 * @return
	 */
	@GetMapping(value = "/addInstitution")
	public String addInstitution(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		String viewValue = null;
		UserDTO userDTO = convertToDTO(user);

		if (userDTO != null) {
			model.addAttribute("userDTO", userDTO);
			model.addAttribute("institutionDTO", new InstitutionDTO());
			viewValue = institutionsListViewValue;
		} else {
			viewValue = "unexpected_error";
		}
		return viewValue;

	}

	/**
	 * 
	 * @param model
	 * @param institutionDTO
	 * @param bindingResult
	 * @param principal
	 * @return
	 */
	@PostMapping(value = "/addInstitution")
	public String addInstitution(Model model, @ModelAttribute("institutionDTO") InstitutionDTO institutionDTO,
			BindingResult bindingResult, final Principal principal) {

		User user = userService.findByUsername(principal.getName());
		UserDTO userDTO = convertToDTO(user);
		if (userDTO != null) {
			institutionService.saveInstitution(convertToEntity(institutionDTO));
			model.addAttribute("userDTO", userDTO);
		}

		return institutionsListViewValue;
	}

	/* Convert Entity to Data Transfer Object (DTO) and vice-versa */
	/**
	 * Converts Entity - domain model object to the DTO object
	 * 
	 * @param institution
	 *            - receives domain model as parameter
	 * @return DTO object
	 */
	private InstitutionDTO convertToDTO(Institution institution) {
		InstitutionDTO institutionDTO = modelMapper.map(institution, InstitutionDTO.class);
		institutionDTO.setNumberOfInstitutions(institution.getServices().size());
		return institutionDTO;
	}

	/**
	 * As we persist through entity object. We need to convert DTO object
	 * to entity object. This method does that.
	 * @param institutionDTO - DTO object to be converted
	 * @return  It returns entity object
	 */
	private Institution convertToEntity(InstitutionDTO institutionDTO) {
		Institution institution = modelMapper.map(institutionDTO, Institution.class);
		return institution;
	}
	
	/* Convert Entity to Data Transfer Object (DTO) and vice-versa */
	/**
	 * Converts Entity - domain model object to the DTO object
	 * 
	 * @param user
	 *            - receives domain model as parameter
	 * @return DTO object
	 */
	private UserDTO convertToDTO(User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}
}
