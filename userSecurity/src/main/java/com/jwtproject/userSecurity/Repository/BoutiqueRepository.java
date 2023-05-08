package com.jwtproject.userSecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwtproject.userSecurity.Entity.Boutique;

@Repository
public interface BoutiqueRepository extends JpaRepository<Boutique, Long> {

}
