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


import jwd.wafepa.model.Izdavac;
import jwd.wafepa.model.Knjiga;
import jwd.wafepa.service.IzdavacService;
import jwd.wafepa.service.KnjigaService;
import jwd.wafepa.support.IzdavacDTOtoIzdavac;
import jwd.wafepa.support.IzdavacToIzdavacDTO;
import jwd.wafepa.support.KnjigaToKnjigaDTO;
import jwd.wafepa.web.dto.IzdavacDTO;
import jwd.wafepa.web.dto.KnjigaDTO;

@RestController
@RequestMapping("/api/izdavaci")
public class ApiIzdavacController {

	
	@Autowired
	KnjigaService knjigaService;
	
	@Autowired
	IzdavacService izdavacService;
	
	
	@Autowired
	IzdavacToIzdavacDTO toDTO;
	
	@Autowired
	KnjigaToKnjigaDTO toKnjigaDTO;
	
	@Autowired
	IzdavacToIzdavacDTO toIzdavacDTO;
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<IzdavacDTO>> get(){
		
		return new ResponseEntity<>(toDTO.convert(izdavacService.findAll()),HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.GET,
			value="/{id}")
public ResponseEntity<IzdavacDTO> get(@PathVariable Long id){
		Izdavac izdavac=izdavacService.findOne(id);
		
		if(izdavac==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDTO.convert(izdavac),HttpStatus.OK);
		
	}
	
	 @RequestMapping(value="/{izdavacId}/knjige")
		public ResponseEntity<List<KnjigaDTO>> izdavaciKnjige(
				@PathVariable Long izdavacId,
				@RequestParam(defaultValue="0") int pageNum){
		 Page<Knjiga>knjige=knjigaService.findByIzdavacId(pageNum, izdavacId);
		 
			HttpHeaders headers = new HttpHeaders();
			headers.add("totalPages", Integer.toString(knjige.getTotalPages()) );
			
			return new ResponseEntity<>(toKnjigaDTO.convert(knjige.getContent()),headers,HttpStatus.OK);
	 }
	
	
}
