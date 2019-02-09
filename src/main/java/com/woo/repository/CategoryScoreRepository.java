package com.woo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.woo.domain.CategoryScore;

@Repository
public interface CategoryScoreRepository extends CrudRepository<CategoryScore, Long> {

}
