package vn.pvhg.socialbackend.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

public record ApiResponse<T>(
        HttpStatus status,
        boolean success,
        String message,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        T data
) {
}
