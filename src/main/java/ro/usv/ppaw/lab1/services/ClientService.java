package ro.usv.ppaw.lab1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.usv.ppaw.lab1.dto.VehicleRentsDTO;
import ro.usv.ppaw.lab1.entities.Client;
import ro.usv.ppaw.lab1.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public List<Client> findAll() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<VehicleRentsDTO> findVehicleRents(Long id){
        return clientRepository.findVehicleRents(id);
    }
}
