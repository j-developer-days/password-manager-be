package com.jdev.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
@SuperBuilder
public class AccountDto {

    private String accountName;
    private String accountURL;
    private String accountPassword;
    private String accountGroupName;

}
