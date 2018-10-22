package br.ufba.fpc.desafio01.utils;

import java.util.List;

public interface ServiceInterface<T> {
	T getById(Long id);
	List<T> getAll();
	void save(T t);
	void update(T t);
	void remove(T t);
}
