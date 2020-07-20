package com.test.testmodule4.service;

import com.test.testmodule4.model.City;
import com.test.testmodule4.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Iterable<Country> findAll();
    Optional<Country> findById(Long id);
    void save(Country country);
    void remove(Long id);
}
