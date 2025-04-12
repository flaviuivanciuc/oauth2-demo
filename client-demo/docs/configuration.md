# Configuration Guide ⚙️

## Application Configuration 🛠️

### application.yaml Settings 📝

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          auth0:
            client-id: ${AUTH0_CLIENT_ID}
            client-secret: ${AUTH0_CLIENT_SECRET}
            scope:
              - openid
              - profile
              - email
          okta:
            client-id: ${OKTA_CLIENT_ID}
            client-secret: ${OKTA_CLIENT_SECRET}
            scope:
              - openid
              - profile
              - email
              - groups
        provider:
          auth0:
            issuer-uri: ${AUTH0_ISSUER_URI}
          okta:
            issuer-uri: ${OKTA_ISSUER_URI}
```

## Environment Variables 🌍

Required variables:

- AUTH0_CLIENT_ID 🆔
- AUTH0_CLIENT_SECRET 🔑
- AUTH0_ISSUER_URI 🏢
- OKTA_CLIENT_ID 🆔
- OKTA_CLIENT_SECRET 🔑
- OKTA_ISSUER_URI 🏢

## Security Configuration 🔒

### SecurityConfig.java 🛡️

Key configuration points:

- OAuth2 login enabled 🔐
- CSRF protection 🛡️
- Session management ⏱️
- Authorization rules ⚔️
- Logout handling 🚪

Example security rules:

```java
http.authorizeHttpRequests(authorize -> authorize
    .requestMatchers("/", "/error").permitAll()
    .anyRequest().authenticated()
)
```

## Template Configuration 🎨

### Templates Location 📂

- Templates are stored in `src/main/resources/templates/` 📁
- Using Thymeleaf template engine 🎨
- Security context available in templates 🔒

### Available Templates 📄

1. `home.html`: Public landing page 🏠
2. `secured.html`: Protected page showing user info 🔐

## External API Integration 🔌

Configure the external API URL in application.yaml:

```yaml
api:
  url: http://localhost:8081
```

## Production Considerations 🚀

1. **SSL Configuration** 🔒

   - Enable HTTPS in production 🔐
   - Configure SSL certificate 📜
   - Set secure cookie flags 🍪

2. **Session Management** ⏱️

   - Configure session timeout ⌛
   - Set session persistence 💾
   - Enable session cleanup 🧹

3. **Security Headers** 🛡️

   - HSTS configuration 🔒
   - XSS protection 🛡️
   - Content Security Policy 🔐

4. **Environmental Specifics** 🌍
   - Use different application-{profile}.yaml files 📄
   - Secure credentials storage 🔑
   - Configure proper CORS settings ⚡
