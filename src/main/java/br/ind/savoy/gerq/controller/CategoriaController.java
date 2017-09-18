package br.ind.savoy.gerq.controller;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.model.Categoria;
import br.ind.savoy.gerq.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<?> getCategorias(@RequestBody PaginationBean pagination) {
		try{
			return new ResponseEntity<>(categoriaService.getListPagination(pagination), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getCategoriaById(@PathVariable int id) {
		try{
			return new ResponseEntity<>(categoriaService.getCategoria(id), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addCategoria(@RequestBody Categoria categoria) { categoriaService.addCategoria(categoria); }

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void updateCountry(@RequestBody Categoria categoria) {
		categoriaService.updateCategoria(categoria);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteCountry(@PathVariable("id") int id) {
		categoriaService.deleteCategoria(id);
	}	
}
