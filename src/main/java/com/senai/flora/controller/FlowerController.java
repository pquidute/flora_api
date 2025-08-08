package com.senai.flora.controller;

import com.senai.flora.dto.FlowerDto;
import com.senai.flora.serviceImpl.FlowerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/flowers")
public class FlowerController {
    @Autowired
    FlowerServiceImpl service;

    @GetMapping
    public ResponseEntity<List<FlowerDto>> listFlowers(){
        List<FlowerDto> flowers = service.listFlowers();
        return ResponseEntity.ok(flowers);
    }

    @GetMapping("/disabled")
    public ResponseEntity<List<FlowerDto>> listDisabledFlowers(){
        List<FlowerDto> flowers = service.listDisabledFlowers();
        return ResponseEntity.ok(flowers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<FlowerDto>> getFlower(@PathVariable Long id){
        Optional<FlowerDto> dto = service.getFlowerById(id);
        return dto.isPresent() ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<String> saveFlower(@RequestBody FlowerDto flowerDto){
        service.saveFlower(flowerDto);
        return ResponseEntity.ok(flowerDto.name() + " successfully saved!");
    }

    @PostMapping("list")
    public ResponseEntity<String> saveFlowers(@RequestBody List<FlowerDto> flowers){
        service.saveFlowers(flowers);
        return ResponseEntity.ok("flowers successfully saved!");
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateFlower(@PathVariable Long id, @RequestBody FlowerDto flowerDto){
        service.updateFlower(id, flowerDto);
        return ResponseEntity.ok(flowerDto.name() + " successfully updated!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> disableFlower(@PathVariable Long id){
        service.disableFlower(id);
        return ResponseEntity.ok("Flower successfully disabled!");
    }
}
