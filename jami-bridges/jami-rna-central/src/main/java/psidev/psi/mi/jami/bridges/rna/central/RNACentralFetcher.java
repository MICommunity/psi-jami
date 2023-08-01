package psidev.psi.mi.jami.bridges.rna.central;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.NucleicAcidFetcher;
import psidev.psi.mi.jami.bridges.rna.central.partials.PartialAlias;
import psidev.psi.mi.jami.bridges.rna.central.partials.PartialCvTerm;
import psidev.psi.mi.jami.bridges.rna.central.partials.PartialXref;
import psidev.psi.mi.jami.bridges.rna.central.utils.OLSUtils;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.NucleicAcid;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.*;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class RNACentralFetcher implements NucleicAcidFetcher {

    public static final Pattern identifierPattern = Pattern.compile("^(?<id>URS[A-Z0-9]+)(_(?<taxid>\\d+))?$");
    public static final String MAIN_URL = "https://rnacentral.org/api/v1/rna/%s.json";
    public static final String XREFS_URL = "https://rnacentral.org/api/v1/rna/%s/xrefs.json";
    public static final String PUBLICATIONS_URL = "https://rnacentral.org/api/v1/rna/%s/publications.json";

    public RNACentralFetcher() throws BridgeFailedException {
    }

    @Override
    public Collection<NucleicAcid> fetchByIdentifier(String identifier) throws BridgeFailedException {
        Matcher matcher = identifierPattern.matcher(identifier);
        if (matcher.find()) {
            return fetchByIdentifier(identifier, Integer.parseInt(matcher.group("taxid")));
        }
        return Collections.emptyList();
    }

    @Override
    public Collection<NucleicAcid> fetchByIdentifier(String identifier, int taxID) throws BridgeFailedException {
        try {
            Matcher matcher = identifierPattern.matcher(identifier);
            if (matcher.find()) {
                String pureIdentifier = matcher.group("id");
                URL entreeURI = new URL(String.format(MAIN_URL, identifier));
                ApiEntree entree = OLSUtils.mapper.readValue(entreeURI, ApiEntree.class);

                DefaultXref id = new DefaultXref(OLSUtils.rnaCentral, identifier, OLSUtils.identityCV);
                CvTerm type = OLSUtils.olsFetcher.fetchByName(entree.getRnaType(), CvTerm.PSI_MI);
                if (type == null) type = OLSUtils.rnaCV;
                Organism organism = new DefaultOrganism(entree.getTaxid(), entree.getSpecies());

                DefaultNucleicAcid nucleicAcid = new DefaultNucleicAcid(entree.getShortDescription(), type, organism, id);
                nucleicAcid.setSequence(entree.getSequence());
                nucleicAcid.setFullName(entree.getDescription());
                addXRefs(nucleicAcid, pureIdentifier, organism);
                if (type == OLSUtils.rnaCV) nucleicAcid.getAnnotations().add(new DefaultAnnotation(OLSUtils.getCVByName("comment"), "RNA-Central type: " + entree.getRnaType()));

                return List.of(nucleicAcid);
            } else return List.of();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BridgeFailedException(e.getMessage());
        }
    }

    private void addXRefs(NucleicAcid nucleicAcid, String pureIdentifier, Organism organism) {
        try {
            URL url = new URL(String.format(XREFS_URL, pureIdentifier));

            while (url != null) {
                ApiXrefs xrefs = OLSUtils.mapper.readValue(url, ApiXrefs.class);
                url = xrefs.getNext() != null ? new URL(xrefs.getNext().toString().replace("http", "https")) : null;
                xrefs.getResults().stream()
                        .filter(result -> result.getAccession().getSpecies().equals(organism.getScientificName()) || result.getTaxid().equals(organism.getTaxId()))
                        .forEach(result -> this.extractXrefsAndAliases(nucleicAcid, result));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void extractXrefsAndAliases(NucleicAcid nucleicAcid, ApiXrefs.Result result) {
        Collection<Xref> xrefs = nucleicAcid.getXrefs();

        XrefType xrefType = XrefType.getByDatabase(result.getDatabase());

        CvTerm database = OLSUtils.getCVByName(result.getDatabase());

        if (database != null) {
            CvTerm qualifier = Stream.of(xrefType)
                    .map(type -> type.qualifierIdBuilder.apply(result))
                    .filter(Objects::nonNull)
                    .map(PartialCvTerm::complete)
                    .findFirst().orElse(OLSUtils.secondaryAcCV);

            xrefs.add(new DefaultXref(database, result.getAccession().getExternalId(), qualifier));

            xrefType.extraReferenceBuilders.stream()
                    .map(builder -> builder.apply(result))
                    .map(partialXref -> partialXref.complete(
                            PartialXref.builder()
                                    .database(PartialCvTerm.from(database))
                                    .build()))
                    .forEach(xrefs::add);
        }

        xrefType.aliasBuilders.stream()
                .map(builder -> builder.apply(result))
                .map(PartialAlias::complete)
                .forEach(alias -> nucleicAcid.getAliases().add(alias));

        xrefType.extraValueSetters.forEach(builder -> builder.accept(result, nucleicAcid));
    }

}
