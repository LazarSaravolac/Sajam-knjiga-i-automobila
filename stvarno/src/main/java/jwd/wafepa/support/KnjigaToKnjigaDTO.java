package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Knjiga;
import jwd.wafepa.web.dto.KnjigaDTO;

@Component
public class KnjigaToKnjigaDTO implements Converter<Knjiga, KnjigaDTO>{

	@Override
	public KnjigaDTO convert(Knjiga knjiga) {
		// TODO Auto-generated method stub
		KnjigaDTO dto=new KnjigaDTO();
		dto.setBrojGlasova(knjiga.getBrojGlasova());
		dto.setId(knjiga.getId());
		dto.setIsbn(knjiga.getIsbn());
		dto.setIzdavacId(knjiga.getIzdavac().getId());
		dto.setIzdavacNaziv(knjiga.getIzdavac().getNaziv());
		dto.setNaziv(knjiga.getNaziv());
		dto.setPisac(knjiga.getPisac());
		dto.setIzdanje(knjiga.getIzdanje());
		return dto;
	}
	
	public List<KnjigaDTO>convert(List<Knjiga>k){
		List<KnjigaDTO>dto=new ArrayList<>();
		for(Knjiga a:k) {
			dto.add(convert(a));
		}
		return dto;
	}

}
