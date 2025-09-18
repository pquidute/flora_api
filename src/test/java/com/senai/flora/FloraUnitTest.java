package com.senai.flora;

import com.senai.flora.application.dto.FlowerDto;
import com.senai.flora.application.mapper.FlowerMapper;
import com.senai.flora.application.mapper.FlowerMapperImpl;
import com.senai.flora.application.service.FlowerAppServiceImpl;
import com.senai.flora.domain.entity.Flower;
import com.senai.flora.domain.repository.FlowerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FloraUnitTest {
    @Mock
    private FlowerRepository repository;

    @Mock
    private FlowerMapperImpl mapper;

    @InjectMocks
    private FlowerAppServiceImpl appService;

    @Test
    void mustSaveValidFlower(){
        FlowerDto dto = new FlowerDto(null, "Rose", "Pink", 18.0, false);   // Status false & id null because it´s the default value when these values aren´t attached to the body, what´s expected by this API
        Flower entity = mapper.fromDto(dto);

        when(repository.save(any())).thenReturn(entity);   // Setup repository so whenever it saves anything, it returns 'entity'

        FlowerDto saved = appService.saveFlower(dto);

        assertNotNull(saved);
        assertEquals("Rose", saved.name());
        verify(repository.save(any()));
    }
}
