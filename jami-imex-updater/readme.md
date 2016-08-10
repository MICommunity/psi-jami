##**This package was made to assign or update imex identifiers to Interaction,Experiment or Publication**

##**How does it relate to other packages?**

It is not used by any package in psi-jami but it uses internally following packages :-

1)jami-imexcentral
2)jami-enricher

##**Jami Examples**

https://github.com/MICommunity/psi-jami/tree/develop/jami-imex-updater/src/test/java/psidev/psi/mi/jami/imex

###Update Imex Id of a Publication - Example

'''java

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

'''




 
