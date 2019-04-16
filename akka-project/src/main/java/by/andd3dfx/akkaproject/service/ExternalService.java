package by.andd3dfx.akkaproject.service;

import by.andd3dfx.akkaproject.service.contract.ServiceRequest;
import by.andd3dfx.akkaproject.service.contract.ServiceResponse;

public interface ExternalService {
    ServiceResponse timeConsumingOperation(ServiceRequest request);
}
