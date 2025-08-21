package com.senai.flora.application.mapper;

import com.senai.flora.application.dto.FlowerDto;
import com.senai.flora.domain.entity.Flower;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//FIXME - not serializing/deserializing correctly
@Mapper(componentModel = "spring")
public interface FlowerMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "status", target = "status")
    FlowerDto toDto(Flower flower);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "status", target = "status")
    Flower fromDto(FlowerDto flowerDto);
}
