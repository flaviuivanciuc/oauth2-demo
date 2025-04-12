# Overview

## Project Description

This Spring Boot application demonstrates OAuth2 resource server implementation using Auth0 as the identity provider. It serves as a protected API that validates JWT tokens issued by Auth0 and provides secured endpoints accessible only to authenticated users with proper roles.

## Features

- OAuth2 JWT token validation
- Role-based access control (RBAC)
- Protected API endpoints
- Custom role conversion from Auth0 claims
- Bearer token authentication
- User context extraction from JWT

## Tech Stack

- Java 21
- Spring Boot 3.4.4
- Spring Security OAuth2 Resource Server
- Spring Security
- Spring Web

## Architecture

The application follows a typical OAuth2 resource server architecture:

1. **Authentication Layer**: JWT validation and role conversion
2. **API Layer**: Protected REST endpoints
3. **Security Configuration**: JWT configuration and security rules
4. **Role Conversion**: Custom Auth0 role claim handling

## Key Components

1. **ResourceServerDemoApplication.java**: The main Spring Boot application class
2. **SecurityConfig.java**: Configures JWT validation and security settings
3. **ApiController.java**: Handles protected API endpoints
4. **Auth0RoleConverter.java**: Converts Auth0 role claims to Spring Security authorities
