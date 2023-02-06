package ru.asayke.jwtappdemo.contoller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asayke.jwtappdemo.dto.AdminUserDTO;
import ru.asayke.jwtappdemo.dto.UserDTO;
import ru.asayke.jwtappdemo.models.User;
import ru.asayke.jwtappdemo.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/user/")
public class UserControllerV1 {
    private final UserService userService;
    private final ModelMapper mapper;

    @Autowired
    public UserControllerV1(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(convertToUserDTO(user));
    }

    private UserDTO convertToUserDTO(User user) {
        return mapper.map(user, UserDTO.class);
    }
}