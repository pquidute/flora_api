package com.senai.flora;

import com.senai.flora.application.dto.FlowerDto;
import com.senai.flora.application.mapper.FlowerMapper;
import com.senai.flora.application.service.FlowerAppServiceImpl;
import com.senai.flora.domain.entity.Flower;
import com.senai.flora.domain.exception.FlowerNotFoundException;
import com.senai.flora.domain.exception.InvalidArgumentException;
import com.senai.flora.domain.exception.StatusException;
import com.senai.flora.domain.repository.FlowerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FloraUnitTest {
    @Mock
    private FlowerRepository repository;

    //Inject a Real Mapper instead of Mock
    private FlowerMapper mapper = Mappers.getMapper(FlowerMapper.class);

    @InjectMocks
    private FlowerAppServiceImpl service;

    //Inject a mapper and a repository to the service before each test
    @BeforeEach
    void setUp() {
        service = new FlowerAppServiceImpl(repository, mapper);
    }



    // CONTROLLER TESTS
    @Test
    void mustSaveValidFlower() {
        // Simulates a DTO body being received
        FlowerDto flowerDto = new FlowerDto(null, "Rose", "Pink", 20.0, true);

        // Simulates saving (converts to entity inside this method)
        FlowerDto saved = service.saveFlower(flowerDto);

        assertNotNull(saved);
        assertAll(
                () -> assertEquals(flowerDto.name(), saved.name()),
                () -> assertEquals(flowerDto.color(), saved.color()),
                () -> assertEquals(flowerDto.price(), saved.price()),
                () -> assertEquals(flowerDto.status(), saved.status())
        );
        verify(repository).save(any(Flower.class));
    }

    @Test
    void mustSaveListOfFlowersSuccessfully() {
        // Simulates a list of DTO bodies being received
        List<FlowerDto> flowersDto = new ArrayList<FlowerDto>() {{
            add(new FlowerDto(null, "Rose", "Pink", 20.0, false));
            add(new FlowerDto(null, "Tulip", "Yellow", 15.5, false));
            add(new FlowerDto(null, "Sunflower", "Yellow", 10.0, false));
            add(new FlowerDto(null, "Lily", "White", 30.0, false));
            add(new FlowerDto(null, "Daffodil", "Orange", 18.0, false));
            add(new FlowerDto(null, "Orchid", "Purple", 50.0, false));
            add(new FlowerDto(null, "Carnation", "Red", 25.0, false));
            add(new FlowerDto(null, "Violet", "Blue", 12.0, false));
            add(new FlowerDto(null, "Tulip", "Purple", 22.0, false));
            add(new FlowerDto(null, "Chrysanthemum", "Pink", 28.0, false));
        }};

        // Simulates saving (converts to entity inside this method)
        List<FlowerDto> savedFlowers = service.saveFlowers(flowersDto);

        assertNotNull(savedFlowers);
        assertAll(
                () -> assertEquals(savedFlowers.size(), flowersDto.size()),
                () -> assertEquals(savedFlowers.getFirst().name(), flowersDto.getFirst().name())
        );
        verify(repository, times(flowersDto.size())).save(any(Flower.class));   // Asserts all flowers were saved
    }

    @Test
    void mustUpdateFlowerSuccessfully() {
        Long id = 1L;
        FlowerDto originalFlowerDto = new FlowerDto(id, "Rose", "Pink", 20.0, true);
        Flower originalFlower = mapper.fromDto(originalFlowerDto);  // Needed to do this conversion because flower entities can't be directly instanced

        when(repository.findByIdAndStatusTrue(id)).thenReturn(Optional.of(originalFlower));

        FlowerDto updatedFlower = new FlowerDto(id, "Rose", "Light Pink", 25.0, true);
        FlowerDto savedFlower = service.updateFlower(id, updatedFlower);
        assertAll(
                () -> assertNotEquals(originalFlower.toString(), updatedFlower.toString()),
                () -> assertEquals(updatedFlower.toString(), savedFlower.toString())
        );
    }

    @Test
    void mustDisableFlowerSuccessfully() {
        Long id = 1L;
        FlowerDto flowerDto = new FlowerDto(id, "Rose", "Pink", 20.0, true);
        Flower flower = mapper.fromDto(flowerDto);  // Needed to do this conversion because flower entities can't be directly instanced

        //Simulates the existence of a flower
        when(repository.findByIdAndStatusTrue(id)).thenReturn(Optional.of(flower));

        Boolean disabled = service.disableFlower(id);

        if (disabled){
            when(repository.findByIdAndStatusTrue(id)).thenReturn(Optional.empty());
        }

        FlowerNotFoundException ex = assertThrowsExactly(FlowerNotFoundException.class, ()-> {
            Optional<FlowerDto> disabledFlower = service.getFlowerById(id);
        });
    }

    @Test
    void mustGetFlowerSuccessfully(){
        Long id = 1L;
        FlowerDto flowerDto = new FlowerDto(id, "Rose", "Pink", 20.0, true);
        Flower flower = mapper.fromDto(flowerDto);  // Needed to do this conversion because flower entities can't be directly instanced

        when(repository.findByIdAndStatusTrue(id)).thenReturn(Optional.of(flower));

        Optional<FlowerDto> getFlower = service.getFlowerById(id);
        FlowerDto getDto = getFlower.get(); // Converts from optional body to actual DTO

        assertNotEquals(getFlower, Optional.empty());
        assertAll(
                () -> assertEquals(flowerDto.name(), getDto.name()),
                () -> assertEquals(flowerDto.color(), getDto.color()),
                () -> assertEquals(flowerDto.price(), getDto.price()),
                () -> assertEquals(flowerDto.status(), getDto.status())
        );
    }

    @Test
    void mustGetListOfFlowersSuccessfully(){
        // Represent entities stored at the repository
        List<FlowerDto> repositoryFlowersDto = new ArrayList<FlowerDto>() {{
            add(new FlowerDto(null, "Rose", "Pink", 20.0, false));
            add(new FlowerDto(null, "Tulip", "Yellow", 15.5, false));
            add(new FlowerDto(null, "Sunflower", "Yellow", 10.0, false));
            add(new FlowerDto(null, "Lily", "White", 30.0, false));
            add(new FlowerDto(null, "Daffodil", "Orange", 18.0, false));
            add(new FlowerDto(null, "Orchid", "Purple", 50.0, false));
            add(new FlowerDto(null, "Carnation", "Red", 25.0, false));
            add(new FlowerDto(null, "Violet", "Blue", 12.0, false));
            add(new FlowerDto(null, "Tulip", "Purple", 22.0, false));
            add(new FlowerDto(null, "Chrysanthemum", "Pink", 28.0, false));
        }};
        List<Flower> repositoryFlowerEntities = repositoryFlowersDto.stream().map(mapper::fromDto).toList();

        when(repository.findByStatusTrue()).thenReturn(repositoryFlowerEntities);

        List<FlowerDto> flowersGet = service.listFlowers();

        assertAll(
                () -> assertDoesNotThrow(() -> service.listFlowers()),
                () -> assertNotNull(flowersGet),
                () -> assertEquals(flowersGet.size(), repositoryFlowerEntities.size())
        );
        }


    // EXCEPTIONS
    @Test
    void nullColorMustThrowInvalidArgumentExceptionOnFlowerSave() {
        // Simulates a DTO body being received
        FlowerDto flowerDto = new FlowerDto(null, "Rose", "", 20.0, false); // Bodies with null color must throw InvalidArgumentException

        // Simulates saving expecting InvalidArgumentException
        InvalidArgumentException ex = assertThrowsExactly(InvalidArgumentException.class, () -> {
            service.saveFlower(flowerDto);
        });

        // Asserts exception message is the same as expected by the situation
        assertEquals("Flower color can't be empty", ex.getMessage());
    }

    @Test
    void nullNameMustThrowInvalidArgumentExceptionOnFlowerSave() {
        // Simulates a DTO body received
        FlowerDto flowerDto = new FlowerDto(null, "", "Pink", 20.0, false); // Bodies with null name must throw InvalidArgumentException

        // Simulates saving expecting InvalidArgumentException
        InvalidArgumentException ex = assertThrowsExactly(InvalidArgumentException.class, () -> {
           service.saveFlower(flowerDto);
        });

        // 3. Asserts exception message is the same as expected by the situation
        assertEquals("Flower name can't be empty", ex.getMessage());
    }

    @Test
    void nullColorMustThrowInvalidArgumentExceptionOnFlowerUpdate() {
        Long id = 1L;
        FlowerDto flowerDto = new FlowerDto(id, "Rose", "Pink", 20.0, false);
        Flower flower = mapper.fromDto(flowerDto);

        when(repository.findByIdAndStatusTrue(id)).thenReturn(Optional.of(flower));

        FlowerDto updatedDto = new FlowerDto(id, "Rose", "", 20.0, false);

        InvalidArgumentException ex = assertThrowsExactly(InvalidArgumentException.class, ()-> {
            FlowerDto saved = service.updateFlower(id, updatedDto);
        });
        assertEquals(ex.getMessage(), "Flower color can't be empty");

    }

    @Test
    void nullNameMustThrowInvalidArgumentExceptionOnFlowerUpdate() {
        Long id = 1L;
        FlowerDto flowerDto = new FlowerDto(id, "Rose", "Pink", 20.0, false);
        Flower flower = mapper.fromDto(flowerDto);

        when(repository.findByIdAndStatusTrue(id)).thenReturn(Optional.of(flower));

        FlowerDto updatedDto = new FlowerDto(id, "", "Pink", 20.0, false);

        InvalidArgumentException ex = assertThrowsExactly(InvalidArgumentException.class, ()-> {
            FlowerDto saved = service.updateFlower(id, updatedDto);
        });
        assertEquals(ex.getMessage(), "Flower name can't be empty");
    }

    @Test
    void inexistentOrDisabledFlowerMustThrowFlowerNotFoundException() {
        Long id = 1L;
        when(repository.findByIdAndStatusTrue(id)).thenReturn(Optional.empty());

        FlowerNotFoundException ex = assertThrowsExactly(FlowerNotFoundException.class, ()-> {
            service.getFlowerById(id);
        });
        assertEquals(ex.getMessage(), "Inexistent flower or disabled");
    }

    @Test
    void disableFlowerMustThrowStatusExceptionOnPriceUpdateWhenItCostsOverThirty() {
        Long id = 1L;
        FlowerDto flowerDto = new FlowerDto(id, "Rose", "Pink", 33.0, true);
        Flower flower = mapper.fromDto(flowerDto);

        when(repository.findByIdAndStatusTrue(id)).thenReturn(Optional.of(flower));

        StatusException ex = assertThrowsExactly(StatusException.class, () -> {
            service.disableFlower(id);
        });

        assertEquals("Flowers with price bigger than $30 can´t be disabled", ex.getMessage());
    }
}
