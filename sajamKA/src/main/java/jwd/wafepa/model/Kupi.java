package jwd.wafepa.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Kupi {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(fetch=FetchType.EAGER)
	Knjiga knjiga;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Knjiga getKnjiga() {
		return knjiga;
	}
	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
		if(knjiga!=null && !knjiga.getKupovinaKarata().contains(this)){
			knjiga.getKupovinaKarata().add(this);
		}
	}
	
	
	
}
