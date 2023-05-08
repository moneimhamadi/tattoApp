package com.jwtproject.userSecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwtproject.userSecurity.Entity.ProductMovement;

@Repository
public interface ProductMovementRepository extends JpaRepository<ProductMovement, Long> {

}
