package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entity.Profilo;
import Entity.Utenti;

public class Utente {
	
	private Connessione c;
	private EntityManager entitymanager;
	
	
	public Utente () {
		
		c=new Connessione();
		entitymanager=c.getEntitymanager();
		
	}
	
	public Utenti getUtenteId(int idUtente) {
		Query q2 = entitymanager.createQuery("select u from Utenti u where u.id=:num");
		q2.setParameter("num", idUtente);
		Utenti u = (Utenti) q2.getSingleResult();
		return u;
	}
	
	public List<Utenti> getUtenteNC(String nome,String cognome){
		Query q2 = entitymanager.createQuery("select u from Utenti u where u.nome=:nom and u.cognome=:cog");
		q2.setParameter("nom", nome);
		q2.setParameter("cog", cognome);
		List<Utenti> lu = q2.getResultList();
		return lu;
	}
	
	public void segui(int idUtente, int idProfiloSeguito) {
		
		entitymanager.getTransaction( ).begin( );
		
		ProfiloU pu = new ProfiloU();
		Utenti u = getUtenteId(idUtente);
		Profilo p = pu.getProfilo(idProfiloSeguito);
		//List<Profilo> lp = new LinkedList<Profilo>();
		//lp.add(p);
		u.setProfiliSeguiti(p);
		//u.setProfiliSeguiti(lp);
		
		entitymanager.persist(u);
		entitymanager.getTransaction( ).commit();
		
	}
	
	public void smettiSeguire(int idUtente, int idProfiloSeguito) {
		
		entitymanager.getTransaction( ).begin( );
		Query q = entitymanager.createNativeQuery("delete from instagram.follower  where id_seguito=? and id_seguo=?");
		q.setParameter(1, idProfiloSeguito);
		q.setParameter(2, idUtente);
		q.executeUpdate();
		
		/*Utenti u = getUtenteId(idUtente);
		ProfiloU pu = new ProfiloU();
		Profilo p = pu.getProfilo(idProfiloSeguito);
		u.eliminaProfiloSeguito(p);
		System.out.println(u.getProfiliSeguiti());
		u.eliminaProfiloSeguito(p);
		System.out.println(u.getProfiliSeguiti());
		entitymanager.persist(u);*/
		entitymanager.getTransaction( ).commit();
	}

}
