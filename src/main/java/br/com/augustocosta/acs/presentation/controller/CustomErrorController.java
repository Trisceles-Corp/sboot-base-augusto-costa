package br.com.augustocosta.acs.presentation.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                // Define your custom logic or model attributes for 404 Not Found
                model.addAttribute("errorTitle", "Page Not Found");
                model.addAttribute("errorCode", "404");
                model.addAttribute("errorMessage", "The page you are looking for might have been removed or is temporarily unavailable.");
                return "errorPage"; // Specify your custom error page view name
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                // Define your custom logic or model attributes for 500 Internal Server Error
                model.addAttribute("errorTitle", "Internal Server Error");
                model.addAttribute("errorCode", "500");
                model.addAttribute("errorMessage", "Oops! Something went wrong on our side.");
                return "errorPage"; // Specify your custom error page view name
            }
            // You can add more conditions for other status codes if needed
        }

        // Default to a general error view if no specific conditions are met
        model.addAttribute("errorTitle", "Error");
        model.addAttribute("errorCode", "Unknown");
        model.addAttribute("errorMessage", "An unexpected error occurred.");
        return "errorPage"; // Specify your custom error page view name
    }

    public String getErrorPath() {
        return "/error";
    }
}