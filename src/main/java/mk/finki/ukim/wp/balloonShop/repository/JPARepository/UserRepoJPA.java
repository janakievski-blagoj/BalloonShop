package mk.finki.ukim.wp.balloonShop.repository.JPARepository;

import mk.finki.ukim.wp.balloonShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepoJPA extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String text);
    Optional<User> findByUsernameAndPassword(String name, String password);
}
