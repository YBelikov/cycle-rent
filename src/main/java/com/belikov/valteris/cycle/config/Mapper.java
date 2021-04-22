package com.belikov.valteris.cycle.config;

public interface Mapper<D, E> {

    D mapEntityToDomain(E entity);

    E mapDomainToEntity(D domain);
}
