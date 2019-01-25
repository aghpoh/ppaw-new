package ro.usv.ppaw.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.usv.ppaw.lab1.dto.ClientMeanRentsDTO;
import ro.usv.ppaw.lab1.dto.MeanTimeDTO;
import ro.usv.ppaw.lab1.entities.*;
import ro.usv.ppaw.lab1.services.ClientService;
import ro.usv.ppaw.lab1.services.RentHistoryService;
import ro.usv.ppaw.lab1.services.VehicleService;

import java.util.*;

@Controller
@RequestMapping("/rents")
public class RentHistoryController {

    private final RentHistoryService rentHistoryService;
    private final VehicleService vehicleService;
    private final ClientService clientService;

    @Autowired
    public RentHistoryController(RentHistoryService rentHistoryService, VehicleService vehicleService, ClientService clientService) {
        this.rentHistoryService = rentHistoryService;
        this.vehicleService = vehicleService;
        this.clientService = clientService;
    }

    @GetMapping(value = "/all")
    public String viewAll(Model model) {
        List<String> brands = new ArrayList<>();
        List<Vehicle> vehicles = vehicleService.findAll();

        for (Vehicle vehicle : vehicles) {
            if (!brands.contains(vehicle.getModel())) {
                brands.add(vehicle.getModel());
            }
        }
        Collections.sort(brands);
        List<Client> clients = clientService.findAll();
        List<ClientMeanRentsDTO> meanRentList = new ArrayList<>();
        for (Client client : clients) {
            Map<String, Integer> map = new TreeMap<>();
            for (String brand : brands) {
                MeanTimeDTO meanRentTime = vehicleService.getMeanRentTime(client.getId(), brand);
                if (meanRentTime == null) {
                    map.put(brand, 0);
                } else {
                    map.put(brand, meanRentTime.getMean());
                }
            }
            ClientMeanRentsDTO clientMeanRentsDTO = new ClientMeanRentsDTO();
            clientMeanRentsDTO.setFirstName(client.getFirstName());
            clientMeanRentsDTO.setLastName(client.getLastName());
            clientMeanRentsDTO.setMap(map);

            meanRentList.add(clientMeanRentsDTO);
        }

        model.addAttribute("client_rents", meanRentList);
        model.addAttribute("brands", brands);

        return "pages/rent-history/view";
    }

    @GetMapping("/add")
    public String viewAddRentForm(Model model, Pageable pageable) {
        getVehiclesAndClientsData(model, pageable);
        model.addAttribute("rent", new RentHistory());

        return "pages/rent-history/add";
    }

    @PostMapping("/add")
    public String addRent(
            @ModelAttribute("rent")
            @Validated RentHistory rentHistory,
            BindingResult bindingResult,
            Model model,
            Pageable pageable
    ) {
        if (bindingResult.hasErrors()) {
            getVehiclesAndClientsData(model, pageable);
            return "pages/rent-history/add";
        }

        rentHistoryService.add(rentHistory);

        return "redirect:/rents/all";
    }

    private void getVehiclesAndClientsData(Model model, Pageable pageable) {
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("clients", clientService.findAll());
    }
}
