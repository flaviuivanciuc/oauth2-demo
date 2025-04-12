# API Reference 📚

## Endpoints 🛣️

### Public Endpoints 🌐

#### GET / 🏠

- **Description**: Home page
- **Access**: Public
- **Response**: Home page with login option or user information if authenticated
- **Template**: `home.html`

### Protected Endpoints 🔒

#### GET /secured 🔐

- **Description**: Protected page
- **Access**: Authenticated users only
- **Response**: Secured page with user information
- **Template**: `secured.html`

#### GET /call-api 📡

- **Description**: Makes authenticated call to external API
- **Access**: Authenticated users only
- **Headers**:
  - `Authorization`: Bearer token 🔑
  - `X-User-Email`: User's email 📧
- **Target**: `http://localhost:8081/api/private`
- **Response**: API response displayed on secured page

## Authentication 🔐

All protected endpoints require:

- Valid session 📝
- OAuth2 authentication via Auth0 or Okta 🔒
- Valid access token for API calls 🎫

## Request/Response Examples 📝

### Successful Authentication Response ✅

```json
{
  "name": "User's Full Name",
  "email": "user@example.com"
}
```

### API Call Response 📡

```json
{
  "apiResponse": "Response from external API",
  "name": "User's Full Name"
}
```

## Error Handling ⚠️

- Unauthorized access redirects to login page 🔄
- API errors are handled gracefully and displayed on the secured page ❌
- Invalid tokens result in session termination 🚫

## Rate Limiting 🚦

- Dependent on Auth0 and external API limitations ⏱️
- No internal rate limiting implemented 📊
