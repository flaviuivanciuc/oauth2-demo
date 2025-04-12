# Authentication Flow 🔄

## Overview 🎯

This application implements JWT token validation for OAuth2 resource server supporting multiple identity providers (Auth0 and Okta).

## Flow Diagram 📊

```mermaid
sequenceDiagram
    participant Client
    participant Resource Server
    participant Auth0
    participant Okta

    alt Auth0 Token
        Client->>Resource Server: API Request + Auth0 Bearer Token
        Resource Server->>Auth0: Validate JWT
        Auth0-->>Resource Server: Token Valid/Invalid
    else Okta Token
        Client->>Resource Server: API Request + Okta Bearer Token
        Resource Server->>Okta: Validate JWT
        Okta-->>Resource Server: Token Valid/Invalid
    end

    Resource Server->>Resource Server: Extract Claims & Roles
    Note right of Resource Server: Convert provider-specific<br/>role claims
    Resource Server-->>Client: Protected Resource/401/403
```

## Detailed Flow Steps 📝

1. **Token Validation** 🔍

   - Client sends request with Bearer token 🎫
   - MultiIssuerJwtDecoder identifies token issuer 🔍
   - Resource server validates JWT signature and claims ✅
   - Checks token expiration and issuer ⏱️
   - Validates audience claim 🎯

2. **Role Processing** 👥

   - MultiTenantRoleConverter extracts role claims 📄
   - Handles different role claim formats per provider 🔄
   - Converts provider roles to Spring authorities 🛡️
   - Apply role-based access control ⚔️

3. **Request Processing** 🔄

   - Validate required headers (X-User-Email) 📧
   - Check endpoint-specific permissions 🔒
   - Process the request if authorized ✅

4. **Response Handling** 📤
   - Return protected resource for authorized requests ✅
   - Return 401 for invalid/expired tokens ❌
   - Return 403 for insufficient permissions 🚫

## Implementation Details 📋

### JWT Validation 🔐

The application validates:

- Token signature using Auth0's public key 🔑
- Token expiration ⏱️
- Token issuer 🏢
- Required claims 📝

### Role-Based Security 👥

Implemented through:

- Custom MultiTenantRoleConverter 🔄
- Role-specific endpoint protection 🛡️
- Spring Security configuration ⚙️

### Error Handling ⚠️

Common scenarios:

- Invalid token format ❌
- Expired tokens ⏱️
- Missing required roles 🚫
- Missing required headers 📝
