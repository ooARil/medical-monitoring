package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.core.api.MedicalMonitoringRouterService;
import liga.medical.medicalmonitoring.core.api.MedicalMonitoringSenderService;
import liga.medical.medicalmonitoring.core.config.ExchangeConfiguration;
import liga.medical.medicalmonitoring.core.model.MessageType;
import liga.medical.medicalmonitoring.core.model.QueueNames;
import liga.medical.medicalmonitoring.core.model.RabbitMessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MedicalMonitoringRouterServiceImpl implements MedicalMonitoringRouterService {

    private final ObjectMapper objectMapper;
    private final MedicalMonitoringSenderService medicalMonitoringSenderService;
    private final RabbitTemplate rabbitTemplate;

    public MedicalMonitoringRouterServiceImpl(ObjectMapper objectMapper,
                                              MedicalMonitoringSenderService medicalMonitoringSenderService,
                                              RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.medicalMonitoringSenderService = medicalMonitoringSenderService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void routeMessage(String message) {
        try {
            RabbitMessageDTO rabbitMessageDTO = objectMapper.readValue(message, RabbitMessageDTO.class);
            MessageType messageType = rabbitMessageDTO.getMessageType();

            switch (messageType) {
                case DAILY:
                    medicalMonitoringSenderService.sendMessage(rabbitMessageDTO, QueueNames.DAILY_QUEUE_NAME);
                    break;
                case ALERT:
                    medicalMonitoringSenderService.sendMessage(rabbitMessageDTO, QueueNames.ALERT_QUEUE_NAME);
                    break;
                default:
                    System.out.println("Нет информации");
            }
        } catch (Exception e) {
            medicalMonitoringSenderService.sendError(e.getMessage());
        }
    }

    @Override
    public void routeMessageWithCustomExchange(String message) {
        rabbitTemplate.setExchange(ExchangeConfiguration.DIRECT_EXCHANGE_NAME);

        try {
            RabbitMessageDTO rabbitMessageDTO = objectMapper.readValue(message, RabbitMessageDTO.class);
            rabbitTemplate.convertAndSend(rabbitMessageDTO.getMessageType().toString(), message);
            System.out.println("Роутер перенаправил сообщение при помощи кстомного обменника");
        } catch (Exception e) {
            rabbitTemplate.convertAndSend(MessageType.ERROR.toString(), e.getMessage());
            System.out.println("При перенаправления сообщения произошла ошибка");
        }
    }
}
