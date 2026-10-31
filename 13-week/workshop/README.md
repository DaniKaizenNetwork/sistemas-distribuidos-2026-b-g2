# Week 13 workshop — Threat modeling OptiView (TB1)

```text
GITHUB_USER: DaniKaizenNetwork
TEAM: G2 — OptiView (code-corhuila/opti-docs)
DOCS_REPO: https://github.com/code-corhuila/opti-docs
BOUNDARY: TB1 — staff browser → Nginx /api/ proxy → Cut 1 Spring Boot API
CLAIM_ISSUE: PENDING — student to confirm (no "Threat model: … — @DaniKaizenNetwork" issue was found in opti-docs on 2026-10-30)
REFLECTION_DOC: PENDING — student to add the URL of the W13 reflection document
LIBRARY_BOOK: Tanenbaum, Andrew S., and Maarten Van Steen. Sistemas distribuidos: principios y paradigmas. 2.ª ed. Pearson Educación, 2008. ISBN 978-970-26-1280-3. Signatura 0001226
LIBRARY_RECORD_TIME: PENDING — student to add the time of the library record
STRIDE_PR: https://github.com/code-corhuila/opti-docs/pull/51
STORIES: https://github.com/code-corhuila/opti-docs/issues/48, https://github.com/code-corhuila/opti-docs/issues/49, https://github.com/code-corhuila/opti-docs/issues/50
<!-- CONFIG-END -->
```

## CP7 — Refined security backlog

The three stories are the three highest risks of the STRIDE table in PR #51. The check below was made against the issue bodies on 2026-10-30.

| Issue | STRIDE | Risk | Priority | Book [B] | Gap found in CP7 |
|---|---|---:|---|---|---|
| [#48](https://github.com/code-corhuila/opti-docs/issues/48) JWT secret without fallback | E | 6 | P0 | §9.4.1, pp. 428–432 | Body lacks STRIDE letter, risk and PR link; criteria need a named automated test. |
| [#49](https://github.com/code-corhuila/opti-docs/issues/49) Login rate limit | S + D | 6 / 4 | P1 | — | Body lacks STRIDE letter, risk and PR link; limit must hold on host port `8081`. |
| [#50](https://github.com/code-corhuila/opti-docs/issues/50) Actor-linked write audit | R | 4 | P1 | — | Body lacks STRIDE letter, risk and PR link; criteria need concrete values. |

Board status: not verified. The available GitHub token has no `read:project` scope, so the student must confirm that the three issues are in the team Project backlog column.

### Text to append to each issue

**#48**

```markdown
## Measurable acceptance criteria added in CP7
- Automated test: a JWT signed with a key other than the configured `APP_JWT_SECRET` calls `POST /api/frames` and receives HTTP 401.
- Automated test: the application context fails to start when `APP_JWT_SECRET` is missing or shorter than 32 bytes.

## Traceability (CP7)
- STRIDE: E — Elevation of privilege
- Risk: 6 (L 2 × I 3)
- Priority label: P0
- Threat model PR: https://github.com/code-corhuila/opti-docs/pull/51
- Related to #34: both concern JWT validation, but #34 covers the gateway → `ms-pacientes` boundary and this issue covers the Cut 1 browser → API boundary, so neither duplicates the other.
```

**#49**

```markdown
## Measurable acceptance criteria added in CP7
- Automated test: after N failed `POST /api/auth/login` requests from the same source inside the configured window, request N+1 returns HTTP 429 with a `Retry-After` header.
- Automated test: the response body for an unknown username is identical to the one for a wrong password.
- The limit is enforced in the backend, not only in Nginx, because host port `8081` bypasses Nginx.

## Traceability (CP7)
- STRIDE: S — Spoofing (risk 6, L 2 × I 3) and D — Denial of service (risk 4, L 2 × I 2)
- Priority label: P1
- Threat model PR: https://github.com/code-corhuila/opti-docs/pull/51
```

**#50**

```markdown
## Measurable acceptance criteria added in CP7
- Automated test: a `POST /api/patients` by user `seller` stores one audit record with subject `seller`, role `SELLER`, action, result `SUCCESS` and a correlation ID.
- Automated test: a `POST /api/frames` by `seller` (HTTP 403) stores one audit record with result `DENIED`.
- The backend generates the correlation ID when the request does not carry one (direct calls to port `8081`).

## Traceability (CP7)
- STRIDE: R — Repudiation
- Risk: 4 (L 2 × I 2)
- Priority label: P1
- Threat model PR: https://github.com/code-corhuila/opti-docs/pull/51
```

Overlap check with teammates' open security issues (#33–#35, #38–#40, #45–#47, #55–#57): only #34 touches JWT validation, recorded above as "Related to #34". No other issue covers TB1.
