<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       10-week/hu-status/README.md  (inside YOUR fork). English. -->
 
# Weekly Status - Week 10
 
<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Daniela Sanabria Mosquera
- GITHUB_USER: DaniKaizenNetwork
- TEAM: The illusionists
- SPRINT_GOAL: Document database-per-service, saga and outbox patterns for the OptiView architecture; validate the persistence strategy against the Corte 2 review and implement CQRS reads for the Figma MVP2 UI spec.
<!-- CONFIG-END -->
 
## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-XXX-012 | Document saga, outbox and CQRS patterns in `05-architecture` | done | [`docs(architecture): add distributed persistence patterns (saga, outbox, CQRS)`](<PR_URL>) — PR [`docs/add-distributed-persistence-patterns` → `main`](<PR_URL>) |
| HU-XXX-013 | Validate OptiView persistence against Corte 2 Unit 3 review feedback | done | Evidence in commit message and `05-architecture/decisions/records/ADR-XXX-persistence-strategy.md` |
| HU-XXX-014 | Align Figma MVP2 read model with CQRS for patient portal and dashboard | in-progress | Branch `feat/mvp2-cqrs-read-model`; Figma prototype linked in PR draft |
 
## 2. My individual contribution
 
During Week 10, I contributed to the OptiView project by documenting the distributed persistence patterns required by Corte 2 Unit 3 (sagas, outbox, CQRS and eventual consistency), auditing the current architecture against the review feedback, and designing the read model for the MVP2 UI (patient portal and staff dashboard).
 
### Main activities completed
 
- **Pattern documentation**: Created `05-architecture/distributed-persistence.md` with diagrams and code sketches for:
  - **Database-per-service**: each service owns its Postgres schema, no cross-service DB reads. Polyglot persistence (Redis for sessions, MongoDB candidates) noted but not yet implemented.
  - **Sagas**: documented the two styles (orchestration vs. choreography) and showed how OptiView will coordinate `PlaceOrder → ReserveStock → ChargePayment → ConfirmOrder` under eventual consistency, with compensations (release stock if charge fails).
  - **Outbox pattern**: explained the dual-write problem (order saved, app crashes, event never published), the fix (write order + outbox row in one transaction), and the relay that publishes from the outbox table to the broker. Applied to `OrdenCreada` and `OrdenEstadoCambiado` events.
  - **CQRS**: separated write model (the Order aggregate in `ms-ordenes`) from the read model (denormalized views for `portal-paciente` and `dashboard`), and documented that reads are eventually consistent (updated from events, ~100ms lag).
- **Corte 2 feedback review**: Cross-checked the Unit 3 lecture against the current `opti-docs`:
  - ✅ Database-per-service: OptiView's schema-per-service (Postgres) matches the required pattern. No cross-service reads found.
  - ✅ Sagas: `OrdenCreada` → `ms-facturacion` and inventory decrement + `return-stock` compensation documented. Compensations for payment failures still pending (needs ADR).
  - ⚠️ Outbox: documented in `domain-events.md` and `authentication.md` but NOT YET IMPLEMENTED in code. Added to blockers.
  - ⚠️ CQRS: only write model exists (ms-ordenes, ms-pacientes aggregates). Read models (for lists, for the dashboard) not yet split. Added to implementation plan.
  - ⚠️ Idempotency: event consumers must be idempotent under at-least-once delivery. Not verified in the current code review.
  
  Created `05-architecture/decisions/records/ADR-XXX-persistence-strategy.md` recording these findings.
- **MVP2 Figma read model**: Analyzed the Figma spec for MVP2 (patient portal: list of orders with status, invoices with balance; dashboard: inventory alerts, daily closing) and designed:
  - `OrderSummary` read view (patientId, workOrderId, status, createdAt, frameDescription, lensDescription, totalPrice, invoiceStatus, lastPaymentDate) — sourced from `OrdenCreada` + `OrdenEstadoCambiado` + `PagoRegistrado` events.
  - `InvoiceSummary` read view (invoiceId, patientId, total, outstandingBalance, status, dueDate) — sourced from `OrdenCreada` + `PagoRegistrado`.
  - `InventoryAlert` read view (frameId/lensId, name, currentStock, minimumThreshold, status) — sourced from `StockMovement` events.
  - Documented projections in branch `feat/mvp2-cqrs-read-model`; Figma prototype includes wireframes for the portal and dashboard that consume these views.
- **Testing Corte 2 concepts**: Completed the self-check quiz from Unit 3 (6/6 correct): database-per-service, saga compensations, dual-write problem, outbox pattern, CQRS separation, eventual consistency.
### Pull request
 
`docs(architecture): add distributed persistence patterns (saga, outbox, CQRS)` — branch `docs/add-distributed-persistence-patterns` → `main` (1 commit, 3 files: `distributed-persistence.md`, `ADR-XXX-persistence-strategy.md`, updated `overview.md`).
 
## 3. Blockers and risks
 
- **Outbox not yet implemented**: the pattern is documented, but the code (relay service, outbox table schema, idempotency keys) does not exist. This blocks reliable event publishing and may cause lost events if the broker crashes.
- **CQRS read models not yet built**: the dashboard and portal still query the write models (slow joins, eventual inconsistency problems). Implementing the three read views above needs a new microservice or a standalone read-model updater, and changes to the CI/CD for event subscriptions.
- **Payment failure compensation pending**: if the charge fails in the saga, the inventory deduction must be compensated (return-stock), but the inverse (if return-stock fails) is not yet specified. Needs a team decision and an ADR.
- **Idempotency keys not verified**: the current event consumers (ms-facturacion, ms-pacientes) may not handle duplicate events correctly under at-least-once delivery. Needs testing (Pact contract tests or integration tests).
- **Corte 2 feedback**: the review asked about polyglot persistence (why not MongoDB for the catalog?), but OptiView uses only Postgres so far. Team decision needed: stick with Postgres or adopt MongoDB for Inventory (the catalog).
## 4. Plan for next week
 
- Complete the MVP2 Figma prototype (branch `feat/mvp2-cqrs-read-model`) and open a PR with the read-model design and wireframes for review.
- Implement the outbox table schema and the relay service (or Debezium CDC) to make event publishing reliable.
- Write Pact contract tests for idempotency: `ms-facturacion` and `ms-pacientes` must handle duplicate events (same `eventId`, same `correlationId`).
- Record the three pending decisions in ADRs: payment compensation, polyglot persistence strategy, and CQRS read-model implementation approach (new service vs. updater).
- Cross-check the payload examples in `07-api/contracts/openapi/` against the CQRS read views (do all fields needed by the UI come through the events?).
## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment — documentation repository: the `docs/...` branch goes straight to `main`.
- [x] Testable acceptance criteria — Corte 2 self-check quiz passed; read-model schema matches Figma spec.
- [ ] Tests added/updated — idempotency tests pending; outbox and CQRS tests are implementation work, not yet started.
- [x] DDD / hexagonal boundaries respected (domain has no I/O) — the read models are projections of events, no business logic added.
- [x] No secrets; config via environment variables
## 6. Evidence links
 
- Commit: [`docs(architecture): add distributed persistence patterns (saga, outbox, CQRS)`](<COMMIT_URL>)
- PR: [`docs/add-distributed-persistence-patterns` → `main`](<PR_URL>)
- Documentation: `05-architecture/distributed-persistence.md`, `05-architecture/decisions/records/ADR-XXX-persistence-strategy.md`
- Figma prototype: branch `feat/mvp2-cqrs-read-model` (PR draft, not yet open)
- Corte 2 Unit 3 evidence: Completed self-check quiz (6/6); pattern review in `ADR-XXX-persistence-strategy.md`
