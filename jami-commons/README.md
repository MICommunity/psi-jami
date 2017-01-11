JAMI commons: This package acts as a maven helper and fetches both XML and MITAB modules.
This might be used as a dependency and entry point for another java application, for example, alongside any other relevant module dependencies (e.g. enrichment if desired).

This package will allow you to initialise the various relevant factories needed to parse & output data.

Code example drawn from https://github.com/intermine/intermine/blob/14e50d301e6ef61146026071ea838b48e4f2b63a/bio/sources/psi-complexes/main/src/org/intermine/bio/dataconversion/PsiComplexesConverter.java, given a datasource `Reader reader`.

```java
    // initialise default factories for reading and writing MITAB/PSI-MI XML files
        PsiJami.initialiseAllFactories();

    // the option factory for reading files and other datasources
    MIDataSourceOptionFactory optionfactory = MIDataSourceOptionFactory.getInstance();
    // the datasource factory for reading MITAB/PSI-MI XML files and other datasources
    MIDataSourceFactory dataSourceFactory = MIDataSourceFactory.getInstance();
    Map<String, Object> parsingOptions = MIDataSourceOptionFactory.getInstance().getOptions(
                MIFileType.psimi_xml, InteractionCategory.complex, null, true, null, reader);

        InteractionStream interactionSource = null;
        try {
            // Get the stream of interactions knowing the default options for this file
            interactionSource = dataSourceFactory.
                    getInteractionSourceWith(parsingOptions);

            // the option factory for reading files and other datasources
            MIWriterOptionFactory optionwriterFactory = MIWriterOptionFactory.getInstance();

            // parse the stream and write as we parse
            // the interactionSource can be null if the file is not recognized or the provided
            // options are not matching any existing/registered datasources
            if (interactionSource != null) {
                Iterator interactionIterator = interactionSource.getInteractionsIterator();

                while (interactionIterator.hasNext()) {
                    Interaction entry = (Interaction) interactionIterator.next();
                    // your code here to handle the data in a desired manner - perhaps writing to your own DB.
                }
            }
        } finally {
            // always close the opened interaction stream
            if (interactionSource != null) {
                interactionSource.close();
            }

        }
 ```
 
 Se also https://github.com/MICommunity/psi-jami/blob/53b6c7c4c867e07d87ac1da7953109f14507dbea/jami-examples/src/main/java/psidev/psi/mi/jami/examples/core/ReadWriteWithJami.java for example usages. Note that this package is reasonable coupled with the core package as an entry package.
