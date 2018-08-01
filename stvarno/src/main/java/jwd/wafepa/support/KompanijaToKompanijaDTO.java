package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Kompanija;
import jwd.wafepa.web.dto.KompanijaDTO;

@Component
public class KompanijaToKompanijaDTO implements Converter<Kompanija, KompanijaDTO>{

	@Override
	public KompanijaDTO convert(Kompanija kompanija) {
		// TODO Auto-generated method stub
		
		KompanijaDTO dto =new KompanijaDTO();
		
		dto.setAdresa(kompanija.getAdresa());
		dto.setId(kompanija.getId());
		dto.setNaziv(kompanija.getNaziv());
		dto.setTelefon(kompanija.getTelefon());
		
		return dto;
	}
	
	public List<KompanijaDTO>convert(List<Kompanija>kompanije){
		List<KompanijaDTO>dto=new ArrayList<>();
		for(Kompanija a:kompanije) {
			dto.add(convert(a));
		}
		return dto;
		
	}

}
