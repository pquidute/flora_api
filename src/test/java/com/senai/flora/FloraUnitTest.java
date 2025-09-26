package com.senai.flora;

import com.senai.flora.application.dto.FlowerDto;
import com.senai.flora.application.mapper.FlowerMapper;
import com.senai.flora.application.service.FlowerAppServiceImpl;
import com.senai.flora.domain.entity.Flower;
import com.senai.flora.domain.exception.InvalidArgumentException;
import com.senai.flora.domain.repository.FlowerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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



    // CRUD
    @Test
    void mustSaveValidFlower() {
        // 1. Simulates a DTO body received
        FlowerDto flowerDto = new FlowerDto(null, "Rose", "Pink", 20.0, true);

        // 2. Simulates saving (converts to entity inside this method)
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
    }

    @Test
    void mustUpdateFlowerSuccessfully() {
    }

    @Test
    void mustDisableFlowerSuccessfully() {
    }

    @Test
    void mustGetFlowerSuccessfully(){
    }

    @Test
    void mustGetListOfFlowersSuccessfully(){
    }


    // EXCEPTIONS
    @Test
    void saveFlower_nullColor_throwsInvalidArgumentException() {
        // 1. Simulates a DTO body received
        FlowerDto flowerDto = new FlowerDto(null, "Rose", "", 20.0, false);

        // 2. Simulates saving expecting InvalidArgumentException
        InvalidArgumentException ex = assertThrowsExactly(InvalidArgumentException.class, () -> {
            service.saveFlower(flowerDto);
        });

        // 3. Asserts exception message is the same as expected by the situation
        assertEquals("Flower color can't be empty", ex.getMessage());
    }

    @Test
    void saveFlower_nullName_throwsInvalidArgumentException() {
        // 1. Simulates a DTO body received
        FlowerDto flowerDto = new FlowerDto(null, "", "Pink", 20.0, false);

        // 2. Simulates saving expecting InvalidArgumentException
        InvalidArgumentException ex = assertThrowsExactly(InvalidArgumentException.class, () -> {
           service.saveFlower(flowerDto);
        });

        // 3. Asserts exception message is the same as expected by the situation
        assertEquals("Flower name can't be empty", ex.getMessage());
    }

    @Test
    void updateFlower_nullColor_throwsInvalidArgumentException() {}

    @Test
    void updateFlower_nullName_throwsInvalidArgumentException() {}

    @Test
    void findFlower_inexistent_throwsFlowerNotFoundException() {}

//    @Test
//    void disableFlower_overThirtyDollars_throwsStatusException() {
//        Long id = 1L;
//        Flower flower = new Flower(id, "Rose", "Pink", 33.0, true);
//
//        // 1. Pretend this flower already exists in the repository and takes it
//        when(repository.findById(id)).thenReturn(Optional.of(flower));
//
//        // 2. Simulates disabling expecting InvalidStatusException
//        StatusException ex = assertThrowsExactly(StatusException.class, () -> {
//            service.disableFlower(id);
//        });
//
//        // 3. Asserts exception message is the same as expected by the situation
//        assertEquals("Flowers with price bigger than $30 can´t be disabled", ex.getMessage());
//    } //FIXME


}
