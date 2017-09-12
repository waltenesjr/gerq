package br.ind.savoy.gerq.dao;

import java.util.List;

import br.ind.savoy.gerq.model.Categoria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<Categoria> getAllCategorias() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Categoria> categoriaList = session.createQuery("from Categoria").list();
		return categoriaList;
	}

	public Categoria getCategoria(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Categoria categoria = (Categoria) session.load(Categoria.class, new Integer(id));
		return categoria;
	}

	public Categoria addCategoria(Categoria categoria) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(categoria);
		return categoria;
	}

	public void updateCategoria(Categoria categoria) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(categoria);
	}

	public void deleteCategoria(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Categoria c = (Categoria) session.load(Categoria.class, new Integer(id));
		if (null != c) {
			session.delete(c);
		}
	}	
}
