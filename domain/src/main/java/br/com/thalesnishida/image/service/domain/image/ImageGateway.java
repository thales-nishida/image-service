package br.com.thalesnishida.image.service.domain.image;

import br.com.thalesnishida.image.service.domain.pagination.Pagination;

import java.util.Optional;

public interface ImageGateway {
    Image create(Image aImage);
    void deleteById(ImageID anId);
    Optional<Image> findById(ImageID anId);
    Image update(Image aImage);
    Pagination<Image> findAll(ImageSearchQuery aQuery);
}
