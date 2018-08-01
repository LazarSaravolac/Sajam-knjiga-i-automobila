package jwd.wafepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="knjigebree")
public class Knjiga {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false)
	private String naziv;
	@Column(nullable=false)
	private String izdanje;
	@Column(nullable=false)
	private String pisac;
	@Column(nullable=false,unique=true)
	private String isbn;
	private Integer brojGlasova;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	Izdavac izdavac;

	@OneToMany(mappedBy="knjiga",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	List<Kupi>kupovinaKarata=new ArrayList<>();
	
	
	
	public List<Kupi> getKupovinaKarata() {
		return kupovinaKarata;
	}


	public void setKupovinaKarata(List<Kupi> kupovinaKarata) {
		this.kupovinaKarata = kupovinaKarata;
	}


	public Knjiga(Long id, String naziv, String izdanje, String pisac, String isbn, Integer brojGlasova,
			Izdavac izdavac) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.izdanje = izdanje;
		this.pisac = pisac;
		this.isbn = isbn;
		this.brojGlasova = brojGlasova;
		this.izdavac = izdavac;
	}


	public Knjiga(String naziv, String izdanje, String pisac, String isbn, Integer brojGlasova, Izdavac izdavac) {
		super();
		this.naziv = naziv;
		this.izdanje = izdanje;
		this.pisac = pisac;
		this.isbn = isbn;
		this.brojGlasova = brojGlasova;
		this.izdavac = izdavac;
	}


	public Knjiga() {
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


	public Izdavac getIzdavac() {
		return izdavac;
	}


	public void setIzdavac(Izdavac izdavac) {
		this.izdavac = izdavac;
		if(izdavac!=null && !izdavac.getKnjige().contains(this)) {
			izdavac.getKnjige().add(this);
		}
	}
	
	
	
}
