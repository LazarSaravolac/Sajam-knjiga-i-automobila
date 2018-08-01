package jwd.wafepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="kompanijavezba")
public class Kompanija {

	
	@Id
	@GeneratedValue
	private Long id;
	private String naziv;
	private String adresa;
	private String telefon;
	
	@OneToMany(mappedBy="kompanija",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	List<Automobil>auta=new ArrayList<>();

	public Kompanija(Long id, String naziv, String adresa, String telefon, List<Automobil> auta) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.auta = auta;
	}

	public Kompanija(String naziv, String adresa, String telefon, List<Automobil> auta) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.auta = auta;
	}

	public Kompanija() {
		super();
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

	public List<Automobil> getAuta() {
		return auta;
	}

	public void setAuta(List<Automobil> auta) {
		this.auta = auta;
	}
	
	
}
