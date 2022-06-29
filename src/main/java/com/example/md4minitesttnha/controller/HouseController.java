package com.example.md4minitesttnha.controller;

import com.example.md4minitesttnha.model.House;
import com.example.md4minitesttnha.service.HouseService;
import com.example.md4minitesttnha.service.house.HouseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/house")
public class HouseController {
    @Autowired
    HouseService houseService;
    @GetMapping  //hien thi
    public ResponseEntity<House> findAllHouse() {
        return new ResponseEntity(houseService.findAll(), HttpStatus.OK);
    }

    @PostMapping("") // them moi , luu
//    @valid de duyet qua validate
    public ResponseEntity<House> add(@Valid @RequestBody House house) {
        houseService.save(house);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // tim theo ten

    @GetMapping("/search-name")
    public ResponseEntity<Iterable<House>> findAllByNameContaining(String name) {
        return new ResponseEntity<>(houseService.findAllByNameContaining(name), HttpStatus.OK);
    }



    //   them phan nay de chay validate
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
