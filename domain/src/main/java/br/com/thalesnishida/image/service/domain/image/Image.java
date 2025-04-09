package br.com.thalesnishida.image.service.domain.image;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Image {

    private String id;
    private String identifierName;
    private List<String> imageList;
    private Instant createdAt;
    private Instant updatedAt;

    private Image(
            final String id,
            final String identifierName,
            final List<String> imageList,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        this.id = id;
        this.identifierName = identifierName;
        this.imageList = imageList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Image newImage(
            final String aIdentifierName,
            final List<String> aImageList
    ) {
        final var id = UUID.randomUUID().toString();
        final var now = Instant.now();
        return new Image(id, aIdentifierName, aImageList, now, now);
    }

    public String getId() {
        return id;
    }

    public String getIdentifierName() {
        return identifierName;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}