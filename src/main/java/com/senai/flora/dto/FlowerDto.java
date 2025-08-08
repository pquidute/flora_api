package com.senai.flora.dto;

import com.senai.flora.model.Flower;

public record FlowerDto (
          Long id,
          String name,
          String color,
          double price,
          boolean status
){
    public static FlowerDto toDTO(Flower flower) {
        return new FlowerDto(
                flower.getId(),
                flower.getName(),
                flower.getColor(),
                flower.getPrice(),
                flower.getStatus()
        );
    }

    public Flower fromDTO() {
        Flower flower = new Flower();
        flower.setId(id);
        flower.setName(name);
        flower.setColor(color);
        flower.setPrice(price);
        flower.setStatus(true);
        return flower;
    }
}
