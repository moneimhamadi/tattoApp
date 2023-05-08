package com.jwtproject.userSecurity.Service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtproject.userSecurity.Entity.Boutique;
import com.jwtproject.userSecurity.Entity.Stock;
import com.jwtproject.userSecurity.Repository.BoutiqueRepository;
import com.jwtproject.userSecurity.Service.BoutiqueService;


@Service
public class BoutiqueServiceImpl implements BoutiqueService {
	@Autowired
	BoutiqueRepository boutiqueRepo;

	@Override
	public Boutique saveBoutiqueWithStock(Boutique boutique) {
		boutique.setCreatedAt(new Date());
		Stock stockBoutique=new Stock();
		stockBoutique.setCreatedAt(new Date());
		stockBoutique.setNameStock(boutique.getNameBoutique().concat(" Stock"));
		stockBoutique.setBoutique(boutique);
		stockBoutique.setCreatedAt(new Date());
		boutique.setStock(stockBoutique);
		
		return boutiqueRepo.save(boutique);

	}

}
