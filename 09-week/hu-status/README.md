<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       09-week/hu-status/README.md  (inside YOUR fork). English. -->
 
# Weekly Status - Week 09
 
<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Daniela Sanabria Mosquera
- GITHUB_USER: DaniKaizenNetwork
- TEAM: The illusionists
- SPRINT_GOAL: Make the API gateway, inventory and billing contracts, and the domain, data and governance documents that describe them, consistent with each other (synchronous stock flow, permission matrix, gateway routing and public base URLs) through a reviewed pull request.
<!-- CONFIG-END -->
 
## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-XXX-010 | Align gateway, inventory and billing API contracts with domain docs | done | [`docs(governance): align gateway, inventory and billing contracts with domain docs`](<PR_1_COMMITS_URL>) — PR [`docs/align-api-contracts-gateway-inventory-billing` → `main`](<PR_1_URL>) |
 
## 2. My individual contribution
 
During Week 09, I contributed to the OptiView project by auditing the whole `07-api` folder against the rest of the documentation and fixing the contradictions I found in the gateway, inventory and billing contracts and in the documents that describe them.
 
### Main activities completed
 
- **Audit of `07-api`**: Read the three guideline documents and the seven OpenAPI files in detail and cross-checked them against `02-domain`, `04-requirements`, `06-data`, `09-microservices` and `00-governance`, which produced a list of 16 inconsistencies (3 of them design-level).
- **Stock flow decision**: Removed the double stock deduction. Stock is now decremented synchronously by `ms-ordenes` through `decrement-stock`, and `ms-inventario` no longer consumes `OrdenCreada`. I added `return-stock` for frames and lenses (a `RETURN` movement) as the compensation when an approval fails half-way. Updated `ms-inventario.yaml`, `domain-events.md`, `domain-map.md` and `service-catalog.md`.
- **Event ownership**: `OrdenCreada` is consumed only by `ms-facturacion`. `ms-pacientes` consumes `OrdenEstadoCambiado` (`DELIVERED`) to refresh the last-visit date (HU-09 Scenario 3), which no contract allowed before.
- **API gateway contract**: Rewrote `api-gateway.yaml` with a routing table (the mount point stripped for each service), the `X-Patient-Id` header, the `httpOnly` cookie option converted to a Bearer header by the gateway, and a rule that internal `decrement-stock` / `return-stock` operations are answered with `404`. Replaced the invalid `{id*}` paths.
- **Permission matrix**: Widened the role table in `07-api/authentication.md` to match what the contracts already required and added an endpoint → permission matrix (new permissions `orders:approve`, `orders:finalize`, `invoices:read`, `billing:daily-closing:read`). `security-policy.md` now points to it instead of repeating it.
- **Patient ownership rule**: Defined the `patientId` claim and applied the "own record only" rule to every `PACIENTE` endpoint (single reads → `403`, lists → forced filter), and documented it in `ms-facturacion.yaml`.
- **Public base URLs and enums**: Unified every `servers.url` to `/api/v1/...` through the gateway, normalized the payment methods to `NEQUI` / `DAVIPLATA` (also in `models.md`, `entities-and-rules.md` and `data-dictionary.md`), added `traceId` to the shared error examples, and listed in `07-api/README.md` what is deliberately not in the contracts.
- **Validation**: Parsed all six OpenAPI files as YAML and checked that every `$ref` resolves (0 broken references).
### Pull request
 
`docs(governance): align gateway, inventory and billing contracts with domain docs` — branch `docs/align-api-contracts-gateway-inventory-billing` → `main` (1 commit, 19 files).
 
## 3. Blockers and risks
 
- The companion pull request (`ms-ordenes` and `ms-pacientes`, by `jssanchezzz`) depends on this one for `return-stock`, the permission matrix and the gateway routing, so this PR should be merged first.
- Several changes are design decisions that still need the team's agreement: synchronous stock calls instead of events, the cookie-to-Bearer conversion at the gateway, and the new permissions in the RBAC table.
- Renaming the payment methods to `NEQUI` / `DAVIPLATA` changes the `CHECK` constraint documented in `06-data/models.md`; any existing data with `Nequi` / `Daviplata` would need a migration.
- The contracts were validated only for YAML syntax and `$ref` resolution, not with a linter such as Spectral or Redocly.
## 4. Plan for next week
 
- Review the companion PR for `ms-ordenes` and `ms-pacientes` and merge both in order.
- Bring the three design decisions above to the team and record them in an ADR if they are accepted.
- Run Spectral over `07-api/contracts/openapi/` and fix whatever it reports.
- Align the SQL analysis and the sustentation script in the project docs, which still assign the services to the opposite Java/Go pairing and describe Saga/Outbox stock events.
## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...) — documentation repository: the `docs/...` branch goes straight to `main` as `branching-policy.md` defines for `-docs` repositories.
- [x] Testable acceptance criteria — every OpenAPI file parses and every `$ref` resolves.
- [ ] Tests added/updated (unit / integration) — documentation-only change, no code touched.
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables
## 6. Evidence links
 
- Commit: [`docs(governance): align gateway, inventory and billing contracts with domain docs`](<PR_1_COMMITS_URL>)
- PR: [`docs/align-api-contracts-gateway-inventory-billing` → `main`](<PR_1_URL>)
- Contracts updated: `07-api/contracts/openapi/api-gateway.yaml`, `ms-inventario.yaml`, `ms-facturacion.yaml`, `_shared.yaml`
- Documents updated: `07-api/authentication.md`, `07-api/guidelines.md`, `02-domain/domain-events.md`, `02-domain/domain-map.md`, `09-microservices/service-catalog.md`, `00-governance/security-policy.md`
