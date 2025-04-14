package br.com.thalesnishida.image.service.application;

import br.com.thalesnishida.image.service.application.image.create.CreateImageCommand;
import br.com.thalesnishida.image.service.domain.image.ImageGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class UseCaseTest {

    @Test
    public void givenAValidParams_whenCallCreateImageCommand_thenShouldCreateImage() {
        final var expectedIdentifierName = "test";
        final var expectedImageList = List.of("image1", "image2");

        final var aCommand = CreateImageCommand.with(expectedIdentifierName, expectedImageList);

        final ImageGateway imageGateway = Mockito.mock(ImageGateway.class);

        Mockito.when(imageGateway.create(Mockito.any()))
                .thenAnswer(returnFirstArgs());

        final var useCase = new CreateImageUseCase(imageGateway);

        final var actualOutPut = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutPut);
        Assertions.assertNotNull(actualOutPut.getId());

        Mockito.verify(imageGateway, Mockito.times(
                .create(Mockito.argThat(aImage -> {
                            Objects.equals(expectedIdentifierName, aImage.getIdentiferName())
                                    && Objects.equals(expectedImageList, aImage.getImageList())
                                    && Objects.nonNull(aImage.getId())
                                    && Objects.nonNull(aImage.getCreateAt())
                                    && Objects.nonNull(aImage.getUpdateAt())
                        }
                ))
        ));
    }
}
