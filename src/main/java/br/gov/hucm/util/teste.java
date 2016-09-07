package br.gov.hucm.util;

import org.hibernate.Session;

import br.gov.hucm.entidades.Foto;

public class teste {
	 
	public static void main(String[] args) {
 
		Session session =  HibernateUtil.getSessionFactory().openSession();
 
	Foto foto = new Foto();
		foto.setNomeFoto("Maria"); 
 
		//realizando operação para salvar no banco
		session.beginTransaction();
		session.save(foto);
		session.getTransaction().commit();
		session.close();
	}
}