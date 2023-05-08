package com.jwtproject.userSecurity.Service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jwtproject.userSecurity.Entity.Reservation;


@Service
public interface ReservationService {
	
	public Reservation getOneReservation(Long idReservation);
	public List<Reservation> getAllReservation();
	public ResponseEntity <?> makeReservation(Long idBoutique, String barcodeProduct, Reservation reservation) ;
	public List<Reservation> getAllReservationForBoutiqueByDate(Long idBoutique,Date reservationDate);

}
