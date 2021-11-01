package com.forte.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.forte.backend.dao.ITipoPermiso;
import com.forte.backend.entity.TipoPermiso;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tipopermiso")
public class APITipoPermiso {
	
	@Autowired
	ITipoPermiso dao;
	
	@GetMapping
	public ResponseEntity<List<TipoPermiso>> getTipoPermisos() {

		System.out.println("SELECT");

		try {
			List<TipoPermiso> tipoPermiso = dao.findAll();
			if (tipoPermiso.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(tipoPermiso, HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<TipoPermiso>> getTipoPermiso(@PathVariable("id") Long idpath) {
		System.out.println(idpath);
		try {
			Optional<TipoPermiso> tipoPermiso = dao.findById(idpath);
			
			if (tipoPermiso.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(tipoPermiso, HttpStatus.OK);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping //INSERT
	public ResponseEntity<TipoPermiso> setTipoPermiso(@RequestBody TipoPermiso tipoPer) {
		System.out.println("INSERT");
		
		try {
			TipoPermiso tipoPermisoGuardado = dao.save(tipoPer);
			return new ResponseEntity<>(tipoPermisoGuardado, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TipoPermiso> updateTipoPermiso(@PathVariable("id") Long idpath, @RequestBody TipoPermiso tipoPer) { 
		System.out.println("UPDATE");
		
		
		try {
			Optional<TipoPermiso> tipoPermisoRecibido = dao.findById(idpath);
			if (tipoPermisoRecibido.isPresent()) {
				tipoPer.setId(idpath);
				TipoPermiso tipoPermiso = dao.save(tipoPer);
				return new ResponseEntity<>(tipoPermiso, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<TipoPermiso> deleteTipoPermiso(@PathVariable("id") Long idpath) {
		System.out.println("DELETE");
		System.out.println("path: " + idpath);

		try {
			Optional<TipoPermiso> tipoPermisoRecibido = dao.findById(idpath);
			if (tipoPermisoRecibido.isPresent()) {
				dao.delete(tipoPermisoRecibido.get());
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
