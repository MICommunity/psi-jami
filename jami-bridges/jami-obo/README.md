Fetcher for Open Biomedical Ontology. It fetches the OntologyTerm/CVTerm entity with parents and children, reading the OBO file given in input.

Example
```java
OboOntologyTermFetcher ontologyFetcher = new OboOntologyTermFetcher("MI:0001", "file:///C:/tmp/psi-mi.obo");
ontologyFetcher.fetchRootTerms("MI:0000");

```

