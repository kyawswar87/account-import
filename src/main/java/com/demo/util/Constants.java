package com.demo.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Constants {
    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    public static final String SYSTEM = "system";
    public static final String DEFAULT_LANGUAGE = "en";

    public static final String ACCOUNT_NUMBER = "ACCOUNT_NUMBER";
    public static final String TRX_AMOUNT = "TRX_AMOUNT";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String TRX_DATE = "TRX_DATE";
    public static final String TRX_TIME = "TRX_TIME";
    public static final String CUSTOMER_ID = "CUSTOMER_ID";

    private Constants() {}

    public static LocalDate formatDate(String str) {
        return LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static LocalTime formatTime(String str) {
        return LocalTime.parse(str, DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

}
