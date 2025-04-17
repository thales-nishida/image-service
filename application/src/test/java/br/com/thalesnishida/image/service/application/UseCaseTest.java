package br.com.thalesnishida.image.service.application;

import br.com.thalesnishida.image.service.application.image.create.CreateImageCommand;
import br.com.thalesnishida.image.service.application.image.create.DefaultCreateImageUseCase;
import br.com.thalesnishida.image.service.domain.image.ImageGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class UseCaseTest {

    @Test
    public void givenAValidParams_whenCallCreateImageCommand_thenShouldCreateImage() {
        final var expectedIdentifierName = "test";
        final var expectedImageList = List.of("image1", "image2");

        final var aCommand = CreateImageCommand.with(expectedIdentifierName, expectedImageList);

        final ImageGateway imageGateway = Mockito.mock(ImageGateway.class);

        when(imageGateway.create(any()))
                .thenAnswer(returnsFirstArg());

        final var useCase = new DefaultCreateImageUseCase(imageGateway);

        final var actualOutPut = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutPut);
        Assertions.assertNotNull(actualOutPut.id());

        Mockito.verify(imageGateway, times(1)).create(argThat(aImage ->
                Objects.equals(expectedIdentifierName, aImage.getIdentifierName())
                        && Objects.equals(expectedImageList, aImage.getImageList())
                        && Objects.nonNull(aImage.getId())
                        && Objects.nonNull(aImage.getCreatedAt())
                        && Objects.nonNull(aImage.getUpdatedAt())
        ));
    }
}
