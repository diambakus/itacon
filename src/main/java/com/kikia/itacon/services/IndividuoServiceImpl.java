package com.kikia.itacon.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kikia.itacon.domain.Individuo;
import com.kikia.itacon.repository.IndividuoRepository;

@Service
public class IndividuoServiceImpl implements IndividuoService {

	private IndividuoRepository individuoRepository;

	@Autowired
	public void setIndividuoRepository(IndividuoRepository individuoRepository) {
		this.individuoRepository = individuoRepository;
	}

	@Override
	public Iterable<Individuo> listAllIndividuos() {
		return individuoRepository.findAll();
	}

	@Override
	public Individuo getIndividuoById(Long id) {
		return individuoRepository.findOne(id);
	}
	
	
	public Individuo getIndividuoByNIF(Long NIF) {
		return individuoRepository.findOne(NIF);
	}

	@Override
	public Individuo saveIndividuo(Individuo individuo) {
		if (individuo.getId() == null)
			individuo.setBalance(new BigDecimal("0.0"));
		return individuoRepository.save(individuo);
	}

	@Override
	public void deleteIndividuo(Long id) {
		individuoRepository.delete(id);
	}

	@Override
	public Individuo getIndividuoByBI(Long BI) {
		// TODO Auto-generated method stub
		return null;
	}
}
