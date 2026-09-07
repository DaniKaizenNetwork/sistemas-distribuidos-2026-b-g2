# Weekly Status - Week 05

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->

* FULL_NAME: Daniela Sanabria Mosquera
* GITHUB_USER: DaniKaizenNetwork
* TEAM: The illusionists
* SPRINT_GOAL: Definition and documentation of the OptiView Design System, including design tokens, framework-agnostic component specifications, responsive UX patterns, and WCAG AA accessibility standards across all four client applications.

<!-- CONFIG-END -->

## 1. User stories worked this week

| HU ID      | Title                                                                | Status (todo/doing/done) | Evidence (PR or commit URL)                                                           |
| ---------- | -------------------------------------------------------------------- | ------------------------ | ------------------------------------------------------------------------------------- |
| HU-XXX-003 | OptiView Design System Definition, Tokens and Component Guidelines | done                     | `docs(design-system): define design tokens, component guidelines, and accessibility` |

## 2. My individual contribution

During Week 06, I contributed to the OptiView project by establishing and documenting the comprehensive **Design System** to ensure visual and operational consistency across all four client applications (`portal-admin`, `portal-ventas`, `portal-paciente`, and `dashboard`).

### Main activities completed

* **Design Tokens Definition**:
  * Established the base color palette (optical/clinical trust theme: blue-teal primary, warm neutrals) and semantic colors (`success`, `warning`, `error`, `info`).
  * Mapped domain status codes (`QUOTATION`, `APPROVED`, `IN_LABORATORY`, `READY`, `DELIVERED`, `CANCELLED`, `PAID`, `PARTIAL`, low-stock) to unified color tokens across Angular and React applications.
  * Specified typography scales using `Source Sans 3` (body), `Manrope` (headings), and `IBM Plex Mono` (SKUs and order codes), enforcing a mobile body font floor of 16px (`--font-size-base`) for `portal-paciente`.
  * Defined standardized spacing scales, border radii, and box shadow tokens.
* **Component Specifications**:
  * Documented framework-agnostic rules for Primary/Secondary/Danger/Ghost buttons, form inputs, comboboxes with real-time search, and `America/Bogota` localized DatePickers.
  * Defined notification patterns (Toasts, Inline Alerts, Modals, Skeleton loaders) and paginated data table standards capped at 50 rows/page.
  * Designed mobile-specific components for `portal-paciente`, including sticky bottom navigation, vertical status timelines, balance cards, and pull-to-refresh interactions.
* **UX Patterns & Error Handling**:
  * Structured UX guidelines for destructive action confirmations, sub-100ms UI feedback, real-time input prevention rules, and onboarding empty states.
  * Mapped standardized HTTP error handling strategies (network retry toasts, 401 JWT expiration redirects, 403 RBAC messages, 404 views, and sanitized 500 error toasts).
* **Accessibility (WCAG AA)**:
  * Established mandatory minimums for 4.5:1 text contrast, full keyboard navigation, visible focus indicators, and 44x44px minimum touch targets for mobile.

### Commit

The contribution was implemented in the following commit:

`docs(ux): define design system tokens and components for the 4 client apps`

## 3. Blockers and risks

* The proposed brand color palette is pending final team approval (`⚠️ Pendiente de definición por el equipo`).
* Maintaining visual alignment across separate Angular (desktop-first back office) and React (mobile-first portal and dashboard) implementations requires strict adherence to CSS custom properties without a shared component library.

## 4. Plan for next week

* Present the proposed design tokens and branding palette to the team for approval.
* Create the master CSS custom properties / theme configuration file for distribution across frontend projects.
* Begin layout implementation and routing structures for client applications based on the design system.

## 5. Compliance self-check

* [x] Conventional Commits - `type(scope): summary`
* [x] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
* [x] Testable acceptance criteria
* [ ] Tests added/updated (unit / integration)
* [x] DDD / hexagonal boundaries respected (domain has no I/O)

## 6. Evidence links

* Commit: `docs(ux): define design system tokens and components for the 4 client apps`
* Design System Documentation: `12-ux-ui/design-system.md`
* Navigation Map: `12-ux-ui/navigation-map.md`
* Security & Roles Policy: `00-governance/security-policy.md`
