package uz.market.uzum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.market.uzum.domains.Destination;

@Repository
public interface    DestinationRepository extends JpaRepository<Destination, Long> {

}
