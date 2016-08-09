The `jami-bridges` package is the home of packages that use web services.

The `jami-enricher` package uses these "fetchers" to get the data.

```java
        // create fetcher from chebi
        // simple chebi fetcher
        BioactiveEntityFetcher fetcher = new ChebiFetcher();
        // cached fetcher using eh-cache to cache objects in memory only as objects are not serializable by default
        BioactiveEntityFetcher cachedFetcher = new CachedChebiFetcher();

        // we have a chebi id which can be primary or secondary
        String chebiId = "CHEBI:15377";

        Collection<BioactiveEntity> chebiEntities = fetcher.fetchByIdentifier(chebiId);
        System.out.println(chebiEntities.size() + " bioactive entities are matching query "+chebiId);

        for (BioactiveEntity entity : chebiEntities){
            System.out.println("Name: "+entity.getShortName());
            System.out.println("Chebi: "+entity.getChebi());
            System.out.println("Smile: "+entity.getSmile());
            System.out.println("Standard Inchi: "+entity.getStandardInchi());
            System.out.println("Standard Inchi key: "+entity.getStandardInchiKey());
            System.out.println("Full name: "+entity.getFullName());

            System.out.println("All synonyms "+entity.getAliases().size());
            System.out.println("All identifiers (primary and secondary) "+entity.getIdentifiers().size());
            System.out.println("All xrefs "+entity.getXrefs().size());
            System.out.println("All checksums "+entity.getChecksums().size());
        }
```
