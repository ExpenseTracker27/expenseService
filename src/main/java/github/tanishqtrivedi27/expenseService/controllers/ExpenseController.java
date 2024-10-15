package github.tanishqtrivedi27.expenseService.controllers;

import github.tanishqtrivedi27.expenseService.models.ExpenseDTO;
import github.tanishqtrivedi27.expenseService.services.ExpenseService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/expense/v1")
    public ResponseEntity<List<ExpenseDTO>> getExpenses(@RequestParam("user_id") String userId) {
        try {
            List<ExpenseDTO> expenseDTOList = expenseService.getExpense(userId);
            return new ResponseEntity<>(expenseDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/expense/v1")
    public ResponseEntity<?> addExpense(ExpenseDTO expenseDTO){
        try{
            expenseService.createExpense(expenseDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
