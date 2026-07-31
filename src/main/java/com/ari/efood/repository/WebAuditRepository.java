package com.ari.efood.repository;

import com.ari.efood.model.WebAudit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebAuditRepository extends MongoRepository<WebAudit, String> {
}
