package com.gateway.application.entry;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import embed.proto.EmbedResponse;

@RestController
@RequestMapping("/api/entry")
public class EntryController {

    private final EntryRepository entryRepository;
    private final EmbedClient embedClient;

    public EntryController(EntryRepository entryRepository, EmbedClient embedClient) {
        this.entryRepository = entryRepository;
        this.embedClient = embedClient;
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
            "INCOMPLETE",
            LocalDateTime.now(),
            null
        );
        entryRepository.save(newEntry);

        EmbedResponse embedResponse = embedClient.GetEmbed(newEntry.get_abstract_content());
        if (embedResponse.getStatus() == EmbedResponse.Status.FAILED) {
            newEntry.set_embed_status("FAILED");
        }
        else {
            newEntry.set_embed_status("Completed");
            newEntry.set_completed(LocalDateTime.now(ZoneId.of("UTC")));
            System.out.println(embedResponse.getEmbed().getValuesList());
        }
        entryRepository.save(newEntry);
    }
}
