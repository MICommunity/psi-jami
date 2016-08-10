# jami-picr

This JAMI module queries the EBI's Protein Identifier Cross-Reference (PICR) database web service to resolve identifiers in other major protein databases. It accepts either accessions or sequences, and an optional taxon identifier.

http://www.ebi.ac.uk/Tools/picr/

Example:
```java

private PicrClient client = new PicrClient();

upis = client.getSwissprotIdsForAccession("NP_41784", null);
for (String upi : upis){
    System.out.println(upi);
}

```