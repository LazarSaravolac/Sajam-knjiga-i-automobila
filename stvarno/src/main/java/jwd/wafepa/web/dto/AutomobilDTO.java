package jwd.wafepa.web.dto;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class AutomobilDTO {
	private Long id;
	@NotBlank
	@NotEmpty
	private String model;
	private String registracija;
	@Max(9999)
	private Integer godiste;
	@Max(99)
	private Double potrosnja;
	private Boolean iznajmljen;
	
	
	private Long kompanijaId;
	private String kompanijaNaziv;
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
	public Long getKompanijaId() {
		return kompanijaId;
	}
	public void setKompanijaId(Long kompanijaId) {
		this.kompanijaId = kompanijaId;
	}
	public String getKompanijaNaziv() {
		return kompanijaNaziv;
	}
	public void setKompanijaNaziv(String kompanijaNaziv) {
		this.kompanijaNaziv = kompanijaNaziv;
	}
}
