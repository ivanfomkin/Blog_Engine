package ru.skillbox.ifomkin.diplom.dto.calendar.factory;

import ru.skillbox.ifomkin.diplom.dto.calendar.response.CalendarResponse;
import ru.skillbox.ifomkin.diplom.dto.calendar.response.PostCountByDateFromDb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarResponseFactory {
    public static CalendarResponse buildCalendarResponse(List<Integer> years,
                                                         List<PostCountByDateFromDb> postCountByDateFromDbList) {
        CalendarResponse calendarResponse = new CalendarResponse();
        calendarResponse.setYears(years.toArray(Integer[]::new));
        Map<String, Integer> resultCalendarMap = new HashMap<>();
        for (PostCountByDateFromDb db : postCountByDateFromDbList) {
            resultCalendarMap.put(db.getDate(), db.getCount());
        }
        calendarResponse.setPosts(resultCalendarMap);
        return calendarResponse;
    }
}
