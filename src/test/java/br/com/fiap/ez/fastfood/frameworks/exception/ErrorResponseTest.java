package br.com.fiap.ez.fastfood.frameworks.exception;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ErrorResponseTest {

    @Test
    void testErrorResponse() {
        String errorMessage = "An error occurred";
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);

        assertEquals(errorMessage, errorResponse.getMessage());

        String newErrorMessage = "A different error occurred";
        errorResponse.setMessage(newErrorMessage);
        assertEquals(newErrorMessage, errorResponse.getMessage());
    }
}