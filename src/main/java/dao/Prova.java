package dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entity.Foto;
import Entity.Utenti;

public class Prova {

	public static void main(String[] args) {

//		 Iscrizione isc = new Iscrizione();
//		 isc.iscrizione("Matte", "Calomino", "mpc", "12345");
//
//		Login login = new Login();
//		Utenti u = login.login("mpc", "12345");
//
//		System.out.println(u.getId());
//
//		Utente ut = new Utente();
//		u = ut.getUtenteId(27);
//
//		System.out.println(u.getId());
//
//		 ut.smettiSeguire(25, 30);
//
//		 FotoU fu = new FotoU();
//		 fu.aggFoto("foto bbbb", 1);
//		 fu.rimuoviFoto(13);

		 LikeU lu = new LikeU();
		 lu.mettiLike(1, 1);
//		 lu.togliLike(12, 26);
//
//		ProfiloU pu = new ProfiloU();
//		List<Foto> lf = pu.fotoSeguiti(25);
//
//		System.out.println(lf);
//		System.out.println("ciao");
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("hibe");
//		System.out.println("ciao");

	}

}
