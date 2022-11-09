package liga.medical.medicalmonitoring.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.model.dto.RabbitMessageDTO;

public interface MedicalMonitoringSenderService {

    void sendMessage(RabbitMessageDTO rabbitMessageDTO, String queue) throws JsonProcessingException;

    void sendError(String message);
}
