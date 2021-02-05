package mk.finki.ukim.wp.balloonShop.repository.JPARepository;

import mk.finki.ukim.wp.balloonShop.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalloonRepoJPA extends JpaRepository<Balloon,Long> {
    List<Balloon> findAllByName(String text);
}
