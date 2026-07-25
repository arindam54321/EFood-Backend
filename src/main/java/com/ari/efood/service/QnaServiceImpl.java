package com.ari.efood.service;

import com.ari.efood.dto.QnaDto;
import com.ari.efood.model.Qna;
import com.ari.efood.repository.QnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QnaServiceImpl implements QnaService {
    @Autowired
    private QnaRepository repository;

    @Override
    public Boolean verify(QnaDto request) {
        String key = request.getKey();
        String value = request.getValue().toLowerCase().trim();
        Optional<Qna> answer = repository.findByKey(key);
        if (answer.isEmpty()) {
            return false;
        } else {
            String actualValue = answer.get().getValue();
            return value.replace(" ", "").equals(actualValue.toLowerCase().trim().replace(" ", ""));
        }
    }
}
