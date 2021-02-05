package mk.finki.ukim.wp.balloonShop.service;

import mk.finki.ukim.wp.balloonShop.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();
    Optional<Manufacturer> findById(Long id);
    Optional<Manufacturer> save(String name, String country, String address);


}
