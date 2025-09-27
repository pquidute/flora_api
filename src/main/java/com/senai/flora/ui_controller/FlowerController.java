package com.senai.flora.ui_controller;

import com.senai.flora.application.dto.FlowerDto;
import com.senai.flora.application.service.FlowerAppServiceImpl;
import com.senai.flora.domain.exception.FlowerNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Flora", description = "Manage flora flowers")
@RestController
@RequestMapping("/flowers")
public class FlowerController {
    @Autowired
    FlowerAppServiceImpl service;

    @Operation(summary = "List all disabled flowers")
    @GetMapping("/disabled")
    public ResponseEntity<List<FlowerDto>> listDisabledFlowers(){
        List<FlowerDto> flowers = service.listDisabledFlowers();
        return ResponseEntity.ok(flowers);
    }

    @Operation(
            summary = "List all active flowers",
            responses = {
                    @ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    @GetMapping
    public ResponseEntity<List<FlowerDto>> listFlowers(){
        List<FlowerDto> flowers = service.listFlowers();
        if (flowers.isEmpty()){
            throw new FlowerNotFoundException("No flowers registered or no flowers active!");
        }
        return ResponseEntity.ok(flowers);
    }

    @Operation(
            summary = "Get a flower",
            responses = {
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "404", description = "Flower not found!")
            }
        )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<FlowerDto>> getFlower(@PathVariable Long id){
        Optional<FlowerDto> dto = service.getFlowerById(id);
        return dto.isPresent() ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Save a flower",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = FlowerDto.class),
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    {
                                        "name":"orchids",
                                        "color": "purple",
                                        "price": 23
                                    }
                                    """)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Flower successfully saved!"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    @PostMapping()
    public ResponseEntity<String> saveFlower(@RequestBody FlowerDto flowerDto){
        service.saveFlower(flowerDto);
        return ResponseEntity.ok(flowerDto.name() + " successfully saved!");
    }

    @Operation(
            summary = "Save a list of flowers",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FlowerDto.class),
                            examples = @ExampleObject(value = """
                            [
                               {
                                 "name": "lilies",
                                 "color": "white",
                                 "price": 18,
                                 "status": true
                               },
                               {
                                 "name": "tulips",
                                 "color": "red",
                                 "price": 15,
                                 "status": true
                               },
                               {
                                 "name": "daisies",
                                 "color": "blue",
                                 "price": 10,
                                 "status": true
                               },
                               {
                                 "name": "sunflowers",
                                 "color": "yellow",
                                 "price": 22,
                                 "status": true
                               },
                               {
                                 "name": "violets",
                                 "color": "purple",
                                 "price": 20,
                                 "status": true
                               },
                               {
                                 "name": "carnations",
                                 "color": "white",
                                 "price": 8,
                                 "status": true
                               },
                               {
                                 "name": "peonies",
                                 "color": "pink",
                                 "price": 30,
                                 "status": true
                               },
                               {
                                 "name": "gerbera",
                                 "color": "orange",
                                 "price": 16,
                                 "status": true
                               },
                               {
                                 "name": "hydrangeas",
                                 "color": "blue",
                                 "price": 25,
                                 "status": true
                               },
                               {
                                 "name": "magnolia",
                                 "color": "white",
                                 "price": 35,
                                 "status": true
                               }
                            ]
                    """)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Flowers successfully saved!"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    @PostMapping("list")
    public ResponseEntity<String> saveFlowers(@RequestBody List<FlowerDto> flowers){
        service.saveFlowers(flowers);
        return ResponseEntity.ok("flowers successfully saved!");
    }


    @Operation(
            summary = "Update a flower",
            description = "Update an existent flower with new information",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = FlowerDto.class),
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    {
                                        "name":"newName",
                                        "color": "newColor",
                                        "price": 10
                                    }
                                    """)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Flower successfully updated!"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            }
    )

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFlower(@PathVariable Long id, @RequestBody FlowerDto flowerDto){
        service.updateFlower(id, flowerDto);
        return ResponseEntity.ok(flowerDto.name() + " successfully updated!");
    }
    @Operation(
            summary = "Disable a flower",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Flower successfully disabled!"),
                    @ApiResponse(responseCode = "404", description = "Flower not found!")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> disableFlower(@PathVariable Long id){
        service.disableFlower(id);
        return ResponseEntity.ok("Flower successfully deleted!");
    }
}
