package ro.usv.ppaw.lab1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ro.usv.ppaw.lab1.dto.ClientRentsDTO;
import ro.usv.ppaw.lab1.dto.MeanTimeDTO;
import ro.usv.ppaw.lab1.entities.Vehicle;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    @Query(value = "SELECT C.FIRST_NAME, C.LAST_NAME, RH.DAYS FROM VEHICLES V "
            + "INNER JOIN RENT_HISTORY RH ON RH.VEHICLE_ID=V.ID "
            + "INNER JOIN CLIENTS C ON RH.CLIENT_ID=C.ID "
            + "WHERE V.ID=?1", nativeQuery = true)
    List<ClientRentsDTO> findClientRents(Long id);


    @Query(value = "SELECT avg(RH.days) as MEAN FROM  CLIENTS C " +
            "INNER JOIN RENT_HISTORY RH ON RH.CLIENT_ID=C.ID " +
            "INNER JOIN VEHICLES V ON RH.VEHICLE_ID=V.ID " +
            "WHERE C.ID=?1 AND V.MODEL=?2 " +
            "GROUP BY C.FIRST_NAME, C.LAST_NAME, V.MODEL ", nativeQuery = true)
    MeanTimeDTO getMeanRentTime(Long id, String brand);
}
