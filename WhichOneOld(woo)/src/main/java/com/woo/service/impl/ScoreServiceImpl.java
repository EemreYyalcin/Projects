package com.woo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.domain.Score;
import com.woo.repository.ScoreRepository;
import com.woo.service.types.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	private ScoreRepository scoreRepository;

	public Score addScore(Score score) {
		return scoreRepository.save(score);
	}

}
