package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.Reason;
import by.andd3dfx.accessdecision.front.ReasonLayer;
import by.andd3dfx.accessdecision.front.ReasonType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractAccessDecision<T extends AbstractAccessDecision<T>> {

    private final List<Reason> reasons;
    private final ReasonLayer reasonLayer;

    public void addGrant(String message) {
        reasons.add(new Reason(reasonLayer, ReasonType.POSITIVE, message));
    }

    public void addDeny(String message) {
        reasons.add(new Reason(reasonLayer, ReasonType.NEGATIVE, message));
    }

    public T addReason(boolean conditionFlag, String universalReasonMessage) {
        return addReason(conditionFlag, universalReasonMessage, universalReasonMessage);
    }

    @SuppressWarnings("unchecked")
    public T addReason(boolean conditionFlag, String positiveReasonMessage, String negativeReasonMessage) {
        if (conditionFlag) {
            addGrant(positiveReasonMessage);
        } else {
            addDeny(negativeReasonMessage);
        }
        return (T) this;
    }

    public boolean isNotGranted() {
        return reasons.stream().anyMatch(Reason::isNegative);
    }

    public String getExceptionMessage() {
        var negativeReasonsMessages = getNegativeReasonsMessages();
        if (CollectionUtils.isEmpty(negativeReasonsMessages)) {
            throw new IllegalStateException("No negative reasons present!");
        }

        return StringUtils.join(negativeReasonsMessages, ", ");
    }

    public List<Reason> getReasons() {
        return List.copyOf(reasons);
    }

    private List<String> getNegativeReasonsMessages() {
        return reasons.stream()
                .filter(Reason::isNegative)
                .map(Reason::message)
                .toList();
    }
}
