package github.tanishqtrivedi27.expenseService.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.tanishqtrivedi27.expenseService.models.ExpenseDTO;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class ExpenseDeserializer implements Deserializer<ExpenseDTO> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public ExpenseDTO deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        ExpenseDTO expenseDTO = null;
        try {
            expenseDTO = objectMapper.readValue(s, ExpenseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return expenseDTO;
    }

    @Override
    public ExpenseDTO deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public ExpenseDTO deserialize(String topic, Headers headers, ByteBuffer data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
