package com.kikia.itacon.services;

import com.kikia.itacon.domain.Individuo;

public interface IndividuoService {

	Iterable<Individuo> listAllIndividuos();

	Individuo getIndividuoById(Long Id);
	
	Individuo getIndividuoByNIF(Long NIF);
	
	Individuo getIndividuoByBI(Long BI);

	Individuo saveIndividuo(Individuo individuo);

	void deleteIndividuo(Long Id);
}
