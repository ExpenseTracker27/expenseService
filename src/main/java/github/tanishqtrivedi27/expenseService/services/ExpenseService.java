package github.tanishqtrivedi27.expenseService.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.tanishqtrivedi27.expenseService.entities.Expense;
import github.tanishqtrivedi27.expenseService.models.ExpenseDTO;
import github.tanishqtrivedi27.expenseService.repositories.ExpenseRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, ObjectMapper objectMapper) {
        this.expenseRepository = expenseRepository;
        this.objectMapper = objectMapper;
    }

    public void createExpense(ExpenseDTO expenseDTO) {
        setCurrency(expenseDTO);
        try {
            expenseRepository.save(objectMapper.convertValue(expenseDTO, Expense.class));
        } catch (Exception ignored) {
        }
    }

    public Boolean updateExpense(ExpenseDTO expenseDTO) {
        Optional<Expense> expenseOpt = expenseRepository.findByUserIdAndExternalId(expenseDTO.getUserId(), expenseDTO.getExternalId());
        if (expenseOpt.isPresent()) {
            Expense expense = expenseOpt.get();
            expense.setCurrency(Strings.isBlank(expenseDTO.getCurrency()) ? expenseDTO.getCurrency() : expense.getCurrency());
            expense.setMerchant(Strings.isBlank(expenseDTO.getMerchant()) ? expenseDTO.getMerchant() : expense.getMerchant());
            expense.setAmount(Strings.isBlank(expenseDTO.getAmount()) ? expenseDTO.getAmount() : expense.getAmount());
            expenseRepository.save(expense);
            return true;
        }

        return false;
    }

    public List<ExpenseDTO> getExpense(String userId) {
        List<Expense> expenseList = expenseRepository.findByUserId(userId);
        return objectMapper.convertValue(expenseList, new TypeReference<>() {});
    }

    public List<ExpenseDTO> getExpenseBetweenDates(String userId, Timestamp startDate, Timestamp endDate) {
        List<Expense> expenseList = expenseRepository.findByUserIdAndCreatedAtBetween(userId, startDate, endDate);
        return objectMapper.convertValue(expenseList, new TypeReference<>() {});
    }


    private void setCurrency(ExpenseDTO expenseDTO) {
        if (Objects.isNull(expenseDTO.getCurrency())) {
            expenseDTO.setCurrency("INR");
        }
    }
}
