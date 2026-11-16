<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       12-week/hu-status/README.md  (inside YOUR fork). English. -->
 
# Weekly Status - Week 12
 
<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Daniela Sanabria Mosquera
- GITHUB_USER: DaniKaizenNetwork
- TEAM: The illusionists
- SPRINT_GOAL: Close Corte 2 after the MVP 2 delivery (`c2-sprints.zip` and OptiView pitch deck) and prepare the start of Corte 3 (security and threat modeling of OptiView).
<!-- CONFIG-END -->
 
## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-C2-CLOSE | Corte 2 delivery: `c2-sprints.zip` and interactive OptiView pitch deck available in my fork | done | commits `https://github.com/DaniKaizenNetwork/sistemas-distribuidos-2026-b-g2/commit/286e361` (`Create c2-sprints.zip`) · `https://github.com/DaniKaizenNetwork/sistemas-distribuidos-2026-b-g2/commit/37df3e0` (`Add interactive OptiView pitch deck`), both 2026-10-18 |
| HU-C3-SEC-PREP | Prepare Corte 3 security work: STRIDE threat model of the browser-to-API boundary (TB1) | todo | Work started in Week 13 (`code-corhuila/opti-docs` branch `security/stride-DaniKaizenNetwork`, first commit 2026-10-29) |
 
## 2. My individual contribution
- The Corte 2 deliverables (`c2-sprints.zip` and the interactive OptiView pitch deck) were pushed to my fork on 2026-10-18, right at the start of this week, and remain the reference artifacts of the MVP 2 cut.
- Between 2026-10-19 and 2026-10-25 there are no commits or pull requests of mine in `code-corhuila/opti-docs`, in the 16 `code-corhuila/opti-*` repositories or in this fork. The last team change before this week was the MVP 2 ADR set (`ADR-003` to `ADR-006`, `opti-docs` release `2.1.0`, 2026-10-08), and the next activity in the repositories starts on 2026-10-26 with the Corte 3 certification structure and the STRIDE work.
## 3. Blockers and risks
- No repository activity this week for the whole team, so there is no new code or documentation evidence to attach.
- Known open risks carried from Corte 2: the outbox relay and the CQRS read models were documented but not yet implemented in code.
## 4. Plan for next week
- Start the Corte 3 security work: map the attack surface and build the STRIDE threat model for the browser-to-API boundary (TB1) of OptiView Cut 1.
- Turn the confirmed STRIDE findings into tracked security issues for the team backlog.
## 5. Compliance self-check
- [ ] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [ ] Testable acceptance criteria
- [ ] Tests added/updated (unit / integration)
- [ ] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables
## 6. Evidence links
- Corte 2 package: `https://github.com/DaniKaizenNetwork/sistemas-distribuidos-2026-b-g2/blob/main/c2-sprints.zip`
- Pitch deck commit: `https://github.com/DaniKaizenNetwork/sistemas-distribuidos-2026-b-g2/commit/37df3e0`
 
