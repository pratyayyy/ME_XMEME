package com.crio.starter.repositoryService;

import java.util.List;
import java.util.Optional;
import com.crio.starter.data.MemeEntity;

public interface MemeRepositoryService {
    
    MemeEntity saveMemes(MemeEntity meme);

    Optional<MemeEntity> getMemeById(String id);

    List<MemeEntity> getLastNMemes(int N);
    
}
