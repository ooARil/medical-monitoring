package liga.medical.medicalmonitoring.core.api;

public interface MedicalMonitoringRouterService {

    void routeMessage(String message);

    void routeMessageWithCustomExchange(String message);
}
