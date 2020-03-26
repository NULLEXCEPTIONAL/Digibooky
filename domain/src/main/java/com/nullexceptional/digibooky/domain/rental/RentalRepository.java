package com.nullexceptional.digibooky.domain.rental;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class RentalRepository {

    private Map<UUID, Rental> rentalsRepo;

    public RentalRepository() {
        this.rentalsRepo = new HashMap<>();
    }

    public Rental saveRental(){
        return null;
    }


}
