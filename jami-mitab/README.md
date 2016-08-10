# jami-mitab

`jami-mitab` was made for reading and writing all the standard PSI-MI-TAB formats availables.

For more information about the molecular interaction standards you can refer to HUPO PSI-MI working group http://www.psidev.info/groups/molecular-interactions

## How does it relate to other packages ?

Only `jami-commons` uses this package. But `jami-commons` is used by following packages
`jami-examples`
`jami-ontology-manager`
`psi-jami`

but 

`jami-mitab` itself uses `jami-core` internally

##Examples
###Reading PSI-MITAB files

```java
//File is your mitab file to read
File file = new File("interactions.mitab");

PsiJami.initialiseAllMIDataSources();

MIDataSourceFactory dataSourceFactory = MIDataSourceFactory.getInstance();
MIDataSourceOptionFactory optionsFactory = MIDataSourceOptionFactory.getInstance();
Map<String, Object> options = optionsFactory.getDefaultOptions(file);

InteractionStream interactionSource = dataSourceFactory.getInteractionSourceWith(options);

try {
    Iterator ielist = interactionSource.getInteractionsIterator();

    while (ielist.hasNext()) {
        psidev.psi.mi.jami.model.Interaction interaction = (psidev.psi.mi.jami.model.Interaction) ielist.next();

        //Add your logic here (e. g. experimental interactions)
        if (interaction instanceof InteractionEvidence) {
            InteractionEvidence ie = (InteractionEvidence) interaction;
            Collection<ParticipantEvidence> participants = ie.getParticipants();
            for (ParticipantEvidence participant : participants) {
                String name = participant.getInteractor().getInteractorType().getShortName();
            }
        }
    }
}

// Always close the opened interaction stream
finally {
    if (interactionSource != null) {
        interactionSource.close();
    }
}
```
###Writing PSI-MITAB files

```java  

PsiJami.initialiseAllInteractionWriters();

InteractionWriterFactory writerFactory = InteractionWriterFactory.getInstance();
MIWriterOptionFactory optionFactory = MIWriterOptionFactory.getInstance();
 
// By default, the writer will be a 2.7 PSI-MI TAB format writer, spoke expanded, with header, when all the aliases, features and confidences are not pure mitab objects, complex type n-ary and interaction category mixed
// The default options can be overridden using the MIWriterOptionFactory or by manually adding options listed in
// MitabWriterOptions
Map<String, Object> mitabWritingOptions = optionFactory.getDefaultMitabOptions(new File("mitabFileName'"));
 
// This example is for 2.6 PSI-MI TAB format, spoke expanded, with header, when all the aliases, features and confidences are not pure mitab objects
// For other type of InteractionCategory or ComplexType you can refer to the advance options
Map<String, Object> mitabWritingOptions = optionFactory.getMitabOptions(
        new File("mitabFileName"),
        InteractionCategory.evidence,
        ComplexType.n_ary,
        new InteractionEvidenceSpokeExpansion(),
        true,
        MitabVersion.v2_6,
        false);


// This example is for 2.5 PSI-MI TAB format, spoke expanded, with header, when all the aliases, features and confidences are not pure mitab objects 
// For other type of InteractionCategory or ComplexType you can refer to the advance options
Map<String, Object> mitabWritingOptions = optionFactory.getMitabOptions(
        new File("mitabFileName"), InteractionCategory.evidence, ComplexType.n_ary,
        new InteractionEvidenceSpokeExpansion(),
        true,
        MitabVersion.v2_5,
        false);
 
InteractionWriter writer = writerFactory.getInteractionWriterWith(mitabWritingOptions);
 
try {
    // start writing
    writer.start();
    
    // write the interaction evidences
    // here evidences are Collection<InteractionEvidence>
    writer.write(evidences);
    
    // finish writing
    writer.end();
}
//close the writer
finally {
    if (writer != null) {
       writer.close();
    }
}
```

#Advance configuration for readers and writers
It has the potential of giving out readers and writers the following configurations.

##InteractionCategory
 - evidence: for InteractionEvidence implementations.
 - modelled: for ModelledInteraction implementations.
 - basic: for basic implementations of Interaction
 - mixed: for a mix of any kind of interactions
 - complex: for biological complexes. Not all datasources support this kind of interactions
 
##ComplexType
 - binary: for binay interactions
 - n_ary: for n-ary interactions
 - self_intra_molecular: for intra molecule interactions
 - self_inter_molecular: for inter molecule interactions
 
##ComplexExpansionMethod
Could be any of the implementation of the [ComplexExpansionMethod](https://github.com/MICommunity/psi-jami/tree/master/jami-core/src/main/java/psidev/psi/mi/jami/binary/expansion) interface, like:
 - BipartiteExpansion
 - MatrixExpansion
 - SpokeExpansion

For more details about all the possible configuration for readers and writers you can have a look to jami-commoms factories [MIWriterOptionFactory](https://github.com/MICommunity/psi-jami/blob/master/jami-commons/src/main/java/psidev/psi/mi/jami/commons/MIWriterOptionFactory.java) and [MIDataSourceOptionFactory](https://github.com/MICommunity/psi-jami/blob/master/jami-commons/src/main/java/psidev/psi/mi/jami/commons/MIDataSourceOptionFactory.java) in

#Other Java Examples

https://github.com/MICommunity/psi-jami/blob/master/jami-examples/src/main/java/psidev/psi/mi/jami/examples/core/ReadWriteWithJami.java

https://github.com/MICommunity/psi-jami/tree/develop/jami-mitab/src/test

