package cybersoft.javabackend.java18.gira.common.util;


import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass // lớp chức năng (khong tao instance)
public class DateTimeUtils { // de quy dinh format cho ngay thang nam
    public static final String DATETIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);

    public static String now() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }
}
