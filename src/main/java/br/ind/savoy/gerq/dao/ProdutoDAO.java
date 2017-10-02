package br.ind.savoy.gerq.dao;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.model.Produto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Produto> getListPagination(PaginationBean pagination) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Produto p" + pagination
				.where()
				.presente("nome", "p.nome like :nome")
				.build()
				.concat(pagination.getSort().getField() != null ? " order by " + pagination.getSort().getField() + " " + pagination.getSort().getDirection() : "");
		Query query = session.createQuery(hql);
		if (pagination.existe("nome")) {
			query.setParameter("nome", "%" + pagination.getField("nome").getValue() + "%");
		}
		query.setFirstResult(pagination.getStart());
		query.setMaxResults(pagination.getEnd());
		return query.list();
	}

	public Long getCountPagination(PaginationBean pagination) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(*) from Produto p" + pagination
				.where()
				.presente("nome", "p.nome like :nome")
				.build();
		Query query = session.createQuery(hql);
		if (pagination.existe("nome")) {
			query.setParameter("nome", "%" + pagination.getField("nome").getValue() + "%");
		}
		return (Long) query.uniqueResult();
	}

	public List<Produto> findByNome(String name) {
		String hql = "from Produto p where p.nome like :nome";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("nome", "%" + name + "%");
		return query.list();
	}

	public Produto get(int id) {
		Session session = sessionFactory.getCurrentSession();
		Produto produto = (Produto) session.get(Produto.class, id);
		return produto;
	}

	public List<Produto> all() {
		Session session = sessionFactory.getCurrentSession();
		List<Produto> all = session.createQuery("from Produto").list();
		return all;
	}

	public Produto add(Produto produto) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(produto);
		return produto;
	}

	public void update(Produto produto) {
		Session session = sessionFactory.getCurrentSession();
		session.update(produto);
	}

	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Produto p = (Produto) session.load(Produto.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}
}
