package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Kompanija;

public interface KompanijaService {
	Kompanija save(Kompanija kompanija);
	void delete(Long id);
	Kompanija findOne(Long id);
	List<Kompanija>findAll();
	
}
