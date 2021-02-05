package mk.finki.ukim.wp.balloonShop.repository.JPARepository;

import mk.finki.ukim.wp.balloonShop.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepoJPA extends JpaRepository<Manufacturer,Long> {
}
