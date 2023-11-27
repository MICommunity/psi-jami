package psidev.psi.mi.jami.bridges.rna.central.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.ols.CachedOlsCvTermFetcher;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.NucleicAcid;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;

public class OLSUtils {
    public static final CvTerm rnaCentral = new DefaultCvTerm("RNAcentral", "MI:1357");
    public static final CvTerm identityCV = new DefaultCvTerm(Xref.IDENTITY, Xref.IDENTITY_MI);
    public static final CvTerm secondaryAcCV = new DefaultCvTerm(Xref.SECONDARY, Xref.SECONDARY_MI);
    public static final CvTerm intactCV = new DefaultCvTerm("intact", Xref.INTACT_MI);
    public static final CvTerm miCV = new DefaultCvTerm(CvTerm.PSI_MI, CvTerm.PSI_MI_MI);
    public static final CvTerm rnaCV = new DefaultCvTerm("ribonucleic acid", "MI:0320");
    public static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    public static final CachedOlsCvTermFetcher olsFetcher;

    static {
        try {
            olsFetcher = new CachedOlsCvTermFetcher();
        } catch (BridgeFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public static CvTerm getCVByMIId(String id) {
        if (id == null) return null;
        try {
            return olsFetcher.fetchByIdentifier(id, CvTerm.PSI_MI);
        } catch (BridgeFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public static CvTerm getCVByName(String name) {
        if (name == null) return null;
        try {
            return olsFetcher.fetchByName(name, CvTerm.PSI_MI);
        } catch (BridgeFailedException e) {
            throw new RuntimeException(e);
        }
    }
}
