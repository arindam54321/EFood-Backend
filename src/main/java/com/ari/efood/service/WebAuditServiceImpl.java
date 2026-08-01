package com.ari.efood.service;

import com.ari.efood.dto.WebAuditDto;
import com.ari.efood.repository.VisitorAuditRepository;
import com.ari.efood.repository.WebAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebAuditServiceImpl implements WebAuditService {

    @Autowired
    private WebAuditRepository repository;

    @Autowired
    private VisitorAuditRepository visitorAuditRepository;

    @Override
    public String addAudit(WebAuditDto request) {
        if (!visitorAuditRepository.existsByVisitorIdAndWebsiteId(request.getVisitorId(), request.getWebsiteId())) {
            visitorAuditRepository.save(request.toVisitorAuditEntity());
        }

        repository.save(request.toEntity());
        return "Audit added for visitor: '" + request.getVisitorId() + "' on website: '" + request.getWebsiteId() + "'";
    }
}
