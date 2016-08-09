Enrichers enrich a single entity or a collection of entities (e.g. Organism, Publication, Protein, Experiment etc.) given a fetcher or sub-enricher (e.g. ExperimentEnricher) pulling missing data from external sources to supplement existing provided / parsed data.

There are enrichers for any specific entity (similar to the fetchers).

Each enricher type has a minimal implementation, providing e minimal enrichment of the entity, and a full implementation. See the specific implementations for more details.

Note that there are two types of enrichment:

1. Enrichment - just adds data and ignores conflicts
2. Updating - replaces / updates existing rows.

The package depends on jami-bridges package and jami-core.

The best place to see the enrichers in action is in the test package.

Example using fetcher
```java
MockOrganismFetcher fetcher = new MockOrganismFetcher();
MinimalOrganismEnricher organismEnricher = new FullOrganismEnricher(new MockOrganismFetcher());
organismEnricher.enrich(persistentOrganism);
```
Example using sub-enrich
```java
Publication persistentPublication = new DefaultPublication();
Experiment persistentExperiment = new DefaultExperiment(persistentPublication);
MinimalExperimentEnricher experimentEnricher = new MinimalExperimentEnricher();
experimentEnricher.setCvTermEnricher(new MinimalCvTermEnricher(new MockCvTermFetcher()));
experimentEnricher.enrich(persistentExperiment);
```
