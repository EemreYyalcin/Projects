package com.woo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.domain.Statistic;
import com.woo.repository.StatisticRepository;
import com.woo.service.types.StatisticService;

@Service
public class StatisticServiceImpl implements StatisticService{

	@Autowired
	private StatisticRepository statisticRepository;
	
	@Override
	public Statistic getStatisticByUserId(long userId) {
		return statisticRepository.findOne(userId);
	}

}
