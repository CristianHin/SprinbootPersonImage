package com.pragma.restapifull.services.implementation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pragma.restapifull.services.interfaces.ICloudinaryService;


@Component
public class CloudinaryImplement implements ICloudinaryService{

	public Cloudinary context () {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("cloud_name", "dou5ldajx");
        valuesMap.put("api_key", "134284233737515");
        valuesMap.put("api_secret", "3dyzhZzG4-ZcCzQnR9VDXVReOfI");
		return  new Cloudinary(valuesMap);
	}


	@Override
	public Map<?, ?> upload(MultipartFile multipartFile) throws IOException {
		 File file = convert(multipartFile);
	     Map<?, ?> result = this.context().uploader().upload(file, ObjectUtils.emptyMap());
	     file.delete();
	     return result;
	}

	@Override
	public Map<?, ?> delete(String id) throws IOException {
		 Map<?, ?> result = this.context().uploader().destroy(id, ObjectUtils.emptyMap());
	     return result;
	}

	@Override
	public File convert(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
	}



}
