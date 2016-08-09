The UniprotProteinMapper fetches uniprot identifiers for a protein sequence, given a protein with known xrefs and sequence.

There are two mapping strategies available to choose from, based on a preference for giving identifiers or sequence the priority. Both can be set to true. 

To run:

```java
        //initialising the protein
        Protein p = new DefaultProtein("test");
        p.setSequence(sequence_a);
        p.setOrganism(new DefaultOrganism(Integer.parseInt("9606")));

        //initialise the mapper
        remap = new UniprotProteinMapper();

        //do the mapping
        remap.setPriorityIdentifiers(ids); //bool, could be true or false
        remap.setPrioritySequence(seq);    //as above. both strategies could apply.
        remap.map(p);

        //examine the results:
        log.info("The ac for p is "+p.getUniprotkb());
```