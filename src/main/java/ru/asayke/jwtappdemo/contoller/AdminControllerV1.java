package ru.asayke.jwtappdemo.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asayke.jwtappdemo.dto.AdminUserDTO;
import ru.asayke.jwtappdemo.models.User;
import ru.asayke.jwtappdemo.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminControllerV1 {
    private final UserService userService;

    @Autowired
    public AdminControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDTO> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(AdminUserDTO.fromUser(user));
    }
}