
##**This package was made to assign or update imex identifiers to Interaction,Experiment or Publication**

##**How does it relate to other packages?**

It is not used by any package in psi-jami but it uses internally following packages :-

1)jami-imexcentral
2)jami-enricher

##**Jami Examples**

https://github.com/MICommunity/psi-jami/tree/develop/jami-imex-updater/src/test/java/psidev/psi/mi/jami/imex

###How to Update Imex Id of a Publication?

```java

public class ImexPublicationUpdaterTest {

    private ImexPublicationUpdater updater;


    @Before
    public void initUpdater() throws BridgeFailedException {
        ImexCentralClient client = new MockImexCentralClient();
        this.updater = new ImexPublicationUpdater(client);

        Publication pub = new Publication();
        Identifier pubmed = new Identifier();
        pubmed.setNs("pmid");
        pubmed.setAc("12345");
        pub.getIdentifier().add(pubmed);
        pub.setImexAccession("IM-1");

        client.createPublication(new ImexPublication(pub));
    }

    @Test
    public void update_publication_successful() throws EnricherException {

        psidev.psi.mi.jami.model.Publication pub = new DefaultPublication("12345");
        pub.assignImexId("IM-1");

        updater.enrich(pub);

        <!--Assert.assertEquals(CurationDepth.IMEx, pub.getCurationDepth());
        Assert.assertEquals(2, pub.getAnnotations().size());

        Assert.assertNotNull(AnnotationUtils.collectFirstAnnotationWithTopicAndValue(pub.getAnnotations(),
                ImexPublicationUpdater.FULL_COVERAGE_MI, ImexPublicationUpdater.FULL_COVERAGE, ImexPublicationUpdater.FULL_COVERAGE_TEXT));
        Assert.assertNotNull(AnnotationUtils.collectFirstAnnotationWithTopic(pub.getAnnotations(),
                ImexPublicationUpdater.IMEX_CURATION_MI, ImexPublicationUpdater.IMEX_CURATION));-->
    }
}
```

###How to update imex id of a experiment?


```java
  public class ImexExperimentUpdaterTest {

  private ImexExperimentUpdater updater;


  @Before
  public void initUpdater() throws BridgeFailedException {
      this.updater = new ImexExperimentUpdater();
      this.updater.setImexAssigner(new ImexAssignerImpl(new MockImexCentralClient()));
  }

  @Test
  public void update_publication_successful() throws EnricherException {

      psidev.psi.mi.jami.model.Publication pub = new DefaultPublication("12345");
      pub.assignImexId("IM-1");
      Experiment exp = new DefaultExperiment(pub);
      pub.getExperiments().add(exp);

      updater.enrich(exp);

      Assert.assertEquals(1, exp.getXrefs().size());

      Assert.assertNotNull(XrefUtils.collectAllXrefsHavingDatabaseQualifierAndId(exp.getXrefs(),
              Xref.IMEX_MI, Xref.IMEX, "IM-1", Xref.IMEX_PRIMARY_MI, Xref.IMEX_PRIMARY));
  }
}
```

###How to update Imex Id of a Interaction?

```java

private ImexInteractionUpdater updater;


    @Before
    public void initUpdater() throws BridgeFailedException {
        this.updater = new ImexInteractionUpdater();
        this.updater.setImexAssigner(new ImexAssignerImpl(new MockImexCentralClient()));
    }

    @Test
    public void update_publication_successful() throws EnricherException {

        psidev.psi.mi.jami.model.Publication pub = new DefaultPublication("12345");
        pub.assignImexId("IM-1");
        Experiment exp = new DefaultExperiment(pub);
        pub.getExperiments().add(exp);

        InteractionEvidence ev1 = new DefaultInteractionEvidence();
        ev1.addParticipant(new DefaultParticipantEvidence(new DefaultProtein("P12345")));
        exp.addInteractionEvidence(ev1);
        InteractionEvidence ev2 = new DefaultInteractionEvidence();
        ev2.addParticipant(new DefaultParticipantEvidence(new DefaultProtein("P12346")));
        exp.addInteractionEvidence(ev2);

        updater.enrich(ev1);
        updater.enrich(ev2);

        Assert.assertEquals(1, ev1.getXrefs().size());
        Assert.assertEquals(1, ev2.getXrefs().size());

        Assert.assertEquals("IM-1-1", ev1.getImexId());
        Assert.assertEquals("IM-1-2", ev2.getImexId());
    }
```




 
