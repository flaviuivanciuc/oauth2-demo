# Overview 📋

## Project Description 🎯

This Spring Boot application demonstrates OAuth2 resource server implementation supporting multiple identity providers (Auth0 and Okta). It serves as a protected API that validates JWT tokens from multiple issuers and provides secured endpoints accessible only to authenticated users with proper roles.

## Features ✨

- Multi-tenant JWT token validation 🔑
- Support for multiple identity providers (Auth0, Okta) 🔐
- Role-based access control (RBAC) 👥
- Protected API endpoints 🛡️
- Custom role conversion from JWT claims 🔄
- Bearer token authentication 🎫
- User context extraction from JWT 📝

## Tech Stack 🛠️

- Java 21 ☕
- Spring Boot 3.4.4 🍃
- Spring Security OAuth2 Resource Server 🔒
- Spring Security 🛡️
- Spring Web 🌐

## Architecture 🏗️

The application follows a multi-tenant OAuth2 resource server architecture:

1. **Authentication Layer** 🔐: Multi-issuer JWT validation and role conversion
2. **API Layer** 📡: Protected REST endpoints
3. **Security Configuration** ⚙️: Multiple JWT issuers configuration and security rules
4. **Role Conversion** 🔄: Custom role claim handling for different providers

## Key Components 🧩

1. **ResourceServerDemoApplication.java** 🚀: The main Spring Boot application class
2. **SecurityConfig.java** 🛡️: Configures JWT validation and security settings
3. **ApiController.java** 🎮: Handles protected API endpoints
4. **MultiTenantRoleConverter.java** 🔄: Converts JWT role claims to Spring Security authorities
5. **MultiIssuerJwtDecoder.java** 🔍: Handles JWT validation from multiple issuers
6. **JwtIssuerProperties.java** ⚙️: Configuration properties for JWT issuers
