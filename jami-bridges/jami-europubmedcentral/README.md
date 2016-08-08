`jami-europubmedcentral` communicates with Euro PubMed Central.


```

        EuroPubmedCentralFetcher fetcher = new EuroPubmedCentralFetcher();
   

        Publication publication = fetcher.fetchByIdentifier("10.1038/171737a0", Xref.DOI);
        assertEquals( "13054692" , publication.getPubmedId() );
        assertEquals( "10.1038/171737a0" , publication.getDoi());
        assertEquals( "Molecular structure of nucleic acids; a structure for deoxyribose nucleic acid." , publication.getTitle());
        assertEquals( "Nature" , publication.getJournal());
        assertTrue(publication.getAuthors().contains("WATSON JD."));
        assertTrue(publication.getAuthors().contains("CRICK FH."));
        
```        
