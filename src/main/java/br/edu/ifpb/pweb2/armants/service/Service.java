package br.edu.ifpb.pweb2.armants.service;

import java.util.List;

public interface Service<T, ID> {

    List<T> findAll();

    T findById(ID id);

    T save(T t);

}
