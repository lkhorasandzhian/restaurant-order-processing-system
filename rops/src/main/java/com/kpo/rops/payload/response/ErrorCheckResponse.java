package com.kpo.rops.payload.response;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ErrorCheckResponse {
    public static ResponseEntity<?> getResponseEntity(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorList = bindingResult.getFieldErrors();
            StringBuilder msg = new StringBuilder("Error: ");
            for (var error : errorList) {
                msg.append(error.getDefaultMessage());
            }
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(msg.toString()));
        }
        return null;
    }
}
