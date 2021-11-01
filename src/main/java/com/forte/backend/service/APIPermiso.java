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
import com.forte.backend.dao.IPermiso;
import com.forte.backend.entity.Permiso;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/permiso")
public class APIPermiso {

	@Autowired
	IPermiso dao;
	
	@GetMapping
	public ResponseEntity<List<Permiso>> getPermisos() {
		// Los id's en Hibernate tienen que manejarse como long

		System.out.println("SELECT");

		try {
			List<Permiso> permiso = dao.findAll();
			if (permiso.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(permiso, HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Permiso>> getPermiso(@PathVariable("id") Long idpath) {
		System.out.println(idpath);
		try {
			Optional<Permiso> permiso = dao.findById(idpath);
			
			if (permiso.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(permiso, HttpStatus.OK);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping //INSERT
	public ResponseEntity<Permiso> setPermiso(@RequestBody Permiso per) {
		System.out.println("INSERT");
		
		try {
			Permiso permisoGuardado = dao.save(per);
			return new ResponseEntity<>(permisoGuardado, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Permiso> updatePermiso(@PathVariable("id") Long idpath, @RequestBody Permiso per) { 
		System.out.println("UPDATE");
		
		
		try {
			Optional<Permiso> permisoRecibido = dao.findById(idpath);
			if (permisoRecibido.isPresent()) {
				per.setId(idpath);
				Permiso permiso = dao.save(per);
				return new ResponseEntity<>(permiso, HttpStatus.OK);
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
	public ResponseEntity<Permiso> deletePermiso(@PathVariable("id") Long idpath) {
		System.out.println("DELETE");
		System.out.println("path: " + idpath);

		try {
			Optional<Permiso> permisoRecibido = dao.findById(idpath);
			if (permisoRecibido.isPresent()) {
				dao.delete(permisoRecibido.get());
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
