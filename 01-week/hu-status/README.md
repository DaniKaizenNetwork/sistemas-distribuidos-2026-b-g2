<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       01-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 01

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Daniela Sanabria Mosquera
- GITHUB_USER: DaniKaizenNetwork
- TEAM: The illusionists
- SPRINT_GOAL: Generation of firts documentacion(PDR) based in the intructions by the teacher
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-XXX-001 |  |  |  |

## 2. My individual contribution
-# PRD — OptiView
## Comprehensive Management System for Optical Stores

---

## 1. Executive Summary

**OptiView** is a SaaS web application in the form of an administrative dashboard, designed for the comprehensive management of optical stores (eyeglasses/glasses stores), accompanied by a **separate patient portal**. The design should be clean, professional, modern, and functional, inspired by the aesthetics of tools such as **Linear, Notion, or Stripe Dashboard**: plenty of whitespace, clear typography, restricted colors, and zero visual noise.

---

## 2. Business Context

An optical store receives patients who need glasses. The optometrist performs a visual examination and generates an **optical prescription** with values for each eye: sphere (SPH), cylinder (CYL), axis (AXIS), addition (ADD), and pupillary distance (PD).

With this prescription, the patient chooses a **frame** and a type of **lens** (single vision, bifocal, or progressive) with optional treatments (anti-reflective, photochromic, blue filter). All of this is consolidated into a **work order (WO)** that is sent to an optical laboratory to cut and mount the lenses. When they are ready, the patient picks up their glasses and pays (they may make partial payments).

---

## 3. Users and Roles

| Role | Description |
|---|---|
| **Administrator** | Manages everything: inventory, reports, configuration, and system users. |
| **Optometrist** | Registers patients, creates optical prescriptions, and views visual history. |
| **Salesperson** | Creates work orders (selects frame + lens + prescription), generates quotes, and records payments. |
| **Patient** (external portal) | Views their prescription, checks their order status, reviews outstanding balance, and receives check-up reminders. |

---

## 4. Visual Direction and Design System

### 4.1 Color Palette

- **Primary color**: teal (associated with visual health, trust, and clarity).
- **Semantic accents**:
  - Green → success / completed
  - Amber → warnings / in progress
  - Red → errors / low stock
  - Blue → information
- **Backgrounds**: very light gray for the canvas, white for cards and panels.

### 4.2 Typography

- Modern sans-serif (Inter, SF Pro, or similar).
- Only two weights: **regular** (body) and **medium** (titles and highlighted data).
- Do not use aggressive bold.

### 4.3 Borders and Shape

- Hairline borders (1px light gray).
- Soft rounded corners: 8px for controls, 12px for cards.
- No decorative shadows, gradients, or glow effects — everything should be flat and clean.

### 4.4 Iconography

- Consistent outline style (Tabler Icons or Phosphor Icons).

---

## 5. Navigation Structure

### 5.1 Main Layout (Administrator / Optometrist / Salesperson)

- **Fixed left sidebar**:
  - Logo "OptiView" + eye icon.
  - "Main" section: Dashboard, Patients, Work Orders, Inventory.
  - "System" section: Billing, Reports, Settings.
  - Sidebar footer: optical store name + city (e.g., "Óptica Central — Neiva, Huila").
- **Content area** to the right of the sidebar, with a contextual header for each screen.

### 5.2 Patient Portal (Separate Design)

- Mobile-first layout, without a sidebar.
- Bottom tab bar or stacked-card navigation.
- Warmer and friendlier tone, with minimal complexity.

---

## 6. Functional Requirements — Screens

### 6.1 Dashboard

The user lands here after logging in. It should answer the question *"how is the day going?"* within 3 seconds.

- Top bar: greeting ("Good morning, [name]") + current date + primary "New Order" button.
- **4 metric cards** in a horizontal row:
  - Orders today (number + % change vs. yesterday).
  - Daily billing (amount in COP + % change).
  - New patients (this week).
  - Orders pending delivery (with an indicator of how many are ready today).
- **Left panel (60% width)** — "Recent Orders": table/list with patient avatar + name, brief details (frame + lens), status badge, total amount. Each row is clickable.
- **Right panel (40% width)** — "Alerts": list of notifications with color-coded icons according to severity:
  - Low stock (red): products below the minimum.
  - Overdue check-ups (amber): patients without a check-up in +12 months.
  - Outstanding receivables (blue): invoices with a balance > 30 days.
  - Laboratory (amber): orders that have gone many days without a status change.

### 6.2 Patients — List

- Top bar: title "Patients" + search bar (by name or document) + "New Patient" button.
- Patient list with: avatar (initials), full name, document number, health insurance provider/insurer, date of last visit.
- Each row is clickable → goes to the details. Subtle hover effect to indicate interactivity.

### 6.3 Patients — Details (Patient Record)

- Breadcrumb/back button: "← Back to patients".
- Header: large avatar + name + document + age + health insurance provider. Buttons: "Edit" and "New Order".
- **Tab bar with 3 tabs**:

**"Information" Tab**

- Left card "Personal Information": phone, email, health insurance provider, next check-up (highlighted in amber if it is approaching).
- Right card "Summary": total visits, latest prescription, active orders, outstanding balance.

**"Prescription" Tab**

- Card with the current prescription. Table with columns: [blank], SPH, CYL, Axis, ADD, PD. Rows: OD (right eye) and OS (left eye). Headers in gray, values in monospace typography or medium weight.
- Indicator of the optometrist who recorded it and date.
- Informational banner below: "Recommended type: Progressive — Presbyopia detected" (subtle teal color).

**"History" Tab**

- Vertical timeline with dots. Each entry: date, summarized OD/OS values (sphere only), lens type, badge if it is the current prescription. Allows viewing the patient's visual evolution over time.

### 6.4 Work Orders — List

- Top bar: title "Work Orders" + "New Order" button.
- Horizontal chip/pill filters: All, In Laboratory, Ready, Pending Payment. The active chip has a teal background.
- Order list with: patient avatar + name, WO code (e.g., WO-2851 in gray), frame + lens, status badge (colored according to status), total amount + balance indicator if there is an outstanding amount. Clickable → details.

### 6.5 Work Orders — Details

This is the **most important screen in the system**. It represents the complete workflow of an order.

- Back button + header with WO code, patient name, creation date. Buttons: "Print" and "Advance Status" (primary teal button).
- **Progress card** (full width):
  - Horizontal progress bar with percentage.
  - Below, **5 steps in a row** as rounded pills: Quote → Approved → In Laboratory → Ready → Delivered.
- Visual states for each step:
  - Completed: light green background + check icon + dark green text.
  - Current: light blue background + blue border + dark blue text (highlighted).
  - Pending: light gray background + light gray text.
- **Two columns below**:
  - Left "Order Details": label-value rows with Frame (name + reference), Lens (type + material), Treatments, Assigned Laboratory, Fitting Height, Estimated Delivery (highlighted in teal).
  - Right "Billing": breakdown (frame $X, lenses $Y, treatments $Z), separator, total, amount paid (in green), outstanding balance (in red if applicable). "Record Payment" button at the bottom.

### 6.6 Inventory

- Top bar: title "Inventory" + grid/list toggle + "Add Frame" button.
- **4 metric cards**: total frames, inventory value, low-stock items (red), active suppliers.
- Search by brand, model, or reference.
- Grid view: cards with glasses icon, reference name, brand, color, price, stock indicator (green if OK, red with alert icon if low).
- List view: compact rows with the same information horizontally.

### 6.7 Patient Portal (Separate Mobile-First App)

Patient-centered, non-technical design. Simple, friendly, read-only (except payments).

- Header: avatar + "Hello, [name]" + subtitle "Your eye health up to date".
- Card 1 — Active Order: product name (e.g., "Progressive Glasses — Ray-Ban"), status badge, progress bar with percentage, estimated delivery date.
- Card 2 — Your Prescription: compact table with SPH, CYL, Axis, ADD, PD for OD and OS. Read-only.
- 2 small cards in a 2-column grid:
  - Next check-up: calendar icon + date + "Annual check-up recommended".
  - Outstanding balance: amount + "Make Payment" button.

---

## 7. Key Interaction Flows

### 7.1 Create Work Order

Salesperson goes to "New Order" → searches/selects patient → selects current prescription → searches/selects frame from inventory → selects lens type and treatments → reviews summary → confirms. Stock is automatically deducted.

### 7.2 Advance Order Status

User opens WO → presses "Advance Status" → confirms in modal → status advances to the next step → patient is notified.

### 7.3 Register Patient + Prescription

Optometrist goes to "New Patient" → fills in personal information → records prescription (form with fields for OD and OS: sphere, cylinder, axis, addition, PD) → saves.

---

## 8. UX Principles to Follow

- **Fair information density**: neither empty nor overwhelming. Each screen answers one main question.
- **Progressive disclosure**: the list shows the minimum, the details show everything.
- **Status visibility**: the user always knows the status of each order without having to open the details.
- **Consistency**: same card, table, badge, and button patterns across all screens.
- **Clear actions**: there is always a visible primary button indicating the main action of each screen.
- **Immediate feedback**: color-coded status badges, stock indicators, visible alerts.
- **Mobile portal**: the patient portal must work perfectly on 375px-wide screens.

---

## 9. Design Constraints — What NOT to Do

- Do not use heavy shadows (`box-shadow`) — everything should be flat with hairline borders.
- Do not use gradients or dark backgrounds.
- Do not use filled/solid icons — outline only.
- Do not use more than 2 typography weights (regular + medium).
- Do not overload with colors — most of the UI should be gray/white; colors are only for statuses and actions.
- Do not use generic placeholder text such as "Lorem ipsum" — use realistic data from a Colombian optical store (names, amounts in COP, real frame brands).

---

## 10. Out of Scope (Implicit)

This document describes the **interface design (UX/UI)** of the system, as originally outlined in the generation prompt. The source material does not specify:

- Technical architecture (backend, database, APIs).
- Detailed data model.
- Security, authentication, or technical permission-level requirements.
- Integrations with external laboratories or payment gateways.

These points should be defined in a later phase of technical specification.

## 3. Blockers and risks
-

## 4. Plan for next week
-

## 5. Compliance self-check
- [ ] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [ ] Testable acceptance criteria
- [ ] Tests added/updated (unit / integration)
- [ ] DDD / hexagonal boundaries respected (domain has no I/O)
- [ ] No secrets; config via environment variables

## 6. Evidence links
-
