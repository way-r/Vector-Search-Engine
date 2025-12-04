package com.gateway.application.entry;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public record EntryRequest(

    @NotEmpty String arxiv_id,
    @NotEmpty String title,
    @NotEmpty String doi,
    @NotEmpty String abstract_content,
    String submittor,
    List<String> authors,
    List<String> categories,
    LocalDate publish_date
) {}
