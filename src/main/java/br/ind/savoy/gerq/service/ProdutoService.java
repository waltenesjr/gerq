package br.ind.savoy.gerq.service;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.dao.ProdutoDAO;
import br.ind.savoy.gerq.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("produtoService")
public class ProdutoService {

	@Autowired
	ProdutoDAO dao;

	@Transactional
	public PaginationBean getListPagination(PaginationBean pagination) {
		pagination.setList(dao.getListPagination(pagination));
		pagination.setTotalResults(dao.getCountPagination(pagination));
		return pagination;
	}

	@Transactional
	public Produto get(int id) {
		return dao.get(id);
	}

	@Transactional
	public void add(Produto produto) {
		dao.add(produto);
	}

	@Transactional
	public void update(Produto produto) {
		dao.update(produto);
	}

	@Transactional
	public void delete(int id) {
		dao.delete(id);
	}
}
