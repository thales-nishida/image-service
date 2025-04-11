package br.com.thalesnishida.image.service.domain.image;

public record ImageSearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {
}
