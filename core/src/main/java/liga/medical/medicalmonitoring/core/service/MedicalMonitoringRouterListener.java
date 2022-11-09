package liga.medical.medicalmonitoring.core.service;

import liga.medical.medicalmonitoring.core.annotation.DbLog;
import liga.medical.medicalmonitoring.core.api.MedicalMonitoringRouterService;
import liga.medical.model.dto.QueueNames;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MedicalMonitoringRouterListener {

    private final MedicalMonitoringRouterService medicalMonitoringRouterService;

    public MedicalMonitoringRouterListener(MedicalMonitoringRouterService medicalMonitoringRouterService) {
        this.medicalMonitoringRouterService = medicalMonitoringRouterService;
    }

    @DbLog
    @RabbitListener(queues = QueueNames.ROUTER_QUEUE_NAME)
    public void receiveAndRedirectMessage(String message) {
        medicalMonitoringRouterService.routeMessage(message);
    }
}
