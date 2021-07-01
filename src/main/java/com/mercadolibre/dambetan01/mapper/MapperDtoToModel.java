package com.mercadolibre.dambetan01.mapper;

public interface MapperDtoToModel <T, S>{
    public T dtoToModel(S dto);
}
