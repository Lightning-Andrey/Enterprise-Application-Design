package com.ead.shipserver.controller;

import com.ead.shipserver.dto.ShipDTO;
import com.ead.shipserver.model.Ship;
import com.ead.shipserver.service.ShipService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@AllArgsConstructor
public class ShipController {

    private final ShipService shipService;

    @PostMapping("/ship/create")
    public ResponseEntity<Ship> create(@RequestBody ShipDTO dto){
        return mappingResponseProduct(shipService.create(dto));
    }

    @GetMapping("/ship/{id}")
    public ResponseEntity<Ship> findById(@PathVariable Long id){
        return mappingResponseProduct(shipService.findById(id));
    }

    @GetMapping("/ships")
    public ResponseEntity<List<Ship>> findAll(){
        return mappingResponseListProduct(shipService.findAll());
    }

    @PutMapping("/ship/update")
    public ResponseEntity<Ship> update(@RequestBody Ship ship){
        return mappingResponseProduct(shipService.update(ship));
    }

    @DeleteMapping("/ship/delete/{id}")
    public HttpStatus delete(@PathVariable Long id){
        shipService.delete(id);
        return HttpStatus.OK;
    }

    private ResponseEntity<Ship> mappingResponseProduct(Ship ship) {
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    private ResponseEntity<java.util.List<Ship>> mappingResponseListProduct(List<Ship> ships) {
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }
}
