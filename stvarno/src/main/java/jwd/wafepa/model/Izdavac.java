package jwd.wafepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;



@Entity
@Table(name="izdavacbree")
public class Izdavac {
	@Id
	@GeneratedValue
	private Long id;
	private String naziv;
	private String adresa;
	private String telefon;
	
	@OneToMany(mappedBy="izdavac",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	List<Knjiga>knjige=new ArrayList<>();

	
	
	
	
	public Izdavac() {
		super();
	}

	public Izdavac(String naziv, String adresa, String telefon, List<Knjiga> knjige) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.knjige = knjige;
	}

	public Izdavac(Long id, String naziv, String adresa, String telefon, List<Knjiga> knjige) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.knjige = knjige;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<Knjiga> getKnjige() {
		return knjige;
	}

	public void setKnjige(List<Knjiga> knjige) {
		this.knjige = knjige;
		
	}
	

	 public void addKnjige(Knjiga knjiga) {
		this.knjige.add(knjiga);
		if(!this.equals(knjiga.getIzdavac())) {
			knjiga.setIzdavac(this);
		}
		
	 }
	 
}
