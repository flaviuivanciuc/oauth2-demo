# OAuth2 Client Demo Documentation

This project demonstrates a Spring Boot application implementing OAuth2 client authentication using Auth0 as the identity provider.

## Table of Contents

1. [Overview](overview.md)
2. [Getting Started](getting-started.md)
3. [Authentication Flow](authentication-flow.md)
4. [API Reference](api-reference.md)
5. [Configuration Guide](configuration.md)

## Quick Start

1. Ensure you have Java 21 installed
2. Configure your Auth0 credentials in `application.yaml`
3. Run the application using: `./mvnw spring-boot:run`
4. Access the application at: `http://localhost:8080`

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── dev/oauth2/client_demo/
│   │       ├── DemoApplication.java
│   │       ├── HomeController.java
│   │       └── SecurityConfig.java
│   └── resources/
│       ├── application.yaml
│       └── templates/
│           ├── home.html
│           └── secured.html
```

For detailed documentation, please refer to the individual sections linked in the Table of Contents.
