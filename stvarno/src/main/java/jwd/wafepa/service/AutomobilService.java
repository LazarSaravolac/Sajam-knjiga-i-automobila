package jwd.wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import jwd.wafepa.model.Automobil;

public interface AutomobilService {

	Automobil save(Automobil automobil);
	void delete(Long id);
	Automobil findOne(Long id);
	Page<Automobil>findAll(int page);
	Page<Automobil>findByKompanijaId(int pageNum, Long kompanijaId);
	Page<Automobil> pretraga(
			@Param("model") String model, 
			@Param("godiste") Integer godiste, 
			@Param("dajId") Long dajId,
			@Param("potrosnja") Double potrosnja,
			int page,Integer velicina);
}
