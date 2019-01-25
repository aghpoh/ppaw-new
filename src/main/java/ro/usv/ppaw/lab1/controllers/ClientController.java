package ro.usv.ppaw.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.usv.ppaw.lab1.dto.VehicleRentsDTO;
import ro.usv.ppaw.lab1.services.ClientService;
import ro.usv.ppaw.lab1.services.VehicleService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    model.addAttribute("vehicles",  clientService.findVehicleRents(clientId));
    model.addAttribute("clients", clientService.findAll());
    getSelectedClient(clientId, model);

    return "pages/clients/all";
  }

  private void getSelectedClient(@RequestParam("client") Long clientId, Model model) {
    if (!clientService.findById(clientId).isPresent()) {
      model.addAttribute("selectedClient", null);
    } else {
      model.addAttribute("selectedClient", clientService.findById(clientId).get());
    }
  }
}
