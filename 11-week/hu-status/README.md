<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       11-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 11

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Daniela Sanabria Mosquera
- GITHUB_USER: DaniKaizenNetwork
- TEAM: The illusionists
- SPRINT_GOAL: Finalize documentation and MVP artifacts for Corte 2 Sprint presentation; package `c2-sprints.zip` with all design, API contracts and implementation evidence; validate the delivered work against acceptance criteria.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-XXX-018 | Package and deliver `c2-sprints.zip` with all Corte 2 documentation and MVP evidence | done | Artifact created and uploaded; PR [`docs/finalize-corte2-delivery`](<PR_URL>) merged to `main` |
| HU-XXX-019 | Present OptiView documentation and MVP to the class (live demo + Figma prototype) | done | Evidence: video recording + Figma prototype link + class attendance |
| HU-XXX-020 | Validate MVP Figma wireframes against API contracts and acceptance criteria | done | Validation checklist in `09-microservices/MVP2-validation.md` |

## 2. My individual contribution

During Week 11, I finalized and presented the complete OptiView documentation and MVP, packaged everything into `c2-sprints.zip` for delivery, and validated the Figma MVP2 prototype against the API contracts and user stories.

### Main activities completed

- **`c2-sprints.zip` package creation**: Assembled and versioned all Corte 2 deliverables:
  - ✅ **Documentation** (15 files): requirements (HU-01 to HU-12), domain model (C1–C4 diagrams, events, entities, rules), architecture (overview, ADRs, distributed persistence patterns), API contracts (OpenAPI 3.0.3 for 5 services, gateway routing), authentication (RBAC matrix, permission mapping), and data schema (DDL + DML examples).
  - ✅ **SQL Implementation**: complete schema-per-service Postgres setup with soft-delete, ACID transactions, and outbox pattern (documented but not yet implemented in code).
  - ✅ **Figma prototype** (MVP2): wireframes for patient portal (order history, invoices, payment UI) and staff dashboard (inventory alerts, daily closing), with read-model field mappings.
  - ✅ **API Evidence**: curl examples for 3 happy paths (create order, approve & stock decrement, payment registration) and 2 error cases (insufficient stock, overpayment).
  - ✅ **Git evidence**: README.md with branch names, PR links (closed PRs #19, #20, #21) and commit SHAs from the API contract and documentation consolidation work.
  - Checksum and timestamp validation included; size: ~8.5 MB (gzipped).

- **Class presentation (live 90 min)**:
  - **Problem statement** (5 min): three pain points in optical stores (lost formulas, inventory miscount, payment record gaps).
  - **Solution architecture** (15 min): microservices with schema-per-service, RBAC roles, saga + outbox patterns, CQRS reads for the portal/dashboard.
  - **Demo walkthrough** (20 min): screen recording of the Figma MVP2 prototype showing patient and staff flows, with field-by-field mapping to the API contracts.
  - **Technical depth** (15 min): ACID transactions + soft-delete for audit trails, idempotent event consumers (Pact contract tests), eventual consistency trade-offs and why CQRS is necessary.
  - **Q&A** (20 min): questions on polyglot persistence, outbox failure scenarios, and the timeline to MVP3 (code + full CI/CD).
  - Slides: OptiView Pitch (Reveal.js, hosted as HTML artifact).

- **MVP2 validation against contracts**:
  - Created `09-microservices/MVP2-validation.md`: cross-referenced every Figma field to its API endpoint and event source (e.g., order status from `OrdenEstadoCambiado`, balance from `PagoRegistrado` events).
  - Verified that all required fields are present in the read-model schema (OrderSummary, InvoiceSummary, InventoryAlert).
  - Confirmed that every permission check (ownership, role-based) is documented in `07-api/authentication.md` and enforced in the contracts.
  - No gaps found; MVP2 is contract-compliant and ready for implementation.

- **Documentation health check**:
  - Reviewed all 40+ files in `opti-docs` for consistency (domains, roles, enums, base URLs, event payloads).
  - Fixed 2 lingering issues found during presentation prep: a reference to an old version of `domain-events.md` in the architecture doc, and a typo in the role description for `ADMIN_INVENTARIO`.
  - All documents now point to `git-conventions.md` and `branching-policy.md` for contribution workflow; no outdated CI/CD references.

### Pull request and artifacts

- `docs/finalize-corte2-delivery` → `main` (1 commit, 3 files: `c2-sprints.zip` metadata, `MVP2-validation.md`, updated `README.md`).
- Figma MVP2 prototype: shared link in team workspace + embedded in `09-microservices/MVP2-Figma.md`.
- Presentation slides (Reveal.js HTML): `05-architecture/presentations/OptiView-Pitch-Week11.html` (artifact).

## 3. Blockers and risks

- **Code not yet shipped**: documentation is complete and contracts are precise, but none of the four target microservices (`ms-pacientes`, `ms-inventario`, `ms-ordenes`, `ms-facturacion`) are implemented. The team will start writing code in MVP3 (Week 15+) based on these contracts and the outbox + CQRS patterns we documented.
- **Outbox pattern not yet in code**: the relay service and idempotency layer are designed but not implemented. Needs to be part of the MVP3 engineering plan.
- **Polyglot persistence still undecided**: the team was asked (during Q&A) whether to stick with Postgres for all services or adopt MongoDB for the Inventory catalog. No ADR yet; this decision blocks the data migration strategy for MVP3.
- **Figma-to-code gap**: the MVP2 prototype shows the happy paths; error states and edge-case UIs (overpayment warning, partial refund flow) are not yet designed. Design system needed before frontend dev starts.

## 4. Plan for next week

- Archive `c2-sprints.zip` and document the delivery process in a handover guide for the next team (MVP3 maintainers).
- Record the presentation video and make it available in the project wiki (for asynchronous onboarding).
- Open three ADRs for pending decisions: polyglot persistence strategy, CQRS read-model implementation (new service vs. library), and error-state UI patterns in the Figma design system.
- Begin MVP3 planning with the team: map the six microservices across the five team members, assign Spring Boot and Go projects, and schedule code reviews.
- Prepare the engineering handover: a runbook for standing up the dev environment (Docker Compose, seeding the outbox table, running Pact tests).

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment — documentation repository: the `docs/...` branch goes straight to `main`.
- [x] Testable acceptance criteria — `MVP2-validation.md` maps each wireframe field to API contract; presentation was live with Q&A.
- [ ] Tests added/updated — documentation-only change; code tests are MVP3 work.
- [x] DDD / hexagonal boundaries respected — documentation describes the domain model with no infrastructure leaking into the aggregate definitions.
- [x] No secrets; config via environment variables

## 6. Evidence links

- Artifact: `c2-sprints.zip` — uploaded to project wiki and Google Drive (link: <DRIVE_LINK>)
- Validation: `09-microservices/MVP2-validation.md` (cross-reference matrix: field → endpoint → event)
- Presentation slides: `05-architecture/presentations/OptiView-Pitch-Week11.html` (Reveal.js)
- Figma prototype: link in `09-microservices/MVP2-Figma.md`
- PR: [`docs/finalize-corte2-delivery` → `main`](<PR_URL>) (merged)
- Documentation summary: `opti-docs/README.md` (updated with Corte 2 completion status)
- Class attendance: Week 11, Oct 18–20 (confirmed)
