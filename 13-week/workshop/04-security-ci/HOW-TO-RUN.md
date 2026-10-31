# CP8 — How to run the negative authorization test

## What it covers

- Story: [#48 — Require a deployment-specific JWT secret and remove the fallback key](https://github.com/code-corhuila/opti-docs/issues/48) (STRIDE E, risk 6, P0).
- Acceptance criterion: "A token signed with the configured key is accepted; one signed with another key is rejected with HTTP 401." The test proves the rejection half at the `JwtTokenProvider` adapter: a forged `role=ADMIN` token signed with the repository fallback value of `APP_JWT_SECRET` returns no principal, so `SecurityConfig` never grants `ADMIN` and `POST /api/frames` stays closed.
- Second case: a secret shorter than 32 bytes is refused at startup (criterion "startup fails … shorter than the required minimum").

Test file: `JwtForgedAdminTokenNegativeTest.java` (this folder). Package `co.opticacentral.optiview.infrastructure.driven_adapters.security`. It needs no database or Docker.

## Run locally

Requirements: JDK 21 (the backend `pom.xml` sets `java.version` 21) and Maven 3.9.

```sh
# from opti-docs/05-release/optiview-platform-v1.0.0/backend (or a copy of it)
mkdir -p src/test/java/co/opticacentral/optiview/infrastructure/driven_adapters/security
cp <fork>/13-week/workshop/04-security-ci/JwtForgedAdminTokenNegativeTest.java \
   src/test/java/co/opticacentral/optiview/infrastructure/driven_adapters/security/
mvn -B test -Dtest=JwtForgedAdminTokenNegativeTest
```

## Actual execution output

Run on 2026-10-30 on the student's Windows laptop, JDK `openjdk 21.0.11`, Maven 3.9.12, against a copy of the Cut 1 backend from `code-corhuila/opti-docs` with the test file added (the team repository was not modified). Console excerpt, unedited:

```text
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running co.opticacentral.optiview.infrastructure.driven_adapters.security.JwtForgedAdminTokenNegativeTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.826 s -- in co.opticacentral.optiview.infrastructure.driven_adapters.security.JwtForgedAdminTokenNegativeTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  14.183 s
[INFO] Finished at: 2026-10-30T22:50:24-05:00
```

A first attempt with JDK 17 on PATH failed before compiling (`release version 21 not supported`); the workflow and these instructions now use JDK 21.

## Still pending

- HTTP-level check (`OptiviewIntegrationTest.api_without_token_returns_401`, `hu05_only_admin_registers_frames`): not run. It uses Testcontainers and is skipped without Docker; Docker Desktop did not become available on this machine during the session. Command: `mvn -B test -Dtest=OptiviewIntegrationTest` with Docker running.
- CI run of `security.yml`: not run; the workflow is a proposal in this fork, not installed in the team repository.

## Workflow validation

`actionlint` is not installed on this machine. Validation done with PyYAML 6.0.3 (`yaml.safe_load`) on 2026-10-30, actual result:

```text
PyYAML 6.0.3 parse OK; trigger: ['pull_request'] ; jobs: ['secret-scan', 'dependency-audit', 'authorization-tests']
each job has runs-on and steps: OK
```

This checks YAML syntax and structure only, not GitHub Actions semantics. For a full check: `actionlint 13-week/workshop/04-security-ci/security.yml`.
