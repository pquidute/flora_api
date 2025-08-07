package com.senai.flora.repository;

import com.senai.flora.model.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {
    Optional<Flower> findById(Long id);
    List<Flower> findByStatusTrue();
}
