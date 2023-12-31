package com.ari.efood.service;

import com.ari.efood.auth.JWTUtils;
import com.ari.efood.enums.Role;
import com.ari.efood.exception.JWTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@Service
public class JWTValidatorService extends JWTUtils {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CustomerService customerService;

    @Value("${admin.field.username}")
    private String adminUsernameField;
    @Value("${admin.field.password}")
    private String adminPasswordField;
    @Value("${admin.field.mfa}")
    private String adminMFAField;

    public void validate(String token, Role... expectedRole) throws JWTException {
        try {
            boolean isValid = false;
            Map<String, Object> jwtMap = JWTUtils.parseJWT(token);
            String id = String.valueOf(jwtMap.get(JWTUtils.JWT_ID_KEY));
            String name = String.valueOf(jwtMap.get(JWTUtils.JWT_NAME_KEY));
            String email = String.valueOf(jwtMap.get(JWTUtils.JWT_EMAIL_KEY));
            String role = String.valueOf(jwtMap.get(JWTUtils.JWT_ROLE_KEY));

            if (expectedRole.length != 0) {
                List<String> roles = Stream.of(expectedRole).map(Role::getTYPE).toList();
                if (roles.contains(Role.ADMIN.getTYPE())) {
                    isValid = validateAdmin(jwtMap);
                    if (isValid) {
                        return;
                    }
                }
                if (!roles.contains(role)) {
                    throw new JWTException("Authentication Failed");
                }
            }

            if (Objects.equals(role, Role.CUSTOMER.getTYPE())) {
                isValid = customerService.doesExists(id, name, email);
            }

            if (!isValid) {
                throw new JWTException("Authentication Failed");
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new JWTException("Authentication Failed");
        }
    }

    private boolean validateAdmin(Map<String, Object> jwtMap) {
        String username = String.valueOf(jwtMap.get(adminUsernameField));
        String password = String.valueOf(jwtMap.get(adminPasswordField));
        String mfa = String.valueOf(jwtMap.get(adminMFAField));
        return adminService.doesExist(username, password, mfa);
    }
}
