package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Automobil;
import jwd.wafepa.service.AutomobilService;
import jwd.wafepa.service.KompanijaService;
import jwd.wafepa.web.dto.AutomobilDTO;

@Component
public class AutomobilDTOtoAutomobil implements Converter<AutomobilDTO, Automobil>{

	@Autowired
	AutomobilService automobilService;
	
	@Autowired
	KompanijaService kompanijaService;
	
	@Override
	public Automobil convert(AutomobilDTO dto) {
		// TODO Auto-generated method stub
		Automobil auto=new Automobil();
		
		if(dto.getId()==null) {
			auto.setKompanija(kompanijaService.findOne(dto.getKompanijaId()));
		}else {
			auto=automobilService.findOne(dto.getId());
		}
		
		auto.setGodiste(dto.getGodiste());
		auto.setId(dto.getId());
		auto.setIznajmljen(dto.getIznajmljen());
		auto.setModel(dto.getModel());
		auto.setPotrosnja(dto.getPotrosnja());
		auto.setRegistracija(dto.getRegistracija());
	
		
		return auto;
	}

	
	public List<Automobil>convert(List<AutomobilDTO>dto){
		List<Automobil>auta=new ArrayList<>();
		for(AutomobilDTO a:dto) {
			auta.add(convert(a));		
			}
		return auta;
	}
	
}
