package com.crio.starter.controller;

import com.crio.starter.exchange.MemeRequestDTO;
import com.crio.starter.exchange.MemeRequestIdDTO;
import com.crio.starter.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class MemeController {

    public static final String MEME_API_ENDPOINT = "/memes";
    public static final int N = 100;    
    
    @Autowired
    private MemeService memeService;
    
    @PostMapping(MEME_API_ENDPOINT)
    public ResponseEntity<MemeRequestIdDTO> saveMemes(@RequestBody(required = true) @Valid  MemeRequestDTO memeRequest)
    {
        Optional<MemeRequestIdDTO> memeIdDTO = memeService.saveMemes(memeRequest);
        
        if(memeIdDTO.isPresent()){
             return ResponseEntity.ok().body(memeIdDTO.get());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }


    @GetMapping(MEME_API_ENDPOINT)
    public ResponseEntity<List<MemeRequestDTO>> getLastNMemes(){
            List<MemeRequestDTO> memeDTO = memeService.getLastNMemes(N);
            return ResponseEntity.ok().body(memeDTO);
    }   


   

    @GetMapping(MEME_API_ENDPOINT+"/{id}")
    public ResponseEntity<MemeRequestDTO> getMemeById(@PathVariable(name = "id") int id) {

       Optional<MemeRequestDTO> memeDTO = memeService.getMemeById(id+"");
   
       if(memeDTO.isPresent()) {
        return ResponseEntity.ok().body(memeDTO.get());
       }

       return ResponseEntity.notFound().build();
  }

}
