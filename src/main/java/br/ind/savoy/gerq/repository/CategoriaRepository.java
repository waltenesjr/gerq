package br.ind.savoy.gerq.repository;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.bean.SelectBean;
import br.ind.savoy.gerq.hibernate.HibernateDAO;
import br.ind.savoy.gerq.model.Categoria;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaRepository {

	@Autowired
	private HibernateDAO dao;

	public List<Categoria> getListPagination(PaginationBean pagination) {
		Criteria criteria = dao.createCriteria(Categoria.class);
		addRestrictionPagination(pagination, criteria);
		criteria.setFirstResult(pagination.getStart());
		criteria.setMaxResults(pagination.getEnd());
		return criteria.list();
	}

	public Long getCountPagination(PaginationBean pagination) {
		Criteria criteria = dao.createCriteria(Categoria.class);
		addRestrictionPagination(pagination, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	public List<SelectBean> getListSelect() {
		final String hql = "select new " + SelectBean.class.getName() + "(c.id, c.descricao) from Categoria c";
		List<SelectBean> all = dao.createQuery(hql).list();
		return all;
	}

	private void addRestrictionPagination(PaginationBean pagination, Criteria criteria) {
		if (pagination.existe("descricao"))
			criteria.add(Restrictions.like("descricao", pagination.getField("descricao").getValue(), MatchMode.ANYWHERE));
	}
}
