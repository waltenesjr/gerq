package br.ind.savoy.gerq.dao;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.bean.SelectBean;
import br.ind.savoy.gerq.model.Categoria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Categoria> getListPagination(PaginationBean pagination) {
		Session session = sessionFactory.getCurrentSession();
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
		Session session = sessionFactory.getCurrentSession();
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

	public Categoria get(int id) {
		Session session = sessionFactory.getCurrentSession();
		Categoria categoria = (Categoria) session.get(Categoria.class, id);
		return categoria;
	}

	public List<SelectBean> getListSelect() {
		final String hql = "select new " + SelectBean.class.getName() + "(c.id, c.descricao) from Categoria c";
		Session session = sessionFactory.getCurrentSession();
		List<SelectBean> all = session.createQuery(hql).list();
		return all;
	}

	public List<Categoria> all() {
		Session session = sessionFactory.getCurrentSession();
		List<Categoria> all = session.createQuery("from Categoria").list();
		return all;
	}

	public Categoria add(Categoria categoria) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(categoria);
		return categoria;
	}

	public void update(Categoria categoria) {
		Session session = sessionFactory.getCurrentSession();
		session.update(categoria);
	}

	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Categoria c = (Categoria) session.load(Categoria.class, new Integer(id));
		if (null != c) {
			session.delete(c);
		}
	}
}
