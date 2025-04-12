# Configuration Guide ⚙️

## Application Configuration 🛠️

### application.yaml Settings 📝

```yaml
spring:
  security:
    oauth2:
      resource-server:
        jwt:
          issuers:
            auth0:
              issuer-uri: ${AUTH0_ISSUER_URI}
              roles-claim: ${AUTH0_ROLES_CLAIM}
            okta:
              issuer-uri: ${OKTA_ISSUER_URI}
              roles-claim: ${OKTA_ROLES_CLAIM}
server:
  port: 8081
```

## Security Configuration 🔒

### SecurityConfig.java 🛡️

Key configuration points:

- Multi-issuer JWT authentication 🔑
- Role-based authorization 👥
- Endpoint security rules ⚔️
- Custom JWT decoder and converter 🔄

Example security rules:

```java
http.authorizeHttpRequests(auth -> auth
    .requestMatchers(HttpMethod.GET, "/api/private").hasAnyRole("ADMIN", "user")
    .anyRequest().authenticated()
)
```

## Role Conversion 🔄

The `MultiTenantRoleConverter` handles:

- Custom role claim extraction per issuer 📎
- Role to authority conversion 🔄
- Support for different claim formats 📋

## JWT Configuration 🎫

### MultiIssuerJwtDecoder 🔍

Handles:

- Multiple issuer validation ✅
- JWT signature verification 🔏
- Issuer-specific configurations ⚙️

## Environment Variables 🌍

Required variables:

- AUTH0_ISSUER_URI 🔑
- AUTH0_ROLES_CLAIM 👥
- OKTA_ISSUER_URI 🔑
- OKTA_ROLES_CLAIM 👥

## Production Considerations 🚀

1. **Security** 🔒

   - Enable HTTPS 🔐
   - Configure proper CORS ⚡
   - Set up rate limiting 🚦
   - Implement audit logging 📝

2. **Performance** ⚡

   - Configure connection pools 🌊
   - Enable caching where appropriate 💾
   - Monitor token validation performance 📊

3. **Monitoring** 📈

   - Set up health endpoints 💓
   - Configure metrics collection 📊
   - Implement proper logging 📝

4. **Environment Configuration** ⚙️
   - Use profiles for different environments 🌍
   - Secure sensitive configuration 🔐
   - Configure proper timeouts ⏱️
