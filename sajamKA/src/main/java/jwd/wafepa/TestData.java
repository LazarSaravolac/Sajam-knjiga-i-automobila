package jwd.wafepa;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Automobil;
import jwd.wafepa.model.Izdavac;
import jwd.wafepa.model.Knjiga;
import jwd.wafepa.model.Kompanija;
import jwd.wafepa.service.AutomobilService;
import jwd.wafepa.service.IzdavacService;
import jwd.wafepa.service.KnjigaService;
import jwd.wafepa.service.KompanijaService;


@Component
public class TestData {

	@Autowired
	AutomobilService automobilService;
	
	@Autowired
	KompanijaService kompanijaService;
	
	@Autowired
	IzdavacService izdavacService;
	
	@Autowired
	KnjigaService knjigaService;
	
	@PostConstruct
	public void init(){
	 
		Izdavac i1=new Izdavac();
		i1.setAdresa("Rudjera Boskovica");
		i1.setNaziv("Milan Sipka");
		i1.setTelefon("064/212-321");
		izdavacService.save(i1);
		
		
		Knjiga k1=new Knjiga();
		k1.setBrojGlasova(3);
		k1.setIsbn("3133-11");
		k1.setIzdanje("2013");
		k1.setNaziv("Za kim zvona zvone");
		k1.setPisac("Lazar");
		k1.setIzdavac(i1);
		knjigaService.save(k1);
		
		
		Izdavac i2=new Izdavac();
		i2.setAdresa("Brace Krkljus");
		i2.setNaziv("Mitros");
		i2.setTelefon("061/212-22");
		izdavacService.save(i2);
		
		
		Knjiga k2=new Knjiga();
		k2.setBrojGlasova(31);
		k2.setIsbn("1221-11");
		k2.setIzdanje("1942");
		k2.setNaziv("Pokondirana tikva");
		k2.setPisac("Mitar");
		k2.setIzdavac(i2);
		knjigaService.save(k2);
		
		
		
		Kompanija komp1=new Kompanija();
		komp1.setAdresa("Laze Nancica");
		komp1.setNaziv("FTNinformatika");
		komp1.setTelefon("062/111-231");
		kompanijaService.save(komp1);
		
		Automobil auto1=new Automobil();
		auto1.setGodiste(1994);
		auto1.setIznajmljen(true);
		auto1.setModel("Korsa");
		auto1.setPotrosnja(31.21);
		auto1.setRegistracija("NS107MZ");
		auto1.setKompanija(komp1);
		automobilService.save(auto1);
		
		
		Kompanija komp2=new Kompanija();
		komp2.setAdresa("Rudjera Boskovic");
		komp2.setNaziv("Aleph");
		komp2.setTelefon("064/312-221");
		kompanijaService.save(komp2);
		
		Automobil auto2=new Automobil();
		auto2.setGodiste(1994);
		auto2.setIznajmljen(true);
		auto2.setModel("Opel");
		auto2.setPotrosnja(11.21);
		auto2.setRegistracija("BG217TZ");
		auto2.setKompanija(komp2);
		automobilService.save(auto2);
		
		
		
		
		
		
		
		
		
	}
}
