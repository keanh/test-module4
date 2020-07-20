package com.test.testmodule4.repository;

import com.test.testmodule4.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryRepository extends CrudRepository<Country,Long> {
}
