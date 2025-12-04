CREATE TABLE IF NOT EXISTS Entries (
    id UUID,

    arxiv_id TEXT NOT NULL,
    title TEXT NOT NULL,
    doi TEXT,
    abstract_content TEXT,
    submittor TEXT,
    authors TEXT[],
    categories TEXT[],
    publish_date DATE,
    
    embed_status TEXT NOT NULL,
    uploaded TIMESTAMP NOT NULL,
    completed TIMESTAMP,
    PRIMARY KEY (id)
);