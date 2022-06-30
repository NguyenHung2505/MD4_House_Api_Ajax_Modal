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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

// them moi , luu + uploadFile
//    @valid de duyet qua validate
    @PostMapping
    public ResponseEntity<House> add(@Valid @RequestParam("file") MultipartFile file, House house) {
        String fileName = file.getOriginalFilename();
        house.setImage(fileName);
        try {
            file.transferTo(new File("D:\\modum4\\MD4-MinitesTTNha\\src\\main\\resources\\image\\" + fileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        houseService.save(house);
        return new ResponseEntity<>(HttpStatus.OK) ;
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

    // edit , sua
//    @PutMapping("/{id}")
//    public ResponseEntity<House> updateCustomer(@PathVariable Long id, @RequestBody House house) {
//        Optional<House> housetOptional = houseService.findById(id);
//        if (!housetOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        house.setId(housetOptional.get().getId());
//        houseService.save(house);
//        return new ResponseEntity<>( HttpStatus.OK);
//    }


    @PutMapping("/{id}")
    public ResponseEntity<House> updateHouse (@RequestParam("file") MultipartFile file, @PathVariable Long id, House house) {
        String fileName = file.getOriginalFilename();
        if (fileName.equals("")){
            house.setImage(houseService.findById(id).get().getImage());
        }
        else {
            house.setImage(fileName);
            try {
                file.transferTo(new File("D:\\modum4\\MD4-MinitesTTNha\\src\\main\\resources\\image\\" + fileName));
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        house.setId(id);
        houseService.save(house);
        return new ResponseEntity<>( HttpStatus.OK);
    }



    @DeleteMapping("/{id}") // xoa
    public ResponseEntity<House> deleteHouse(@PathVariable Long id) {
        Optional<House> houseOptional = houseService.findById(id);
        if (!houseOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        houseService.remove(id);
        return new ResponseEntity<>(houseOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")         //Tìm theo id
    public ResponseEntity<House> findHouseById(@PathVariable Long id) {
        Optional<House> house = houseService.findById(id);
        if (!house.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(house.get(), HttpStatus.OK);
    }



}
