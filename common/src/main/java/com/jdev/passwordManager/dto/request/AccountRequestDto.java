package com.jdev.passwordManager.dto.request;

import com.jdev.passwordManager.dto.common.AccountDto;
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
