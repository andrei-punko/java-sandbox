package by.andd3dfx.accessdecision.example;

import by.andd3dfx.accessdecision.back.AbstractJudge;

/**
 * Demo: example of custom Judge class which throws exceptions of different types according to violation cases.
 */
class CustomJudge extends AbstractJudge {

    @Override
    protected void viewModeRightsViolated(String exceptionMessage) {
        throw new WrongViewModeException(exceptionMessage);
    }

    @Override
    protected void permissionsViolated(String exceptionMessage) {
        throw new PermissionDeniedException(exceptionMessage);
    }

    @Override
    protected void businessRuleRightsViolated(String exceptionMessage) {
        throw new BusinessRuleViolationException(exceptionMessage);
    }

    /**
     * Demo: replace with your exception type related with view mode violation.
     */
    static final class WrongViewModeException extends RuntimeException {
        WrongViewModeException(String message) {
            super(message);
        }
    }

    /**
     * Demo: replace with your exception type related with permission or authorization violation.
     */
    static final class PermissionDeniedException extends RuntimeException {
        PermissionDeniedException(String message) {
            super(message);
        }
    }

    /**
     * Demo: replace with your exception type related with validation or business-rule violation.
     */
    static final class BusinessRuleViolationException extends RuntimeException {
        BusinessRuleViolationException(String message) {
            super(message);
        }

    }
}
