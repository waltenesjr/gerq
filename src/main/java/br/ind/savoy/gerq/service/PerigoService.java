package br.ind.savoy.gerq.service;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.dao.PerigoDAO;
import br.ind.savoy.gerq.model.Perigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("perigoService")
public class PerigoService {

	@Autowired
	PerigoDAO dao;

	@Transactional
	public PaginationBean getListPagination(PaginationBean pagination) {
		pagination.setList(dao.getListPagination(pagination));
		pagination.setTotalResults(dao.getCountPagination(pagination));
		return pagination;
	}

	@Transactional
	public Perigo get(int id) {
		return dao.get(id);
	}

	@Transactional
	public List<Perigo> all() {
		return dao.all();
	}

	@Transactional
	public void add(Perigo perigo) {
		dao.add(perigo);
	}

	@Transactional
	public void update(Perigo perigo) {
		dao.update(perigo);
	}

	@Transactional
	public void delete(int id) {
		dao.delete(id);
	}
}
