# access-decision

A small Java library for **layered access decisions** (view mode / permissions / business rules) with a clear mapping to UI action state.

- **Coordinates:** `by.andd3dfx:access-decision`
- **Java:** 21
- **Packages:**
  - `by.andd3dfx.accessdecision.back` (core, factories, judge),
  - `by.andd3dfx.accessdecision.front` (DTOs and enums for API/UI)

## Purpose

Three decision layers are combined in `AccessDecisionVerdict`. Each layer is a `Supplier`, so later layers are not evaluated once a denial is already known:

| Layer          | Type                               | Factory                                  | Role                                       |
|----------------|------------------------------------|------------------------------------------|--------------------------------------------|
| View mode      | `ViewModeRightsAccessDecision`     | `AccessDecisions.VIEW_MODE_RIGHTS`       | Hide/show policy for the current view mode |
| Permissions    | `PermissionsAccessDecision`        | `AccessDecisions.PERMISSIONS`            | Classic grants and roles                   |
| Business rules | `BusinessRuleRightsAccessDecision` | `AccessDecisions.BUSINESS_RULE_RIGHTS`   | Entity state and runtime/business rules    |

Build a layer with `granted(...)`, `denied(...)`, or `create()` plus `addGrant` / `addDeny` / `addReason`. A layer is denied when `isNotGranted()` is true (any negative reason).

The verdict supports **`toActionState()`** — map the outcome to `ActionVisibilityState` (`ENABLED` / `DISABLED` / `INVISIBLE`) with a list of `Reason` values:

- view mode denied → `INVISIBLE`
- permissions or business rules denied → `DISABLED`
- all granted → `ENABLED`

For service-side checks, extend **`AbstractJudge`** and implement the three `*Violated` hooks. `makeJudgement(verdict)` walks the same layers in order and calls the matching hook on the first denial. The library does not throw domain exceptions itself: map denials to your own types (see `CustomJudge` in the example test).

A `Reason` carries `layer` (`ReasonLayer`), `type` (`ReasonType.POSITIVE` / `NEGATIVE`), and `message`.

## Build and install to local Maven

From the monorepo root (the module is listed in the parent [`pom.xml`](../pom.xml)):

```bash
mvn -pl access-decision clean install
```

Standalone, using only this module’s POM:

```bash
mvn -f access-decision/pom.xml clean install
```

After `install`, depend on the artifact:

```xml
<dependency>
  <groupId>by.andd3dfx</groupId>
  <artifactId>access-decision</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```

Keep the version in sync with your parent POM or BOM. This artifact does not inherit the sandbox parent; treat the
version as the library’s own unless you add a `<parent>` later.

## Library dependencies

- Lombok (provided)
- Apache Commons Lang3 / Collections4
- Jackson Annotations (`@JsonIgnore` on `Reason.isNegative()`)
- Swagger Annotations Jakarta (`@Schema` on DTOs / OpenAPI)
- SpotBugs annotations (provided, for suppressions on record DTOs)
- Jakarta Validation API and Jakarta Annotation API (on the compile classpath)

## Docs and examples

| Resource                                                                                                                 | Description                                                                  |
|--------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------|
| [`doc/access-decision-class-model.puml`](doc/access-decision-class-model.puml)                                           | PlantUML class diagram                                                       |
| [`AccessDecisionUsageExampleTest`](src/test/java/by/andd3dfx/accessdecision/example/AccessDecisionUsageExampleTest.java) | Examples: `toActionState()`, `AbstractJudge` / `CustomJudge`                 |

Run tests for this module only:

```bash
mvn -f access-decision/pom.xml test
```

## Artifact content policy

Sources and resources **avoid** third-party product names and app-specific domain coupling so the library stays
reusable.
