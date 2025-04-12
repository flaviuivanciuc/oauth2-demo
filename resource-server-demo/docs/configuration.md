# Configuration Guide

## Application Configuration

### application.yaml Settings

```yaml
spring:
  security:
    oauth2:
      resource-server:
        jwt:
          issuer-uri: YOUR_AUTH0_DOMAIN
server:
  port: 8081
```

## Security Configuration

### SecurityConfig.java

Key configuration points:

- JWT authentication
- Role-based authorization
- Endpoint security rules
- Custom JWT converter

Example security rules:

```java
http.authorizeHttpRequests(auth -> auth
    .requestMatchers(HttpMethod.GET, "/api/private").hasRole("ADMIN")
    .anyRequest().authenticated()
)
```

## Role Conversion

The `Auth0RoleConverter` class handles:

- Custom role claim extraction
- Role to authority conversion
- Auth0-specific claim format handling

## API Endpoints

Protected endpoints require:

- Valid JWT token
- Proper role assignments
- Required headers

## Logging Configuration

Add to application.yaml for detailed security logs:

```yaml
logging:
  level:
    org.springframework.security: DEBUG
    org.springframework.security.oauth2: DEBUG
```

## Production Considerations

1. **Security**

   - Enable HTTPS
   - Configure proper CORS
   - Set up rate limiting
   - Implement audit logging

2. **Performance**

   - Configure connection pools
   - Enable caching where appropriate
   - Monitor token validation performance

3. **Monitoring**

   - Set up health endpoints
   - Configure metrics collection
   - Implement proper logging

4. **Environment Configuration**
   - Use profiles for different environments
   - Secure sensitive configuration
   - Configure proper timeouts
