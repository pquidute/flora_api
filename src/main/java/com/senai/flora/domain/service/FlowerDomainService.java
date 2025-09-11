package com.senai.flora.domain.service;


import com.senai.flora.domain.exception.StatusException;
import com.senai.flora.domain.exception.InvalidArgumentException;
import org.springframework.stereotype.Service;

@Service
public class FlowerDomainService {
    public void validatePrice(Double price){
        if (price <= 5) {
            throw new InvalidArgumentException("The minimal price is $5");
        }
    }

    public void validateStatusChange(Double price){
        if (price > 30){
            throw new StatusException("Flowers with price bigger than $30 can´t be disabled");
        }
    }
}