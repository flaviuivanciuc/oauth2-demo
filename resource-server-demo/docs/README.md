# OAuth2 Resource Server Demo Documentation

This project demonstrates a Spring Boot application implementing OAuth2 resource server functionality using Auth0 as the identity provider.

## Table of Contents

1. [Overview](overview.md)
2. [Getting Started](getting-started.md)
3. [Authentication Flow](authentication-flow.md)
4. [API Reference](api-reference.md)
5. [Configuration Guide](configuration.md)

## Quick Start

1. Ensure you have Java 21 installed
2. Configure your Auth0 issuer URI in `application.yaml`
3. Run the application using: `./mvnw spring-boot:run`
4. Access the API at: `http://localhost:8081`

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── dev/oauth2/resource_server_demo/
│   │       ├── ResourceServerDemoApplication.java
│   │       ├── ApiController.java
│   │       ├── SecurityConfig.java
│   │       └── Auth0RoleConverter.java
│   └── resources/
│       └── application.yaml
```

For detailed documentation, please refer to the individual sections linked in the Table of Contents.
