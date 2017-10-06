package br.ind.savoy.gerq.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Query createQuery(String query) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery (query);
	}

	public Criteria createCriteria(Class model) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(model);
	}

	public Object get(Class model, int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(model, id);
	}

	public void persist(Object model) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(model);
	}

	public void update(Object model) {
		Session session = sessionFactory.getCurrentSession();
		session.update(model);
	}

	public void delete(Class model, int id) {
		Session session = sessionFactory.getCurrentSession();
		Object obj = session.load(model, id);
		if (null != obj) {
			session.delete(obj);
		}
	}
}
