package mk.finki.ukim.wp.balloonShop.service;

import mk.finki.ukim.wp.balloonShop.model.Balloon;
import mk.finki.ukim.wp.balloonShop.model.enums.TYPE;

import java.util.List;
import java.util.Optional;

public interface BalloonService {
    List<Balloon> listAll();
    List<Balloon> searchByName(String text);
    Optional<Balloon> SaveOrUpdate(String name, String description, Long manufacturerId, TYPE balloonType);
    void deleteBalloon(Long id);
    Optional<Balloon> findBalloonById(Long id);
//    List<Balloon> searchByType(String type);
}
