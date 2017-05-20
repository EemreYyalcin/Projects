package com.woo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.woo.domain.QuestionScore;

@Repository
public interface QuestionScoreRepository extends CrudRepository<QuestionScore, Long> {


}
