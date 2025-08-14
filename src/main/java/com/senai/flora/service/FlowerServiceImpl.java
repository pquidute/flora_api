package com.senai.flora.service;

import com.senai.flora.dto.FlowerDto;
import com.senai.flora.model.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlowerServiceImpl implements FlowerService {
    @Autowired
    FlowerRepository repository;

    @Override
    public void saveFlower(FlowerDto dto) {
        repository.save(dto.fromDTO());
    }

    @Override
    public void saveFlowers(List<FlowerDto> flowers) {
        for (FlowerDto f : flowers){
         repository.save(f.fromDTO());
        }
    }

    @Override
    public List<FlowerDto> listFlowers() {
        System.out.println(repository.findAll());
        return repository.findByStatusTrue().stream().map(FlowerDto::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<FlowerDto> listDisabledFlowers() {
        return repository.findByStatusFalse().stream().map(FlowerDto::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<FlowerDto> getFlowerById(Long id) {
        System.out.println(repository.findByIdAndStatusTrue(id));
        return repository.findByIdAndStatusTrue(id);
    }

    @Override
    public boolean updateFlower(Long id, FlowerDto dto) {
        return repository.findById(id).map(flower -> {
            flower.setName(dto.name());
            flower.setColor(dto.color());
            flower.setPrice(dto.price());
            repository.save(flower);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean disableFlower(Long id) {
        return repository.findById(id).map(flower -> {
            flower.setStatus(false);
            repository.save(flower);
            return true;
        }).orElse(false);
    }
}
