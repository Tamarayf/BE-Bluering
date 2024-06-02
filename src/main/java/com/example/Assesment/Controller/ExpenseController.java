package com.example.Assesment.Controller;


import com.example.Assesment.Apiresponse;
import com.example.Assesment.DTO.*;
import com.example.Assesment.Entity.DepartmentEntity;
import com.example.Assesment.Entity.ExpenseClaimEntity;
import com.example.Assesment.Entity.ExpenseClaimEntryEntity;
import com.example.Assesment.Entity.ExpenseTypeEntity;
import com.example.Assesment.Services.ExpenseService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    private final ExpenseService service3;

    @Autowired
    public ExpenseController(ExpenseService service3) {
        this.service3 = service3;
    }


    @PostMapping("/expenseTypes/")
    public Apiresponse defineExpenseType(@RequestBody ExpenseTypeDTO expenseTypeDTO ) {
        ExpenseTypeEntity createdExpenseType = service3.DefineExpenseType(expenseTypeDTO);
        return new Apiresponse(true, "ExpenseType created successfully", createdExpenseType.getExpenseTypeId());
    }


    @PostMapping("/expenseClaim/")
    public Apiresponse defineExpenseClaim(@RequestBody ExpenseClaimDTO expenseClaimDTO ) {
        ExpenseClaimEntity createdExpenseClaim = service3.createExpenseClaim(expenseClaimDTO);
        return new Apiresponse(true, "ExpenseClaim created successfully", createdExpenseClaim);
    }
//
//    @GetMapping("/employee/{id}/expenseClaims")
//    public ResponseEntity<List<EmployeeDTO>> getExpenseClaimsPerEmployeePerType(@PathVariable Integer id) {
//        List<EmployeeDTO> expenseClaims = service3.getExpenseClaimsPerEmployeePerType(id);
//        if (expenseClaims.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(expenseClaims);
//    }




    @PostMapping("/total")
    public Apiresponse getTotalAmount(@RequestBody Map<String, Integer> requestBody) {
        if (!requestBody.containsKey("expenseTypeId") || !requestBody.containsKey("employeeId")) {
            return new Apiresponse(false, "Missing required parameters: expenseTypeId and employeeId", null);
        }

        Integer expenseTypeId = requestBody.get("expenseTypeId");
        Integer employeeId = requestBody.get("employeeId");

        if (expenseTypeId == null || employeeId == null) {
            return new Apiresponse(false, "expenseTypeId and employeeId cannot be null", null);
        }

        Double totalAmount = service3.getTotalAmountByTypeIdAndEmployee(expenseTypeId, employeeId);

        if (totalAmount == null) {
            return new Apiresponse(false, "No expenses found for the given expenseTypeId and employeeId", null);
        }

        return new Apiresponse(true, "Total amount retrieved successfully", totalAmount);
    }

//

    }



//    @PostMapping("/expenseClaimEntry/")
//    public Apiresponse defineExpenseClaimEntry(@RequestBody ExpenseClaimEntryDTO expenseClaimEntryDTO ) {
//        ExpenseClaimEntryEntity createdExpenseClaimEntry = service3.submitExpenseClaim(expenseClaimEntryDTO);
//        return new Apiresponse(true, "ExpenseClaimEntry created successfully", createdExpenseClaimEntry.getExpenseClaimEntryId());
//    }
////



//
//


