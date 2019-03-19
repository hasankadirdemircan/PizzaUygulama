package com.hkdemircan.pideuygulama.controller;

import com.hkdemircan.pideuygulama.model.Pide;
import com.hkdemircan.pideuygulama.model.PideKaydet;
import com.hkdemircan.pideuygulama.repository.PideKaydetRepository;
import com.hkdemircan.pideuygulama.repository.PideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pide")
public class PideController {

    @Autowired
    PideRepository pideRepository;

    @Autowired
    PideKaydetRepository pideKaydetRepository;

    @GetMapping("/all")
    public List<Pide> getAllPide(){
        return pideRepository.findAll();
    }

    @PostMapping("/save")
    public PideKaydet savePide (@Valid @RequestBody PideKaydet pideKaydet){
        return pideKaydetRepository.save(pideKaydet);
    }

    @GetMapping("/all/{email}")
    public List<PideKaydet> getAllEmailPide(@PathVariable("email") String email){
            return pideKaydetRepository.findAllByEmail(email);
    }

    @GetMapping("/first/{id}")
    public PideKaydet getFirstByIdPide(@PathVariable("id") int id){
        return pideKaydetRepository.findFirstById(id);
    }

}
