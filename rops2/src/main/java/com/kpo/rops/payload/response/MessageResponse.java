package com.kpo.rops.payload.response;

import lombok.Data;

/**
 * Special shell for responses.
 */
@Data
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
