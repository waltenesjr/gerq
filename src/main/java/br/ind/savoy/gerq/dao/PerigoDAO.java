package br.ind.savoy.gerq.dao;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.model.Perigo;
import br.ind.savoy.gerq.model.Produto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PerigoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Perigo> getListPagination(PaginationBean pagination) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(*) from Perigo p" + pagination
				.where()
				.presente("produto", "p.produto.nome like :produto")
				.build();
		Query query = session.createQuery(hql);
		if (pagination.existe("produto")) {
			query.setParameter("produto", "%" + pagination.getField("produto").getValue() + "%");
		}
		query.setFirstResult(pagination.getStart());
		query.setMaxResults(pagination.getEnd());
		return query.list();
	}

	public Long getCountPagination(PaginationBean pagination) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(*) from Perigo p" + pagination
				.where()
				.presente("produto", "p.produto.nome like :produto")
				.build();
		Query query = session.createQuery(hql);
		if (pagination.existe("produto")) {
			query.setParameter("produto", "%" + pagination.getField("produto").getValue() + "%");
		}
		return (Long) query.uniqueResult();
	}

	public Perigo get(int id) {
		Session session = sessionFactory.getCurrentSession();
		Perigo perigo = (Perigo) session.get(Perigo.class, id);
		return perigo;
	}

	public List<Perigo> all() {
		Session session = sessionFactory.getCurrentSession();
		List<Perigo> all = session.createQuery("from Perigo").list();
		return all;
	}

	public Perigo add(Perigo perigo) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(perigo);
		return perigo;
	}

	public void addAll(List<Perigo> perigos, Produto produto){
		for (Perigo p : perigos){
			p.setProduto(produto);
			add(p);
		}
	}

	public void update(Perigo perigo) {
		Session session = sessionFactory.getCurrentSession();
		session.update(perigo);
	}

	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Perigo p = (Perigo) session.load(Perigo.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}
}
