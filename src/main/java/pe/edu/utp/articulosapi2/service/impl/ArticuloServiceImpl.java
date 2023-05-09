package pe.edu.utp.articulosapi2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.utp.articulosapi2.entity.Articulo;
import pe.edu.utp.articulosapi2.repository.ArticuloRepository;
import pe.edu.utp.articulosapi2.service.ArticuloService;

@Service
public class ArticuloServiceImpl implements ArticuloService {

	@Autowired
	private ArticuloRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findByNombre(String nombre, Pageable page) {
		try {
			return repository.findByNombreContaining(nombre,page);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Articulo findById(int id) {
		try {
			return repository.findById(id).orElseThrow();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Articulo save(Articulo articulo) {
		try {
			return repository.save(articulo);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Articulo update(Articulo articulo) {
		try {
			Articulo registro=repository.findById(articulo.getId()).orElseThrow();
			registro.setNombre(articulo.getNombre());
			registro.setPrecio(articulo.getPrecio());
			return repository.save(registro);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Articulo registro=repository.findById(id).orElseThrow();
			repository.delete(registro);
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
}
