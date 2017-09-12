package br.ind.savoy.gerq.service;

import java.util.List;

import br.ind.savoy.gerq.dao.CategoriaDAO;
import br.ind.savoy.gerq.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("categoriaService")
public class CategoriaService {

	@Autowired
	CategoriaDAO categoriaDao;
	
	@Transactional
	public List<Categoria> getAllCategorias() {
		return categoriaDao.getAllCategorias();
	}

	@Transactional
	public Categoria getCategoria(int id) {
		return categoriaDao.getCategoria(id);
	}

	@Transactional
	public void addCategoria(Categoria categoria) {
		categoriaDao.addCategoria(categoria);
	}

	@Transactional
	public void updateCategoria(Categoria categoria) {
		categoriaDao.updateCategoria(categoria);
	}

	@Transactional
	public void deleteCategoria(int id) {
		categoriaDao.deleteCategoria(id);
	}
}
