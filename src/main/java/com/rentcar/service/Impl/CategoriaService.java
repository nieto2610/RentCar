package com.rentcar.service.Impl;

import com.rentcar.DTOS.CategoriaDTO;
import com.rentcar.model.Categoria;
import com.rentcar.repository.ICategoriaRepository;
import com.rentcar.service.ICategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoriaService implements ICategoriaService {

    private final ICategoriaRepository categoriaRepository;
    private final ObjectMapper mapper;

    public CategoriaService(ICategoriaRepository categoriaRepository, ObjectMapper mapper) {
        this.categoriaRepository = categoriaRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public CategoriaDTO guardar(CategoriaDTO categoriaDTO) {
        Categoria categoria = mapper.convertValue(categoriaDTO, Categoria.class);
        categoriaRepository.save(categoria);
        Optional<Categoria> categoriaResponse = categoriaRepository.findById(categoria.getId());
        return mapper.convertValue(categoriaResponse, CategoriaDTO.class);

    }

    @Override
    @Transactional
    public Set<CategoriaDTO> listar() {
        List<Categoria> categorias = categoriaRepository.findAll();
        Set<CategoriaDTO> categoriaDTOS = new HashSet<>();

        for (Categoria categoria : categorias) {
            categoriaDTOS.add(mapper.convertValue(categoria, CategoriaDTO.class));
        }
        return categoriaDTOS;
    }

    @Override
    @Transactional
    public CategoriaDTO buscar(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        CategoriaDTO categoriaDTO = null;
        if (categoria.isPresent()) {
            categoriaDTO = mapper.convertValue(categoria, CategoriaDTO.class);
        }
        return categoriaDTO;
    }

    @Override
    @Transactional
    public CategoriaDTO actualizar(CategoriaDTO categoriaDTO) {
        Categoria categoria = mapper.convertValue(categoriaDTO, Categoria.class);
        categoriaRepository.save(categoria);
        Optional<Categoria> categoriaResponse = categoriaRepository.findById(categoria.getId());
        return mapper.convertValue(categoriaResponse, CategoriaDTO.class);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}
