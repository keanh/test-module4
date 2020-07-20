package com.test.testmodule4.service;

import com.test.testmodule4.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CityService {
    Page<City> findAll(Pageable pageable);
    Optional<City> findById(Long id);
    void save(City city);
    void remove(Long id);
}
