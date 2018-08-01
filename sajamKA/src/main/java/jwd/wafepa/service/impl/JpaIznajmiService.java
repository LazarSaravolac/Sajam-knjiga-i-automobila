package jwd.wafepa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Automobil;
import jwd.wafepa.model.Iznajmi;
import jwd.wafepa.repository.AutomobilRepository;
import jwd.wafepa.repository.IznajmiRepository;

import jwd.wafepa.service.IznajmiService;

@Service
public class JpaIznajmiService implements IznajmiService{
	
	@Autowired
	IznajmiRepository iznajmiRepository;
	
	@Autowired
	AutomobilRepository automobilRepository;
	
	
	
	@Override
	public Iznajmi iznajmiAuto(Long autoId) {
		// TODO Auto-generated method stub
		
		if(autoId==null) {
			throw new IllegalArgumentException("id required");			
		}
		
		Automobil auto=new Automobil();
		auto=automobilRepository.findOne(autoId);
		if(auto==null) {
			throw new IllegalArgumentException("no car with that id!");	
		}
		
		Iznajmi iznajmi=new Iznajmi();
		
		
		iznajmi.setAutomobil(auto);
		
		iznajmiRepository.save(iznajmi);
		auto.setIznajmljen(true);
		
		automobilRepository.save(auto);
		
		
		return iznajmi;
	}

}
