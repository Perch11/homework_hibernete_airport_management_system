package com.airport.repository.common;

import java.util.Set;

public interface CommonRepository<T> {


    T getBy(int id);


    Set<T> getAll();

    Set<T> get(int offset, int perPage, String sort);

    T save(T item);

   // boolean updateBy(int id, T item);

    boolean deleteBy(int id);
}