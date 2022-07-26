package it2.develhope.CRUDexercise.repositories;

import it2.develhope.CRUDexercise.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//a dedicated repository for the Car
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
