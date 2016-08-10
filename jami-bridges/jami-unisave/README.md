# jami-unisave

This JAMI module queries the EBI's UniProtKB Sequence/Annotation Version Archive (UniSave) web service. UniSave provides a repository containing every version of every Swiss-Prot/TrEMBL entry in the UniProt Knowledge Base (UniProtKB).

### Examples:

Get the last sequence on a provided date:

```java
UnisaveClient service = new UnisaveClient();

String sequence = service.getLastSequenceAtTheDate("Q98753", new Date(System.currentTimeMillis()));
```
Get a FASTA sequence:

```java
UnisaveClient service = new UnisaveClient();
String id = "Q00001";
service.getFastaSequence( id , 7);
```

More examples can be found in the test coverage:

https://github.com/MICommunity/psi-jami/blob/master/jami-bridges/jami-unisave/src/test/java/psidev/psi/mi/jami/bridges/unisave/UnisaveClientTest.java