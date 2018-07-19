package dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entity.Utenti;

public class Login {
	
	private Connessione c;
	private EntityManager entitymanager;
	
	
	public Login () {
		
		c=new Connessione();
		entitymanager=c.getEntitymanager();
		
	}
	
public Utenti login(String user,String pass) {
		
		Query q = entitymanager.createNativeQuery("select u.id from instagram.utenti u where u.user=? and u.pass=md5(?)");
		q.setParameter(1, user);
		q.setParameter(2, pass);
		
		int id = (int) q.getSingleResult();
		
		return new Utente().getUtenteId(id);
		
	}

}
