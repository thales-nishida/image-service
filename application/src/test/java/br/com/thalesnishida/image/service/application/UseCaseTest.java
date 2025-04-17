package br.com.thalesnishida.image.service.application;

import br.com.thalesnishida.image.service.application.image.create.CreateImageCommand;
import br.com.thalesnishida.image.service.application.image.create.DefaultCreateImageUseCase;
import br.com.thalesnishida.image.service.domain.exceptions.DomainException;
import br.com.thalesnishida.image.service.domain.image.ImageGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UseCaseTest {

    @InjectMocks
    private DefaultCreateImageUseCase useCase;

    @Mock
    private ImageGateway imageGateway;

    @Test
    public void givenAValidParams_whenCallCreateImageCommand_thenShouldCreateImage() {
        final var expectedIdentifierName = "test";
        final var expectedImageList = List.of("image1", "image2");

        final var aCommand = CreateImageCommand.with(expectedIdentifierName, expectedImageList);

        when(imageGateway.create(any()))
                .thenAnswer(returnsFirstArg());

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

    @Test
    public void givenAInvalidName_whenCallCreateImageCommand_thenShouldReturnADomainException() {
        final String expectedIdentifierName = null;
        final var expectedImageList = List.of("image1", "image2");
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand = CreateImageCommand.with(expectedIdentifierName, expectedImageList);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());

        Mockito.verify(imageGateway, times(0)).create(any());
    }

    @Test
    public void givenAInvalidCommand_whenGatewayThrowsRandomException_thenShouldReturnADomainException() {
        final String expectedIdentifierName = "test";
        final var expectedImageList = List.of("image1", "image2");
        final var expectedErrorMessage = "Gateway Error";

        final var aCommand = CreateImageCommand.with(expectedIdentifierName, expectedImageList);

        when(imageGateway.create(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException =
                Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        Mockito.verify(imageGateway, times(1)).create(argThat(aImage ->
                Objects.equals(expectedIdentifierName, aImage.getIdentifierName())
                        && Objects.equals(expectedImageList, aImage.getImageList())
                        && Objects.nonNull(aImage.getId())
                        && Objects.nonNull(aImage.getCreatedAt())
                        && Objects.nonNull(aImage.getUpdatedAt())
        ));
    }
}
