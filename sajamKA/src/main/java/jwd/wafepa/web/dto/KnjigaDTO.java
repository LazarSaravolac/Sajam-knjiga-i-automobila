package jwd.wafepa.web.dto;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class KnjigaDTO {

	
	private Long id;
	@NotBlank
	@NotEmpty
	private String naziv;
	@Max(9999)
	private String izdanje;
	private String pisac;
	@Length(max=16)
	private String isbn;
	private Integer brojGlasova;
	
	private Long izdavacId;
	private String izdavacNaziv;
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
	public String getIzdanje() {
		return izdanje;
	}
	public void setIzdanje(String izdanje) {
		this.izdanje = izdanje;
	}
	public String getPisac() {
		return pisac;
	}
	public void setPisac(String pisac) {
		this.pisac = pisac;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Integer getBrojGlasova() {
		return brojGlasova;
	}
	public void setBrojGlasova(Integer brojGlasova) {
		this.brojGlasova = brojGlasova;
	}
	public Long getIzdavacId() {
		return izdavacId;
	}
	public void setIzdavacId(Long izdavacId) {
		this.izdavacId = izdavacId;
	}
	public String getIzdavacNaziv() {
		return izdavacNaziv;
	}
	public void setIzdavacNaziv(String izdavacNaziv) {
		this.izdavacNaziv = izdavacNaziv;
	}
	
}
