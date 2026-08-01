package com.ari.efood.repository;

import com.ari.efood.model.VisitorAudit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorAuditRepository extends MongoRepository<VisitorAudit, String> {
    boolean existsByVisitorIdAndWebsiteId(String visitorId, String websiteId);
}
