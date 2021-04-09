package com.belikov.valteris.cycle.user;

import com.belikov.valteris.cycle.user.model.UserDTO;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    public static final String SUCCESSFUL = "successful";
    public static final String FALSE = "false";

    private final UserService userService;

    @GetMapping("/login")
    public String showLoginPage(@ModelAttribute UserDTO userDTO,
                                @RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", "Incorrect email or password!");
        }
        return "login";
    }

    @GetMapping("/signUp")
    public String showSignUpPage() {
        return "signUp";
    }

    @PostMapping("/signUp")
    @ResponseBody
    public String registerUser(@Valid @RequestBody UserDTO userDTO) {
        final JSONObject json = new JSONObject();
        final Optional<UserDTO> optionalUser = userService.findByUsername(userDTO.getUsername());

        if (optionalUser.isPresent()) {
            json.put("username", "Username is already used!");
        }
        final String password = userDTO.getPassword();
        if (password != null && !password.equals(userDTO.getRePassword())) {
            json.put("rePassword", "Passwords are not equal!");
        }
        if (!json.isEmpty()) {
            json.put(SUCCESSFUL, FALSE);
            return json.toString();
        }
        userService.register(userDTO);
        json.put(SUCCESSFUL, "true");
        return json.toString();
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errors.put(SUCCESSFUL, FALSE);
        return errors;
    }

    @GetMapping("/myOrders")
    public String showOrdersPage() {
        return "my-orders";
    }

    @GetMapping("/cart")
    public String showCartPage() {
        return "cart-page";
    }

    @GetMapping("/checkout")
    public String showCheckoutPage() {
        return "checkout-page";
    }
}