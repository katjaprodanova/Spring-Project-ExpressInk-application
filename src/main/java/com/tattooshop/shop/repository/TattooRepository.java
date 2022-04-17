package com.tattooshop.shop.repository;

import com.tattooshop.shop.model.Tattoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TattooRepository extends JpaRepository<Tattoo,Long> {
}
