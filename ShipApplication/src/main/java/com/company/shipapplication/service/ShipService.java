package com.company.shipapplication.service;



import com.company.shipapplication.dto.ShipDTO;
import com.company.shipapplication.model.Ship;
import com.company.shipapplication.repository.ShipRepository;
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

    public Ship getById(Long id){
        return shipRepository.findById(id).orElse(null);
    }

    public List<Ship> findAll() {
        return shipRepository.findAll();
    }

    public void deleteAll() {
        shipRepository.deleteAll();
    }
}
