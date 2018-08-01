package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Automobil;
import jwd.wafepa.web.dto.AutomobilDTO;

@Component
public class AutomobilToAutomobilDTO implements Converter<Automobil, AutomobilDTO>{

	
	
	@Override
	public AutomobilDTO convert(Automobil auto) {
		AutomobilDTO dto=new AutomobilDTO();
		
		dto.setGodiste(auto.getGodiste());
		dto.setId(auto.getId());
		dto.setIznajmljen(auto.getIznajmljen());
		dto.setKompanijaId(auto.getKompanija().getId());
		dto.setKompanijaNaziv(auto.getKompanija().getNaziv());
		dto.setModel(auto.getModel());
		dto.setPotrosnja(auto.getPotrosnja());
		dto.setRegistracija(auto.getRegistracija());
		
		return dto;
	}
	
	
	public List<AutomobilDTO>convert(List<Automobil>auta){
		List<AutomobilDTO>dto=new ArrayList<>();
		for(Automobil a:auta) {
			dto.add(convert(a));
		}
		return dto;
	}

}
