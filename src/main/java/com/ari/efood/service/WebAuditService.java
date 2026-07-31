package com.ari.efood.service;

import com.ari.efood.dto.WebAuditDto;

public interface WebAuditService {
    String addAudit(WebAuditDto request);
}
