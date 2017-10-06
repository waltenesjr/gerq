package br.ind.savoy.gerq.service;

import br.ind.savoy.gerq.hibernate.HibernateDAO;
import br.ind.savoy.gerq.model.Perigo;
import br.ind.savoy.gerq.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("perigoService")
public class PerigoService {

	@Autowired
	private HibernateDAO dao;

	public void addList(List<Perigo> perigos, Produto produto){
		for (Perigo p : perigos){
			p.setProduto(produto);
			dao.persist(p);
		}
	}
}
