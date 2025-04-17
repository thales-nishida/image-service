package br.com.thalesnishida.image.service.application.image.create;

import br.com.thalesnishida.image.service.application.UseCase;
import br.com.thalesnishida.image.service.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateImageUseCase extends UseCase<CreateImageCommand, Either<Notification, CreateImageOutput>> {
}
