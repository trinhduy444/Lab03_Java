package org.example.Utils;

import org.example.Domain.Phone;

import java.util.List;

public interface Services<T> {
    boolean add(T p);

    T get(String id);

    List<T> getAll();

    boolean remove(String id);

    boolean remove(T p);


    boolean update(T p);
}
