## UniprotProteinFetcher
 Takes the various type of uniprot protein identifier and uses the uniprotJAPI to retrieve the matching proteins.

```java
    UniprotProteinFetcher = new UniprotProteinFetcher();
    Collection<Protein> results = fetcher.fetchByIdentifier(identifier);
    //now do something with the results you've fetched, maybe:
    for(Protein result : results){
       log.info(result.toString())
    };
```

## UniprotGeneFetcher
Gets Genes from uniprot using the ensembl ID, the refseq ID or the ensemblGenomes ID.

```java
   GeneFetcher fetcher = new UniprotGeneFetcher();
   Collection<Gene> candidatesList = fetcher.fetchByIdentifier(id);
   //now you can do things with your returned results, e.g.
   for(Gene inta : candidatesList)  {
        log.info("---- full: " + inta.getFullName());
   }
```
