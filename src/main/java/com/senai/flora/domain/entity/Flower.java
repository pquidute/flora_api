package com.senai.flora.domain.entity;

import com.senai.flora.domain.exception.InvalidArgumentException;
import com.senai.flora.domain.exception.StatusException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String color;


    @Positive
    @NotNull
    private Double price;

    private Boolean status = true;

    //Manual getters and setters because annotations (@Data, @Getter or @Setter) were conflicting with mapstruct library
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }

    public void validatePrice(Double price){
        if (price <= 5) {
            throw new InvalidArgumentException("The minimal price is $5");
        }
    }

    public void validateStatusChange(Double price){
        if (price > 30){
            throw new StatusException("Flowers with price bigger than $30 can´t be disabled");
        }
    }
}
