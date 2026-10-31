# CP9 — Implementation plan for my project

Author: @DaniKaizenNetwork · Group G2 · TB1 (staff browser → Nginx `/api/` → Cut 1 Spring Boot API)

This section is also pasted into the W13 reflection document under the heading "Implementation plan for my project".

## Implementation plan for my project

The threat model of TB1 left six risks in PR #51 and three backlog stories (#48, #49, #50). The plan below orders them by risk and by how much each one blocks a real deployment of OptiView.

**Sprint 1 (next sprint) — remove the known JWT signing key, issue #48 (E, risk 6, P0).** This goes first because it is the only threat that skips the login entirely: anyone who reads `application.yml` or `docker-compose.yml` can sign a token with `role=ADMIN` and call `POST /api/frames` or `POST /api/work-orders`. The change is small and contained in the backend: delete the default value of `APP_JWT_SECRET`, keep the existing 32-byte check in `JwtTokenProvider` so startup fails without a real secret, inject the secret from the environment, and document rotation. The negative test `JwtForgedAdminTokenNegativeTest` already proves that a token signed with another key is rejected; it moves into the backend test suite in the same sprint and runs in the proposed `security.yml` gate on every pull request.

**Sprint 1 — keep Nginx as the only entry point.** The CP6 review found that `docker-compose.yml` publishes the backend on host port `8081`. Publishing it only on the internal Compose network is a one-line change and makes every later control (rate limit, correlation ID) impossible to bypass.

**Sprint 2 — throttle `POST /api/auth/login`, issue #49 (S risk 6, D risk 4, P1).** Credential stuffing has the same risk value as E, but it needs a valid password to succeed, while E does not, so it comes second. The limit must live in the backend, return HTTP 429 with `Retry-After`, and answer unknown users and wrong passwords with the same body. Tests: N+1 failed attempts return 429, and both failure bodies are identical.

**Sprint 2 — actor-linked audit of patient and work-order writes, issue #50 (R, risk 4, P1).** `POST /api/patients` and `POST /api/work-orders` will store subject, role, action, resource, result, source IP, timestamp, and correlation ID, as `00-governance/security-policy.md` already requires. It is P1 because it does not prevent an attack; it makes one investigable.

**Sprint 3 — tampering test for `CreateWorkOrderUseCase` (T, risk 4).** The use case already reloads `Patient` and `Frame` and computes the price from `frame.salePrice()`, so only a negative API test that sends an extra `price` field is missing.

**Risk accepted for now — clear-text HTTP (I, risk 3).** While Cut 1 runs on one local development host, patient data and tokens never leave the machine, so TLS is postponed. The acceptance ends the day the stack moves to a shared or remote host: then TLS termination in front of Nginx and an HTTP→HTTPS redirect become blocking, as the security policy requires.

**What I learned.** Drawing the boundary first showed that the real enforcement point is the backend, not Nginx: the open port `8081` would have made an Nginx-only fix useless. Ranking by likelihood × impact also showed that two risks with the same number (E and S, both 6) are not equal in practice, because one needs a stolen password and the other only needs to read the repository. The book's key-management section (Tanenbaum and Van Steen, §9.4.1, pp. 428–432) supported treating the signing secret as a key with its own provisioning and rotation, which is why #48 is P0.

## References

- Tanenbaum, Andrew S., and Maarten Van Steen. *Sistemas distribuidos: principios y paradigmas*. 2.ª ed. Pearson Educación, 2008. ISBN 978-970-26-1280-3. CORHUILA physical copy, signatura 0001226. Chapter 9, pp. 378–388, 397–404, 405–407, 413–417, 427, 428–432 (pages as recorded in PR #51).
- Team documentation: `05-architecture/security/stride-DaniKaizenNetwork.md`, `00-governance/security-policy.md`, `05-release/optiview-platform-v1.0.0/` (`docker-compose.yml`, `application.yml`, `SecurityConfig`, `JwtTokenProvider`, `CreateWorkOrderUseCase`).
