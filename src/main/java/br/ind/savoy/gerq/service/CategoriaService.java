package br.ind.savoy.gerq.service;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.bean.SelectBean;
import br.ind.savoy.gerq.hibernate.HibernateDAO;
import br.ind.savoy.gerq.model.Categoria;
import br.ind.savoy.gerq.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoriaService")
public class CategoriaService {

	@Autowired
	private HibernateDAO dao;

	@Autowired
	CategoriaRepository repository;

	@Transactional
	public PaginationBean getListPagination(PaginationBean pagination) {
		pagination.setList(repository.getListPagination(pagination));
		pagination.setTotalResults(repository.getCountPagination(pagination));
		return pagination;
	}

	@Transactional
	public List<SelectBean> getListSelect() {
		return repository.getListSelect();
	}

	@Transactional
	public Categoria get(int id) {
		return (Categoria) dao.get(Categoria.class, id);
	}


	@Transactional
	public void add(Categoria categoria) {
		dao.persist(categoria);
	}

	@Transactional
	public void update(Categoria categoria) {
		dao.update(categoria);
	}

	@Transactional
	public void delete(int id) {
		dao.delete(Categoria.class, id);
	}
}
