package com.senai.flora.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record FlowerDto (
          @Schema(description = "Flower id", example = "1")
          Long id,
          @Schema(description = "Flower name", example = "Rose")
          String name,
          @Schema(description = "Flower color", example = "Pink")
          String color,
          @Schema(description = "Flower price", example = "$20")
          double price,
          @Schema(description = "Flower status")
          boolean status
){}
