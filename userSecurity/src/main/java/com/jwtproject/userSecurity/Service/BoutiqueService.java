package com.jwtproject.userSecurity.Service;

import org.springframework.stereotype.Service;

import com.jwtproject.userSecurity.Entity.Boutique;


@Service
public interface BoutiqueService {
	
	public Boutique saveBoutiqueWithStock(Boutique boutique );

}
