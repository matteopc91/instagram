package Entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import dao.Connessione;

@Entity
public class Profilo {
	
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	private int id;
	private String nome;
	
	 @ManyToMany(targetEntity=Utenti.class)
	 @JoinTable(name="follower", joinColumns=@JoinColumn(name="id_seguito"),inverseJoinColumns=@JoinColumn(name="id_seguo"))
	 private Set utentiSeguono;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String toString() {
		return ""+id;
		
	}
	
	
	public static void main (String []args) {
		Connessione c = new Connessione();
		/*c.iscrizione("luca", "luca", "luca", "luca");
		Utenti u =c.login("luca", "luca");
		c.segui(u.getId(), 29);*/
		
		//c.aggFoto("foto", 30);
		c.mettiLike(11, 26);
	}
	

}
