package br.com.thalesnishida.image.service.application.image.create;

import br.com.thalesnishida.image.service.domain.image.Image;
import br.com.thalesnishida.image.service.domain.image.ImageID;

public record CreateImageOutput(
        ImageID id
) {
    public static CreateImageOutput from(final Image aImage) {
        return new CreateImageOutput(aImage.getId());
    }
}
