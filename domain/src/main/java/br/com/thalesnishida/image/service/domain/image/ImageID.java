package br.com.thalesnishida.image.service.domain.image;

import br.com.thalesnishida.image.service.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class ImageID extends Identifier {
    protected final String value;

    private ImageID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static ImageID unique() {
        return new ImageID(UUID.randomUUID().toString());
    }

    public static ImageID from(final String anId) {
        return new ImageID(anId);
    }

    public static ImageID from(final UUID anId) {
        return new ImageID(anId.toString().toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageID imageID = (ImageID) o;
        return Objects.equals(value, imageID.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
