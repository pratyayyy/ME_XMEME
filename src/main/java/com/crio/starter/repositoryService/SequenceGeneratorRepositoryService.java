package com.crio.starter.repositoryService;

import java.util.Objects;
import com.crio.starter.data.DatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorRepositoryService {

    private MongoOperations mongoOperations;

    @Autowired
    public  SequenceGeneratorRepositoryService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateSequence(String seq) {

        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(
            seq)),new Update().inc("seq",1), options().returnNew(true).upsert(true),
            DatabaseSequence.class);
    return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
    
}