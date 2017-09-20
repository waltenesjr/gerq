package br.ind.savoy.gerq.service;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.dao.EmpresaDAO;
import br.ind.savoy.gerq.model.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("empresaService")
public class EmpresaService {

	@Autowired
	EmpresaDAO dao;

	@Transactional
	public PaginationBean getListPagination(PaginationBean pagination) {
		pagination.setList(dao.getListPagination(pagination));
		pagination.setTotalResults(dao.getCountPagination(pagination));
		return pagination;
	}

	@Transactional
	public Empresa get(int id) {
		return dao.get(id);
	}

	@Transactional
	public void add(Empresa empresa) {
		dao.add(empresa);
	}

	@Transactional
	public void update(Empresa empresa) {
		dao.update(empresa);
	}

	@Transactional
	public void delete(int id) {
		dao.delete(id);
	}
}
