package com.senai.flora.application.mapper;

import com.senai.flora.application.dto.FlowerDto;
import com.senai.flora.domain.entity.Flower;
import org.mapstruct.Mapper;

//FIXME - not serializing/deserializing correctly
@Mapper(componentModel = "spring")
public interface FlowerMapper {
    FlowerDto toDto(Flower flower);
    Flower fromDto(FlowerDto flowerDto);
}
