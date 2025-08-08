package com.senai.flora.repository;

import com.senai.flora.dto.FlowerDto;
import com.senai.flora.model.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {
    List<Flower> findByStatusTrue();
    List<Flower> findByStatusFalse();
    Optional<FlowerDto> findByIdAndStatusTrue(Long id);
}
