package com.mercadolibre.dambetan01.mapper;

public interface MapperModelToDto <T,S>{
    public S modelToDto(T model);
}
