package com.woo.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.woo.domain.Category;
import com.woo.domain.Item;
import com.woo.domain.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

	Question findById(long id);

	Question findByItemAAndItemB(Item itemA, Item itemB);

	ArrayList<Question> findByCategoryAndLevel(Category category, int level);

}
