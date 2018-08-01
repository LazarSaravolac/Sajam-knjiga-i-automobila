package jwd.wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;


import jwd.wafepa.model.Knjiga;

public interface KnjigaService {

	Knjiga save(Knjiga knjiga);
	void delete(Long id);
	Knjiga findOne(Long id);
	Page<Knjiga>findAll(int page);
	Page<Knjiga>findByIzdavacId(int pageNum, Long izdavacId);
	Page<Knjiga> pretraga(
	@Param("naziv") String naziv, 
	@Param("pisac") String pisac, 
	@Param("minGlasova") Integer minGlasova,
	int page);
}
