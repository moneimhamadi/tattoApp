package com.jwtproject.userSecurity.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwtproject.userSecurity.Entity.Reservation;
import com.jwtproject.userSecurity.Service.ReservationService;

@RestController
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	
	@PostMapping("/makeReservation/{idBoutique}/{barcodeProduct}")
	public  ResponseEntity <?> makeReservation (@PathVariable Long idBoutique,@PathVariable String barcodeProduct,@RequestBody Reservation reservation) {
		return reservationService.makeReservation(idBoutique, barcodeProduct, reservation);
	}
	
	@GetMapping("/getAllResrvationByIdBoutiqueByDate/{idBoutique}/{dateReservation}")
	public List<Reservation> getAllResrvationByIdBoutiqueByDate(@PathVariable Long idBoutique, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateReservation){
		return reservationService.getAllReservationForBoutiqueByDate(idBoutique, dateReservation);
	}
}
 