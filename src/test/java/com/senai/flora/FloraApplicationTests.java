package com.senai.flora;

import com.senai.flora.application.dto.FlowerDto;
import com.senai.flora.application.mapper.FlowerMapper;
import com.senai.flora.application.service.FlowerAppServiceImpl;
import com.senai.flora.domain.entity.Flower;
import com.senai.flora.domain.repository.FlowerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FloraApplicationTests {
	@Mock
	private FlowerRepository repository;

	@Mock
	private FlowerMapper mapper;

	@InjectMocks
	private FlowerAppServiceImpl service;


	@Test
	void mustSaveValidFlower(){
		FlowerDto flowerDto = new FlowerDto(null, "Rose", "Pink", 20.0, true);

		when(repository.save(mapper.fromDto(flowerDto))).thenReturn(mapper.fromDto(flowerDto));

		FlowerDto saved = service.saveFlower(flowerDto);

		assertNotNull(saved);

		verify(repository).save(mapper.fromDto(flowerDto));
	}	//FIXME not working (data type issues)

}
