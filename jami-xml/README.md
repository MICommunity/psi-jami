
##Jami-Xml was made for reading and writing xmls for different kinds of data.

##How does it relate to other packages ?

Only 
     jami-commons
uses this package 

But jami-commons is used by following packages

crosslink-uploader-app
interactionviewer-app
jami-examples
jami-ontology-manager
psi-jami


##Detailed Description:-

It has the potential of giving out writers for following interaction categories, there are more detailed options available :-

* - evidence : for InteractionEvidence implementations.
* - modelled: for ModelledInteraction implementations.
* - basic: for basic implementations of Interaction
* - mixed: for a mix of any kind of interactions
* - complex: for biological complexes. Not all datasources support this kind of interactions

##Java Examples


https://github.com/MICommunity/psi-jami/tree/develop/jami-xml/src/test

#**How to Write 2.5 and 3.0 xmls ?**

This example is for compact, for expanded you can choose choose to use optionFactory.getDefaultExpandedXmlOptions
```java  
InteractionWriterFactory writerFactory = InteractionWriterFactory.getInstance();


IntactPsiXml.initialiseAllIntactXmlWriters();
   MIWriterOptionFactory optionFactory = MIWriterOptionFactory.getInstance();

//3.0

InteractionWriter writer=
 writerFactory.getInteractionWriterWith(optionFactory.getDefaultCompactXmlOptions(output,InteractionCategory.evidence, ComplexType.n_ary,PsiXmlVersion.v3_0_0));


//2.5
InteractionWriter writer=
writerFactory.getInteractionWriterWith(optionFactory.getDefaultCompactXmlOptions(output, InteractionCategory.evidence, ComplexType.n_ary,
       PsiXmlVersion.v2_5_4)) ;

// writer to start writing

writer.start();
writer.write(evidences);
 // here evidences are Collection<InteractionEvidence>

writer.end();


// below is an example of initialiseAllIntactXmlWriters method used above

public static void initialiseAllIntactXmlWriters() {
   InteractionWriterFactory writerFactory = InteractionWriterFactory.getInstance();
   Map supportedOptions4 = createXMLInteractionWriterOptions((InteractionCategory)null, (ComplexType)null, (PsiXmlType)null, (Boolean)null, (Boolean)null);
   writerFactory.registerDataSourceWriter(DefaultIntactXmlWriter.class, supportedOptions4);
}
```java
#Reading xml file

```java
//File is your xml file to read
File file = new File("interactions.xml");

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
