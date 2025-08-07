package com.senai.flora.serviceImpl;

import com.senai.flora.dto.FlowerDto;
import com.senai.flora.model.Flower;
import com.senai.flora.service.FlowerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerServiceImpl implements FlowerService {
    @Override
    public FlowerDto saveFlower(FlowerDto dto) {
        return null;
    }

    @Override
    public List<Flower> getAllFlowers() {
        return List.of();
    }

    @Override
    public FlowerDto getFlowerById(Long id) {
        return null;
    }

    @Override
    public FlowerDto updateFlower(Long id, FlowerDto dto) {
        return null;
    }

    @Override
    public void disableFlower(Long id) {

    }

    //TODO - fill up methods
}
