package com.demo.service;

import com.demo.domain.Account;
import com.demo.exception.InvalidDataException;
import com.demo.json.AccountJson;
import com.demo.repository.AccountRepository;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;

import static com.demo.util.Constants.*;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account updateDescription(AccountJson accountJson) {
        Account account = repository.findById(accountJson.getId())
                        .orElseThrow(() -> new InvalidDataException("Data is not found"));
        account.setDescription(accountJson.getDescription());
        log.info("Account updated {}", account);
        return repository.save(account);
    }

    @Transactional(readOnly = true)
    public Page<Account> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Account> findByCustomerIdOrAccountNumberOrDescription(String customerId, @Nullable String acctNo,@Nullable String description, Pageable pageable) {
        return repository.findByCustomerIdOrAccountNumberOrDescription(customerId, acctNo, description, pageable);
    }

    @Async
    public void parseCSV(MultipartFile file) {
        log.info("Parse CSV file {}", file.getName());
        try (
                InputStreamReader in = new InputStreamReader(file.getInputStream());
        ) {
            CSVParser csvParser = CSVFormat.EXCEL
                    .withHeader(ACCOUNT_NUMBER,TRX_AMOUNT,DESCRIPTION,TRX_DATE,TRX_TIME,CUSTOMER_ID)
                    .withDelimiter('|')
                    .parse(in);
            for (CSVRecord csvRecord : csvParser) {
                if(csvRecord.getRecordNumber() == 1){
                    if(
                            !csvRecord.get(0).equals(ACCOUNT_NUMBER)
                            || !csvRecord.get(1).equals(TRX_AMOUNT)
                            || !csvRecord.get(2).equals(DESCRIPTION)
                            || !csvRecord.get(3).equals(TRX_DATE)
                            || !csvRecord.get(4).equals(TRX_TIME)
                            || !csvRecord.get(5).equals(CUSTOMER_ID)){
                        new InvalidDataException("The headers are incorrect.");
                    }
                } else {

                    Account account = Account.builder()
                                        .accountNumber(csvRecord.get(0))
                                        .trxAmount(Strings.isNotEmpty(csvRecord.get(1))? Double.parseDouble(csvRecord.get(1)): 0.0)
                                        .description(csvRecord.get(2))
                                        .trxDate(formatDate(csvRecord.get(3)))
                                        .trxTime(formatTime(csvRecord.get(4)))
                                        .customerId(csvRecord.get(5))
                                        .build() ;
                    log.debug(account.toString());
                    repository.save(account);
                }
            }
        }catch (Exception e) {
            new InvalidDataException(e.getMessage());
        }
    }



}
