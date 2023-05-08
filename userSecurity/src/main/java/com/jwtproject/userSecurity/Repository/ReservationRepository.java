package com.jwtproject.userSecurity.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwtproject.userSecurity.Entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	

	public List<Reservation> findAllByReservationDate(Date reservationDate);
	public List<Reservation> findAllByReservationDateAndIdBoutique(Date reservationDate,Long idBoutique);

}
