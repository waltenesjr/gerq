package br.ind.savoy.gerq.service;

import java.util.List;

import br.ind.savoy.gerq.bean.PaginationBean;
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
	public Categoria getCategoria(int id) {
		return dao.getCategoria(id);
	}

	@Transactional
	public void addCategoria(Categoria categoria) {
		dao.addCategoria(categoria);
	}

	@Transactional
	public void updateCategoria(Categoria categoria) {
		dao.updateCategoria(categoria);
	}

	@Transactional
	public void deleteCategoria(int id) {
		dao.deleteCategoria(id);
	}
}
