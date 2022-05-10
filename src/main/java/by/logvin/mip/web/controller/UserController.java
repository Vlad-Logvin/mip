package by.logvin.mip.web.controller;

import by.logvin.mip.model.entity.User;
import by.logvin.mip.model.request.UserRequest;
import by.logvin.mip.model.response.UserResponse;
import by.logvin.mip.model.security.JwtModel;
import by.logvin.mip.model.security.UserAuthRequest;
import by.logvin.mip.security.service.SecurityService;
import by.logvin.mip.service.UserService;
import by.logvin.mip.service.exception.AutomatedDrugServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Validated
@CrossOrigin
public class UserController {
    private UserService userService;
    private SecurityService securityService;
    private ModelMapper modelMapper;

    @PostMapping("/login")
    public JwtModel login(HttpServletRequest request, HttpServletResponse response,
                          @RequestBody @Valid UserAuthRequest userAuthRequest) {
        return securityService.autologin(request, response, userAuthRequest.getEmail(), userAuthRequest.getPassword());
    }

    @PostMapping("/register")
    public JwtModel register(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody @Valid UserRequest userRequest, BindingResult bindingResult) {
        validate(bindingResult);
        User user = userService.savePharmacyOwner(modelMapper.map(userRequest, User.class));
        return securityService.autologin(request, response, user.getEmail(), user.getPassword());
    }


    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return modelMapper.map(userService.findById(id), UserResponse.class);
    }

    @GetMapping("/search")
    public User findByEmail(@RequestParam(name = "email") String email) {
        return userService.findByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    private void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new AutomatedDrugServiceException("Not valid data: " +
                    bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .collect(Collectors.joining(", ")), 400);
        }
    }
}
