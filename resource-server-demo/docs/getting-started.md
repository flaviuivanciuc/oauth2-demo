# Getting Started

## Prerequisites

1. Java 21 or higher
2. Maven (or use the included Maven wrapper)
3. Auth0 account and configured API

## Auth0 Setup

1. Create a new Auth0 API:

   - Go to Auth0 Dashboard
   - Create a new API
   - Set the identifier as: `https://resource-server-demo-api-v1`
   - Choose RS256 signing algorithm

2. Configure RBAC:
   - Enable RBAC in your Auth0 API settings
   - Create roles (e.g., 'ADMIN')
   - Assign roles to users

## Installation

1. Clone the repository
2. Configure Auth0 issuer URI in `src/main/resources/application.yaml`:

   ```yaml
   spring:
     security:
       oauth2:
         resource-server:
           jwt:
             issuer-uri: YOUR_AUTH0_DOMAIN
   server:
     port: 8081
   ```

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

## Verification

1. Get a valid JWT token from Auth0
2. Make a request to `http://localhost:8081/api/private` with:
   - Bearer token in Authorization header
   - X-User-Email header with user's email

## Next Steps

1. Test the protected endpoints
2. Review the security configuration
3. Implement additional endpoints as needed
