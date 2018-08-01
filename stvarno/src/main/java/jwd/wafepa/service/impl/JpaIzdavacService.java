package jwd.wafepa.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Izdavac;
import jwd.wafepa.repository.IzdavacRepository;
import jwd.wafepa.service.IzdavacService;

@Service
public class JpaIzdavacService  implements IzdavacService{

	@Autowired
	IzdavacRepository izdavacRepository;
	
	@Override
	public Izdavac save(Izdavac izdavac) {
		// TODO Auto-generated method stub
		return izdavacRepository.save(izdavac);
	}

	@Override
	public List<Izdavac> findAll() {
		// TODO Auto-generated method stub
		return izdavacRepository.findAll();
	}

	@Override
	public Izdavac findOne(Long id) {
		// TODO Auto-generated method stub
		return izdavacRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		izdavacRepository.delete(id);
	}

}
