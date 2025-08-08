package com.senai.flora.service;
import com.senai.flora.dto.FlowerDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface FlowerService {
    void saveFlower(FlowerDto dto);
    void saveFlowers(List<FlowerDto> dto);
    List<FlowerDto> listFlowers();
    List<FlowerDto> listDisabledFlowers();
    Optional<FlowerDto> getFlowerById(Long id);
    boolean updateFlower(Long id, FlowerDto dto);
    boolean disableFlower(Long id);
}
