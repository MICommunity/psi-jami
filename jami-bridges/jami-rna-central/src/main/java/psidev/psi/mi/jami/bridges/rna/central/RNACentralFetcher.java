package psidev.psi.mi.jami.bridges.rna.central;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.NucleicAcidFetcher;
import psidev.psi.mi.jami.bridges.ols.CachedOlsCvTermFetcher;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.NucleicAcid;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class RNACentralFetcher implements NucleicAcidFetcher {

    public static final Pattern identifierPattern = Pattern.compile("^(?<id>URS[A-Z0-9]+)(_(?<taxid>\\d+))?$");
    public static final String MAIN_URL = "https://rnacentral.org/api/v1/rna/%s.json";
    public static final String XREFS_URL = "https://rnacentral.org/api/v1/rna/%s/xrefs.json";
    public static final String PUBLICATIONS_URL = "https://rnacentral.org/api/v1/rna/%s/publications.json";
    public static final String MI_ONTOLOGY_NAME = "psi-mi";

    public final CvTerm rnaCentral = new DefaultCvTerm("RNAcentral", "MI:1357");
    public final CvTerm identity = new DefaultCvTerm(Xref.IDENTITY, Xref.IDENTITY_MI);
    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    private final CachedOlsCvTermFetcher olsFetcher = new CachedOlsCvTermFetcher();

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
                ApiEntree entree = mapper.readValue(entreeURI, ApiEntree.class);

                DefaultXref id = new DefaultXref(rnaCentral, identifier, identity);
                CvTerm type = olsFetcher.fetchByName(entree.getRnaType(), MI_ONTOLOGY_NAME);
                if (type == null) type = new DefaultCvTerm("ribonucleic acid", "MI:0320");
                Organism organism = new DefaultOrganism(entree.getTaxid(), entree.getSpecies());

                DefaultNucleicAcid nucleicAcid = new DefaultNucleicAcid(entree.getShortDescription(), type, organism, id);
                nucleicAcid.setSequence(entree.getSequence());
                nucleicAcid.setFullName(entree.getDescription());
                addXRefs(nucleicAcid, pureIdentifier, organism);

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
                ApiXrefs xrefs = mapper.readValue(url, ApiXrefs.class);
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

        CvTerm database = getCVByName(result.getDatabase());

        if (database != null) {
            CvTerm qualifier = Stream.of(xrefType)
                    .map(type -> type.qualifierIdBuilder.apply(result))
                    .filter(Objects::nonNull)
                    .map(this::getCVByMIId)
                    .filter(Objects::nonNull)
                    .findFirst().orElse(null);

            xrefs.add(new DefaultXref(database, result.getAccession().getExternalId(), qualifier));

            xrefType.extraReferenceBuilders.stream()
                    .map(builder -> builder.apply(result))
                    .map(partialXref -> new DefaultXref(database, partialXref.getIdentifier(), getCVByMIId(partialXref.getQualifierMI())))
                    .forEach(xrefs::add);
        }

        xrefType.aliasBuilders.stream()
                .map(builder -> builder.apply(result))
                .map(partialAlias -> new DefaultAlias(getCVByMIId(partialAlias.getTypeMI()), partialAlias.getName()))
                .forEach(alias -> nucleicAcid.getAliases().add(alias));

        xrefType.extraValueSetters.forEach(builder -> builder.accept(result, nucleicAcid));
    }

    private CvTerm getCVByMIId(String id) {
        if (id == null) return null;
        try {
            return olsFetcher.fetchByIdentifier(id, MI_ONTOLOGY_NAME);
        } catch (BridgeFailedException e) {
            throw new RuntimeException(e);
        }
    }

    private CvTerm getCVByName(String name) {
        if (name == null) return null;
        try {
            return olsFetcher.fetchByName(name, MI_ONTOLOGY_NAME);
        } catch (BridgeFailedException e) {
            throw new RuntimeException(e);
        }
    }
}
