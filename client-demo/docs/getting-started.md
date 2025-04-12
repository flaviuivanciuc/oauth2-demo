# Getting Started

## Prerequisites

1. Java 21 or higher
2. Maven (or use the included Maven wrapper)
3. Auth0 account and configured application

## Auth0 Setup

1. Create a new Auth0 application:

   - Go to Auth0 Dashboard
   - Create a new Regular Web Application
   - Configure allowed callback URLs: `http://localhost:8080/login/oauth2/code/auth0`
   - Configure allowed logout URLs: `http://localhost:8080/`

2. Note your Auth0 credentials:
   - Domain (issuer URI)
   - Client ID
   - Client Secret

## Installation

1. Clone the repository
2. Configure Auth0 credentials in `src/main/resources/application.yaml`:

   ```yaml
   spring:
     security:
       oauth2:
         client:
           registration:
             auth0:
               client-id: YOUR_CLIENT_ID
               client-secret: YOUR_CLIENT_SECRET
               scope:
                 - openid
                 - profile
                 - email
           provider:
             auth0:
               issuer-uri: YOUR_ISSUER_URI
   ```

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

## Verification

1. Access `http://localhost:8080`
2. Click "Login with Auth0"
3. You should be redirected to Auth0's login page
4. After successful login, you'll be redirected back to the application

## Next Steps

1. Explore the secured endpoints
2. Test the API integration
3. Review the authentication flow documentation
