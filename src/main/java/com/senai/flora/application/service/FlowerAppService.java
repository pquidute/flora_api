package com.senai.flora.application.service;
import com.senai.flora.application.dto.FlowerDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface FlowerAppService {
    FlowerDto saveFlower(FlowerDto dto);
    List<FlowerDto> saveFlowers(List<FlowerDto> dto);
    List<FlowerDto> listFlowers();
    List<FlowerDto> listDisabledFlowers();
    Optional<FlowerDto> getFlowerById(Long id);
    FlowerDto updateFlower(Long id, FlowerDto dto);
    boolean disableFlower(Long id);
}
