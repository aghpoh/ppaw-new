package ro.usv.ppaw.lab1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.usv.ppaw.lab1.dto.ClientRentsDTO;
import ro.usv.ppaw.lab1.dto.MeanTimeDTO;
import ro.usv.ppaw.lab1.entities.Vehicle;
import ro.usv.ppaw.lab1.repositories.VehicleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Optional<Vehicle> findById(Long id) {
        return vehicleRepository.findById(id);
    }


    public List<Vehicle> findAll() {
        return StreamSupport.stream(vehicleRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<ClientRentsDTO> findClientRents(Long id){
        return vehicleRepository.findClientRents(id);
    }

    public MeanTimeDTO getMeanRentTime(Long id, String brand) {
        return vehicleRepository.getMeanRentTime(id, brand);
    }
}
