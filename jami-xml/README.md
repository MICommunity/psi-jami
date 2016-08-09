
Reading xml file

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
