package cybersoft.javabackend.java18.gira.common.model;


import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
public class ResponseDTO implements Serializable { // chuan dau ra
    private Object content;
    private boolean hasErrors;
    private List<String> errors;
    private String timestamp;
    private int status;
}
