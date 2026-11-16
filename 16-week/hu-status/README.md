<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       16-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 16

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Daniela Sanabria Mosquera
- GITHUB_USER: DaniKaizenNetwork
- TEAM: The illusionists
- SPRINT_GOAL: Close Corte 3: complete the certification deliverable (README + architecture design note + two ADRs) on top of the released OptiView MVP 3 (`v3.0.0`), and record the final status of my STRIDE security issues.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-C3-CERT | Corte 3 certification: README (cover, study log, labs, exam, skills) | doing | evidence uploaded in `https://github.com/DaniKaizenNetwork/sistemas-distribuidos-2026-b-g2/commit/8ddd0df`; README still pending |
| HU-C3-DESIGN | Certification architecture design note + ADR-C3-01 / ADR-C3-02 | todo | `certification/design/` (template, not filled yet) |
| HU-C3-SEC-FOLLOWUP | Final status of STRIDE issues `#48` (P0), `#49` (P1), `#50` (P1) | todo | `https://github.com/code-corhuila/opti-docs/issues/48` · `/49` · `/50` |

## 2. My individual contribution
- Week 16 started on 2026-11-16; at the time of this update there are no new commits of mine in `opti-docs`, the `opti-*` repositories or this fork for this week.
- Starting point: the certification evidence (20 screenshots) is already in `certification/evidence/`, and my STRIDE model (PR `#51`) is merged and included in the MVP 3 release (`v3.0.0`, 2026-11-15).

## 3. Blockers and risks
- `certification/README.md` and `certification/design/*` must be fully filled before the deadline; any `<...>` left counts as missing.
- Issue `#48` (P0, fallback JWT signing key) is still not linked to a fix; it blocks any non-local deployment according to my STRIDE model.
- My profile repo `DaniKaizenNetwork/DaniKaizenNetwork` still needs the `CONFIG` block for the automatic grading.

## 4. Plan for next week
- Course close: no next sprint. Remaining items above are to be closed during this week.

## 5. Compliance self-check
- [ ] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [ ] Testable acceptance criteria
- [ ] Tests added/updated (unit / integration)
- [ ] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

## 6. Evidence links
- Certification evidence: `https://github.com/DaniKaizenNetwork/sistemas-distribuidos-2026-b-g2/tree/main/certification/evidence`
- STRIDE model (merged): `https://github.com/code-corhuila/opti-docs/pull/51`
- MVP 3 release: tag `v3.0.0` across the `code-corhuila/opti-*` repositories
