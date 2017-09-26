package br.ind.savoy.gerq.service;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.dao.PerigoDAO;
import br.ind.savoy.gerq.dao.ProdutoDAO;
import br.ind.savoy.gerq.model.Perigo;
import br.ind.savoy.gerq.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("produtoService")
public class ProdutoService {

	@Autowired
	ProdutoDAO dao;

	@Autowired
	PerigoDAO perigoDAO;

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
	public List<Produto> all() {
		return dao.all();
	}

	@Transactional
	public List<Produto> findByName(String name) {
		return dao.findByNome(name);
	}

	@Transactional
	public void add(Produto produto) {
		List<Perigo> perigos = new ArrayList<>();
		perigos.addAll(produto.getPerigos());
		produto.getPerigos().clear();
		dao.add(produto);
		perigoDAO.addAll(perigos, produto);
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
