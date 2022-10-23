package com.rentcar.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO implements Serializable {

    private Long id;
    private String titulo;
    private String descripcion;
    private String urlImagen;
}
