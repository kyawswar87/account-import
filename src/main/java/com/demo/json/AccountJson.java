package com.demo.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountJson {

    @NotNull
    private int id;
    @JsonProperty("customer_id")
    private String customerId;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("trx_amount")
    private double trxAmount;
    @JsonProperty("description")
    private String description;
    @JsonProperty("trx_date")
    private String trxDate;
    @JsonProperty("trx_time")
    private String trxTime;
}
