package br.ind.savoy.gerq.dao;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.model.Categoria;
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

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<Categoria> getListPagination(PaginationBean pagination) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Produto p" + pagination
				.where()
				.presente("nome", "p.nome like :nome")
				.presente("categoria", "p.categoria.id = :idCategoria")
				.build();
		Query query = session.createQuery(hql);
		if (pagination.existe("nome")) {
			query.setParameter("nome", "%" + pagination.getField("nome").getValue() + "%");
		} else if (pagination.existe("categoria")) {
			query.setParameter("idCategoria", Integer.valueOf(pagination.getField("categoria").getValue()));
		}
		query.setFirstResult(pagination.getStart());
		query.setMaxResults(pagination.getEnd());
		return query.list();
	}

	public Long getCountPagination(PaginationBean pagination) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select count(*) from Produto p" + pagination
				.where()
				.presente("nome", "p.nome like :nome")
				.presente("categoria", "p.categoria.id = :idCategoria")
				.build();
		Query query = session.createQuery(hql);
		if (pagination.existe("nome")) {
			query.setParameter("nome", "%" + pagination.getField("nome").getValue() + "%");
		} else if (pagination.existe("categoria")) {
			query.setParameter("idCategoria", Integer.valueOf(pagination.getField("categoria").getValue()));
		}
		return (Long) query.uniqueResult();
	}

	public Produto get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Produto produto = (Produto) session.get(Produto.class, id);
		return produto;
	}

	public Produto add(Produto produto) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(produto);
		return produto;
	}

	public void update(Produto produto) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(produto);
	}

	public void delete(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Produto p = (Produto) session.load(Produto.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}	
}
