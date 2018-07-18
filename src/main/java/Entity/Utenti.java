package Entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Query;


@Entity
public class Utenti {
	
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	private int id;
	private String nome;
	private String cognome;
	private String pass;
	private String user;
	
	@OneToOne
	@JoinColumn(name="profilo")
	private Profilo profiloMio;
	
	 @ManyToMany(targetEntity=Profilo.class)
	 @JoinTable(name="follower", joinColumns=@JoinColumn(name="id_seguo"),inverseJoinColumns=@JoinColumn(name="id_seguito"))
	 private List<Profilo> profiliSeguiti;

	public List<Profilo> getProfiliSeguiti() {
		return profiliSeguiti;
	}

	public void setProfiliSeguiti(Profilo p) {
		//this.profiliSeguiti = profiliSeguiti;
		this.profiliSeguiti.add(p);
	}
	
	public void eliminaProfiloSeguito(Profilo p) {
		this.profiliSeguiti.remove(p);
	}

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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Profilo getProfiloMio() {
		return profiloMio;
	}

	public void setProfiloMio(Profilo profilo) {
		this.profiloMio = profilo;
	}
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String toString() {
		return ""+id;
		
	}
	
	public static void main (String[]args) {
		
		   EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		   EntityManager entitymanager = emfactory.createEntityManager( );
		   entitymanager.getTransaction( ).begin( );
		   
		   Profilo p = new Profilo();
		   p.setNome("paolo/imbro");
		   entitymanager.persist(p);
		   
		   Utenti u = new Utenti();
		   u.setNome("Paolo");
		   u.setCognome("Imbo");
		   u.setPass("md5(foca)");
		   u.setUser("pi");
		   u.setProfiloMio(p);
		   entitymanager.persist(u);

		   
		  Query q= entitymanager.createQuery("select u from Utenti u where u.nome='Matteo'");
		  List<Utenti> list2 = q.getResultList();
		  
		  List<Profilo> seguo= new LinkedList<Profilo>();
		  
		  for(Utenti ut : list2) {
		  Profilo profilo = ut.getProfiloMio();
		  seguo.add(profilo);
		  }
		  
		  //u.setProfiliSeguiti(seguo);
		  entitymanager.persist(u);
		  
		  Foto f = new Foto();
		  f.setFoto("Foto del monte");
		  f.setProfilo(p);
		  entitymanager.persist(f);
		  
		  Like1 l = new Like1();
		  l.setFoto(f);
		  l.setUtenti(u);
		  entitymanager.persist(l);
		  
		  
		  Query insert = entitymanager.createNativeQuery("insert into utenti (nome,cognome,user,pass) values (?,?,?,md5(?))");
		  insert.setParameter(1, "Matteo");
		  insert.setParameter(2, "Matteo");
		  insert.setParameter(3, "Matteo");
		  insert.setParameter(4, "Matteo");
		  insert.executeUpdate();
		  
		  entitymanager.getTransaction( ).commit();
		   
		   entitymanager.close();
		   emfactory.close();
		   
		   
		   
		   
		   
	}

	
	
	

}
