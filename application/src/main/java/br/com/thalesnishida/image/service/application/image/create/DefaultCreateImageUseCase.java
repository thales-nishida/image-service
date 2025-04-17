package br.com.thalesnishida.image.service.application.image.create;

import br.com.thalesnishida.image.service.domain.image.Image;
import br.com.thalesnishida.image.service.domain.image.ImageGateway;
import br.com.thalesnishida.image.service.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultCreateImageUseCase extends CreateImageUseCase {

    private final ImageGateway imageGateway;

    public DefaultCreateImageUseCase(final ImageGateway imageGateway) {
        this.imageGateway = Objects.requireNonNull(imageGateway);
    }

    @Override
    public Either<Notification, CreateImageOutput> execute(final CreateImageCommand aCommand) {
        final var aIdentifierName = aCommand.identifierName();
        final var aImageList = aCommand.imageList();

        final var notification = Notification.create();

        final var aImage = Image.newImage(aIdentifierName, aImageList);
        aImage.validate(notification);

        return notification.hasError() ? Left(notification) : create(aImage);
    }

    private Either<Notification, CreateImageOutput> create(Image aImage) {
        return Try(() -> this.imageGateway.create(aImage))
                .toEither()
                .bimap(Notification::create, CreateImageOutput::from);
    }
}
