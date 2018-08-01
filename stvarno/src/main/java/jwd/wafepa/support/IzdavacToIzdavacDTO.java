package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Izdavac;
import jwd.wafepa.web.dto.IzdavacDTO;

@Component
public class IzdavacToIzdavacDTO implements Converter<Izdavac, IzdavacDTO>{

	@Override
	public IzdavacDTO convert(Izdavac izdavac) {
		// TODO Auto-generated method stub
		IzdavacDTO dto=new IzdavacDTO();
		dto.setAdresa(izdavac.getAdresa());
		dto.setId(izdavac.getId());
		dto.setNaziv(izdavac.getNaziv());
		dto.setTelefon(izdavac.getTelefon());
		return dto;
	}
	
	public List<IzdavacDTO>convert(List<Izdavac>izdavaci){
		List<IzdavacDTO>dto=new ArrayList<>();
		for(Izdavac a:izdavaci) {
			dto.add(convert(a));
		}
		return dto;
		
	}

}
