package jwd.wafepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="autovezba")
public class Automobil {

	@Id
	@GeneratedValue
	private Long id;
	private String model;
	private String registracija;
	private Integer godiste;
	private Double potrosnja;
	private Boolean iznajmljen;
	
	@ManyToOne(fetch=FetchType.EAGER)
	Kompanija kompanija;

	
	@OneToMany(mappedBy="automobil",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	List<Iznajmi>iznajmljivanje=new ArrayList<>();
	
	public List<Iznajmi> getIznajmljivanje() {
		return iznajmljivanje;
	}

	public void setIznajmljivanje(List<Iznajmi> iznajmljivanje) {
		this.iznajmljivanje = iznajmljivanje;
	}

	public Automobil() {
		super();
	}

	public Automobil(String model, String registracija, Integer godiste, Double potrosnja, Boolean iznajmljen,
			Kompanija kompanija) {
		super();
		this.model = model;
		this.registracija = registracija;
		this.godiste = godiste;
		this.potrosnja = potrosnja;
		this.iznajmljen = iznajmljen;
		this.kompanija = kompanija;
	}

	public Automobil(Long id, String model, String registracija, Integer godiste, Double potrosnja, Boolean iznajmljen,
			Kompanija kompanija) {
		super();
		this.id = id;
		this.model = model;
		this.registracija = registracija;
		this.godiste = godiste;
		this.potrosnja = potrosnja;
		this.iznajmljen = iznajmljen;
		this.kompanija = kompanija;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRegistracija() {
		return registracija;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}

	public Integer getGodiste() {
		return godiste;
	}

	public void setGodiste(Integer godiste) {
		this.godiste = godiste;
	}

	public Double getPotrosnja() {
		return potrosnja;
	}

	public void setPotrosnja(Double potrosnja) {
		this.potrosnja = potrosnja;
	}

	public Boolean getIznajmljen() {
		return iznajmljen;
	}

	public void setIznajmljen(Boolean iznajmljen) {
		this.iznajmljen = iznajmljen;
	}

	public Kompanija getKompanija() {
		return kompanija;
	}

	public void setKompanija(Kompanija kompanija) {
		this.kompanija = kompanija;
		if(kompanija!=null && !kompanija.getAuta().contains(this)) {
			kompanija.getAuta().add(this);
		}
	}
	
	
	
}
