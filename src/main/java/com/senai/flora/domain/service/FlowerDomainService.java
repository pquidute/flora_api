package com.senai.flora.domain.service;

import com.senai.flora.application.dto.FlowerDto;
import com.senai.flora.domain.exception.InvalidColorException;
import com.senai.flora.domain.exception.InvalidNameException;
import com.senai.flora.domain.exception.InvalidPriceException;
import org.springframework.stereotype.Service;

@Service
public class FlowerDomainService {

    public void validateFlower(FlowerDto dto){
        validateName(dto.name());
        validatePrice(dto.price());
        validateColor(dto.color());
    }

    public void validatePrice(Double price) {
        if (price <= 0) {
            throw new InvalidPriceException("The price must be bigger than 0");
        }
    }

    public void validateName(String name) {
        if (name.trim().isEmpty()) {
            throw new InvalidNameException("Flower name can´t be empty");
        }
    }

    public void validateColor(String color) {
        if (color.trim().isEmpty()) {
            throw new InvalidColorException("Flower color can´t be empty");
        }
    }
}