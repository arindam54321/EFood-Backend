package com.ari.efood.service;

import com.ari.efood.dto.LocationDto;
import com.ari.efood.exception.LocationException;

import java.util.List;

public interface LocationService {
    LocationDto addLocation(LocationDto locationDto) throws LocationException;

    List<LocationDto> getAllLocations();
}
