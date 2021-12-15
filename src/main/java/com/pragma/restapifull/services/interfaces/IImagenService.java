package com.pragma.restapifull.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pragma.restapifull.dto.ImageDTO;

@Service
public interface IImagenService {

    List<ImageDTO> findAll();
    ImageDTO findById(String id);
    ImageDTO findByImageUrl(String id);
    boolean saveIamge(ImageDTO image);
    boolean deleteImage(String id);
    ImageDTO finByIdUser(int id);
}
