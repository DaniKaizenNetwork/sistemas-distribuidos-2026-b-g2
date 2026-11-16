<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       15-week/hu-status/README.md  (inside YOUR fork). English. -->
 
# Weekly Status - Week 15
 
<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Daniela Sanabria Mosquera
- GITHUB_USER: DaniKaizenNetwork
- TEAM: The illusionists
- SPRINT_GOAL: Ship the MVP 3 release (`v3.0.0`) of OptiView with my STRIDE threat model for the browser-to-API boundary (TB1) merged into the team docs, and upload the evidence of the Corte 3 certification.
<!-- CONFIG-END -->
 
## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-C3-SEC-TB1 | STRIDE threat model: browser-to-API boundary (TB1), merged into MVP 3 docs | done | PR `https://github.com/code-corhuila/opti-docs/pull/51` (merged 2026-11-10, merge commit `702e8a6`) · commit `59bbc85` `docs(security): address review on STRIDE for browser-to-API boundary` |
| HU-C3-CERT | Corte 3 certification: course completion evidence | doing | commits `https://github.com/DaniKaizenNetwork/sistemas-distribuidos-2026-b-g2/commit/8ddd0df` (`Add certification completion evidence`) · `https://github.com/DaniKaizenNetwork/sistemas-distribuidos-2026-b-g2/commit/de7d09f` (`Rename enrollment evidence image`) |
 
## 2. My individual contribution
- **STRIDE model merged (closes the Week 13/14 carry-over)**: my PR `#51` (`security/stride-DaniKaizenNetwork-final`) was reviewed and merged into `opti-docs/main` on 2026-11-10 together with the other team STRIDE models (`#52`, `#54`, `#58`, `#60`). The document `05-architecture/security/stride-DaniKaizenNetwork.md` covers the six STRIDE categories for the browser → Nginx → Spring Boot → PostgreSQL path, with likelihood × impact scores and residual risk.
- **Part of the MVP 3 release**: the `opti-docs` `CHANGELOG.md` section `[3.0.0] - 2026-11-15` lists the STRIDE threat models (browser → API among them) as part of the MVP 3 cut, and the `release/3.0.0` branches were promoted to `main` and tagged `v3.0.0` on 2026-11-15 across the 16 `opti-*` repositories.
- **Corte 3 certification evidence**: uploaded the 20 evidence screenshots to `certification/evidence/` — enrollment (`00-enrollment.png`), the 14 chapters (`01`–`14`), the 4 labs (`15-lab-1` … `18-lab-4`) and the final exam (`19-final-exam.png`) — and renamed the enrollment image to the required file name.
- I did not author `opti-*` product (code) commits this week. The MVP 3 features were implemented by teammates: simulated payments with OTP and private payment evidence (`opti-sales-api`, `opti-sales-db`, `opti-api-gateway`), invoice PDF (`opti-sales-api`, `opti-sales-portal`), optical-prescription PDF (`opti-customers-api`, `opti-customers-portal`), PDF/Excel reports (`opti-sales-portal`) and authenticated downloads (`opti-front`).
## 3. Blockers and risks
- `certification/README.md` is still the template (cover, study log, labs, final exam score and skills summary are `<...>`), and `certification/design/` (architecture design note, `ADR-C3-01`, `ADR-C3-02`) is not filled yet. Text left as `<...>` counts as missing.
- Security issues opened from my STRIDE model (`#48` P0 fallback JWT signing key, `#49` P1 login spoofing/DoS, `#50` P1 write-audit trail) have no linked fix in this week's commits, so I cannot mark them as closed. Login rate limiting exists in `opti-api-gateway` (`limit_req zone=login`), which partially covers `#49`.
- My profile repo `DaniKaizenNetwork/DaniKaizenNetwork` does not contain the `CONFIG` block (`FULL_NAME` / `GITHUB_USER`) the course README asks for.
## 4. Plan for next week
- Fill `certification/README.md` (cover, study log with hours, labs, final exam score, skills summary).
- Write `certification/design/architecture-design-note.md`, `ADR-C3-01.md` and `ADR-C3-02.md` using the team's `opti-docs` as evidence.
- Follow up on issues `#48`, `#49` and `#50` and record their final status.
## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [ ] Testable acceptance criteria
- [ ] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables
> Note: the STRIDE PR used Conventional Commits; the certification upload commits did not. Branch-per-environment and test items do not apply to documentation and certification evidence.
 
## 6. Evidence links
- PR (merged): `https://github.com/code-corhuila/opti-docs/pull/51`
- Document: `05-architecture/security/stride-DaniKaizenNetwork.md` (in `code-corhuila/opti-docs`)
- MVP 3 changelog: `CHANGELOG.md` section `[3.0.0] - 2026-11-15` (in `code-corhuila/opti-docs`)
- Release: tag `v3.0.0` (e.g. `https://github.com/code-corhuila/opti-front/releases/tag/v3.0.0`)
- Certification evidence: `https://github.com/DaniKaizenNetwork/sistemas-distribuidos-2026-b-g2/tree/main/certification/evidence`
 
