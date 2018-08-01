package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Knjiga;
import jwd.wafepa.service.IzdavacService;
import jwd.wafepa.service.KnjigaService;
import jwd.wafepa.web.dto.KnjigaDTO;

@Component
public class KnjigaDTOtoKnjiga implements Converter<KnjigaDTO, Knjiga>{

	@Autowired
	KnjigaService knjigaService;
	
	@Autowired
	IzdavacService izdavacService;
	
	@Override
	public Knjiga convert(KnjigaDTO dto) {
		// TODO Auto-generated method stub
		Knjiga knjiga=new Knjiga();
		
		if(dto.getId()==null) {
			knjiga.setIzdavac(izdavacService.findOne(dto.getIzdavacId()));
		}else {
			knjiga=knjigaService.findOne(dto.getId());
		}
		
		knjiga.setBrojGlasova(dto.getBrojGlasova());
		knjiga.setId(dto.getId());
		knjiga.setIsbn(dto.getIsbn());
		knjiga.setIzdanje(dto.getIzdanje());
		knjiga.setNaziv(dto.getNaziv());
		knjiga.setPisac(dto.getPisac());
		
		return knjiga;
	}

	
	
	public List<Knjiga>convert(List<KnjigaDTO>dto){
		List<Knjiga>knjige=new ArrayList<>();
		for(KnjigaDTO a:dto) {
			knjige.add(convert(a));
		}
		return knjige;
	}
}
