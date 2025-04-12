# API Reference 📚

## Endpoints 🛣️

### Protected Endpoints 🔒

#### GET /api/private 🔐

- **Description**: Protected endpoint requiring ADMIN role
- **Access**: Authenticated users with ADMIN role 👥
- **Headers Required**:
  - `Authorization`: Bearer {jwt_token} 🎫
  - `X-User-Email`: User's email address 📧
- **Response**: JSON object containing welcome message 📝

### Authentication Requirements 🔑

All endpoints require:

- Valid JWT token from Auth0 🎫
- Proper role claims in the token 👥
- Required headers in the request 📧

## Request/Response Examples 📝

### Successful Request ✅

Request:

```http
GET /api/private HTTP/1.1
Host: localhost:8081
Authorization: Bearer eyJ0eXAi...
X-User-Email: user@example.com
```

Response:

```json
{
  "message": "Hello, user@example.com! This is a protected API."
}
```

### Error Responses ⚠️

#### Invalid Token (401 Unauthorized) ❌

```json
{
  "error": "Unauthorized",
  "error_description": "Full authentication is required to access this resource"
}
```

#### Insufficient Permissions (403 Forbidden) 🚫

```json
{
  "error": "Forbidden",
  "error_description": "Access is denied"
}
```

## Security Considerations 🔒

1. **Token Validation** 🔍

   - All tokens must be valid JWTs ✅
   - Tokens must not be expired ⏱️
   - Tokens must be issued by Auth0 🔑
   - Tokens must contain required role claims 👥

2. **Header Requirements** 📋

   - Authorization header must use Bearer scheme 🎫
   - X-User-Email header must be present 📧
   - Token must match the user context 🔍

3. **Role Requirements** 👥
   - ADMIN role required for /api/private 🛡️
   - Roles must be properly formatted in token 📝
   - Role converter handles Auth0's custom claim format 🔄

## Rate Limiting 🚦

- Dependent on Auth0 rate limits ⏱️
- No additional rate limiting implemented 📊
- Consider implementing rate limiting for production use 🔄
