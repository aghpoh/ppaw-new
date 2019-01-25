package ro.usv.ppaw.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.usv.ppaw.lab1.services.VehicleService;

@Controller
@RequestMapping("vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping(value = "/all")
    public String index(Model model) {
        model.addAttribute("vehicles", vehicleService.findAll());

        return "pages/vehicles/all";
    }

    @PostMapping(value = "/all")
    public String clientVehicles(@RequestParam("vehicle") Long vehicleId, Model model) {
        model.addAttribute("clients", vehicleService.findClientRents(vehicleId));
        model.addAttribute("vehicles", vehicleService.findAll());

        return "pages/vehicles/all";
    }
}
