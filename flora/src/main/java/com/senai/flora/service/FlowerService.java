package com.senai.flora.service;
import com.senai.flora.dto.FlowerDto;
import com.senai.flora.model.Flower;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface FlowerService {

    FlowerDto saveFlower(FlowerDto dto);
    List<Flower> getAllFlowers();
    FlowerDto getFlowerById(Long id);
    FlowerDto updateFlower(Long id, FlowerDto dto);
    void disableFlower(Long id);



}
