package br.ind.savoy.gerq.controller;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.model.Produto;
import br.ind.savoy.gerq.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	ProdutoService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<?> getList(@RequestBody PaginationBean pagination) {
		PaginationBean resultList = service.getListPagination(pagination);
		try{
			return new ResponseEntity<>(resultList, HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable int id) {
		Produto result = service.get(id);
		try{
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<?> getByName(@PathVariable String name) {
		List<Produto> resultList = service.findByName(name);
		try{
			return new ResponseEntity<>(resultList, HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public void add(@RequestBody Produto produto) { service.add(produto); }

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void update(@RequestBody Produto produto) {
		service.update(produto);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") int id) {
		service.delete(id);
	}	
}
