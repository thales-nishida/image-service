package br.com.thalesnishida.image.service.application.image.create;

import br.com.thalesnishida.image.service.domain.image.Image;
import br.com.thalesnishida.image.service.domain.image.ImageGateway;
import br.com.thalesnishida.image.service.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateImageUseCase extends CreateImageUseCase {

    private final ImageGateway imageGateway;

    public DefaultCreateImageUseCase(final ImageGateway imageGateway) {
        this.imageGateway = Objects.requireNonNull(imageGateway);
    }

    @Override
    public CreateImageOutput execute(final CreateImageCommand aCommand) {
        final var aIdentifierName = aCommand.identifierName();
        final var aImageList = aCommand.imageList();

        final var aImage = Image.newImage(aIdentifierName, aImageList);
        aImage.validate(new ThrowsValidationHandler());

        return CreateImageOutput.from(this.imageGateway.create(aImage));
    }
}
