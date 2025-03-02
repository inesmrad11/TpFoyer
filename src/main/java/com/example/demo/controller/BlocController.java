package com.example.demo.controller;

import com.example.demo.entities.Bloc;
import com.example.demo.services.IBlocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blocs")
public class BlocController {
    @Autowired
    IBlocService blocService;

    @GetMapping("/all")
    public List<Bloc> getAll(){
        return blocService.retrieveBlocs();
    }

    @PostMapping("/add")
    public Bloc addBlocs(@RequestBody Bloc bloc){
        return blocService.addBloc(bloc);
    }

    @PutMapping("/update")
    public Bloc updateBloc(@RequestBody Bloc f){
        return blocService.updateBloc(f);
    }

    @GetMapping("/get/{id}")
    public Bloc getBloc(@PathVariable("id") Long id){
        return blocService.retrieveBloc(id);
    }

    @DeleteMapping("/delete/{id}")
    public void removeBloc(@PathVariable("id") Long id){
        blocService.removeBloc(id);
    }
}