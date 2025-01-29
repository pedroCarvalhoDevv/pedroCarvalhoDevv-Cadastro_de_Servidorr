package com.cadastro.cadastroServidor.controller;

import com.cadastro.cadastroServidor.model.dto.ServidorDto;
import com.cadastro.cadastroServidor.service.ServidorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servidor")
public class ServidorController {
    private ServidorService servidorService;

    public ServidorController(ServidorService servidorService) {
        this.servidorService = servidorService;
    }

    @PostMapping
    public ServidorDto create(@Valid @RequestBody ServidorDto servidorDto){
        return servidorService.create(servidorDto);
    }

    @GetMapping("/findAll")
    List<ServidorDto> findAll(){
        return servidorService.findAll();
    }

    @GetMapping("/findById/{id}")
    public ServidorDto findById(@PathVariable Long id) {
        return servidorService.findById(id);
    }

    @PutMapping("{id}")
    public ServidorDto update (@RequestBody ServidorDto servidorDto){
        return servidorService.update(servidorDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        servidorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
