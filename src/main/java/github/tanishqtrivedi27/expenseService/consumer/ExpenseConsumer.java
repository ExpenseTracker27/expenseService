package github.tanishqtrivedi27.expenseService.consumer;

import github.tanishqtrivedi27.expenseService.models.ExpenseDTO;
import github.tanishqtrivedi27.expenseService.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class ExpenseConsumer {
    private final ExpenseService expenseService;

    @Autowired
    private ExpenseConsumer(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ExpenseDTO expenseDTO) {
        try {
            expenseService.createExpense(expenseDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
