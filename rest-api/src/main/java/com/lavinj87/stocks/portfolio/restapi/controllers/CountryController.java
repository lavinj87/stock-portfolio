package com.lavinj87.stocks.portfolio.restapi.controllers;

import com.lavinj87.stocks.portfolio.restapi.entities.Country;
import com.lavinj87.stocks.portfolio.restapi.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/countries/")
public class CountryController {
	private final CountryRepository repository;

	@Autowired
	public CountryController(CountryRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/{countryId}")
	public Country findCountryById(@PathVariable String countryId) {
		return repository.findById(countryId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find country with id " + countryId));
	}

	@PostMapping("/{countryId}")
	public Country saveCountry(@PathVariable String countryId, @RequestBody Country country) {
		country.setCountryId(countryId);
		return repository.save(country);
	}

	@DeleteMapping("/{countryId}")
	public void deleteCountry(@PathVariable String countryId) {
		 repository.deleteById(countryId);
	}
}
