package com.nullexceptional.digibooky.domain.rental;

import com.nullexceptional.digibooky.domain.book.Book;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class RentalRepository {

    private final Map<UUID, Rental> rentalsRepo;

    public RentalRepository() {
        this.rentalsRepo = new HashMap<>();
    }

    public Map<UUID, Rental> getRentalsRepo() {
        return rentalsRepo;
    }

    public Rental saveRental(Rental rental) {
        rentalsRepo.put(rental.getId(), rental);
        return rental;
    }

    public Rental updateEndDateRental(UUID rentalId) {
        rentalsRepo.get(rentalId).setEndDate(LocalDate.now());
        return rentalsRepo.get(rentalId);
    }

    public List<Book> getLentBooksByMember(UUID userId) {
        return rentalsRepo.values().stream()
                .filter(rental -> rental.getUser().getId().equals(userId))
                .map(Rental::getBook)
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooksOverdue() {
        return rentalsRepo.values().stream()
                .filter(rental -> rental.getReturnDate().isBefore(LocalDate.now()))
                .map(Rental::getBook)
                .collect(Collectors.toList());
    }

    public Rental getRental(UUID id){
        return rentalsRepo.get(id);
    }
}
