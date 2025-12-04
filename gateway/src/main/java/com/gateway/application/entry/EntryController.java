package com.gateway.application.entry;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/entry")
public class EntryController {

    private final EntryRepository entryRepository;

    public EntryController(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @PostMapping("")
    void upload_entry(@RequestBody EntryRequest entryRequest) {
        UUID id = UUID.randomUUID();
        Entry newEntry = new Entry(
            id,
            entryRequest.arxiv_id(),
            entryRequest.title(),
            entryRequest.doi(),
            entryRequest.abstract_content(),
            entryRequest.submittor(),
            entryRequest.authors(),
            entryRequest.categories(),
            entryRequest.publish_date(),
            "incomplete",
            LocalDateTime.now(),
            null
        );
        entryRepository.save(newEntry);
    }
}
