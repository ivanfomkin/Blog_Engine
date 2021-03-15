package ru.skillbox.ifomkin.diplom.dto.security.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PasswordRestoreResponse {
    private boolean result;
    private PasswordRestoreErrorsResponse errors;
}
