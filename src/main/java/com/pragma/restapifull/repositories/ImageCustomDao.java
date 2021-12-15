package com.pragma.restapifull.repositories;

import com.pragma.restapifull.models.Image;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Repository
public interface ImageCustomDao {

    @Transactional(readOnly = true)
    List<Image> findAll();

    @Transactional(readOnly = true)
    Image findByImageUrl(String url);


    @Transactional(readOnly = true)
    Image findByIdUser(int id);

}
