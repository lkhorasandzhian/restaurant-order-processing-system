package com.kpo.rops.payload.response;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

/**
 * Request error checker.
 */
public class ErrorCheckResponse {
    /**
     * Specialized for checking errors.
     * @param bindingResult status of HTTP-request.
     * @return If there is no errors gives null, otherwise — 'bad request' status.
     */
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
