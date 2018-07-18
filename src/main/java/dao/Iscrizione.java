package dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entity.Profilo;

public class Iscrizione {
	
	private Connessione c;
	private EntityManager entitymanager;
	
	
	public Iscrizione () {
		
		c=new Connessione();
		entitymanager=c.getEntitymanager();
		
	}
	
	public void iscrizione(String nome, String cognome, String user, String pass) {

		entitymanager.getTransaction( ).begin();
		
		Profilo p = new Profilo();
		p.setNome(nome+"/"+cognome);
		entitymanager.persist(p);

		entitymanager.getTransaction().commit();
		
		entitymanager.getTransaction().begin();
		Query insert = entitymanager.createNativeQuery("insert into utenti (nome,cognome,user,pass,profilo) values (?,?,?,md5(?),?)");
		insert.setParameter(1, nome);
		insert.setParameter(2, cognome);
		insert.setParameter(3, user);
		insert.setParameter(4, pass);
		insert.setParameter(5, p.getId());
		insert.executeUpdate();
		
		entitymanager.getTransaction().commit();
	}

}
