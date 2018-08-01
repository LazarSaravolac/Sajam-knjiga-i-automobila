package jwd.wafepa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Knjiga;
import jwd.wafepa.model.Kupi;
import jwd.wafepa.repository.KnjigaRepository;
import jwd.wafepa.repository.KupiRepository;
import jwd.wafepa.service.KupiService;

@Service
public class JpaKupovinaService implements KupiService{

	
	@Autowired
	KupiRepository kupiRepository;
	@Autowired
	KnjigaRepository knjigaRepository;
	
	@Override
	public Kupi kupiknjigu(Long knjigaId) {
		
		if(knjigaId==null) {
			throw new IllegalArgumentException("id required");
		}
		Knjiga knjiga=knjigaRepository.findOne(knjigaId);
		if(knjiga==null) {
			throw new IllegalArgumentException("no book with this id");
		}
		

		
		Kupi kupi=new Kupi();
		kupi.setKnjiga(knjiga);
		
		kupiRepository.save(kupi);
		knjiga.setBrojGlasova(knjiga.getBrojGlasova()+1);
		knjigaRepository.save(knjiga);
		
		return kupi;
	}

}
