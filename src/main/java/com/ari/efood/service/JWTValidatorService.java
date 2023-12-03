package com.ari.efood.service;

import com.ari.efood.auth.JWTUtils;
import com.ari.efood.enums.Role;
import com.ari.efood.exception.JWTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class JWTValidatorService extends JWTUtils {
    @Autowired
    private CustomerServiceImpl customerService;

    public void validate(String token, Role... expectedRole) throws JWTException {
        try {
            boolean isValid = false;
            Map<String, Object> jwtMap = JWTUtils.parseJWT(token);
            String id = String.valueOf(jwtMap.get(JWTUtils.JWT_ID_KEY));
            String name = String.valueOf(jwtMap.get(JWTUtils.JWT_NAME_KEY));
            String email = String.valueOf(jwtMap.get(JWTUtils.JWT_EMAIL_KEY));
            String role = String.valueOf(jwtMap.get(JWTUtils.JWT_ROLE_KEY));

            if (expectedRole.length != 0) {
                List<String> roles = Stream.of(expectedRole).map(Role::getRole).toList();
                if (!roles.contains(role)) {
                    throw new JWTException("Authentication Failed");
                }
            }

            if (Objects.equals(role, Role.CUSTOMER.getRole())) {
                isValid = customerService.doesExists(id, name, email);
            }

            if (!isValid) {
                throw new JWTException("Authentication Failed");
            }

        } catch (Exception e) {
            throw new JWTException("Authentication Failed");
        }
    }
}
