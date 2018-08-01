package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Automobil;
import jwd.wafepa.model.Kompanija;
import jwd.wafepa.service.AutomobilService;
import jwd.wafepa.service.KompanijaService;
import jwd.wafepa.support.AutomobilToAutomobilDTO;
import jwd.wafepa.support.IzdavacToIzdavacDTO;
import jwd.wafepa.support.KompanijaToKompanijaDTO;
import jwd.wafepa.web.dto.AutomobilDTO;
import jwd.wafepa.web.dto.KnjigaDTO;
import jwd.wafepa.web.dto.KompanijaDTO;

@RestController
@RequestMapping("/api/kompanije")
public class ApiKompanijaController {

	@Autowired
	AutomobilService automobilService;
	
	@Autowired
	KompanijaService kompanijaService;
	
	@Autowired
	KompanijaToKompanijaDTO toDTO;
	
	@Autowired
	AutomobilToAutomobilDTO toAutoDTO;
	
	
	
	@Autowired
	IzdavacToIzdavacDTO toIzdavacDTO;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<KompanijaDTO>> get(){
		return new ResponseEntity<>(toDTO.convert(kompanijaService.findAll()),HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,
			value="/{id}")
public ResponseEntity<KompanijaDTO> get(@PathVariable Long id){
		Kompanija kompanija=kompanijaService.findOne(id);

		if(kompanija==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDTO.convert(kompanija),HttpStatus.OK);
	}
	
	

	 @RequestMapping(value="/{kompanijaId}/auta")
		public ResponseEntity<List<AutomobilDTO>> autoKompanija
		(@PathVariable Long kompanijaId,@RequestParam(defaultValue="0") int pageNum){
		 Page<Automobil>automobili=automobilService.findByKompanijaId(pageNum, kompanijaId);
		 
		 HttpHeaders headers = new HttpHeaders();
			headers.add("totalPages", Integer.toString(automobili.getTotalPages()) );
			
			return new ResponseEntity<>(toAutoDTO.convert(automobili.getContent()),headers,HttpStatus.OK);
	 }
	
	
}
