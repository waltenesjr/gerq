package br.ind.savoy.gerq.controller;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.model.Produto;
import br.ind.savoy.gerq.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	ProdutoService produtoService;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<?> getList(@RequestBody PaginationBean pagination) {
		try{
			return new ResponseEntity<>(produtoService.getListPagination(pagination), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getById(@PathVariable int id) {
		try{
			return new ResponseEntity<>(produtoService.get(id), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public void add(@RequestBody Produto produto) { produtoService.add(produto); }

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void update(@RequestBody Produto produto) {
		produtoService.update(produto);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") int id) {
		produtoService.delete(id);
	}	
}
