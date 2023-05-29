package com.jdev.dto.request;

import com.jdev.dto.common.AccountDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AccountRequestDto extends AccountDto {

    private String accountPasswordConfirm;

}
