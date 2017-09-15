package br.ind.savoy.gerq.controller;

import java.util.List;

import br.ind.savoy.gerq.model.Categoria;
import br.ind.savoy.gerq.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@RequestMapping(value = "/categorias", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Categoria> getCountries() {
		List<Categoria> listCategorias = categoriaService.getAllCategorias();
		return listCategorias;
	}

	@RequestMapping(value = "/getCategoria/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Categoria getCategoriaById(@PathVariable int id) {
		return categoriaService.getCategoria(id);
	}

	@RequestMapping(value = "/addCategoria", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addCategoria(@RequestBody Categoria categoria) {
		categoriaService.addCategoria(categoria);
	}

	@RequestMapping(value = "/updateCategoria", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void updateCountry(@RequestBody Categoria categoria) {
		categoriaService.updateCategoria(categoria);
	}

	@RequestMapping(value = "/deleteCategoria/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteCountry(@PathVariable("id") int id) {
		categoriaService.deleteCategoria(id);
	}	
}
