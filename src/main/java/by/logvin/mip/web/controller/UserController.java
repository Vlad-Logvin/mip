package by.logvin.mip.web.controller;

import by.logvin.mip.model.entity.User;
import by.logvin.mip.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Validated
public class UserController {
    private UserService userService;
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/search")
    public User findById(@RequestParam(name = "email") String email) {
        return userService.findByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
