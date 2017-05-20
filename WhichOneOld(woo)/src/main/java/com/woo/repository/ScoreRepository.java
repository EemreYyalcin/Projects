package com.woo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.woo.domain.Score;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Long> {

}
