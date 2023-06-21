package com.demo;

import com.demo.domain.Account;
import com.demo.repository.AccountRepository;
import com.demo.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @BeforeAll
    public void setUp() throws Exception {
        Optional<Account> account1 = Optional.of(Account.builder()
                .customerId("222")
                .trxTime(LocalTime.now())
                .accountNumber("8872838283")
                .trxDate(LocalDate.now())
                .trxAmount(123.0)
                .build());

        Optional<Account> account2 = Optional.of(Account.builder()
                .customerId("222")
                .trxTime(LocalTime.now())
                .accountNumber("6872838260")
                .trxDate(LocalDate.now())
                .trxAmount(123.0)
                .build());

        Optional<Account> account3 = Optional.of(Account.builder()
                .customerId("333")
                .trxTime(LocalTime.now())
                .accountNumber("8872838283")
                .trxDate(LocalDate.now())
                .trxAmount(12993.0)
                .build());

        MockitoAnnotations.openMocks(this);
        Page<Account> page = new PageImpl(List.of(account1,account2));
        when(accountRepository.findByCustomerIdOrAccountNumberOrDescription("222", null, null, Pageable.ofSize(10)))
                .thenReturn(page);
        Page<Account> pagedResponse = new PageImpl(List.of(account1,account2,account3));
        when(accountRepository.findAll(Pageable.ofSize(10)))
                .thenReturn(pagedResponse);

        when(accountRepository.findById(1))
                .thenReturn(account1);
    }

    @Test
    public void findByIdTest() {
        Page<Account> account = accountService.findByCustomerIdOrAccountNumberOrDescription("222", null, null, Pageable.ofSize(10));
        Assertions.assertTrue(
                !account.isEmpty()
        );
    }

    @Test
    public void findAllTest() {
        Page<Account> account = accountService.findAll(Pageable.ofSize(10));
        Assertions.assertTrue(
                !account.isEmpty()
                        && account.getSize() == 3
        );
    }

    private String fileData = """
            ACCOUNT_NUMBER|TRX_AMOUNT|DESCRIPTION|TRX_DATE|TRX_TIME|CUSTOMER_ID
            8872838283|123.00|FUND TRANSFER|2019-09-12|11:11:11|222
            8872838283|1123.00|ATM WITHDRWAL|2019-09-11|11:11:11|222
            8872838283|1223.00|FUND TRANSFER|2019-10-11|11:11:11|222
            8872838283|1233.00|3rd Party FUND TRANSFER|2019-11-11|11:11:11|222
            8872838283|1243.00|3rd Party FUND TRANSFER|2019-08-11|11:11:11|222
            8872838283|12553.00|3rd Party FUND TRANSFER|2019-07-11|11:11:11|222
            """;

    @Test
    public void parseCSVTest() {
        MultipartFile file = new MockMultipartFile("file", fileData.getBytes());
        accountService.parseCSV(file);
    }
}
