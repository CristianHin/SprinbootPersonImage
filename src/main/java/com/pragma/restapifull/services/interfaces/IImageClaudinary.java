package com.pragma.restapifull.services.interfaces;

import com.pragma.restapifull.utils.exceptions.ApiBadRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageClaudinary {

    boolean uploadImageCloudinary(MultipartFile multipartFile, int id) throws ApiBadRequest, IOException;
    boolean     deleteImageCloudinary (int id) throws ApiBadRequest, IOException;
}
