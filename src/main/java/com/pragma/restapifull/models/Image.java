package com.pragma.restapifull.models;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("image")
public class Image {

	@Id
    private String id;
 	@Field(name = "id_person")
 	private int idUser;
	@Field(name = "name")
    private String name;
	@Field(name = "url")
    private String imageUrl;
	@Field(name = "image_id")
    private String imageId;




    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdPerson() {
		return idUser;
	}

	public void setIdPerson(int idPerson) {
		this.idUser = idPerson;
	}

	public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imagenUrl) {
        this.imageUrl = imagenUrl;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imagenId) {
        this.imageId = imagenId;
    }
}
