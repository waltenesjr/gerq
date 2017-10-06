package br.ind.savoy.gerq.repository;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.hibernate.HibernateDAO;
import br.ind.savoy.gerq.model.Produto;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepository {

	@Autowired
	private HibernateDAO dao;

	public List<Produto> getListPagination(PaginationBean pagination) {
		Criteria criteria = dao.createCriteria(Produto.class);
		if (pagination.existe("nome"))
			criteria.add(Restrictions.like("nome", pagination.getField("nome").getValue(), MatchMode.ANYWHERE));
		if (pagination.getSort().getField() != null)
			criteria.addOrder(pagination.getSort().getDirection().equals("asc")
							? Order.asc(pagination.getSort().getField())
							: Order.desc(pagination.getSort().getField()));
		criteria.setFirstResult(pagination.getStart());
		criteria.setMaxResults(pagination.getEnd());
		return criteria.list();
	}

	public Long getCountPagination(PaginationBean pagination) {
		Criteria criteria = dao.createCriteria(Produto.class);
		if (pagination.existe("nome"))
			criteria.add(Restrictions.like("nome", pagination.getField("nome").getValue(), MatchMode.ANYWHERE));
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	public List<Produto> findByNome(String name) {
		Criteria criteria = dao.createCriteria(Produto.class)
				.add(Restrictions.like("nome", name, MatchMode.ANYWHERE));
		return criteria.list();
	}
}
