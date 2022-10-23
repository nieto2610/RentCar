package com.rentcar.service;

import java.io.Serializable;
import java.util.Set;

public interface IBaseService<E, ID extends Serializable> {

    public Set<E> listar();

    public E buscar(ID id);

    public E guardar(E entity);

    public E actualizar(E entity);

    public void eliminar(ID id);


}

