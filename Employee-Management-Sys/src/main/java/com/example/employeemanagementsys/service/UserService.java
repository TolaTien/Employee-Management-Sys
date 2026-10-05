package com.example.employeemanagementsys.service;

import com.example.employeemanagementsys.dto.request.CreateUser;
import com.example.employeemanagementsys.entity.Role;
import com.example.employeemanagementsys.entity.Team;
import com.example.employeemanagementsys.entity.User;
import com.example.employeemanagementsys.repository.TeamRepository;
import com.example.employeemanagementsys.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public UserService(UserRepository userRepository, TeamRepository teamRepository){
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    public List<User> getAllUser(){
        return userRepository.findAll()
                .stream().toList();
    }

    public User createUser(CreateUser request) throws Exception {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new Exception("Email '" + request.getEmail() + "' đã được sử dụng!");
        }

        Team team = null;
        if (request.getTeamId() != null && !request.getTeamId().isBlank()) {
            team = teamRepository.findById(request.getTeamId())
                    .orElseThrow(() -> new Exception("Team với ID '" + request.getTeamId() + "' không tồn tại!"));
        }

        User newUser = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole() != null ? request.getRole() : Role.STAFF)
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .birthday(request.getBirthday())
                .team(team)
                .build();

        return userRepository.save(newUser);
    }
}
