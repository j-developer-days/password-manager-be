package com.jdev.passwordManager.restController;

import com.github.javafaker.Company;
import com.jdev.passwordManager.ParentTestHelper;
import com.jdev.passwordManager.dto.request.AccountRequestDto;
import com.jdev.passwordManager.dto.response.AccountResponseDto;
import com.jdev.passwordManager.dto.response.CommonResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

class AccountRestControllerIT extends ParentTestHelper {

    @Test
    void test_createGroupAccount() {
        create();
    }

    @Test
    void test_getAccounts_empty() {
        getAll(0);
    }

    @Test
    void test_getAccounts() {
        int count = 3;
        for (int i = 0; i < 3; i++) {
            create();
        }
        getAll(count);
    }

    private void getAll(int expectedCount) {
        ResponseEntity<CommonResponse> response = template.getForEntity(getUrlWithRestControllerRequestMapping(),
                CommonResponse.class);

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);

        CommonResponse<List<AccountResponseDto>> commonResponse = (CommonResponse<List<AccountResponseDto>>) response.getBody();
        Assertions.assertThat(commonResponse.getBody()).hasSize(expectedCount);
        Assertions.assertThat(commonResponse.getError()).isNull();
    }

    private Integer create() {
        final String password = faker.numerify("ABC##EFG!");
        final Company company = faker.company();
        ResponseEntity<CommonResponse> response = template.postForEntity(getUrlWithRestControllerRequestMapping(),
                AccountRequestDto.builder()
                        .accountPasswordConfirm(password)
                        .accountPassword(password)
                        .accountURL(company.url())
                        .accountName(company.name())
                        .accountGroupName(company.suffix())
                        .build(), CommonResponse.class);

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);

        CommonResponse<Integer> commonResponse = (CommonResponse<Integer>) response.getBody();
        Assertions.assertThat(commonResponse.getBody()).isGreaterThan(0);
        Assertions.assertThat(commonResponse.getError()).isNull();
        return commonResponse.getBody();
    }

    @Override
    protected String getUrlWithRestControllerRequestMapping() {
        return getUrl() + "account/";
    }

}