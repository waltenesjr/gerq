package br.ind.savoy.gerq.controller;

import br.ind.savoy.gerq.bean.PaginationBean;
import br.ind.savoy.gerq.model.Perigo;
import br.ind.savoy.gerq.service.PerigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perigo")
public class PerigoController {
	
	@Autowired
	PerigoService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<?> getList(@RequestBody PaginationBean pagination) {
		try{
			return new ResponseEntity<>(service.getListPagination(pagination), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> get(@PathVariable int id) {
		try{
			return new ResponseEntity<>(service.get(id), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> all() {
		try{
			return new ResponseEntity<>(service.all(), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public void add(@RequestBody Perigo perigo) { service.add(perigo); }

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void update(@RequestBody Perigo perigo) {
		service.update(perigo);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") int id) {
		service.delete(id);
	}	
}
