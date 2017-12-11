package com.kikia.itacon.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.kikia.itacon.domain.Institution;
import com.kikia.itacon.services.InstitutionService;

@Component
public class StringToInstitutionConverter implements Converter<String, Institution>{

	
	private InstitutionService institutionService;
	
	@Autowired
	public StringToInstitutionConverter(InstitutionService institutionService) {
		this.institutionService = institutionService;
	}
	/**
	 * It reads selected option id and
	 * returns DTO object
	 * @param source it's object id
	 * @return DTO object
	 */
	@Override
	public Institution convert(String source) {
		Long id = new Long(source);
		return institutionService.getInstitution(id);
	}


}
