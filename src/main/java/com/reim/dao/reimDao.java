package com.reim.dao;

import java.util.ArrayList;

public interface reimDao <T> {
	ArrayList<T> getAll();
	T getByID(int ID);
	//void insert(T entity);
	void delete(T entity);
}
