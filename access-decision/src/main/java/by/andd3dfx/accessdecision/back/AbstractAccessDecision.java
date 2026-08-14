package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.Reason;
import by.andd3dfx.accessdecision.front.ReasonLayer;
import by.andd3dfx.accessdecision.front.ReasonType;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AbstractAccessDecision {

    private final ReasonLayer reasonLayer;
    private final List<Reason> reasons;
    // Optimization to speed up calculation of isNotGranted(), getExceptionMessage()
    private final List<String> negativeReasonsMessages;

    protected AbstractAccessDecision(List<Reason> reasons, ReasonLayer reasonLayer) {
        this.reasons = new ArrayList<>(reasons);
        this.reasonLayer = reasonLayer;
        this.negativeReasonsMessages = new ArrayList<>(reasons.stream()
                .filter(Reason::isNegative)
                .map(Reason::message).toList());
    }

    public void addGrant(String message) {
        reasons.add(new Reason(reasonLayer, ReasonType.POSITIVE, message));
    }

    public void addDeny(String message) {
        reasons.add(new Reason(reasonLayer, ReasonType.NEGATIVE, message));
        negativeReasonsMessages.add(message);
    }

    public boolean isNotGranted() {
        return !negativeReasonsMessages.isEmpty();
    }

    public String getExceptionMessage() {
        if (CollectionUtils.isEmpty(negativeReasonsMessages)) {
            throw new IllegalStateException("No negative reasons present!");
        }

        return StringUtils.join(negativeReasonsMessages, ",");
    }
}
