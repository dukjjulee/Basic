package org.example.usercrud.service;

import lombok.RequiredArgsConstructor;
import org.example.usercrud.dto.UserRequestDto;
import org.example.usercrud.dto.UserResponseDto;
import org.example.usercrud.entity.User;
import org.example.usercrud.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto save(UserRequestDto UserRequestDto){
        User savedUser = userRepository.save(new User(UserRequestDto.getName()));
        return new UserResponseDto(savedUser.getId(), savedUser.getName());
    }
    @Transactional(readOnly = true)
    public List<UserResponseDto> findUser() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> dtos = new ArrayList<>();

        for (User user : users) {
            UserResponseDto userResponseDto = new UserResponseDto(
                    user.getId(),
                    user.getName()
            );
            dtos.add(userResponseDto);
        }
        return dtos;
    }
    @Transactional
    public UserResponseDto update(Long userId, UserRequestDto userRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 userID가 없습니다")
        );
        user.updateName(userRequestDto.getName());
        return new UserResponseDto(
                user.getId(),
                user.getName()
        );
    }

    @Transactional
    public void deleteUser(Long userId) {
        boolean b = userRepository.existsById(userId);
        if(!b) {
            throw new IllegalArgumentException("해당하는 userID가 없습니다");
        }
        userRepository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    public UserResponseDto findUser(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 userID가 없습니다")
        );

        return new UserResponseDto(user.getId(), user.getName());
    }
}
