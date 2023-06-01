package com.jdev.passwordManager.dto.response;

import com.jdev.passwordManager.dto.common.AccountDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AccountResponseDto extends AccountDto {

    private ZonedDateTime creationZonedDateTime;

}
