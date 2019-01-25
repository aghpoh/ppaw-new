package ro.usv.ppaw.lab1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.usv.ppaw.lab1.entities.RentHistory;

@Repository
public interface RentHistoryRepository extends CrudRepository<RentHistory, Long> {
}
