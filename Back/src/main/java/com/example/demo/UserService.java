package com.example.demo;

import com.example.demo.UserRegisterRequest;
import com.example.demo.User;
import com.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void register(UserRegisterRequest request) throws Exception {
        
        if(request.getPhoneNumber() == null || request.getPhoneNumber().isEmpty()) {
            throw new Exception("手機號碼不可為空");
        }
        if(request.getUserName() == null || request.getUserName().isEmpty()) {
            throw new Exception("使用者名稱不可為空");
        }
        if(request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new Exception("密碼不可為空");
        }
        if(request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new Exception("Email不可為空");
        }

        
        if(userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new Exception("手機號碼已被註冊");
        }

        
        String hashedPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());

        
        User user = new User();
        user.setPhoneNumber(request.getPhoneNumber());
        user.setUserName(request.getUserName());
        user.setPassword(hashedPassword);
        user.setEmail(request.getEmail());

        
        userRepository.save(user);
    }
}
