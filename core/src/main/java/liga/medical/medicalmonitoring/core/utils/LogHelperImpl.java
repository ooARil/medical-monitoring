package liga.medical.medicalmonitoring.core.utils;

import liga.medical.medicalmonitoring.core.utils.api.LogHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LogHelperImpl implements LogHelper {

    @Override
    public String getId() {
        return UUID.randomUUID().toString();
    }
}
