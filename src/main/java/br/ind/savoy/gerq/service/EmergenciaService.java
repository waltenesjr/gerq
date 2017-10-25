package br.ind.savoy.gerq.service;

import br.ind.savoy.gerq.hibernate.HibernateDAO;
import br.ind.savoy.gerq.model.Emergencia;
import br.ind.savoy.gerq.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("emergenciaService")
public class EmergenciaService {

	@Autowired
	private HibernateDAO dao;

	public void addList(List<Emergencia> list, Produto produto){
		for (Emergencia e : list){
			e.setProduto(produto);
			dao.persist(e);
		}
	}
}
