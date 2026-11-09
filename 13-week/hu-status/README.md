<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       13-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 13

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Daniela Sanabria Mosquera
- GITHUB_USER: DaniKaizenNetwork
- TEAM: The illusionists
- SPRINT_GOAL: STRIDE threat-model the browser-to-API trust boundary (TB1) of OptiView Cut 1 and turn confirmed findings into tracked CP3 security issues.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| | STRIDE threat model: browser-to-API boundary (TB1), Cut 1 | doing | PR `https://github.com/code-corhuila/opti-docs/pull/51` · commits `docs(w13): complete CP2 STRIDE and book reference`, `docs(w13): map book references to STRIDE risks`, `docs(w13): present complete TB1 STRIDE model for review`, `docs(w13): link STRIDE risks to CP3 stories`, `docs(w13): correct CP2 security page references`, `docs(security): address review on STRIDE for browser-to-API boundary` |

## 2. My individual contribution
- This week I built the STRIDE threat model for the browser-to-API trust boundary (TB1) of OptiView Cut 1, covering the path from the React/Nginx frontend through the Spring Boot backend to PostgreSQL.
- **Boundary definition**: documented the real Cut 1 deployment topology (`docker-compose.yml`), including the fact that the backend is also published directly on host port `8081`, bypassing Nginx — which makes the Spring Boot backend, not Nginx, the real enforcement point for TB1.
- **Entry-point inventory**: mapped the five concrete entry points crossing the boundary (`POST /api/auth/login`, `GET/POST /api/patients`, `POST /api/frames`, `POST /api/work-orders`) against their actual Cut 1 authorization (JWT + role checks in `SecurityConfig`), not an assumed target design.
- **Full STRIDE analysis**: for each of the six categories (S/T/R/I/D/E) identified a concrete threat grounded in the real code (`AuthenticateUseCase`, `CreateWorkOrderUseCase`, `JwtTokenProvider`, `application.yml`, `docker-compose.yml`), scored likelihood × impact (1–3 scale), and proposed mitigations still missing today.
- **Risk ranking**: ranked the six threats by score; the two highest (risk 6) are a known fallback JWT signing key that can mint an `ADMIN` token (Elevation of Privilege) and staff credential stuffing against the unthrottled login endpoint (Spoofing).
- **Residual risk + issue linking**: documented which risks are accepted as-is (Tampering, because price is already derived server-side; Information Disclosure, only while Cut 1 stays local) and opened/linked the ones that are not: CP3 issue `#48` (P0) for the Elevation-of-Privilege key issue, `#49` (P1) for Spoofing/DoS on login, `#50` (P1) for the missing write-audit trail (Repudiation).
- **Self-review response (CP6)**: since PR `#51` had not received teammate review by 2026-10-30, I re-checked the table myself against the actual `docker-compose.yml`, `application.yml`, and `SecurityConfig`, corrected the Nginx host-port number, named exactly where the vulnerable fallback secret lives, and added an explicit exit condition for the accepted Information-Disclosure risk.
- **Bibliographic grounding**: tied each STRIDE row to specific pages of Tanenbaum & Van Steen's *Sistemas distribuidos: principios y paradigmas* (2nd ed.), per the workshop's `[B]` citation requirement.
- Commits: `docs(w13): complete CP2 STRIDE and book reference` · `docs(w13): map book references to STRIDE risks` · `docs(w13): present complete TB1 STRIDE model for review` · `docs(w13): link STRIDE risks to CP3 stories` · `docs(w13): correct CP2 security page references` · `docs(security): address review on STRIDE for browser-to-API boundary`

## 3. Blockers and risks
- PR `#51` had no teammate review or comment as of 2026-10-30.
- Residual risk **E** (fallback JWT signing key, risk score 6) stays open and blocks any non-local deployment until issue `#48` is resolved.
- Overlap with a teammate's finding: issue `#48` relates to `#34` (audience check in `ms-pacientes`) — both concern JWT validation but cover different boundaries, kept as separate issues.

## 4. Plan for next week
- Get PR `#51` reviewed and merged, or address any review comments that arrive.
- Follow up on issue `#48` (P0 — fallback signing key) since it blocks deployment readiness.
- Add the negative API test proving an injected `price` field on `POST /api/work-orders` cannot change the stored total (accepted mitigation for Tampering, still not implemented as a test).

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [ ] Testable acceptance criteria
- [ ] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

## 6. Evidence links
- PR: `https://github.com/code-corhuila/opti-docs/pull/51`
- Issues: `https://github.com/code-corhuila/opti-docs/issues/48` (P0, Elevation of Privilege) · `https://github.com/code-corhuila/opti-docs/issues/49` (P1, Spoofing/DoS) · `https://github.com/code-corhuila/opti-docs/issues/50` (P1, Repudiation)
- Document: `05-architecture/security/stride-DaniKaizenNetwork.md`
- Related docs referenced in the analysis: `05-architecture/overview.md`, `05-architecture/deployment.md`, `07-api/authentication.md`, `00-governance/security-policy.md`
