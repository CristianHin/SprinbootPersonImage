package com.pragma.restapifull.repositories;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pragma.restapifull.models.Image;

@Repository
public interface ImageDao extends MongoRepository<Image, String>, ImageCustomDao{

}
