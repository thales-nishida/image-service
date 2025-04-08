package br.com.thalesnishida.image.service.domain.image;

import java.time.Instant;
import java.util.List;

public class Image {

    private String id;
    private String identifierName;
    private List<String> imageList;
    private Instant createdAt;
    private Instant updatedAt;
}