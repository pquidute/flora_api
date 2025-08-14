package com.senai.flora.controller;

import com.senai.flora.dto.FlowerDto;
import com.senai.flora.service.FlowerServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/flowers")
@OpenAPIDefinition(info = @Info(title = "Flower API", version = "1.0", description = "Operations to manage flowers"))
public class FlowerController {
    @Autowired
    FlowerServiceImpl service;

    @Operation(summary = "Gets a list active of flowers")
    @GetMapping
    public ResponseEntity<List<FlowerDto>> listFlowers(){
        List<FlowerDto> flowers = service.listFlowers();
        return ResponseEntity.ok(flowers);
    }
    @Operation(summary = "Gets a list of disabled flowers")
    @GetMapping("/disabled")
    public ResponseEntity<List<FlowerDto>> listDisabledFlowers(){
        List<FlowerDto> flowers = service.listDisabledFlowers();
        return ResponseEntity.ok(flowers);
    }

    @Operation(summary = "Get a flower")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<FlowerDto>> getFlower(@PathVariable Long id){
        Optional<FlowerDto> dto = service.getFlowerById(id);
        return dto.isPresent() ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }
    @Operation(summary = "Save a flower")
    @PostMapping()
    public ResponseEntity<String> saveFlower(@RequestBody FlowerDto flowerDto){
        service.saveFlower(flowerDto);
        return ResponseEntity.ok(flowerDto.name() + " successfully saved!");
    }

    @Operation(summary = "Save a list of flowers")
    @PostMapping("list")
    public ResponseEntity<String> saveFlowers(@RequestBody List<FlowerDto> flowers){
        service.saveFlowers(flowers);
        return ResponseEntity.ok("flowers successfully saved!");
    }

    @Operation(summary = "Update a flower")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateFlower(@PathVariable Long id, @RequestBody FlowerDto flowerDto){
        service.updateFlower(id, flowerDto);
        return ResponseEntity.ok(flowerDto.name() + " successfully updated!");
    }
    @Operation(summary = "Disable a flower")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> disableFlower(@PathVariable Long id){
        service.disableFlower(id);
        return ResponseEntity.ok("Flower successfully disabled!");
    }
}
