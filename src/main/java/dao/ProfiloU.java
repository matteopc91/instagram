package dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entity.Foto;
import Entity.Profilo;
import Entity.Utenti;

public class ProfiloU {
	
	private Connessione c;
	private EntityManager entitymanager;
	
	
	public ProfiloU () {
		
		c=new Connessione();
		entitymanager=c.getEntitymanager();
		
	}
	
	public Profilo getProfilo(int idProfilo){
		
		Query q2 = entitymanager.createQuery("select p from Profilo p where p.id=:num");
		q2.setParameter("num", idProfilo);
		Profilo p = (Profilo) q2.getSingleResult();
		return p;

	}
	
	public List<Foto> fotoSeguiti (int idUtente){
		
		Utente utente = new Utente();
		FotoU fu =new FotoU();
		Foto f = new Foto();
		Utenti u = utente.getUtenteId(idUtente);
		Query q = entitymanager.createNativeQuery("select f.id from (select fl.* from instagram.follower fl where fl.id_seguo=?) g,instagram.foto f where g.id_seguito=f.profilo;");
		q.setParameter(1, idUtente);
		List<Integer> l = q.getResultList();
		List<Foto> lf = new LinkedList<Foto>();
		for(int i : l) {
			 f=fu.getFoto(i);
			 lf.add(f);
			
		}
		return lf;

	}

}
