package jwd.wafepa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Kompanija;
import jwd.wafepa.repository.KompanijaRepository;
import jwd.wafepa.service.KompanijaService;

@Service
public class JpaKompanijaService implements KompanijaService{

	@Autowired
	KompanijaRepository kompanijaRepository;
	
	@Override
	public Kompanija save(Kompanija kompanija) {
		// TODO Auto-generated method stub
		return kompanijaRepository.save(kompanija);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		kompanijaRepository.delete(id);
	}

	@Override
	public Kompanija findOne(Long id) {
		// TODO Auto-generated method stub
		return kompanijaRepository.findOne(id);
	}

	@Override
	public List<Kompanija> findAll() {
		// TODO Auto-generated method stub
		return kompanijaRepository.findAll();
	}

}
