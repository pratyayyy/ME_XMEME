package com.crio.starter.service;

import java.util.List;
import java.util.Optional;
import com.crio.starter.exchange.MemeRequestDTO;
import com.crio.starter.exchange.MemeRequestIdDTO;

public interface MemeService {
    
    Optional<MemeRequestIdDTO> saveMemes(MemeRequestDTO meme);

    Optional<MemeRequestDTO> getMemeById(String Id);

    List<MemeRequestDTO> getLastNMemes(int N);
}
