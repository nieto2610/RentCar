package com.rentcar.controller;

import com.rentcar.DTOS.CategoriaDTO;
import com.rentcar.exceptions.ResourceNotFoundException;
import com.rentcar.service.ICategoriaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/categoria")

public class CategoriaController {
    final static Logger logger = LogManager.getLogger(CategoriaController.class);

    private ICategoriaService categoriaService;


    public CategoriaController( ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> guardar(@RequestBody CategoriaDTO categoriaDTO) {
        logger.info("Estamos guardando la nueva categoria: " + categoriaDTO.getTitulo());
        return new ResponseEntity<>(categoriaService.guardar(categoriaDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscar(@PathVariable("id") Long id) {
        ResponseEntity<CategoriaDTO> response;
        if (id != 0 && categoriaService.buscar(id) != null) {
            response = ResponseEntity.ok(categoriaService.buscar(id));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }


    @GetMapping
    public ResponseEntity<Set<CategoriaDTO>> listar() throws ResourceNotFoundException {
        logger.info("Estamos Listando las categorias");
        if (!categoriaService.listar().isEmpty()) {
            return ResponseEntity.ok(categoriaService.listar());
        } else {
            throw new ResourceNotFoundException("No hay categorias para listar");
        }
    }

    @PutMapping
    public ResponseEntity<CategoriaDTO> actualizar(@RequestBody CategoriaDTO categoriaDTO) {
        ResponseEntity<CategoriaDTO> response;
        if ( categoriaService.buscar(categoriaDTO.getId()) != null) {
            response = ResponseEntity.ok(categoriaService.actualizar(categoriaDTO));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable Long id) {
        logger.info("Estamos borrando la categoria con id: "+ id);
        ResponseEntity<HttpStatus> response;
        if (id != 0 && categoriaService.buscar(id) != null) {
            categoriaService.eliminar(id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }


}
