package com.ari.efood.controller;

import com.ari.efood.dto.QnaDto;
import com.ari.efood.service.QnaService;
import com.ari.efood.utils.ResponseWrapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("qna")
@CrossOrigin
public class QnaApi {

    @Autowired
    private QnaService service;

    @PostMapping("verify")
    public ResponseEntity<ResponseWrapper<Boolean>> verify (
            @Valid @RequestBody QnaDto request
    ) {
        Boolean response = service.verify(request);
        HttpStatus status = response ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseWrapper.entity(response, status);
    }
}
