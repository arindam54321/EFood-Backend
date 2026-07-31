package com.ari.efood.controller;

import com.ari.efood.dto.WebAuditDto;
import com.ari.efood.service.WebAuditService;
import com.ari.efood.utils.ResponseWrapper;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("web/audit")
public class WebAuditApi {

    @Autowired
    private WebAuditService service;

    @Hidden
    @PostMapping
    public ResponseEntity<ResponseWrapper<String>> addAudit(
            @RequestBody WebAuditDto request
    ) {
        String response = service.addAudit(request);
        return ResponseWrapper.entity(response, HttpStatus.OK);
    }
}
