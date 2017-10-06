package br.ind.savoy.gerq.service;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.bean.SelectBean;
import br.ind.savoy.gerq.hibernate.HibernateDAO;
import br.ind.savoy.gerq.repository.EmpresaRepository;
import br.ind.savoy.gerq.model.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("empresaService")
public class EmpresaService {

	@Autowired
	private HibernateDAO dao;

	@Autowired
	EmpresaRepository repository;

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
	public Empresa get(int id) {
		return (Empresa) dao.get(Empresa.class, id);
	}

	@Transactional
	public void add(Empresa empresa) {
		dao.persist(empresa);
	}

	@Transactional
	public void update(Empresa empresa) {
		dao.update(empresa);
	}

	@Transactional
	public void delete(int id) {
		dao.delete(Empresa.class, id);
	}
}
