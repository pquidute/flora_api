package com.senai.flora.application.service;

import com.senai.flora.application.dto.FlowerDto;
import com.senai.flora.application.mapper.FlowerMapper;
import com.senai.flora.domain.entity.Flower;
import com.senai.flora.domain.exception.FlowerNotFoundException;
import com.senai.flora.domain.exception.InvalidArgumentException;
import com.senai.flora.domain.repository.FlowerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FlowerAppServiceImpl implements FlowerAppService {
    private final FlowerRepository repository;
    private final FlowerMapper mapper;

    // injection by constructor (better practice for testing and immutability)
    public FlowerAppServiceImpl(FlowerRepository repository, FlowerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public FlowerDto saveFlower(FlowerDto dto) {
        validateFlower(dto);
        Flower flower = formatBody(dto);
        repository.save(flower);
        return mapper.toDto(flower);
    }

    @Override
    public List<FlowerDto> saveFlowers(List<FlowerDto> flowers) {
        List<FlowerDto> savedFlowers = List.of();
        for (FlowerDto f : flowers){
            validateFlower(f);
            Flower flower = formatBody(f);
            repository.save(flower);
            savedFlowers.add(mapper.toDto(flower));
        }
        return savedFlowers;
    }

    @Override
    public List<FlowerDto> listFlowers() {
        System.out.println(repository.findAll());
        return repository.findByStatusTrue().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<FlowerDto> listDisabledFlowers() {
        return repository.findByStatusFalse().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<FlowerDto> getFlowerById(Long id) {
        System.out.println(repository.findByIdAndStatusTrue(id));
        Optional<Flower> target = repository.findByIdAndStatusTrue(id);
        Optional<FlowerDto> targetDto = target.map(mapper::toDto);
        return targetDto;
    }

    @Override
    public FlowerDto updateFlower(Long id, FlowerDto dto) {
        Flower target = repository.findByIdAndStatusTrue(id)
                .orElseThrow(() -> new FlowerNotFoundException("Flower not found or not active"));

        validateFlower(dto);
        Flower flower = formatBody(dto);
        flower.setId(target.getId());
        repository.save(flower);
        return dto;
    }

    @Override
    public boolean disableFlower(Long id) {
        return repository.findById(id).map(flower -> {
            flower.setStatus(false);
            repository.save(flower);
            return true;
        }).orElse(false);
    }


    public void validateFlower(FlowerDto dto){
        validateName(dto.name());

        Flower f = mapper.fromDto(dto);
        f.validatePrice(dto.price());

        validateColor(dto.color());
    }

    public void validateName(String name) {
        if (name.trim().isEmpty()) {
            throw new InvalidArgumentException("Flower name can't be empty");
        }
    }


    public void validateColor(String color) {
        if (color.trim().isEmpty()) {
            throw new InvalidArgumentException("Flower color can't be empty");
        }
    }

    //This method ensures that all flowers are saved with status true
    public Flower formatBody(FlowerDto dto){
        Flower flower = mapper.fromDto(dto);
        flower.setStatus(true);
        return flower;
    }
}
