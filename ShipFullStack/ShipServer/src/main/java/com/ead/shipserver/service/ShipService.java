package com.ead.shipserver.service;


import com.ead.shipserver.dto.ShipDTO;
import com.ead.shipserver.model.Ship;
import com.ead.shipserver.repository.ShipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShipService {

    private final ShipRepository shipRepository;

    public Ship create(ShipDTO dto) {
        return shipRepository.save(Ship.builder()
                .type(dto.getType())
                .mass(dto.getMass())
                .crew(dto.getCrew())
                .build());
    }

    public List<Ship> findAll() {
        return shipRepository.findAll();
    }

    public Ship findById(Long id){
        return shipRepository.findById(id).orElse(null);
    }

    public Ship update(Ship ship){
        return shipRepository.save(ship);
    }

    public void delete(Long id) {
        shipRepository.deleteById(id);
    }
}
