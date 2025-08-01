package org.example.usercrud.controller;

import lombok.RequiredArgsConstructor;
import org.example.usercrud.dto.UserRequestDto;
import org.example.usercrud.dto.UserResponseDto;
import org.example.usercrud.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public UserResponseDto createUser(
            @RequestBody UserRequestDto userRequestDto
    ) {
        return userService.save(userRequestDto);
    }

    @GetMapping("/user/{userId}")
    public UserResponseDto getUser(
            @PathVariable Long userId
    ) {
        return userService.findUser(userId);
    }

    @PutMapping("/user/{userId}")
    public UserResponseDto updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequestDto userRequestDto
    ) {
        return userService.update(userId, userRequestDto);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(
            @PathVariable Long userId
    ) {
        userService.deleteUser(userId);
    }
}
