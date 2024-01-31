package com.ari.efood.service;

import com.ari.efood.dto.LocationDto;
import com.ari.efood.exception.LocationException;
import com.ari.efood.model.Location;
import com.ari.efood.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository repository;

    @Override
    public LocationDto addLocation(LocationDto locationDto) throws LocationException {
        Optional<Location> location = repository.findById(locationDto.getPin());

        if (location.isPresent()) {
            throw new LocationException("Location with same PIN already exists");
        }

        return repository.save(locationDto.toEntity()).toDto();
    }

    @Override
    public List<LocationDto> getAllLocations() {
        List<Location> locations = repository.findAll();
        return locations.stream().map(Location::toDto).toList();
    }

    @Override
    public String deleteLocation(String pin) throws LocationException {
        Optional<Location> location = repository.findById(pin);

        if (location.isEmpty()) {
            throw new LocationException("No Location found with the given PIN");
        }

        repository.delete(location.get());
        return "Location deleted with PIN: " + pin;
    }

    @Override
    public boolean doesExist(String pin) {
        return repository.existsById(pin);
    }
}
