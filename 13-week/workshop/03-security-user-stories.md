# CP3 — Security user stories for TB1

Author: @DaniKaizenNetwork · Group G2 · 2026-10-29

These are issue drafts for `code-corhuila/opti-docs` and the team security board. Scope: browser client → Nginx `/api/` → Cut 1 Spring Boot API. They do not overlap the open stories for the gateway-to-service boundaries listed by the team.

## 1. [security] Require a deployment-specific JWT secret and remove the fallback key

**Priority:** P0  
**User story:** As a platform administrator, I want each deployment to provide its own JWT signing secret and the application to refuse a missing secret, so that a repository-known value cannot be used to mint an `ADMIN` token.

**Acceptance criteria**

- The application has no default JWT signing secret in `application.yml` or `docker-compose.yml`.
- Startup fails with a clear configuration error when the secret is missing, blank, or shorter than the required minimum.
- The configured value is injected from the deployment environment or secret store and never appears in logs or error responses.
- A token signed with the configured key is accepted; one signed with another key is rejected with 401.
- The team documents key rotation and invalidates tokens signed with a retired key.

**Book citation [B]:** Tanenbaum, Andrew S., and Maarten Van Steen. *Sistemas distribuidos: principios y paradigmas*. 2.ª ed. Pearson Educación, 2008, ISBN 978-970-26-1280-3, §9.4.1, pp. 428–432 (key management). CORHUILA physical copy, library signatura 0001226 (reported by the student). The key-management section supports provisioning, protecting, and rotating the JWT signing secret.

## 2. [security] Rate-limit public login attempts and detect credential stuffing

**Priority:** P1  
**User story:** As an OptiView operator, I want repeated login attempts to be throttled and observable, so that password guessing cannot consume the API or compromise staff accounts unnoticed.

**Acceptance criteria**

- `POST /api/auth/login` applies configurable rate limits by source and account identifier.
- Requests over the limit return 429 with `Retry-After` and a generic response that does not reveal whether an account exists.
- The control does not permanently lock an account based solely on unauthenticated requests.
- Repeated failures produce a security event with timestamp and source; the password and request body are never logged.
- Automated tests cover allowed attempts, throttling, and the generic response for unknown users.

## 3. [security] Audit patient and work-order writes with the authenticated actor

**Priority:** P1  
**User story:** As a security reviewer, I want patient and work-order writes to produce an audit record with the authenticated actor and outcome, so that changes can be investigated and staff actions cannot be repudiated.

**Acceptance criteria**

- Successful and rejected `POST /api/patients` and `POST /api/work-orders` requests record actor subject, role, action, resource identifier, result, source IP, timestamp, and correlation ID.
- Audit records are append-only and access-controlled.
- Passwords, JWTs, signing secrets, and full patient payloads are excluded from audit events.
- Automated tests verify actor and outcome fields for a successful write and a rejected write.

## Submission checklist

- [x] Created [#48 — deployment-specific JWT signing key (P0)](https://github.com/code-corhuila/opti-docs/issues/48) with `security` and `P0` labels.
- [x] Created [#49 — login throttling and credential-stuffing detection (P1)](https://github.com/code-corhuila/opti-docs/issues/49) with `security` and `P1` labels.
- [x] Created [#50 — actor-linked patient and work-order audit (P1)](https://github.com/code-corhuila/opti-docs/issues/50) with `security` and `P1` labels.
- [x] The student confirmed the physical copy's CORHUILA library signatura as `0001226`.

