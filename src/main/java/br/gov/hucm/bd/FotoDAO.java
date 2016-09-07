package br.gov.hucm.bd;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.gov.hucm.entidades.Foto;
import br.gov.hucm.util.HibernateUtil;

public class FotoDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("usuarios");
	private EntityManager em = factory.createEntityManager();
	protected Session session;

	public void save(Foto f) {
		session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.getTransaction().begin();
			session.save(f);
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}
	

    @SuppressWarnings("unchecked")
    public List<Foto> listarFotosCandidatos() {
        session = HibernateUtil.getSessionFactory().openSession();
 

		try {
			List<Foto> foto = (List<Foto>) em.createQuery("from Foto u").getResultList();
			return foto;
		} catch (NoResultException e) {
			return null;
		}
    }
}