package com.woo.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.woo.domain.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

	Category findById(long id);

	ArrayList<Category> findByNameLike(String name);

	Iterable<Category> findByNameAndDecade(String name, int decade);

}
