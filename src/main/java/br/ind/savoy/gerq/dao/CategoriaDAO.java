package br.ind.savoy.gerq.dao;

import java.util.List;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.model.Categoria;
import org.hibernate.Query;
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

	public List<Categoria> getListPagination(PaginationBean pagination) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Categoria c" + pagination
				.where()
				.presente("descricao", "c.descricao like :descricao")
				.build();
		Query query = session.createQuery(hql);
		if (pagination.existe("descricao")) {
			query.setParameter("descricao", "%" + pagination.getField("descricao").getValue() + "%");
		}
		query.setFirstResult(pagination.getStart());
		query.setMaxResults(pagination.getEnd());
		return query.list();
	}

	public Long getCountPagination(PaginationBean pagination) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select count(*) from Categoria c" + pagination
				.where()
				.presente("descricao", "c.descricao like :descricao")
				.build();
		Query query = session.createQuery(hql);
		if (pagination.existe("descricao")) {
			query.setParameter("descricao", "%" + pagination.getField("descricao").getValue() + "%");
		}
		return (Long) query.uniqueResult();
	}

	public Categoria getCategoria(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Categoria categoria = (Categoria) session.get(Categoria.class, id);
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
