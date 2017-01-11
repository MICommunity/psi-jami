# jami-ols

This JAMI module provides query access to the EBI's Ontology Lookup Service's web service.

The Ontology Lookup Service (OLS) is a repository for biomedical ontologies that aims to provide a single point of access to the latest ontology versions.

Example:

```java

private OlsCvTermFetcher fetcher = new OlsCvTermFetcher();

String identifier = "MI:0580";
String ontologyName = "psi-mi";
CvTerm cvTermFetched =  fetcher.fetchByIdentifier(identifier, ontologyName);
```

More examples can be found in the test coverage:

https://github.com/MICommunity/psi-jami/blob/master/jami-bridges/jami-ols/src/test/java/psidev/psi/mi/jami/bridges/ols/OlsFetcherTest.java
