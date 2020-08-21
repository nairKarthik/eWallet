package com.example.services_wallet.Service;

import com.example.services_wallet.Model.User;
import com.example.services_wallet.Model.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public List<User> getAllUsers() {
        ResponseEntity<UserResponse> forEntity = restTemplate.getForEntity("http://localhost:9016/users", UserResponse.class);
        logger.info(forEntity.getHeaders().toString());
        if(forEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            return null;
        }
        return forEntity.getBody().getList();
    }

    public User getAUser(int id) {
        final String uri = "http://localhost:9016/users/{id}";
        Map<Integer, Integer> params = new HashMap<>();
        params.put(id,id);
        ResponseEntity<User> forEntity = restTemplate.getForEntity(uri, User.class, params);
        if(forEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            return null;
        }
        return forEntity.getBody();
    }
}
