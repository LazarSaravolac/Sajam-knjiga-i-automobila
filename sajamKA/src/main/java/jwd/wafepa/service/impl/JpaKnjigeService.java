package jwd.wafepa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Knjiga;
import jwd.wafepa.repository.KnjigaRepository;
import jwd.wafepa.service.KnjigaService;

@Service
public class JpaKnjigeService implements KnjigaService{

	@Autowired
	KnjigaRepository knjigaRepository;
	
	@Override
	public Knjiga save(Knjiga knjiga) {
		// TODO Auto-generated method stub
		return knjigaRepository.save(knjiga);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		knjigaRepository.delete(id);
	}

	@Override
	public Knjiga findOne(Long id) {
		// TODO Auto-generated method stub
		return knjigaRepository.findOne(id);
	}

	@Override
	public Page<Knjiga> findAll(int page) {
		// TODO Auto-generated method stub
		return knjigaRepository.findAll(new PageRequest(page, 5));
	}

	@Override
	public Page<Knjiga> findByIzdavacId(int pageNum, Long izdavacId) {
		// TODO Auto-generated method stub
		return knjigaRepository.findByIzdavacId(izdavacId, new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Knjiga> pretraga(String naziv, String pisac, Integer minGlasova, int page) {
		// TODO Auto-generated method stub
		
		if(naziv!=null) {
			naziv="%" + naziv +"%";
		}
		if(pisac!=null) {
			pisac="%" + pisac + "%";
		}
		return knjigaRepository.pretraga(naziv, pisac, minGlasova, new PageRequest(page, 5));
	}

}
