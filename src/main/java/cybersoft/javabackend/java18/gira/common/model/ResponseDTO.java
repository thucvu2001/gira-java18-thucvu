package cybersoft.javabackend.java18.gira.common.model;


import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
public class ResponseDTO implements Serializable { // chuan response tra ve
    private Object content; // co noi dung neu tra ve code 200
    private boolean hasErrors;
    private List<String> errors; // if hasErrors == true, errors not blank
    private String timestamp;
    private int status; // 200, 500
}
