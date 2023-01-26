package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.models.ModelProduct;
import com.example.demo.models.ResponseModel;
import com.example.demo.repository.RepositoryProduct;

@Service
public class ServiceProduct {
    @Autowired
    private RepositoryProduct rp;

    @Autowired
    private ResponseModel rm;

    public Iterable<ModelProduct> listAll(){
        return rp.findAll();
    }

    public boolean queryProduct(Long code){
        return rp.existsById(code);
    }

    public ResponseEntity<?> registerProduct(ModelProduct mp, String action){
        if(mp.getName().equals("")){
            rm.setResponse("The name of product is obligatory");
            return new ResponseEntity<ResponseModel>(rm, HttpStatus.BAD_REQUEST);
        }else if(mp.getMark().equals("")){
            rm.setResponse("The mark of product is obligatory");
            return new ResponseEntity<ResponseModel>(rm, HttpStatus.BAD_REQUEST);
        }else{
            if(action.equals("register")){
                if(queryProduct(mp.getCode())){
                    rm.setResponse("This product is already registered");
                    return new ResponseEntity<ResponseModel>(rm, HttpStatus.BAD_REQUEST);
                }else{
                    return new ResponseEntity<ModelProduct>(rp.save(mp), HttpStatus.CREATED);
                }                
            }else{
                return new ResponseEntity<ModelProduct>(rp.save(mp), HttpStatus.OK);
            }
            
        }
    }

    public ResponseEntity<ResponseModel> remove(Long code){
        rp.deleteById(code);
        rm.setResponse("The product is removed with success");
        return new ResponseEntity<ResponseModel>(rm, HttpStatus.OK);
    }
}
