package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Like1 {
	
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_foto")
	private Foto foto;
	
	@ManyToOne
	@JoinColumn(name="id_utente")
	private Utenti utenti;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public Utenti getUtenti() {
		return utenti;
	}

	public void setUtenti(Utenti utenti) {
		this.utenti = utenti;
	}
	
	

}
