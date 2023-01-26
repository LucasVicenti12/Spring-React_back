package com.example.demo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ModelProduct;
import com.example.demo.models.ResponseModel;
import com.example.demo.services.ServiceProduct;

@RestController
@CrossOrigin(origins = "http://192.168.1.11:3000/")
public class ControlProduct {

    @Autowired
    private ServiceProduct sp;

    @PostMapping("/register")
    public ResponseEntity<?> registerProduct(@RequestBody ModelProduct mp){
        return sp.registerProduct(mp, "register");
    }

    @PutMapping("/alter")
    public ResponseEntity<?> alterProduct(@RequestBody ModelProduct mp){
        return sp.registerProduct(mp, "alter");
    }

    @GetMapping("/listall")
    public Iterable<ModelProduct> list(){
        return sp.listAll();
    }

    @GetMapping ("/")
    public String rota(){
        return "API de produtos funcionando";
    }   

    @DeleteMapping("/remove/{code}")
    public ResponseEntity<ResponseModel> remove(@PathVariable Long code){
        return sp.remove(code);
    }
}
