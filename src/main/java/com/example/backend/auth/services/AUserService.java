package com.example.backend.auth.services;

import com.example.backend.auth.repository.IAUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AUserService {
    @Autowired
    private IAUserRepository iaUserRepository;
}
