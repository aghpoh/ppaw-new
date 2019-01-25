package ro.usv.ppaw.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.usv.ppaw.lab1.dto.VehicleRentsDTO;
import ro.usv.ppaw.lab1.services.ClientService;
import ro.usv.ppaw.lab1.services.VehicleService;

import java.util.List;

@Controller
@RequestMapping("clients")
public class ClientController {

  private final ClientService clientService;

  @Autowired
  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping(value = "/all")
  public String viewAllClients(Model model) {
    model.addAttribute("clients", clientService.findAll());

    return "pages/clients/all";
  }

  @PostMapping(value = "/all")
  public String clientVehicles(@RequestParam("client") Long clientId, Model model) {
    List<VehicleRentsDTO> rents = clientService.findVehicleRents(clientId);
    model.addAttribute("vehicles", rents);
    model.addAttribute("clients", clientService.findAll());

    return "pages/clients/all";
  }
}
