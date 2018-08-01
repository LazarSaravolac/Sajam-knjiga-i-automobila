package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Izdavac;

public interface IzdavacService {

	Izdavac save(Izdavac izdavac);
	List<Izdavac>findAll();
	Izdavac findOne(Long id);
	void delete(Long id);
	
}
