# Overview

## Project Description

This Spring Boot application demonstrates OAuth2 client implementation using Auth0 as the identity provider. It serves as a client application that authenticates users through Auth0 and can make authenticated requests to a resource server.

## Features

- OAuth2 authentication with Auth0
- OpenID Connect integration
- Protected endpoints
- User profile information display
- Secured API calls with token forwarding
- Thymeleaf templating for the frontend

## Tech Stack

- Java 21
- Spring Boot 3.4.4
- Spring Security OAuth2 Client
- Spring Security
- Thymeleaf
- Jackson for JSON processing

## Architecture

The application follows a typical OAuth2 client architecture:

1. **Authentication Layer**: Handled by Spring Security OAuth2 Client
2. **Web Layer**: Controllers for handling user requests
3. **Template Layer**: Thymeleaf templates for view rendering
4. **Security Configuration**: Custom security settings and OAuth2 configuration

## Key Components

1. **DemoApplication.java**: The main Spring Boot application class
2. **SecurityConfig.java**: Configures security settings and OAuth2 client
3. **HomeController.java**: Handles web requests and API interactions
4. **Templates**: HTML templates for the user interface
