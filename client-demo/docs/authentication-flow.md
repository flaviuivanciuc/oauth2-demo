# Authentication Flow 🔄

## Overview 🎯

This application implements OAuth2 client authentication flow with OpenID Connect (OIDC) supporting multiple providers (Auth0 and Okta).

## Flow Diagram 📊

```mermaid
sequenceDiagram
    participant User
    participant Client
    participant Auth0
    participant Okta
    participant Resource Server

    alt Auth0 Login
        User->>Client: Click Login with Auth0
        Client->>Auth0: 1. Authorization Request
        Note right of Client: client_id, redirect_uri,<br/>scope, audience
        Auth0->>User: 2. Login Page
        User->>Auth0: 3. Authenticate
        Auth0-->>Client: 4. Authorization Code
        Client->>Auth0: 5. Token Request
        Note right of Client: code, client_secret
        Auth0-->>Client: 6. ID & Access Tokens
    else Okta Login
        User->>Client: Click Login with Okta
        Client->>Okta: 1. Authorization Request
        Note right of Client: client_id, redirect_uri,<br/>scope, groups
        Okta->>User: 2. Login Page
        User->>Okta: 3. Authenticate
        Okta-->>Client: 4. Authorization Code
        Client->>Okta: 5. Token Request
        Note right of Client: code, client_secret
        Okta-->>Client: 6. ID & Access Tokens
    end

    alt Auth0 API Call
        Client->>Resource Server: 7. API Request + Auth0 Token
    else Okta API Call
        Client->>Resource Server: 7. API Request + Okta Token
    end
    Resource Server-->>Client: 8. Protected Resource
    Client-->>User: 9. Display Data
```

## Authentication Steps 📝

1. **Initial Request** 🚀

   - User clicks either "Login with Auth0" or "Login with Okta" 🔐
   - Application redirects to chosen provider with required parameters:
     - client_id 🆔
     - redirect_uri 🔄
     - scope (openid, profile, email) 📋
     - Additional scopes:
       - Auth0: audience (for API access) 🎯
       - Okta: groups (for role mapping) 👥

2. **Authorization** ✅

   - User authenticates with the chosen provider 🔑
   - Provider redirects back with authorization code 📝

3. **Token Exchange** 🔄

   - Application exchanges code for tokens 🎫
   - Tokens include:
     - Access token (for API calls) 🔑
     - ID token (user information) 👤
     - Refresh token (optional) 🔄

4. **Session Management** ⚙️
   - Spring Security creates user session 📝
   - User information extracted from ID token 👤
   - Access token stored for API calls 🔑

## Implementation Details 🛠️

### Security Configuration 🔒

The `SecurityConfig` class configures:

- Protected endpoints 🛡️
- OAuth2 login 🔐
- Custom authorization request resolver 🔍
- Audience parameter for API access 🎯

### Token Handling 🎫

`HomeController` demonstrates:

- Accessing user information from ID token 👤
- Using access token for API calls 🔑
- Sending user context in API requests 📨

### Session Attributes 📝

Available user information:

- Full name 👤
- Email 📧
- Additional claims from ID token 📋
- Access token for API calls 🔑
