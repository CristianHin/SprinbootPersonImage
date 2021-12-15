package com.pragma.restapifull.dto;




import lombok.Data;

@Data
public class ImageDTO {

    private String id;
    private int idUSer;
    private String name;
    private String imageUrl;
    private String imageId;

    public ImageDTO() {
    }

    public ImageDTO(int idUser, String name, String imageUrl, String imageId) {
        this.idUSer = idUser;
        this.name = name;
        this.imageUrl = imageUrl;
        this.imageId = imageId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getIdUSer() {
        return idUSer;
    }

    public void setIdUSer(int idUSer) {
        this.idUSer = idUSer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
