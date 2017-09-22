package br.ind.savoy.gerq.dao;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.model.Empresa;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpresaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<Empresa> getListPagination(PaginationBean pagination) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Empresa e" + pagination
				.where()
				.presente("nome", "e.nome like :nome")
				.build();
		Query query = session.createQuery(hql);
		if (pagination.existe("nome")) {
			query.setParameter("nome", "%" + pagination.getField("nome").getValue() + "%");
		}
		query.setFirstResult(pagination.getStart());
		query.setMaxResults(pagination.getEnd());
		return query.list();
	}

	public Long getCountPagination(PaginationBean pagination) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select count(*) from Empresa e" + pagination
				.where()
				.presente("nome", "e.nome like :nome")
				.build();
		Query query = session.createQuery(hql);
		if (pagination.existe("nome")) {
			query.setParameter("nome", "%" + pagination.getField("nome").getValue() + "%");
		}
		return (Long) query.uniqueResult();
	}

	public List<Empresa> all() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Empresa> all = session.createQuery("from Empresa").list();
		return all;
	}

	public Empresa get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Empresa empresa = (Empresa) session.get(Empresa.class, id);
		return empresa;
	}

	public Empresa add(Empresa empresa) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(empresa);
		return empresa;
	}

	public void update(Empresa empresa) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(empresa);
	}

	public void delete(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Empresa e = (Empresa) session.load(Empresa.class, new Integer(id));
		if (null != e) {
			session.delete(e);
		}
	}
}
