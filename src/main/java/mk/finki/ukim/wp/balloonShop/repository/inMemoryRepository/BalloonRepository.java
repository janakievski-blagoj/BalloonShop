//package mk.finki.ukim.wp.lab.repository.inMemoryRepository;
//
//import mk.finki.ukim.wp.lab.model.Balloon;
//import mk.finki.ukim.wp.lab.model.Manufacturer;
//import mk.finki.ukim.wp.lab.model.enums.TYPE;
//import mk.finki.ukim.wp.lab.model.exceptions.BalloonAlreadyExistsException;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class BalloonRepository {
//
//    public final List<Balloon> balloons = new ArrayList<>(){
//    };
//
//    public List<Balloon> findAllBalloons(){
//        balloons.sort(Balloon::compareTo);
//        return balloons;
//    }
//
//    public List<Balloon> findByType(String text){
//        return balloons.stream().filter(r -> r.getBalloonType() == TYPE.valueOf(text)).collect(Collectors.toList());
//    }
//
//    public List<Balloon> findAllByNameOrDescription(String text){
//        return balloons.stream().filter(r -> r.getName().contains(text) || r.getDescription().contains(text)).collect(Collectors.toList());
//    }
//
//    public Optional<Balloon> saveOrUpdate(String name, String description, Manufacturer manufacturer, TYPE balloonType/*, boolean update*/){
//        /*if (balloons.stream().anyMatch(r -> r.getName().equals(name)) && !update){
//            throw new BalloonAlreadyExistsException(name);
//        }*/
//        balloons.removeIf(r -> r.getName().equals(name) && r.getDescription().equals(description));
//        Balloon b = new Balloon(name, description, manufacturer, balloonType);
//        balloons.add(b);
//        return Optional.of(b);
//    }
//
//    public void deleteBalloon(Long id){
//        balloons.removeIf(r -> r.getId().equals(id));
//    }
//
//    public Optional<Balloon> findBalloonById(Long id){
//        return balloons.stream().filter(r -> r.getId().equals(id)).findFirst();
//    }
//
//
//
//}
