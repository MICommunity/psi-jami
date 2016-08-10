Fetcher for Uniprot taxonomy

Example  
```java
UniprotTaxonomyFetcher taxonomyFetcher = new UniprotTaxonomyFetcher();
Organism organism = taxonomyFetcher.fetchByTaxID(9615);
```