package ro.usv.ppaw.lab1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.usv.ppaw.lab1.dto.VehicleRentsDTO;
import ro.usv.ppaw.lab1.entities.Client;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    @Query(value = "SELECT RH.DAYS, V.MODEL FROM CLIENTS C "
            + "INNER JOIN RENT_HISTORY RH ON RH.CLIENT_ID=C.ID "
            + "INNER JOIN VEHICLES V ON RH.VEHICLE_ID=V.ID "
            + "WHERE C.ID=?1 ", nativeQuery = true)
    public List<VehicleRentsDTO> findVehicleRents(Long id);
}
