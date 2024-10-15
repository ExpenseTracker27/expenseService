package github.tanishqtrivedi27.expenseService.repositories;

import org.springframework.data.repository.CrudRepository;
import github.tanishqtrivedi27.expenseService.entities.Expense;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    List<Expense> findByUserId(String userId);
    List<Expense> findByUserIdAndCreatedAtBetween(String UserId, Timestamp startTime, Timestamp endTime);
    Optional<Expense> findByUserIdAndExternalId(String userId, String externalId);
}
