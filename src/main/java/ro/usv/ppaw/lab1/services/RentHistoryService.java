package ro.usv.ppaw.lab1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.usv.ppaw.lab1.entities.RentHistory;
import ro.usv.ppaw.lab1.repositories.RentHistoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RentHistoryService {

    private final RentHistoryRepository rentHistoryRepository;

    @Autowired
    public RentHistoryService(RentHistoryRepository rentHistoryRepository) {
        this.rentHistoryRepository = rentHistoryRepository;
    }

    public Optional<RentHistory> findById(Long id) {
        return rentHistoryRepository.findById(id);
    }

    public List<RentHistory> findAll() {
        return StreamSupport.stream(rentHistoryRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void add(RentHistory rentHistory) {
        rentHistoryRepository.save(rentHistory);
    }
}
