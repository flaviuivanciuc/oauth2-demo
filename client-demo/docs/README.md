# 🔐 OAuth2 Client Demo Documentation

This project demonstrates a Spring Boot application implementing OAuth2 client authentication supporting multiple providers (Auth0 and Okta).

## 📚 Table of Contents

1. [Overview](overview.md)
2. [Getting Started](getting-started.md)
3. [Authentication Flow](authentication-flow.md)
4. [API Reference](api-reference.md)
5. [Configuration Guide](configuration.md)

## ⚡ Quick Start

1. ☕ Ensure you have Java 21 installed
2. ⚙️ Configure Auth0 and Okta credentials in `application.yaml`
3. 🚀 Run the application using: `./mvnw spring-boot:run`
4. 🌐 Access the application at: `http://localhost:8080`
5. 🔑 Choose to login with either Auth0 or Okta

## 📁 Project Structure

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

For detailed documentation, please refer to the individual sections linked in the Table of Contents. This application supports multiple OAuth2 providers for authentication and demonstrates how to integrate with both Auth0 and Okta identity providers.
