package model.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static Session session;
	public static String erro;

	public static Session abrirSession() {
		try {
			SessionFactory sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			return session;
		} catch (Throwable e) {
			erro = "Erro ao conectar o BD:\n " + e.toString();
			return null;
		}
	}
	
	public static void fecharSession() {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}
}
