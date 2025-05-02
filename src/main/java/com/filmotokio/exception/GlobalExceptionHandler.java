package com.filmotokio.exception;

import jakarta.validation.ValidationException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleNotFound(ResourceNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("errorValue", "404");
        return "error/404";
    }

    @ExceptionHandler(DatabaseException.class)
    public String handleDatabaseError(DatabaseException ex, Model model) {
        model.addAttribute("errorMessage", "Erro de base de dados: " + ex.getMessage());
        return "error/database";
    }

    @ExceptionHandler(ValidationException.class)
    public String handleValidationError(ValidationException ex, Model model) {
        model.addAttribute("errorMessage", "Erro de validação: " + ex.getMessage());
        return "error/validation";
    }

    @ExceptionHandler(GenericException.class)
    public String handleGeneric(GenericException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/errorPage";
    }

    @ExceptionHandler(Exception.class)
    public String handleOtherErrors(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Erro inesperado: " + ex.getMessage());
        model.addAttribute("errorValue","404");
        return "error/404";
    }
    @ExceptionHandler(CannotGetJdbcConnectionException.class)
    public String handleJdbcConnectionError(Model model, CannotGetJdbcConnectionException ex) {
        model.addAttribute("errorMessage", "Erro ao conectar com o banco de dados.");
        model.addAttribute("details", ex.getMessage());
        model.addAttribute("errorValue","500");
        return "error/database";
    }


}

