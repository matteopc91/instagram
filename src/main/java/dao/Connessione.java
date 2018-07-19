package dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Entity.Foto;
import Entity.Like1;
import Entity.Profilo;
import Entity.Utenti;

public class Connessione {

	private EntityManagerFactory emfactory;
	private EntityManager entitymanager;

	public Connessione() {
		emfactory = Persistence.createEntityManagerFactory("hibe");
		entitymanager = emfactory.createEntityManager();
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
	
	public Utenti login(String user,String pass) {
		
		Query q = entitymanager.createNativeQuery("select u.id from utenti u where u.user=? and u.pass=md5(?)");
		q.setParameter(1, user);
		q.setParameter(2, pass);
		
		int id = (int) q.getSingleResult();
		
		return getUtente(id);
		
		
		
	}
	
	
	public void segui (int idUtente, int idProfiloSeguito) {
		
		entitymanager.getTransaction( ).begin( );
		
		Utenti u = getUtente(idUtente);
		Profilo p = getProfilo(idProfiloSeguito);
		List<Profilo> lp = new LinkedList<Profilo>();
		lp.add(p);
		//u.setProfiliSeguiti(lp);
		
		entitymanager.persist(p);
		entitymanager.getTransaction( ).commit();
		
	}
	
	public void aggFoto (String foto,int idProfilo) {
		
		  entitymanager.getTransaction( ).begin( );
		  
		  Foto f = new Foto();
		  f.setFoto(foto);
		  Profilo p = getProfilo(idProfilo);
		  f.setProfilo(p);
		  entitymanager.persist(f);
		  
		  entitymanager.getTransaction( ).commit();
	}
	
	public void mettiLike (int idFoto, int idUtente) {
		
		  entitymanager.getTransaction( ).begin( );
		
		  Like1 l = new Like1();
		  Foto f = getFoto(idFoto);
		  l.setFoto(f);
		  Utenti u = getUtente(idUtente);
		  l.setUtenti(u);
		  entitymanager.persist(l);
		  
		  entitymanager.getTransaction( ).commit();
	}
	
	public Utenti getUtente(int idUtente) {
		Query q2 = entitymanager.createQuery("select u from Utenti u where u.id=:num");
		q2.setParameter("num", idUtente);
		Utenti u = (Utenti) q2.getSingleResult();
		return u;
	}
	
	
	public Profilo getProfilo(int idProfilo){
		Query q2 = entitymanager.createQuery("select p from Profilo p where p.id=:num");
		q2.setParameter("num", idProfilo);
		Profilo p = (Profilo) q2.getSingleResult();
		return p;
	}
	
	public Foto getFoto(int idFoto) {
		Query q2 = entitymanager.createQuery("select f from Foto f where f.id=:num");
		q2.setParameter("num", idFoto);
		Foto f  = (Foto) q2.getSingleResult();
		return f;
	}

	public EntityManagerFactory getEmfactory() {
		return emfactory;
	}

	public void setEmfactory(EntityManagerFactory emfactory) {
		this.emfactory = emfactory;
	}

	public EntityManager getEntitymanager() {
		return entitymanager;
	}

	public void setEntitymanager(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
	}

	

}
