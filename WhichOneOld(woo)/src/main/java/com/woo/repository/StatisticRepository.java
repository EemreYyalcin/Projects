package com.woo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.woo.domain.Statistic;

@Repository
public interface StatisticRepository extends CrudRepository<Statistic, Long> {

}
