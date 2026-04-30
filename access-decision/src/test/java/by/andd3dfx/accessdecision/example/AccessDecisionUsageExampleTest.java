package by.andd3dfx.accessdecision.example;

import by.andd3dfx.accessdecision.back.AccessDecisionAggregate;
import by.andd3dfx.accessdecision.back.AccessDecisions;
import by.andd3dfx.accessdecision.front.ActionVisibilityState;
import by.andd3dfx.accessdecision.front.VisibilityState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Demonstrates building an aggregate and mapping it to a UI action state.
 * Also shows how application code can implement a {@code judge} step that turns denials into
 * runtime exceptions (here: neutral demo types; replace with your domain exceptions).
 */
class AccessDecisionUsageExampleTest {

    /** Demo: replace with your permission or authorization exception type. */
    static final class ExamplePermissionDeniedException extends RuntimeException {
        ExamplePermissionDeniedException(String message) {
            super(message);
        }
    }

    /** Demo: replace with your validation or business-rule exception type. */
    static final class ExampleBusinessRuleViolationException extends RuntimeException {
        ExampleBusinessRuleViolationException(String message) {
            super(message);
        }
    }

    /**
     * Same layering idea as in a real service: view and permission denials vs action-right denial.
     * The library does not ship this method so it stays free of your exception types.
     */
    static void judgeExample(AccessDecisionAggregate aggregate) {
        if (aggregate.viewRight().isNotGranted()) {
            throw new ExamplePermissionDeniedException(aggregate.viewRight().getExceptionMessage());
        }
        if (aggregate.permission().isNotGranted()) {
            throw new ExamplePermissionDeniedException(aggregate.permission().getExceptionMessage());
        }
        if (aggregate.actionRight().isNotGranted()) {
            throw new ExampleBusinessRuleViolationException(aggregate.actionRight().getExceptionMessage());
        }
    }

    @Test
    void grantedAggregateMapsToEnabled() {
        var aggregate = new AccessDecisionAggregate(
                AccessDecisions.VIEW_RIGHT.granted("Visible in view"),
                AccessDecisions.PERMISSION.granted("Role allows action"),
                AccessDecisions.ACTION_RIGHT.granted("Business rules pass"));

        ActionVisibilityState state = aggregate.toActionState();

        assertEquals(VisibilityState.ENABLED, state.state());
        assertNotNull(state.reasons());
    }

    @Test
    void viewDeniedMapsToInvisible() {
        var aggregate = new AccessDecisionAggregate(
                AccessDecisions.VIEW_RIGHT.denied("Hidden in this view"),
                AccessDecisions.PERMISSION.granted(),
                AccessDecisions.ACTION_RIGHT.granted());

        ActionVisibilityState state = aggregate.toActionState();

        assertEquals(VisibilityState.INVISIBLE, state.state());
    }

    @Test
    void permissionDeniedMapsToDisabled() {
        var aggregate = new AccessDecisionAggregate(
                AccessDecisions.VIEW_RIGHT.granted(),
                AccessDecisions.PERMISSION.denied("Missing grant"),
                AccessDecisions.ACTION_RIGHT.granted());

        ActionVisibilityState state = aggregate.toActionState();

        assertEquals(VisibilityState.DISABLED, state.state());
    }

    @Test
    void judgeThrowsPermissionExceptionWhenViewDenied() {
        var aggregate = new AccessDecisionAggregate(
                AccessDecisions.VIEW_RIGHT.denied("Not in allowed view"),
                AccessDecisions.PERMISSION.granted(),
                AccessDecisions.ACTION_RIGHT.granted());

        ExamplePermissionDeniedException ex = assertThrows(
                ExamplePermissionDeniedException.class,
                () -> judgeExample(aggregate));

        assertEquals("Not in allowed view", ex.getMessage());
    }

    @Test
    void judgeThrowsPermissionExceptionWhenPermissionDenied() {
        var aggregate = new AccessDecisionAggregate(
                AccessDecisions.VIEW_RIGHT.granted(),
                AccessDecisions.PERMISSION.denied("Role missing"),
                AccessDecisions.ACTION_RIGHT.granted());

        ExamplePermissionDeniedException ex = assertThrows(
                ExamplePermissionDeniedException.class,
                () -> judgeExample(aggregate));

        assertEquals("Role missing", ex.getMessage());
    }

    @Test
    void judgeThrowsBusinessRuleExceptionWhenActionRightDenied() {
        var aggregate = new AccessDecisionAggregate(
                AccessDecisions.VIEW_RIGHT.granted(),
                AccessDecisions.PERMISSION.granted(),
                AccessDecisions.ACTION_RIGHT.denied("Entity in wrong state"));

        ExampleBusinessRuleViolationException ex = assertThrows(
                ExampleBusinessRuleViolationException.class,
                () -> judgeExample(aggregate));

        assertEquals("Entity in wrong state", ex.getMessage());
    }

    @Test
    void judgeDoesNothingWhenAllGranted() {
        var aggregate = new AccessDecisionAggregate(
                AccessDecisions.VIEW_RIGHT.granted(),
                AccessDecisions.PERMISSION.granted(),
                AccessDecisions.ACTION_RIGHT.granted());

        assertDoesNotThrow(() -> judgeExample(aggregate));
    }
}
