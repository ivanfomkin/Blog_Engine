package ru.skillbox.ifomkin.diplom.dto.calendar.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponse implements Dto {
    private Integer[] years;
    private Map<String, Integer> posts;
}
