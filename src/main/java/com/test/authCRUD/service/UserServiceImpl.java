package com.test.authCRUD.service;

import com.test.authCRUD.dto.UserRequestDTO;
import com.test.authCRUD.dto.UserResponseDTO;
import com.test.authCRUD.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.test.authCRUD.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;


    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        UserEntity user = new UserEntity(userRequestDTO.getName(), userRequestDTO.getEmail(), userRequestDTO.getPassword());
        userRepository.save(user);
        return new UserResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        return new UserResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        user.updateInfo(userRequestDTO.getName(), userRequestDTO.getEmail(), userRequestDTO.getPassword());
        userRepository.save(user);
        return new UserResponseDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
