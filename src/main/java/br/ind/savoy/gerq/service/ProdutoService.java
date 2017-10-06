package br.ind.savoy.gerq.service;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.hibernate.HibernateDAO;
import br.ind.savoy.gerq.repository.PerigoRepository;
import br.ind.savoy.gerq.repository.ProdutoRepository;
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
	private HibernateDAO dao;

	@Autowired
	ProdutoRepository repository;

	@Autowired
	PerigoService perigoService;

	@Transactional
	public PaginationBean getListPagination(PaginationBean pagination) {
		pagination.setList(repository.getListPagination(pagination));
		pagination.setTotalResults(repository.getCountPagination(pagination));
		return pagination;
	}

	@Transactional
	public Produto get(int id) {
		return (Produto) dao.get(Produto.class, id);
	}

	@Transactional
	public List<Produto> all() {
		return dao.createCriteria(Produto.class).list();
	}

	@Transactional
	public List<Produto> findByName(String name) {
		return repository.findByNome(name);
	}

	@Transactional
	public void add(Produto produto) {
		List<Perigo> perigos = new ArrayList<>();
		perigos.addAll(produto.getPerigos());
		produto.getPerigos().clear();
		dao.persist(produto);
		perigoService.addList(perigos, produto);
	}

	@Transactional
	public void update(Produto produto) {
		dao.update(produto);
	}

	@Transactional
	public void delete(int id) {
		dao.delete(Produto.class, id);
	}
}
