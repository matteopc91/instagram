package dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entity.Foto;
import Entity.Profilo;
import Entity.Utenti;

public class FotoU {
	
	private Connessione c;
	private EntityManager entitymanager;
	
	
	public FotoU () {
		
		c=new Connessione();
		entitymanager=c.getEntitymanager();
		
	}
	
	public void aggFoto (String foto,int idProfilo) {
		
		  entitymanager.getTransaction( ).begin( );
		  
		  Foto f = new Foto();
		  f.setFoto(foto);
		  ProfiloU pu = new ProfiloU();
		  Profilo p = pu.getProfilo(idProfilo);
		  f.setProfilo(p);
		  entitymanager.persist(f);
		  
		  entitymanager.getTransaction( ).commit();
	}
	
	public void rimuoviFoto (int idFoto) {
		
		entitymanager.getTransaction( ).begin( );
		Query q = entitymanager.createNativeQuery("delete from instagram.foto  where id=?");
		q.setParameter(1, idFoto);
		q.executeUpdate();
		entitymanager.getTransaction( ).commit();
	}
	
	public Foto getFoto (int idFoto) {
		Query q2 = entitymanager.createQuery("select f from Foto f where f.id=:num");
		q2.setParameter("num", idFoto);
		Foto f = (Foto) q2.getSingleResult();
		return f;
	}

}
