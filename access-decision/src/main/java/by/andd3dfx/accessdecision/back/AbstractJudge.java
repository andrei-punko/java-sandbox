package by.andd3dfx.accessdecision.back;

public abstract class AbstractJudge {

    /**
     * Purpose of this method is to call it from check* function of some service when throwing exception expected
     * in case if validation not passed
     */
    public void makeJudgement(AccessDecisionVerdict verdict) {
        ViewModeRightsAccessDecision viewModeRights = verdict.viewModeRightsSupplier().get();
        if (viewModeRights.isNotGranted()) {
            viewModeRightsViolated(viewModeRights.getExceptionMessage());
        }

        PermissionsAccessDecision permissions = verdict.permissionsSupplier().get();
        if (permissions.isNotGranted()) {
            permissionsViolated(permissions.getExceptionMessage());
        }

        BusinessRuleRightsAccessDecision businessRuleRights = verdict.businessRuleRightsSupplier().get();
        if (businessRuleRights.isNotGranted()) {
            businessRuleRightsViolated(businessRuleRights.getExceptionMessage());
        }
    }

    protected abstract void viewModeRightsViolated(String exceptionMessage);

    protected abstract void permissionsViolated(String exceptionMessage);

    protected abstract void businessRuleRightsViolated(String exceptionMessage);
}
