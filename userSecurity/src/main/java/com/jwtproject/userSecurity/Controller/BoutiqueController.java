package com.jwtproject.userSecurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwtproject.userSecurity.Entity.Boutique;
import com.jwtproject.userSecurity.Service.BoutiqueService;

@RestController
public class BoutiqueController {

	@Autowired
	BoutiqueService boutiqueService;
	
	@PostMapping("/saveBoutiqueWitchStock")
	public Boutique saveBoutiqueWitchStock(@RequestBody Boutique boutique) {
		return boutiqueService.saveBoutiqueWithStock(boutique);
	}
}
