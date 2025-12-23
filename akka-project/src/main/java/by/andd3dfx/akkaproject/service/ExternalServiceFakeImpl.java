package by.andd3dfx.akkaproject.service;

import by.andd3dfx.akkaproject.model.StatusCodeType;
import by.andd3dfx.akkaproject.service.contract.ServiceRequest;
import by.andd3dfx.akkaproject.service.contract.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static by.andd3dfx.akkaproject.model.StatusCodeType.SUCCESS;
import static java.lang.Math.random;
import static java.lang.Thread.sleep;

@Service
public class ExternalServiceFakeImpl implements ExternalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalServiceFakeImpl.class);

    @Value("${delay.base:300}")
    long delayBase;

    @Value("${delay.spread:300}")
    long delaySpread;

    @Override
    public ServiceResponse timeConsumingOperation(ServiceRequest request) {
        try {
            sleep(delayBase + (int) (delaySpread * random()));
        } catch (InterruptedException e) {
            LOGGER.error("Thread was interrupted during delay", e);
            Thread.currentThread().interrupt();
        }
        return buildServiceResponse(SUCCESS);
    }

    private ServiceResponse buildServiceResponse(StatusCodeType statusCode) {
        ServiceResponse result = new ServiceResponse();
        result.setStatusCode(statusCode);
        return result;
    }
}
