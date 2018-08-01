package jwd.wafepa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Automobil;
import jwd.wafepa.repository.AutomobilRepository;
import jwd.wafepa.service.AutomobilService;

@Service
public class JpaAutomobilService implements AutomobilService{

	@Autowired
	AutomobilRepository automobilRepository;
	
	@Override
	public Automobil save(Automobil automobil) {
		// TODO Auto-generated method stub
		return automobilRepository.save(automobil);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		automobilRepository.delete(id);
	}

	@Override
	public Automobil findOne(Long id) {
		// TODO Auto-generated method stub
		return automobilRepository.findOne(id);
	}

	@Override
	public Page<Automobil> findAll(int page) {
		// TODO Auto-generated method stub
		return automobilRepository.findAll(new PageRequest(page, 5));
	}

	@Override
	public Page<Automobil> findByKompanijaId(int pageNum, Long kompanijaId) {
		// TODO Auto-generated method stub
		return automobilRepository.findByKompanijaId(kompanijaId, new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Automobil> pretraga(String model, Integer godiste, Long dajId,Double potrosnja, int page,Integer velicina) {
		// TODO Auto-generated method stub
		
		if(model!=null) {
			model="%" + model + "%";
		}
		return automobilRepository.pretraga(model, godiste, dajId,potrosnja, new PageRequest(page, velicina));
	}

}
