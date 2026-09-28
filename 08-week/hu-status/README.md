<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Daniela Sanabria Mosquera
- GITHUB_USER: DaniKaizenNetwork
- TEAM: The illusionists
- SPRINT_GOAL: Add the corresponding C4 diagrams to the project documentation, following correct Mermaid usage and good practice through a reviewed pull request.
<!-- CONFIG-END -->
 
## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-XXX-008 | C4 Architecture Diagrams (System Context, Containers, Components, Code) | done | `docs(diagrams): add c1,c2,c3,c4 diagrams` — PR `docs/add-c4-api-diagrams` → `main`, merged (1 commit) *(https://github.com/code-corhuila/opti-docs/pull/18)* |
 
## 2. My individual contribution
 
During Week 08, I contributed to the OptiView project by analyzing the existing documentation and producing the full set of **C4 model diagrams** (Context, Containers, Components and Code) required by `08-uml/README.md` and `08-uml/diagram-index.md`.
 
### Main activities completed
 
- **Documentation analysis**: Reviewed `01-context/overview.md`, `02-domain/domain-map.md`, `05-architecture/overview.md`, the ratified `ADR-002-tech-stack.md`, `02-domain/domain-events.md`, `00-governance/security-policy.md`, and the actual Corte 1 source code (`05-release/optiview-platform-v1.0.0/`) to ground every diagram in what is documented and what is actually built, rather than in assumptions.
- **C1 — System Context**: Modeled OptiView as a black box with its 7 user roles (receptionist, optometrist, inventory administrator, salesperson/cashier, laboratory operator, store administrator, patient) and its external dependencies (Keycloak, the optical laboratory, and a payment provider marked as an unratified candidate).
- **C2 — Containers**: Produced three views — the target synchronous architecture (4 domain microservices behind an API gateway, PostgreSQL with schema-per-service, Keycloak), the target event-driven view (message broker, notification service), and the real Corte 1 deployment (SPA + Nginx, Spring Boot backend, PostgreSQL) — plus a monolith-to-microservices mapping table.
- **C3 — Components**: Documented the internal components of the two containers that actually exist today (the hexagonal-architecture backend and the React SPA), deliberately leaving out the four unbuilt microservices to avoid publishing diagrams for code that does not exist yet.
- **C4 — Code**: Produced class-level diagrams for the domain model, the work-order creation flow (HU-08), and the authentication flow, tracing the real classes in `model/`, `usecase/` and `infrastructure/`.
- **Validation**: Rendered all 9 Mermaid diagram blocks with `mmdc` / a Playwright harness to confirm they are syntactically valid and legible before delivery.
- **Registry update**: Updated `08-uml/diagram-index.md` to reference the four new files, replacing the placeholder template rows.
- **Documentation quality**: Instead of silently resolving them, I flagged 6 concrete contradictions found across existing documents (service-to-stack assignment, database-per-service vs. shared instance, message broker not yet decided, and an unfilled `service-catalog.md` template) directly inside the C2 diagram file, stating which source each diagram followed and which documents still need a team decision.
### Pull request
 
`docs(diagrams): add c1,c2,c3,c4 diagrams` — branch `docs/add-c4-api-diagrams` → `main`, merged (1 commit).
 
## 3. Blockers and risks
 
- The service-to-stack assignment conflicts between `ADR-002-tech-stack.md` (Spring Boot for `ms-pacientes`/`ms-ordenes`, Go for `ms-inventario`/`ms-facturacion`) and `02-domain/domain-map.md` / `00-governance/microservices-documentation.md` (which assign the opposite pairing). The diagrams follow the ADR, but the team needs to correct the other documents.
- The database strategy is inconsistent: `05-architecture/overview.md` diagrams a database per service, while `06-data/models.md` defines a single PostgreSQL instance with one schema per service.
- The message broker (RabbitMQ vs. Kafka) is still undecided across the documents that reference it, so the event-driven container view had to mark it as pending.
- `09-microservices/service-catalog.md` is still the original generic template (`auth-service`, port 3001) and does not reflect the real OptiView services.
## 4. Plan for next week
 
- Bring the 6 documentation conflicts listed above to the team so the losing documents can be corrected and the ADR stays the single source of truth.
- Fill in `09-microservices/service-catalog.md` with the real services once the stack conflict is resolved.
- Export static SVG/PNG versions of the four diagrams into `08-uml/diagrams/exports/` for stakeholders who cannot render Mermaid.
- Revisit the C3/C4 diagrams once any of the four target microservices moves from documentation to actual code.
## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...) — this PR merged directly to `main` from a `docs/...` branch, not the per-HU/per-environment branch convention.
- [x] Testable acceptance criteria — each diagram was validated by rendering it without Mermaid syntax errors.
- [ ] Tests added/updated (unit / integration) — documentation-only change, no code touched.
- [x] DDD / hexagonal boundaries respected (domain has no I/O) — diagrams document the existing hexagonal boundaries as-is, no code was changed.
- [x] No secrets; config via environment variables
## 6. Evidence links
 
- PR: `docs/add-c4-api-diagrams` → `main` (merged, 1 commit) *(https://github.com/code-corhuila/opti-docs/pull/18)*
- Diagrams: `08-uml/diagrams/source/c1-system-context.md`, `c2-container-diagram.md`, `c3-component-diagram.md`, `c4-code-diagram.md`
- Diagram registry: `08-uml/diagram-index.md`
- Sources followed: `05-architecture/overview.md`, `05-architecture/decisions/records/ADR-002-tech-stack.md`, `02-domain/domain-map.md`, `02-domain/domain-events.md`
