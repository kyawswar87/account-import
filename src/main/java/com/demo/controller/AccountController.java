package com.demo.controller;

import com.demo.domain.Account;
import com.demo.exception.InvalidDataException;
import com.demo.exception.ResourceNotFoundException;
import com.demo.json.AccountJson;
import com.demo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/data")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping(params = { "page", "size" }, produces = "application/json")
    public ResponseEntity<Page<Account>> findAll(@RequestParam("page") int page,
                                       @RequestParam("size") int pageSize) {
        log.info("Find All");

        Pageable paging  = PageRequest.of(page, pageSize);
        Page<Account> resultPage = service.findAll(paging);
        if (resultPage.getTotalPages() == 0) {
            throw new ResourceNotFoundException("There is no data.");
        }
        return ResponseEntity.ok(
                resultPage
        );
    }

    @GetMapping(params = { "customerId", "acctNo", "description","page", "size" },
            produces = "application/json")
    public ResponseEntity<Page<Account>> find(@RequestParam String customerId,
                                              @RequestParam(required = false) String acctNo,
                                              @RequestParam(required = false) String description,
                                              @RequestParam("page") int page,
                                              @RequestParam("size") int pageSize) {
        log.info("Find by {} {} {}", customerId, acctNo, description);

        Page<Account> account = service.findByCustomerIdOrAccountNumberOrDescription(customerId,
                acctNo,
                description,
                PageRequest.of(page, pageSize));
        return ResponseEntity.ok(
                account
        );
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file){
        log.info("upload by {}", file.getName());
        String message = "Upload will process on background.";
        HttpStatus status = HttpStatus.ACCEPTED;
        if(file.getOriginalFilename().contains(".csv")){
            service.parseCSV(file);
        } else {
            throw new InvalidDataException("Please upload .csv file");
        }
        return new ResponseEntity<>(message, status);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<Account> update(@RequestBody AccountJson accountJson){
        log.info("update by {}", accountJson);
        Account account = service.updateDescription(accountJson);
        return ResponseEntity.ok(account);
    }

}
