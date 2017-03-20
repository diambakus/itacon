package com.kikia.itacon.services;

import com.kikia.itacon.entities.PublicInstitution;
import com.kikia.itacon.entities.Service;

public interface IServiceService {
	
	Iterable<Service> listAllServices();
	Iterable<Service> listAllServicesByPublicInstitution(PublicInstitution publicInstitution);
	Service getServicebyId(Long Id);
	Service saveService(Service service);
	void deleteService(Long Id);

}
