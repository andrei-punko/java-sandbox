# access-decision

A small Java library for **layered access decisions** (view / permissions / business rules) with a clear mapping to UI
action state.

- **Coordinates:** `by.andd3dfx:access-decision`
- **Java:** 21
- **Packages:** `by.andd3dfx.accessdecision.back` (core and factories), `by.andd3dfx.accessdecision.front` (DTOs and
  enums for API/UI)

## Purpose

Three decision layers are combined in `AccessDecisionAggregate`:

| Layer      | Type                        | Role                                        |
|------------|-----------------------------|---------------------------------------------|
| View       | `ViewRightAccessDecision`   | Visibility in the current presentation mode |
| Permission | `PermissionAccessDecision`  | Classic grants and roles                    |
| Action     | `ActionRightAccessDecision` | Entity state and business rules             |

Factories are exposed via `AccessDecisions` (`VIEW_RIGHT`, `PERMISSION`, `ACTION_RIGHT`). The aggregate supports:

- **`toActionState()`** — map the outcome to `ActionVisibilityState` (`ENABLED` / `DISABLED` / `INVISIBLE`) with a list
  of `Reason` values.
- **`isGranted()`** — quick check that everything is allowed.

There is **no `judge()`** in the library: how you signal denial (exceptions, problem details, etc.) is
application-specific. The example test shows a typical `judgeExample(...)` pattern with neutral runtime exceptions; plug
in your own types in production (e.g. via something like `AccessDecisionAggregateJudge` in the host project).

## Build and install to local Maven

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

Keep the version in sync with your parent POM or BOM.

## Library dependencies

- Lombok (provided)
- Jakarta Validation API (`@NotNull` on record components)
- Jakarta Annotation API (`@Nullable`)
- Swagger Annotations Jakarta (`@Schema` on DTOs / OpenAPI)
- SpotBugs annotations (provided, for suppressions on record DTOs)

## Docs and examples

| Resource                                                                                                                 | Description                                                                                               |
|--------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| [`doc/access-decision-class-model.puml`](doc/access-decision-class-model.puml)                                           | PlantUML class diagram                                                                                    |
| [`doc/generate.bat`](doc/generate.bat)                                                                                   | Windows helper: set `PLANTUML_JAR` to your `plantuml.jar`, then run from anywhere (script `cd`s to `doc`) |
| [`AccessDecisionUsageExampleTest`](src/test/java/by/andd3dfx/accessdecision/example/AccessDecisionUsageExampleTest.java) | Examples: `toActionState()`, `judge`-style flow with demo exceptions                                      |

Run tests for this module only:

```bash
mvn -f access-decision/pom.xml test
```

## Artifact content policy

Sources and resources **avoid** third-party product names and app-specific domain coupling so the library stays
reusable.
