package br.com.thalesnishida.image.service.domain.image;

import br.com.thalesnishida.image.service.domain.AggregateRoot;
import br.com.thalesnishida.image.service.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.List;

public class Image extends AggregateRoot<ImageID> {

    private final String identifierName;
    private final List<String> imageList;
    private final Instant createdAt;
    private final Instant updatedAt;

    private Image(
            final ImageID anID,
            final String aIdentifierName,
            final List<String> aImageList,
            final Instant aCreationDate,
            final Instant aUpdatedAt
    ) {
        super(anID);
        this.identifierName = aIdentifierName;
        this.imageList = aImageList;
        this.createdAt = aCreationDate;
        this.updatedAt = aUpdatedAt;
    }

    public static Image newImage(
            final String aIdentifierName,
            final List<String> aImageList
    ) {
        final var id = ImageID.unique();
        final var now = Instant.now();
        return new Image(id, aIdentifierName, aImageList, now, now);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new ImageValidator(this, handler).validate();
    }

    public ImageID getId() {
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