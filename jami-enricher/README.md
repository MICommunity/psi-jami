Enrichment can be applied to a single entity or a collection of entities (e.g. Organism, Publication, Protein, etc.).
Enrichers use the fetcher (bridges) packages to pull data from external sources to supplement existing provided / parsed data.

See bridges to understand which items specifically can be enriched. Note that there are two types of enrichment:

1) Enrichment - just adds data and ignores conflicts
2) Updating - replaces / updates existing rows.


*Each enricher type has a minimal and a full implementation.