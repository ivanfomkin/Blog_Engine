package ru.skillbox.ifomkin.diplom.dto.security.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginsRequest implements Dto {
    @JsonProperty("e_mail")
    private String eMail;
    private String password;
}
