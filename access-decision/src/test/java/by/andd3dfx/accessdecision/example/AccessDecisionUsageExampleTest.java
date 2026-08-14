package by.andd3dfx.accessdecision.example;

import by.andd3dfx.accessdecision.back.AccessDecisionVerdict;
import by.andd3dfx.accessdecision.back.AccessDecisions;
import by.andd3dfx.accessdecision.front.ActionVisibilityState;
import by.andd3dfx.accessdecision.front.VisibilityState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Demonstrates building an aggregate and mapping it to a UI action state.
 * <p>
 * Also shows how application code can implement a {@code judge} step that turns denials into
 * runtime exceptions (here: neutral demo types; replace with your domain exceptions).
 */
class AccessDecisionUsageExampleTest {

    private CustomJudge judge;

    @BeforeEach
    void setUp() {
        judge = new CustomJudge();
    }

    @Test
    void judgeThrowsWrongViewModeExceptionWhenViewDenied() {
        var aggregate = new AccessDecisionVerdict(
                () -> AccessDecisions.VIEW_MODE_RIGHTS.denied("Not in allowed view"),
                () -> AccessDecisions.PERMISSIONS.granted(),
                () -> AccessDecisions.BUSINESS_RULE_RIGHTS.granted());

        ActionVisibilityState visibilityState = aggregate.toActionState();
        assertEquals(VisibilityState.INVISIBLE, visibilityState.state());

        CustomJudge.WrongViewModeException ex = assertThrows(
                CustomJudge.WrongViewModeException.class,
                () -> judge.makeJudgement(aggregate));
        assertEquals("Not in allowed view", ex.getMessage());
    }

    @Test
    void judgeThrowsPermissionDeniedExceptionWhenPermissionDenied() {
        var aggregate = new AccessDecisionVerdict(
                () -> AccessDecisions.VIEW_MODE_RIGHTS.granted(),
                () -> AccessDecisions.PERMISSIONS.denied("Role missing"),
                () -> AccessDecisions.BUSINESS_RULE_RIGHTS.granted());

        ActionVisibilityState visibilityState = aggregate.toActionState();
        assertEquals(VisibilityState.DISABLED, visibilityState.state());

        CustomJudge.PermissionDeniedException ex = assertThrows(
                CustomJudge.PermissionDeniedException.class,
                () -> judge.makeJudgement(aggregate));
        assertEquals("Role missing", ex.getMessage());
    }

    @Test
    void judgeThrowsBusinessRuleViolationExceptionWhenActionRightDenied() {
        var aggregate = new AccessDecisionVerdict(
                () -> AccessDecisions.VIEW_MODE_RIGHTS.granted(),
                () -> AccessDecisions.PERMISSIONS.granted(),
                () -> AccessDecisions.BUSINESS_RULE_RIGHTS.denied("Entity in wrong state"));

        ActionVisibilityState visibilityState = aggregate.toActionState();
        assertEquals(VisibilityState.DISABLED, visibilityState.state());

        CustomJudge.BusinessRuleViolationException ex = assertThrows(
                CustomJudge.BusinessRuleViolationException.class,
                () -> judge.makeJudgement(aggregate));
        assertEquals("Entity in wrong state", ex.getMessage());
    }

    @Test
    void judgeDoesNothingWhenAllGranted() {
        var aggregate = new AccessDecisionVerdict(
                () -> AccessDecisions.VIEW_MODE_RIGHTS.granted("Visible in view"),
                () -> AccessDecisions.PERMISSIONS.granted("Role allows action"),
                () -> AccessDecisions.BUSINESS_RULE_RIGHTS.granted("Business rules pass"));

        ActionVisibilityState state = aggregate.toActionState();
        assertEquals(VisibilityState.ENABLED, state.state());

        assertDoesNotThrow(() -> judge.makeJudgement(aggregate));
    }
}
