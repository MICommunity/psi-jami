Enrichment can be applied to a single entity or a collection of entities (e.g. Organism, Publication, Protein, etc.).
Enrichers use the fetcher (bridges) packages to pull missing data from external sources to supplement existing provided / parsed data.

See bridges to understand which items specifically can be enriched. Note that there are two types of enrichment:

1. Enrichment - just adds data and ignores conflicts
2. Updating - replaces / updates existing rows.


* Each enricher type has a minimal and a full implementation.

The best place to see the enrichers in action is in the test package.

Many enrichments require a fetcher to be initialised correctly, but if they do not require a fetcher,
generally one of their data members *will* have fetchers - for example, experiments do not require fetchers,
but experiments may have fetchers in their Publication, Organism & CvTerm members.
