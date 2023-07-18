package pl.zbadajsie.przychodnia.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import pl.zbadajsie.przychodnia.exceptions.UserAlreadyExistException;

import java.util.Optional;

@Slf4j
@ControllerAdvice
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        String message = String.format("Other exception occurred: [%s]", ex.getMessage());
        log.error(message, ex);
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", message);
        return modelView;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(BindException ex) {
        String message = String.format("Bad request for field: [%s], wrong value: [%s]",
                Optional.ofNullable(ex.getFieldError()).map(FieldError::getField).orElse(null),
                Optional.ofNullable(ex.getFieldError()).map(FieldError::getRejectedValue).orElse(null));
        log.error(message, ex);
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", message);
        return modelView;
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(UserAlreadyExistException ex) {
        String message = "Użytkownik o tym emailu już isnieje";
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", message);
        return modelView;
    }
}
