package com.jdev.passwordManager.restController;

import com.jdev.passwordManager.dto.response.CommonResponse;
import com.jdev.passwordManager.ParentTestHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.jdev.passwordManager.GroupAccountHelper.GROUP_ACCOUNT_NAME;

class GroupAccountRestControllerIT extends ParentTestHelper {

    @Test
    void test_createGroupAccount() {
        create();
    }

    @Test
    void test_getGroupAccountById() {
        final int id = create();

        ResponseEntity<CommonResponse> response = template.getForEntity(getUrlWithRestControllerRequestMapping() + id,
                CommonResponse.class);
        CommonResponse<String> commonResponse = (CommonResponse<String>) response.getBody();
        Assertions.assertThat(commonResponse.getBody()).isEqualTo(GROUP_ACCOUNT_NAME);
        Assertions.assertThat(commonResponse.getError()).isNull();
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void test_getGroupAccountById_notFound() {
        ResponseEntity<CommonResponse> response = template.getForEntity(getUrl() + "group-account/0",
                CommonResponse.class);

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);

        CommonResponse<String> commonResponse = (CommonResponse<String>) response.getBody();
        Assertions.assertThat(commonResponse.getBody()).isNull();
        Assertions.assertThat(commonResponse.getError().getErrorType())
                .isEqualTo(CommonResponse.ErrorType.NOT_FOUND_ENTITY);
        Assertions.assertThat(commonResponse.getError().getErrorMessage())
                .isEqualTo("not found group account by id - 0");
    }

    @Test
    void test_getGroupAccounts() {
        create();
        create(GROUP_ACCOUNT_NAME + 2);

        ResponseEntity<CommonResponse> response = template.getForEntity(getUrlWithRestControllerRequestMapping(),
                CommonResponse.class);
        CommonResponse<List<String>> commonResponse = (CommonResponse<List<String>>) response.getBody();
        Assertions.assertThat(commonResponse.getBody()).isEqualTo(List.of(GROUP_ACCOUNT_NAME, GROUP_ACCOUNT_NAME + 2));
        Assertions.assertThat(commonResponse.getError()).isNull();
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    private Integer create() {
        return create(null);
    }

    private Integer create(String groupName) {
        ResponseEntity<CommonResponse> response = template.postForEntity(getUrlWithRestControllerRequestMapping()
                        + (groupName == null ? GROUP_ACCOUNT_NAME : groupName),
                null, CommonResponse.class);

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);

        CommonResponse<Integer> commonResponse = (CommonResponse<Integer>) response.getBody();
        Assertions.assertThat(commonResponse.getBody()).isGreaterThan(0);
        Assertions.assertThat(commonResponse.getError()).isNull();
        return commonResponse.getBody();
    }

    @Override
    protected String getUrlWithRestControllerRequestMapping() {
        return getUrl() + "group-account/";
    }
}