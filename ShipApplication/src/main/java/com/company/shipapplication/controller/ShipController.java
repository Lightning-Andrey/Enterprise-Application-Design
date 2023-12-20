package com.company.shipapplication.controller;


import com.company.shipapplication.dto.ShipDTO;
import com.company.shipapplication.parser.DomShipsParser;
import com.company.shipapplication.service.ShipService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@AllArgsConstructor
public class ShipController {

    private final ShipService shipService;

    @GetMapping("/ships")
    public String ships(Model model) {
        model.addAttribute("ships", shipService.findAll());
        return "ships";
    }

    @GetMapping("/")
    public String showHome() {
        return "redirect:/ships";
    }

    @PostMapping("ships/parse")
    public String parseShips() throws ParserConfigurationException, IOException, SAXException {
        DomShipsParser parser = new DomShipsParser();
        ArrayList<ShipDTO> dtos = parser.getShips("ship.xml");
        for (ShipDTO shipDTO : dtos)
            shipService.create(shipDTO);

        return "redirect:/ships";
    }

    @GetMapping("/ship/{id}")
    public String shipInfo(@PathVariable Long id, Model model) {
        model.addAttribute("ship", shipService.getById(id));
        return "ship-info";
    }

    @PostMapping("/ships/delete")
    public String deleteAll() {
        shipService.deleteAll();
        return "redirect:/ships";
    }
}
