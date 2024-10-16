package com.crio.starter.repositoryService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

@Service
public class MemeRepositoryServiceImpl implements MemeRepositoryService{
    
    @Autowired
    private MongoTemplate mongoTemplate;
  
    @Autowired 
    private MemeRepository memesRepository;

    @Autowired
    private SequenceGeneratorRepositoryService sequenceGenerator;


    private boolean validityCheck(MemeEntity meme){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(meme.getName())
        .and("url").is(meme.getUrl()).and("caption").is(meme.getCaption()));

        List<MemeEntity> memeList = mongoTemplate.find(query, MemeEntity.class);

        if(memeList.isEmpty()) {
            return true;
        }
        return false;
    }

    
    public MemeEntity saveMemes(MemeEntity meme){
        if(!validityCheck(meme)) {
            throw new RuntimeException();
        }
        meme.setId(sequenceGenerator.generateSequence(MemeEntity.SEQUENCE_NAME)+"");
        meme.setCreatedDate(new Date());
        meme = memesRepository.save(meme);

        return meme;
    }

    public Optional<MemeEntity> getMemeById(String id){
        return memesRepository.findById(id);
    }

    public List<MemeEntity> getLastNMemes(int N){
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "createdDate")).limit(N);
        List<MemeEntity> memesList = mongoTemplate.find(query,MemeEntity.class);

        return memesList;   
    }
}
