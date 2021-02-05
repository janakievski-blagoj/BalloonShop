package mk.finki.ukim.wp.balloonShop.service.Impl;

import mk.finki.ukim.wp.balloonShop.model.Balloon;
import mk.finki.ukim.wp.balloonShop.model.Manufacturer;
import mk.finki.ukim.wp.balloonShop.model.enums.TYPE;
import mk.finki.ukim.wp.balloonShop.model.exceptions.BalloonNotFoundException;
import mk.finki.ukim.wp.balloonShop.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.wp.balloonShop.repository.JPARepository.BalloonRepoJPA;
import mk.finki.ukim.wp.balloonShop.repository.JPARepository.ManufacturerRepoJPA;
import mk.finki.ukim.wp.balloonShop.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    public final BalloonRepoJPA balloonRepository;
    public final ManufacturerRepoJPA manufacturerRepository;

    public BalloonServiceImpl(BalloonRepoJPA balloonRepository, ManufacturerRepoJPA manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByName(String text) {
        return balloonRepository.findAllByName(text);
    }

    @Override
    public Optional<Balloon> SaveOrUpdate(String name, String description, Long manufacturerId, TYPE balloonType) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElseThrow(()-> new ManufacturerNotFoundException(manufacturerId));
        List<Balloon> sameNameBalloons = balloonRepository.findAllByName(name);
        if (!sameNameBalloons.isEmpty()){
            Long balloonId = sameNameBalloons.get(0).getId();
            if (balloonRepository.existsById(balloonId)) {
                balloonRepository.deleteById(balloonId);
                Balloon balloon = new Balloon(name, description, manufacturer, balloonType);
                return Optional.of(balloonRepository.save(balloon));
            }
        }
        return Optional.of(balloonRepository.save(new Balloon(name, description, manufacturer, balloonType)));
    }

    @Override
    public void deleteBalloon(Long id) {
        balloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> findBalloonById(Long id) {
        return balloonRepository.findById(id);
    }

    /*
    @Override
    public List<Balloon> searchByType(String type) {
        return balloonRepository.findByType(type);
    */

}
