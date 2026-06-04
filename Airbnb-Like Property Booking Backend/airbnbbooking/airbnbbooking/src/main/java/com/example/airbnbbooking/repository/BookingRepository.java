package com.example.airbnbbooking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.airbnbbooking.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	List<Booking> findByGuestId(Long guestId);
	List<Booking> findByPropertyId(Long propertyId);
	
	@Query("""
			SELECT b
			FROM Booking b
			where b.property.id = :propertyId
			AND b.status IN(
				com.example.airbnbbooking.enums.BookingStatus.REQUESTED,
				com.example.airbnbbooking.enums.BookingStatus.CONFIRMED
			)
			AND b.startDate <= :endDate
			AND b.endDate >= :startDate
			""")
	List<Booking> findConflictingBookings(
			@Param("propertyId") Long propertyId,
			@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

}
