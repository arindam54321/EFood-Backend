package com.ari.efood.repository;

import com.ari.efood.model.Qna;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface QnaRepository extends MongoRepository<Qna, String> {
    boolean existsByKeyAndValue(String key, String value);
    Optional<Qna> findByKey(String key);
}
