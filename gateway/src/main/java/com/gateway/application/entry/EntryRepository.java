package com.gateway.application.entry;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface EntryRepository extends ListCrudRepository<Entry, UUID>{
}
