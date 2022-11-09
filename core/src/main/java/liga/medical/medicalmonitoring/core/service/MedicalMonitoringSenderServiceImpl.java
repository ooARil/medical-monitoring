package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.core.annotation.DbLog;
import liga.medical.medicalmonitoring.core.api.MedicalMonitoringSenderService;
import liga.medical.model.dto.QueueNames;
import liga.medical.model.dto.RabbitMessageDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class MedicalMonitoringSenderServiceImpl implements MedicalMonitoringSenderService {

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public MedicalMonitoringSenderServiceImpl(AmqpTemplate amqpTemplate, ObjectMapper objectMapper) {
        this.amqpTemplate = amqpTemplate;
        this.objectMapper = objectMapper;
    }

    @DbLog
    @Override
    public void sendMessage(RabbitMessageDTO rabbitMessageDTO, String queue) throws JsonProcessingException {
        String messageStr = objectMapper.writeValueAsString(rabbitMessageDTO);
        amqpTemplate.convertAndSend(queue, messageStr);
        System.out.println(String.format("Сообщение [%s] в очередь [%s] отправлено.", messageStr, queue));
    }

    @DbLog
    @Override
    public void sendError(String message) {
        amqpTemplate.convertAndSend(QueueNames.ERROR_QUEUE_NAME, message);
        System.out.println(String.format("Сообщение [%s] в очередь [%s] отправлено.", message, QueueNames.ERROR_QUEUE_NAME));
    }
}
