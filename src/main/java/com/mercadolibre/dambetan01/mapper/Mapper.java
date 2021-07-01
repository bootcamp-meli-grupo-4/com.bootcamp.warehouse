package com.mercadolibre.dambetan01.mapper;

public interface Mapper <T, S>{
    public T dtoToModel(S dto);
    public S modelToDto(T model);
}
