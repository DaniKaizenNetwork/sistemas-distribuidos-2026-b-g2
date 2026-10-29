# CP4 — CI and negative authorization test

Author: @DaniKaizenNetwork · Group G2 · 2026-10-29

## Selected negative test

The Cut 1 source snapshot already includes `OptiviewIntegrationTest.api_without_token_returns_401`: a `GET /api/patients` request without an Authorization token must return HTTP 401. It also includes `hu05_only_admin_registers_frames`: a SELLER token must receive HTTP 403 when it tries to create a frame. Both tests exercise the client-to-API boundary and authorization controls.

Source: `05-release/optiview-platform-v1.0.0/backend/src/test/java/co/opticacentral/optiview/OptiviewIntegrationTest.java` in the team documentation repository.

## Verification status

- Test source inspected; neither test has been run during this work.
- The checkout contains `pom.xml` but no Maven wrapper, and Maven is not available on PATH in this environment.
- The integration test uses Testcontainers and is configured to skip when Docker is unavailable.`r`n- The Docker CLI is installed, but its daemon is unavailable in this session (Docker API named pipe not found).
- CI result and Actions run link: pending. Do not report a passing check until a real run is visible.

To run when Maven and Docker are available, from `05-release/optiview-platform-v1.0.0/backend/` execute `mvn -Dtest=OptiviewIntegrationTest test`. Record the actual exit status and link the corresponding CI run in the checkpoint submission.

