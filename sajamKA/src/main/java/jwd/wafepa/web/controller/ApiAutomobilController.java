package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Automobil;
import jwd.wafepa.model.Iznajmi;
import jwd.wafepa.service.AutomobilService;
import jwd.wafepa.service.IznajmiService;
import jwd.wafepa.service.KompanijaService;
import jwd.wafepa.support.AutomobilDTOtoAutomobil;
import jwd.wafepa.support.AutomobilToAutomobilDTO;
import jwd.wafepa.support.IznajmiToIznajmiDTO;
import jwd.wafepa.web.dto.AutomobilDTO;
import jwd.wafepa.web.dto.IznajmiDTO;
import jwd.wafepa.web.dto.KnjigaDTO;
import jwd.wafepa.web.dto.KupiDTO;


@RestController
@RequestMapping(value="/api/automobili")
public class ApiAutomobilController {

	@Autowired
	AutomobilService automobilService;
	
	@Autowired
	KompanijaService kompanijaService;
	
	@Autowired
	AutomobilDTOtoAutomobil toAuto;
	
	@Autowired
	AutomobilToAutomobilDTO toDTO;
	
	@Autowired
	IznajmiToIznajmiDTO toIznajmiDTO;
	
	
	@Autowired
	IznajmiService iznajmiService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<AutomobilDTO>> get(
			@RequestParam(required=false) String model,
			@RequestParam(required=false) Integer godiste ,
			@RequestParam(required=false) Long dajId,
			@RequestParam(required=false) Double potrosnja,
			@RequestParam(defaultValue="0") int page,
			@RequestParam(defaultValue="5") Integer velicina){
		
		Page<Automobil>automobili;
		if(model!=null || godiste!=null || dajId!=null || velicina!=null) {
			automobili=automobilService.pretraga(model, godiste, dajId,potrosnja, page,velicina);
		}else {
			automobili=automobilService.findAll(page);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(automobili.getTotalPages()) );
		
		
		
		return new ResponseEntity<>(toDTO.convert(automobili.getContent()),headers,HttpStatus.OK);
		
	
	}
	
	
	
	
	
	@RequestMapping(method=RequestMethod.GET,value="/{id}")
	public ResponseEntity<AutomobilDTO> get(@PathVariable Long id){
		Automobil auto=automobilService.findOne(id);
		
		if(auto==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDTO.convert(auto),HttpStatus.OK);
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<AutomobilDTO> add(
			@Validated @RequestBody AutomobilDTO novAuto){
		
		
		Automobil auto=toAuto.convert(novAuto);
		automobilService.save(auto);
		
		return new ResponseEntity<>(toDTO.convert(auto),HttpStatus.OK);
	}
	
	
	 @RequestMapping(method=RequestMethod.PUT,
				value="/{id}")
		public ResponseEntity<AutomobilDTO> edit(
				@PathVariable Long id,
		@Validated @RequestBody AutomobilDTO izmenjen){
		 
		 if(!id.equals(izmenjen.getId())) {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		 }
		 
			
			Automobil auto=toAuto.convert(izmenjen);
			automobilService.save(auto);
			
			return new ResponseEntity<>(toDTO.convert(auto),HttpStatus.OK);
	 }
	
	 @RequestMapping(method=RequestMethod.DELETE,
				value="/{id}")
		public ResponseEntity<KnjigaDTO> delete(@PathVariable Long id){
		 
		 automobilService.delete(id);
		 
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	 }
	
	 @RequestMapping(method=RequestMethod.POST, value="/{id}/iznajmi")
		public ResponseEntity<IznajmiDTO> iznajmi(@PathVariable Long id){
		 
		 
		Iznajmi i =iznajmiService.iznajmiAuto(id);
		 
		 return new ResponseEntity<>(toIznajmiDTO.convert(i), HttpStatus.CREATED);
	 }
	
	
	
	
	
	
}
