package com.minty.lib.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private Object data;
    private HttpStatus status;

    @Builder.Default
    private String message = "Success";

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

}
