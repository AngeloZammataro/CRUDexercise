package it2.develhope.CRUDexercise.controllers;

import com.sun.net.httpserver.Headers;
import it2.develhope.CRUDexercise.entities.Car;
import it2.develhope.CRUDexercise.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;


    //create a new Car
    @PostMapping()
    public Car create(@RequestBody Car car){
        Car userSaved = carRepository.saveAndFlush(car);
        return userSaved;
    }

    //return a list of all the Cars
    @GetMapping()
    public List<Car> getAllCar(){
        List<Car> car = carRepository.findAll();
        return car;
    }

    //return a single Car
    @GetMapping("/{id}")
    public Car getSingleCar(@PathVariable long id){
        Car car = carRepository.getReferenceById(id);
        boolean existsById = carRepository.existsById(id);
        if(existsById){
            //return a single Car - if the id is not in the db
            return car;
        }
        //returns an empty Car
        return new Car();
    }

    //UPDATE for id
    @PutMapping("/{id}")
    public Car updateSingleCar(@PathVariable long id, @RequestBody Car car){
        car.setId(id);
        Car newCar = carRepository.saveAndFlush(car);
        boolean existsById = carRepository.existsById(id);
        if(existsById){
            //modify a single Car - if the id is in the db
            return car;
        }
        //returns an empty Car if the id is NOT in the db
        return new Car();
    }

    //DELETE for id
    @DeleteMapping("/{id}")
    public void deleteSingleCar(@PathVariable long id, HttpServletRequest request, HttpServletResponse response){
        boolean existsById = carRepository.existsById(id);
        if (existsById){
            carRepository.deleteById(id);
        }else {
            System.out.println(HttpStatus.CONFLICT);
            try {
                response.sendError(409,"CONFLICT");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //DELETE all
    @DeleteMapping("")
    public void deleteAllCar(){
        carRepository.deleteAll();
    }
}
