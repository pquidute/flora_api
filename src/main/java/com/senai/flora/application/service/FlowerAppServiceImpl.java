package com.senai.flora.application.service;

import com.senai.flora.application.dto.FlowerDto;
import com.senai.flora.application.mapper.FlowerMapper;
import com.senai.flora.domain.repository.FlowerRepository;
import com.senai.flora.domain.service.FlowerDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlowerAppServiceImpl implements FloweAppService {
    @Autowired
    FlowerRepository repository;
    @Autowired
    FlowerDomainService domainService;

    @Autowired
    FlowerMapper mapper;

    @Override
    public void saveFlower(FlowerDto dto) {
        domainService.validateFlower(dto);
        repository.save(mapper.fromDto(dto));
    }

    @Override
    public void saveFlowers(List<FlowerDto> flowers) {
        for (FlowerDto f : flowers){
            domainService.validateFlower(f);
            repository.save(mapper.fromDto(f));
        }
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
        return repository.findByIdAndStatusTrue(id);
    }

    @Override
    public boolean updateFlower(Long id, FlowerDto dto) {
        return repository.findById(id).map(flower -> {
            domainService.validateFlower(dto);
            repository.save(mapper.fromDto(dto));
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
