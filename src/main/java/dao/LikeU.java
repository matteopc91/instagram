package dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entity.Foto;
import Entity.Like1;
import Entity.Utenti;

public class LikeU {
	
	private Connessione c;
	private EntityManager entitymanager;
	
	
	public LikeU () {
		
		c=new Connessione();
		entitymanager=c.getEntitymanager();
		
	}
	
	public void mettiLike (int idFoto, int idUtente) {
		
		  entitymanager.getTransaction( ).begin( );
		
		  Like1 l = new Like1();
		  FotoU fu = new FotoU();
		  Foto f = fu.getFoto(idFoto);
		  l.setFoto(f);
		  Utente ut = new Utente();
		  Utenti u = ut.getUtenteId(idUtente);
		  l.setUtenti(u);
		  entitymanager.persist(l);
		  
		  entitymanager.getTransaction( ).commit();
	}
	
	public void togliLike(int idFoto, int idUtente) {
		
		entitymanager.getTransaction( ).begin( );
		Query q = entitymanager.createNativeQuery("delete from instagram.like1  where id_foto=? and id_utente=?");
		q.setParameter(1, idFoto);
		q.setParameter(2, idUtente);
		q.executeUpdate();
		entitymanager.getTransaction( ).commit();
	}

}
