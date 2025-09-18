package com.senai.flora.domain.repository;

import com.senai.flora.application.dto.FlowerDto;
import com.senai.flora.domain.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {
    List<Flower> findByStatusTrue();
    List<Flower> findByStatusFalse();
    Optional<Flower> findByIdAndStatusTrue(Long id);
}
