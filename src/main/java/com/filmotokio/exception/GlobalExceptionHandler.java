package com.filmotokio.exception;

import jakarta.validation.ValidationException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleNotFound(ResourceNotFoundException ex, Model model, HttpServletResponse response) {
        response.setStatus(404);
        model.addAttribute("status", 404);
        model.addAttribute("titulo", "Recurso não encontrado");
        model.addAttribute("mensagem", ex.getMessage());
        return "error/errorPage";
    }

    @ExceptionHandler(DatabaseException.class)
    public String handleDatabaseError(DatabaseException ex, Model model, HttpServletResponse response) {
        response.setStatus(500);
        model.addAttribute("status", 500);
        model.addAttribute("titulo", "Erro de Banco de Dados");
        model.addAttribute("mensagem", ex.getMessage());
        return "error/errorPage";
    }

    @ExceptionHandler(ValidationException.class)
    public String handleValidationError(ValidationException ex, Model model, HttpServletResponse response) {
        response.setStatus(400);
        model.addAttribute("status", 400);
        model.addAttribute("titulo", "Erro de Validação");
        model.addAttribute("mensagem", ex.getMessage());
        return "error/errorPage";
    }

    @ExceptionHandler(GenericException.class)
    public String handleGeneric(GenericException ex, Model model, HttpServletResponse response) {
        response.setStatus(500);
        model.addAttribute("status", 500);
        model.addAttribute("titulo", "Erro");
        model.addAttribute("mensagem", ex.getMessage());
        return "error/errorPage";
    }

    @ExceptionHandler(Exception.class)
    public String handleOtherErrors(Exception ex, Model model, HttpServletResponse response) {
        response.setStatus(500);
        model.addAttribute("status", 500);
        model.addAttribute("titulo", "Erro inesperado");
        model.addAttribute("mensagem", "Ocorreu um erro inesperado: " + ex.getMessage());
        return "error/errorPage";
    }

    @ExceptionHandler(CannotGetJdbcConnectionException.class)
    public String handleJdbcConnectionError(Model model, CannotGetJdbcConnectionException ex, HttpServletResponse response) {
        response.setStatus(500);
        model.addAttribute("status", 500);
        model.addAttribute("titulo", "Erro de Conexão com o Banco");
        model.addAttribute("mensagem", "Erro ao conectar com o banco de dados: " + ex.getMessage());
        return "error/errorPage";
    }

    @ExceptionHandler(ReviewExistException.class)
    public String handleReviewExistError(Model model, ReviewExistException ex, HttpServletResponse response) {
        response.setStatus(409);
        model.addAttribute("status", 409);
        model.addAttribute("titulo", "Conflito de Avaliação");
        model.addAttribute("mensagem", ex.getMessage());
        return "error/errorPage";
    }
}


