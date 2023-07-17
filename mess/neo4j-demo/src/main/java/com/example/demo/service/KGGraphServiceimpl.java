package com.example.demo.service;

import com.example.demo.repository.KgRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KGGraphServiceimpl implements  KGGraphService{

    private final KgRepository kgRepository;
    @Override
    public void createDomain(String label) {
        kgRepository.createDomain(label);
    }
}
