package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import jwd.wafepa.model.Knjiga;
import jwd.wafepa.model.Kupi;

import jwd.wafepa.service.IzdavacService;
import jwd.wafepa.service.KnjigaService;
import jwd.wafepa.service.KupiService;
import jwd.wafepa.support.KnjigaDTOtoKnjiga;
import jwd.wafepa.support.KnjigaToKnjigaDTO;
import jwd.wafepa.support.KupiToKupiDTO;
import jwd.wafepa.web.dto.KnjigaDTO;
import jwd.wafepa.web.dto.KupiDTO;


@RestController
@RequestMapping(value="/api/knjige")
public class ApiKnjigaController {

	@Autowired
	KnjigaService knjigaService;
	
	@Autowired
	IzdavacService izdavacService;
	
	@Autowired
	KnjigaToKnjigaDTO toDTO;
	
	@Autowired
	KnjigaDTOtoKnjiga toKnjiga;
	
	@Autowired
	KupiService kupiService;
	
	@Autowired
	KupiToKupiDTO toKupiDTO;
	

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<KnjigaDTO>> get(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) String pisac ,
			@RequestParam(required=false) Integer minGlasova,
			@RequestParam(defaultValue="0") int page){
		
		Page<Knjiga>knjige;
		
		if(naziv!=null || pisac!=null || minGlasova!=null) {
			knjige=knjigaService.pretraga(naziv, pisac, minGlasova,page);
		}else {
			knjige=knjigaService.findAll(page);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(knjige.getTotalPages()) );
		
		
		return new ResponseEntity<>(toDTO.convert(knjige.getContent()),headers,HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/{id}")
	public ResponseEntity<KnjigaDTO> get(@PathVariable Long id){
		
		Knjiga knjiga=new Knjiga();
		knjiga=knjigaService.findOne(id);
		
		if(knjiga==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<>(toDTO.convert(knjiga),HttpStatus.OK);
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<KnjigaDTO> add(
			@Validated @RequestBody KnjigaDTO novaKnjiga){
		
		Knjiga knjiga=toKnjiga.convert(novaKnjiga);
		knjigaService.save(knjiga);
		return new ResponseEntity<>(toDTO.convert(knjiga),HttpStatus.OK);
		
		
	}
	
	
	 @RequestMapping(method=RequestMethod.PUT,
				value="/{id}")
		public ResponseEntity<KnjigaDTO> edit(
				@PathVariable Long id,
		@Validated @RequestBody KnjigaDTO izmenjen){
		 
		 if(!id.equals(izmenjen.getId())) {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		 }
		 Knjiga knjiga=toKnjiga.convert(izmenjen);
		 knjigaService.save(knjiga);
		 
		 return new ResponseEntity<>(toDTO.convert(knjiga),HttpStatus.OK);
	 }
	
	 
	 @RequestMapping(method=RequestMethod.DELETE,
				value="/{id}")
		public ResponseEntity<KnjigaDTO> delete(@PathVariable Long id){
		 knjigaService.delete(id);
		 
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	 }
	 
		@RequestMapping(method=RequestMethod.POST, value="/{id}/kupiKnjigu")
		public ResponseEntity<KupiDTO> rent(@PathVariable Long id){
			

		 	
		 	Kupi k=kupiService.kupiknjigu(id);
			
		
			
			return new ResponseEntity<>(toKupiDTO.convert(k), HttpStatus.CREATED);

			
		}
}
