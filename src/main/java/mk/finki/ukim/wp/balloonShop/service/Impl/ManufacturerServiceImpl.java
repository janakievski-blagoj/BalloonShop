package mk.finki.ukim.wp.balloonShop.service.Impl;

import mk.finki.ukim.wp.balloonShop.model.Manufacturer;
import mk.finki.ukim.wp.balloonShop.repository.JPARepository.ManufacturerRepoJPA;
import mk.finki.ukim.wp.balloonShop.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepoJPA manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepoJPA manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String country, String address) {
        return Optional.of(manufacturerRepository.save(new Manufacturer(name, country, address)));
    }
}
