package com.test.testmodule4.controller;

import com.test.testmodule4.model.City;
import com.test.testmodule4.model.Country;
import com.test.testmodule4.repository.CityRepository;
import com.test.testmodule4.service.CityService;
import com.test.testmodule4.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CityController {
    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @ModelAttribute("country")
    public Iterable<Country> countries(){ return countryService.findAll();}

    @GetMapping("create-city")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("city",new City());
        return modelAndView;
    }

    @GetMapping("list")
    public ModelAndView showListCity(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("list");
        Page<City> cities = cityService.findAll(pageable);
        modelAndView.addObject("cities",cities);
        return modelAndView;
    }

    @PostMapping("create")
    public String createCity(@Validated @ModelAttribute("city") City city, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            return"redirect:/create-city";
        }else {
            cityService.save(city);
            return "redirect:/list";
        }
    }

    @GetMapping("edit-city/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()){
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("city",city.get());
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        }
    }

    @PostMapping("edit")
    public String editCity(@Validated @ModelAttribute("city") City city,BindingResult bindingResult){
        cityService.save(city);
        return "redirect:/list";
    }

    @GetMapping("delete-city/{id}")
    public String showDeleteForm(@PathVariable Long id){
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()){
            City city1 = city.get();
            cityService.remove(city1.getId());
            return "redirect:/list";
        }else {
            return "error";
        }
    }

    @GetMapping("info/{id}")
    public ModelAndView info(@PathVariable Long id){
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()){
            ModelAndView modelAndView = new ModelAndView("info");
            modelAndView.addObject("city",city.get());
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        }
    }

}
