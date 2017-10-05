package br.ind.savoy.gerq.service;

import java.util.List;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.bean.SelectBean;
import br.ind.savoy.gerq.dao.CategoriaDAO;
import br.ind.savoy.gerq.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("categoriaService")
public class CategoriaService {

	@Autowired
	CategoriaDAO dao;

	@Transactional
	public PaginationBean getListPagination(PaginationBean pagination) {
		pagination.setList(dao.getListPagination(pagination));
		pagination.setTotalResults(dao.getCountPagination(pagination));
		return pagination;
	}

	@Transactional
	public List<SelectBean> getListSelect() {
		return dao.getListSelect();
	}

	@Transactional
	public Categoria get(int id) {
		return dao.get(id);
	}

	@Transactional
	public List<Categoria> all() {
		return dao.all();
	}

	@Transactional
	public void add(Categoria categoria) {
		dao.add(categoria);
	}

	@Transactional
	public void update(Categoria categoria) {
		dao.update(categoria);
	}

	@Transactional
	public void delete(int id) {
		dao.delete(id);
	}
}
