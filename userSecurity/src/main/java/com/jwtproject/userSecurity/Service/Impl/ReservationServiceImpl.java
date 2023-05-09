package com.jwtproject.userSecurity.Service.Impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jwtproject.userSecurity.Entity.Product;
import com.jwtproject.userSecurity.Entity.Reservation;
import com.jwtproject.userSecurity.Entity.Stock;
import com.jwtproject.userSecurity.Repository.BoutiqueRepository;
import com.jwtproject.userSecurity.Repository.ProductRepository;
import com.jwtproject.userSecurity.Repository.ReservationRepository;
import com.jwtproject.userSecurity.Repository.StockRepository;
import com.jwtproject.userSecurity.Service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	StockRepository stcokRepos;
	@Autowired
	BoutiqueRepository boutiqueRepository;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	ProductRepository productRepository;

	@Override
	public Reservation getOneReservation(Long idReservation) {
		
		return reservationRepository.findById(idReservation).get();
	}

	@Override
	public List<Reservation> getAllReservation() {
		
		return reservationRepository.findAll();
	}

	@Override
	public ResponseEntity <?> makeReservation(Long idBoutique, String barcodeProduct, Reservation reservation) {
	    // Get the stock of the boutique
	    List<Product> stockProducts = stcokRepos.findById(idBoutique).get().getStockProducts();

	    // Check if the product exists and is available
	    boolean productAvailable = false;
	    for (Product searchedProduct : stockProducts) {
	        if ((searchedProduct.getBarcode().equals(barcodeProduct)) && (searchedProduct.getQuantity() > 0)
	                && (searchedProduct.getExiryAt().after(reservation.getReservationDate()))) {
	            productAvailable = true;
	            break;
	        }
	    }
	    if (!productAvailable) {
	        return ResponseEntity.badRequest().body("Product is not available");
	    }

	    // Check if the reservation slot is available and within the range of 8 to 14 hours
	    Date reservationDate = reservation.getReservationDate();
	    int reservationHour = reservation.getReservationHour();
	    if (reservationHour < 8 || reservationHour > 14) {
	        return ResponseEntity.badRequest().body("Reservation slot have to be between 8 and 14");
	    }
	    for (Reservation existingReservation : reservationRepository.findAllByReservationDateAndIdBoutique(reservationDate,idBoutique)) {
	        if (existingReservation.getReservationHour() == reservationHour) {
	            return ResponseEntity.badRequest().body("Reservation hour is not available,Choose other hour ");
	        }
	    }
	    Calendar start = Calendar.getInstance();
	    start.setTime(reservationDate);
	    start.set(Calendar.HOUR_OF_DAY, reservationHour);
	    start.set(Calendar.MINUTE, 0);
	    start.set(Calendar.SECOND, 0);
	    start.set(Calendar.MILLISECOND, 0);
	    Calendar end = Calendar.getInstance();
	    end.setTime(reservationDate);
	    end.set(Calendar.HOUR_OF_DAY, reservationHour + 1);
	    end.set(Calendar.MINUTE, 0);
	    end.set(Calendar.SECOND, 0);
	    end.set(Calendar.MILLISECOND, 0);

	    // Create the reservation and update the product stock
	    reservation.setIdBoutique(idBoutique);
	    reservation.setCreatedAt(new Date());
	    reservation.setBarcodeProduct(barcodeProduct);
	    reservation.setNameProduct(productRepository.findFirstByBarcode(barcodeProduct).getName());
	    reservation.setDescriptionProduct((productRepository.findFirstByBarcode(barcodeProduct)).getDescription());
	    reservation.setReservationHour(reservationHour-1);
	    reservation.setQuantityPrduct((long) 1);

	    Reservation savedReservation = reservationRepository.save(reservation);
	    Product reservedProduct = productRepository.findFirstByBarcodeAndStockId(barcodeProduct,idBoutique);
	    reservedProduct.setQuantity(reservedProduct.getQuantity() - 1);
	    productRepository.save(reservedProduct);

	    return ResponseEntity.ok("Reservation made successfully");
	}


	public boolean checkProductExistsAndNotExpired(String barcodeProduct, Date date) {
		boolean exists = false;
		List<Product> products = productRepository.findAllByBarcode(barcodeProduct);
		if (products.isEmpty()) {
			return false;
		} else {
			for (Product p : products) {
				int result = p.getExiryAt().compareTo(date);
				if (result > 0) {
					exists = true;
					break;
				} else
					exists = false;
			}
		}
		return exists;
	}

	public boolean isReservationSlotAvailable(Date reservationDate, int reservationHour) {
		Calendar start = Calendar.getInstance();
		start.setTime(reservationDate);
		start.set(Calendar.HOUR_OF_DAY, reservationHour);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		Calendar end = Calendar.getInstance();
		end.setTime(reservationDate);
		end.set(Calendar.HOUR_OF_DAY, reservationHour + 1);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		for (Reservation existingReservation : reservationRepository.findAllByReservationDate(reservationDate)) {
			if (start.getTime().compareTo(existingReservation.getReservationDate()) == 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Reservation> getAllReservationForBoutiqueByDate(Long idBoutique, Date reservationDate) {
		
		return reservationRepository.findAllByReservationDateAndIdBoutique(reservationDate, idBoutique);
	}

}
