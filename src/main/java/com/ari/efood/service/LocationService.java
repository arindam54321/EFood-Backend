package com.ari.efood.service;

import com.ari.efood.dto.LocationDto;
import com.ari.efood.exception.LocationException;

public interface LocationService {
    LocationDto addLocation(LocationDto locationDto) throws LocationException;
}
