package Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Query;


@Entity
public class Foto {
	
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	private int id;
	
	private String foto;
	
	@ManyToOne
	@JoinColumn(name="profilo")
	private Profilo profilo;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Profilo getProfilo() {
		return profilo;
	}

	public void setProfilo(Profilo profilo) {
		this.profilo = profilo;
	}
	
	public String toString() {
		return foto;
	}
	
	
	

}
