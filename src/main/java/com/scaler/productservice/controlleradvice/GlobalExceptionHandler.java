package com.scaler.productservice.controlleradvice;
import com.scaler.productservice.dtos.ExceptionDto;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticexception(){
        ExceptionDto exceptionDto = new ExceptionDto();

        exceptionDto.setMessage("ArithmeticException has occurred");
        exceptionDto.setSolution("Please try again");

        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(exceptionDto,
                HttpStatus.NOT_FOUND
        );
        return response;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<String> handleArrayIndexOutOfBoundsException(){
        ResponseEntity<String> response = new ResponseEntity<>("ArrayIndexOutOfBounds Exception Encountered",
                HttpStatus.BAD_REQUEST
        );
        return response;
    }

//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<String> handleNullPointerException(){
//        ResponseEntity<String> response = new ResponseEntity<>("NullPointerException Exception Encountered",
//                HttpStatus.NOT_FOUND
//        );
//        return response;
//    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(){
        ExceptionDto exceptionDto = new ExceptionDto();

        exceptionDto.setMessage("Product not found ");
        exceptionDto.setSolution("Please try again with valid id");

        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(exceptionDto,
                HttpStatus.BAD_REQUEST
        );
        return response;
    }
}
