package com.ari.efood.service;

import com.ari.efood.dto.WebAuditDto;
import com.ari.efood.repository.WebAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebAuditServiceImpl implements WebAuditService {

    @Autowired
    private WebAuditRepository repository;

    @Override
    public String addAudit(WebAuditDto request) {
        repository.save(request.toEntity());
        return "Audit added successfully";
    }
}
