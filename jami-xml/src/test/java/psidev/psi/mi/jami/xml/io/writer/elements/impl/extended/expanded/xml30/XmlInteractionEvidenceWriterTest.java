package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import psidev.psi.mi.jami.exception.IllegalRangeException;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.*;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.InteractorUtils;
import psidev.psi.mi.jami.utils.RangeUtils;
import psidev.psi.mi.jami.xml.cache.InMemoryIdentityObjectCache;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.AbstractXmlWriterTest;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence;
import psidev.psi.mi.jami.xml.model.extension.xml300.*;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Unit tester for XmlInteractionEvidenceWriter
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>26/11/13</pre>
 */

public class XmlInteractionEvidenceWriterTest extends AbstractXmlWriterTest {

    private String interaction = "<interaction id=\"1\">\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "</interaction>";

    private String interaction_complex = "<interaction id=\"1\">\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactionRef>4</interactionRef>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "</interaction>";

    private String interaction_complexAsInteractor ="<interaction id=\"1\">\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>test complex</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>complex</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0314\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "</interaction>";

    private String interaction_shortName ="<interaction id=\"1\">\n" +
            "  <names>\n" +
            "    <shortLabel>interaction test</shortLabel>\n"+
            "  </names>\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "</interaction>";

    private String interaction_fullName ="<interaction id=\"1\">\n" +
            "  <names>\n" +
            "    <fullName>interaction test</fullName>\n"+
            "  </names>\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "</interaction>";

    private String interaction_aliases ="<interaction id=\"1\">\n" +
            "  <names>\n" +
            "    <alias type=\"synonym\">interaction synonym</alias>\n"+
            "    <alias>test</alias>\n"+
            "  </names>\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "</interaction>";

    private String interaction_identifier = "<interaction id=\"1\">\n" +
            "  <xref>\n" +
            "    <primaryRef db=\"intact\" id=\"EBI-xxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "    <secondaryRef db=\"test\" id=\"xxxx1\"/>\n"+
            "  </xref>\n"+
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "</interaction>";
    private String interaction_xref = "<interaction id=\"1\">\n" +
            "  <xref>\n" +
            "    <primaryRef db=\"test2\" id=\"xxxx2\"/>\n" +
            "    <secondaryRef db=\"test\" id=\"xxxx1\"/>\n"+
            "  </xref>\n"+
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "</interaction>";
    private String interaction_inferred = "<interaction id=\"1\">\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "      <featureList>\n" +
            "        <feature id=\"5\">\n" +
            "          <featureRangeList>\n" +
            "            <featureRange>\n" +
            "              <startStatus>\n" +
            "                <names>\n" +
            "                  <shortLabel>certain</shortLabel>\n"+
            "                </names>\n"+
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0335\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "                </xref>\n"+
            "              </startStatus>\n" +
            "              <begin position=\"1\"/>\n"+
            "              <endStatus>\n" +
            "                <names>\n" +
            "                  <shortLabel>certain</shortLabel>\n"+
            "                </names>\n"+
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0335\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "                </xref>\n"+
            "              </endStatus>\n" +
            "              <end position=\"4\"/>\n"+
            "            </featureRange>\n"+
            "          </featureRangeList>\n" +
            "        </feature>\n"+
            "      </featureList>\n" +
            "    </participant>\n"+
            "    <participant id=\"6\">\n" +
            "      <interactor id=\"7\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test2</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "      <featureList>\n" +
            "        <feature id=\"8\">\n" +
            "          <featureRangeList>\n" +
            "            <featureRange>\n" +
            "              <startStatus>\n" +
            "                <names>\n" +
            "                  <shortLabel>certain</shortLabel>\n"+
            "                </names>\n"+
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0335\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "                </xref>\n"+
            "              </startStatus>\n" +
            "              <begin position=\"1\"/>\n"+
            "              <endStatus>\n" +
            "                <names>\n" +
            "                  <shortLabel>certain</shortLabel>\n"+
            "                </names>\n"+
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0335\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "                </xref>\n"+
            "              </endStatus>\n" +
            "              <end position=\"4\"/>\n"+
            "            </featureRange>\n"+
            "          </featureRangeList>\n" +
            "        </feature>\n"+
            "      </featureList>\n" +
            "    </participant>\n"+
            "    <participant id=\"9\">\n" +
            "      <interactor id=\"10\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test3</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "      <featureList>\n" +
            "        <feature id=\"11\">\n" +
            "          <featureRangeList>\n" +
            "            <featureRange>\n" +
            "              <startStatus>\n" +
            "                <names>\n" +
            "                  <shortLabel>certain</shortLabel>\n"+
            "                </names>\n"+
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0335\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "                </xref>\n"+
            "              </startStatus>\n" +
            "              <begin position=\"1\"/>\n"+
            "              <endStatus>\n" +
            "                <names>\n" +
            "                  <shortLabel>certain</shortLabel>\n"+
            "                </names>\n"+
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0335\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "                </xref>\n"+
            "              </endStatus>\n" +
            "              <end position=\"4\"/>\n"+
            "            </featureRange>\n"+
            "          </featureRangeList>\n" +
            "        </feature>\n"+
            "      </featureList>\n" +
            "    </participant>\n"+
            "    <participant id=\"12\">\n" +
            "      <interactor id=\"13\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test4</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "      <featureList>\n" +
            "        <feature id=\"14\">\n" +
            "          <featureRangeList>\n" +
            "            <featureRange>\n" +
            "              <startStatus>\n" +
            "                <names>\n" +
            "                  <shortLabel>certain</shortLabel>\n"+
            "                </names>\n"+
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0335\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "                </xref>\n"+
            "              </startStatus>\n" +
            "              <begin position=\"1\"/>\n"+
            "              <endStatus>\n" +
            "                <names>\n" +
            "                  <shortLabel>certain</shortLabel>\n"+
            "                </names>\n"+
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0335\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "                </xref>\n"+
            "              </endStatus>\n" +
            "              <end position=\"4\"/>\n"+
            "            </featureRange>\n"+
            "          </featureRangeList>\n" +
            "        </feature>\n"+
            "      </featureList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "  <inferredInteractionList>\n" +
            "    <inferredInteraction>\n" +
            "      <participant>\n" +
            "        <participantFeatureRef>5</participantFeatureRef>\n" +
            "      </participant>\n"+
            "      <participant>\n" +
            "        <participantFeatureRef>11</participantFeatureRef>\n" +
            "      </participant>\n"+
            "      <participant>\n" +
            "        <participantFeatureRef>8</participantFeatureRef>\n" +
            "      </participant>\n"+
            "    </inferredInteraction>\n"+
            "    <inferredInteraction>\n" +
            "      <participant>\n" +
            "        <participantFeatureRef>11</participantFeatureRef>\n" +
            "      </participant>\n"+
            "      <participant>\n" +
            "        <participantFeatureRef>14</participantFeatureRef>\n" +
            "      </participant>\n"+
            "    </inferredInteraction>\n"+
            "  </inferredInteractionList>\n" +
            "</interaction>";
    private String interaction_type =  "<interaction id=\"1\">\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "  <interactionType>\n" +
            "    <names>\n" +
            "      <shortLabel>association</shortLabel>\n" +
            "    </names>\n" +
            "    <xref>\n" +
            "      <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0914\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "    </xref>\n" +
            "  </interactionType>\n" +
            "</interaction>";
    private String interaction_attributes =  "<interaction id=\"1\">\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "  <attributeList>\n" +
            "    <attribute name=\"test2\"/>\n"+
            "    <attribute name=\"test3\"/>\n"+
            "  </attributeList>\n"+
            "</interaction>";
    private String interaction_registered = "<interaction id=\"2\">\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"3\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"4\">\n" +
            "      <interactor id=\"5\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "</interaction>";

    private String interaction_availability = "<interaction id=\"1\">\n" +
            "  <availability id=\"2\">copyright</availability>\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"3\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"4\">\n" +
            "      <interactor id=\"5\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "</interaction>";

    private String interaction_negative = "<interaction id=\"1\">\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "  <negative>true</negative>\n"+
            "</interaction>";

    private String interaction_confidence = "<interaction id=\"1\">\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "  <confidenceList>\n" +
            "    <confidence>\n" +
            "      <unit>\n" +
            "        <names>\n" +
            "          <shortLabel>intact-miscore</shortLabel>\n"+
            "        </names>\n"+
            "      </unit>\n" +
            "      <value>0.8</value>\n" +
            "    </confidence>\n"+
            "  </confidenceList>\n" +
            "</interaction>";

    private String interaction_parameters = "<interaction id=\"1\">\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "  <parameterList>\n" +
            "    <parameter term=\"kd\" base=\"10\" exponent=\"0\" factor=\"5\"/>\n" +
            "  </parameterList>\n" +
            "</interaction>";
    private String interaction_intra = "<interaction id=\"1\">\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "  <intraMolecular>true</intraMolecular>\n" +
            "</interaction>";
    private String interaction_modelled = "<interaction id=\"1\">\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "  <modelled>true</modelled>\n" +
            "</interaction>";
    private String interaction_variable_parameters = "<interaction id=\"1\">\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "  <experimentalVariableValueList>\n" +
            "    <experimentalVariableValues>\n" +
            "      <variableValueRef>5</variableValueRef>\n" +
            "    </experimentalVariableValues>\n"+
            "  </experimentalVariableValueList>\n" +
            "</interaction>";
    private String interaction_causal_relationship = "<interaction id=\"1\">\n" +
            "  <experimentList>\n" +
            "    <experimentDescription id=\"2\">\n" +
            "      <bibref>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"pubmed\" dbAc=\"MI:0446\" id=\"xxxxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </bibref>\n"+
            "      <interactionDetectionMethod>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified method</shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </interactionDetectionMethod>\n"+
            "    </experimentDescription>\n"+
            "  </experimentList>\n" +
            "  <participantList>\n" +
            "    <participant id=\"3\">\n" +
            "      <interactor id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>protein test</shortLabel>\n" +
            "        </names>\n" +
            "        <interactorType>\n" +
            "          <names>\n" +
            "            <shortLabel>protein</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </interactorType>\n" +
            "      </interactor>\n" +
            "      <biologicalRole>\n" +
            "        <names>\n" +
            "          <shortLabel>unspecified role</shortLabel>\n" +
            "        </names>\n" +
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "        </xref>\n" +
            "      </biologicalRole>\n" +
            "      <experimentalRoleList>\n" +
            "        <experimentalRole>\n" +
            "          <names>\n" +
            "            <shortLabel>unspecified role</shortLabel>\n" +
            "          </names>\n" +
            "          <xref>\n" +
            "            <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "          </xref>\n" +
            "        </experimentalRole>\n" +
            "      </experimentalRoleList>\n" +
            "    </participant>\n"+
            "  </participantList>\n" +
            "  <causalRelationshipList>\n" +
            "    <causalRelationship>\n" +
            "      <sourceParticipantRef>3</sourceParticipantRef>\n" +
            "      <causalityStatement>\n" +
            "        <names>\n" +
            "          <shortLabel>increases RNA expression of </shortLabel>\n"+
            "        </names>\n"+
            "        <xref>\n" +
            "          <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:xxxx\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n"+
            "        </xref>\n"+
            "      </causalityStatement>\n"+
            "      <targetParticipantRef>5</targetParticipantRef>\n" +
            "    </causalRelationship>\n"+
            "  </causalRelationshipList>\n"+
            "</interaction>";

    private PsiXmlObjectCache elementCache = new InMemoryIdentityObjectCache();

    @Test
    public void test_write_interaction() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction, output.toString());
    }

    @Test
    public void test_write_participant_complex() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        Complex complex = new DefaultComplex("test complex");
        complex.getParticipants().add(new XmlModelledParticipant(new DefaultProtein("test protein")));
        ParticipantEvidence participant = new XmlParticipantEvidence(complex);
        interaction.addParticipant(participant);
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_complex, output.toString());
    }

    @Test
    public void test_write_participant_complex_as_interactor() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        Complex complex = new DefaultComplex("test complex");
        complex.getParticipants().add(new XmlModelledParticipant(new DefaultProtein("test protein")));
        ParticipantEvidence participant = new XmlParticipantEvidence(complex);
        interaction.addParticipant(participant);
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.setComplexAsInteractor(true);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_complexAsInteractor, output.toString());
    }

    @Test
    public void test_write_participant_complex_no_participants() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        Complex complex = new DefaultComplex("test complex");
        ParticipantEvidence participant = new XmlParticipantEvidence(complex);
        interaction.addParticipant(participant);
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_complexAsInteractor, output.toString());
    }

    @Test
    public void test_write_interaction_shortName() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence("interaction test");
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_shortName, output.toString());
    }

    @Test
    public void test_write_interaction_fullName() throws XMLStreamException, IOException, IllegalRangeException {
        NamedInteraction interaction = new XmlInteractionEvidence();
        interaction.setFullName("interaction test");
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        elementCache.clear();

        ((InteractionEvidence)interaction).setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write((InteractionEvidence)interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_fullName, output.toString());
    }

    @Test
    public void test_write_interaction_alias() throws XMLStreamException, IOException, IllegalRangeException {
        NamedInteraction interaction = new XmlInteractionEvidence();
        interaction.getAliases().add(new DefaultAlias(new DefaultCvTerm("synonym"), "interaction synonym"));
        interaction.getAliases().add(new DefaultAlias("test"));
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        elementCache.clear();

        ((InteractionEvidence)interaction).setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);        writer.write((InteractionEvidence)interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_aliases, output.toString());
    }

    @Test
    public void test_write_interaction_identifier() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        interaction.getIdentifiers().add(new DefaultXref(new DefaultCvTerm("intact"), "EBI-xxx"));
        interaction.getXrefs().add(new DefaultXref(new DefaultCvTerm("test"), "xxxx1"));
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_identifier, output.toString());
    }

    @Test
    public void test_write_interaction_xref() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        interaction.getXrefs().add(new DefaultXref(new DefaultCvTerm("test2"), "xxxx2"));
        interaction.getXrefs().add(new DefaultXref(new DefaultCvTerm("test"), "xxxx1"));
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_xref, output.toString());
    }

    @Test
    @Ignore
    public void test_write_interaction_inferred() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        ParticipantEvidence participant2 = new XmlParticipantEvidence(new DefaultProtein("protein test2"));
        ParticipantEvidence participant3 = new XmlParticipantEvidence(new DefaultProtein("protein test3"));
        ParticipantEvidence participant4 = new XmlParticipantEvidence(new DefaultProtein("protein test4"));
        // two inferred interactiosn f1, f2, f3 and f3,f4
        FeatureEvidence f1 = new DefaultFeatureEvidence();
        f1.getRanges().add(RangeUtils.createRangeFromString("1-4"));
        FeatureEvidence f2 = new DefaultFeatureEvidence();
        f2.getRanges().add(RangeUtils.createRangeFromString("1-4"));
        FeatureEvidence f3 = new DefaultFeatureEvidence();
        f3.getRanges().add(RangeUtils.createRangeFromString("1-4"));
        FeatureEvidence f4 = new DefaultFeatureEvidence();
        f4.getRanges().add(RangeUtils.createRangeFromString("1-4"));
        f1.getLinkedFeatures().add(f2);
        f1.getLinkedFeatures().add(f3);
        f2.getLinkedFeatures().add(f1);
        f2.getLinkedFeatures().add(f3);
        f3.getLinkedFeatures().add(f1);
        f3.getLinkedFeatures().add(f2);
        f3.getLinkedFeatures().add(f4);
        f4.getLinkedFeatures().add(f3);
        participant.addFeature(f1);
        participant2.addFeature(f2);
        participant3.addFeature(f3);
        participant4.addFeature(f4);
        interaction.addParticipant(participant);
        interaction.addParticipant(participant2);
        interaction.addParticipant(participant3);
        interaction.addParticipant(participant4);
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_inferred, output.toString());
    }

    @Test
    public void test_write_interaction_type() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        interaction.setInteractionType(CvTermUtils.createMICvTerm("association", "MI:0914"));
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_type, output.toString());
    }

    @Test
    public void test_write_interaction_attributes() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        interaction.getAnnotations().add(new DefaultAnnotation(new DefaultCvTerm("test2")));
        interaction.getAnnotations().add(new DefaultAnnotation(new DefaultCvTerm("test3")));
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_attributes, output.toString());
    }

    @Test
    public void test_write_interaction_registered() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        elementCache.clear();
        elementCache.extractIdForInteraction(new DefaultInteraction());
        elementCache.extractIdForInteraction(interaction);

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_registered, output.toString());
    }

    @Test
    public void test_write_interaction_negative() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        interaction.setNegative(true);
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_negative, output.toString());
    }

    @Test
    public void test_write_interaction_confidences() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        interaction.getConfidences().add(new DefaultConfidence(new DefaultCvTerm("intact-miscore"), "0.8"));
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_confidence, output.toString());
    }

    @Test
    public void test_write_interaction_parameters() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        interaction.getParameters().add(new DefaultParameter(new DefaultCvTerm("kd"), new ParameterValue(new BigDecimal(5))));
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_parameters, output.toString());
    }

    @Test
    public void test_write_interaction_intraMolecular() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        interaction.setIntraMolecular(true);
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_intra, output.toString());
    }

    @Test
    public void test_write_interaction_modelled() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        interaction.setModelled(true);
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_modelled, output.toString());
    }

    @Test
    public void test_write_interaction_availability() throws XMLStreamException, IOException, IllegalRangeException {
        ExtendedPsiXmlInteractionEvidence interaction = new XmlInteractionEvidence();
        ParticipantEvidence participant = new XmlParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        interaction.setAvailability("copyright");
        elementCache.clear();

        interaction.setExperiment(new DefaultXmlExperiment(new DefaultPublication("xxxxxx")));
        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_availability, output.toString());
    }

    @Test
    public void test_write_interaction_variable_parameters() throws XMLStreamException, IOException, IllegalRangeException {
        InteractionEvidence interaction = new XmlInteractionEvidence();
        ParticipantEvidence participant = new DefaultNamedParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        VariableParameterValueSet valueSet = new DefaultVariableParameterValueSet();
        valueSet.add(new DefaultVariableParameterValue("test", null));
        interaction.getVariableParameterValues().add(valueSet);
        interaction.setExperiment(new DefaultNamedExperiment(new DefaultPublication("xxxxxx")));
        elementCache.clear();

        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_variable_parameters, output.toString());
    }

    @Test
    public void test_write_interaction_causal_relationship() throws XMLStreamException, IOException, IllegalRangeException {
        InteractionEvidence interaction = new XmlInteractionEvidence();
        ParticipantEvidence participant = new DefaultNamedParticipantEvidence(new DefaultProtein("protein test"));
        interaction.addParticipant(participant);
        CausalRelationship rel = new DefaultCausalRelationship(CvTermUtils.createMICvTerm("increases RNA expression of ","MI:xxxx"),
                new DefaultModelledParticipant(InteractorUtils.createUnknownBasicInteractor()));
        participant.getCausalRelationships().add(rel);
        interaction.setExperiment(new DefaultNamedExperiment(new DefaultPublication("xxxxxx")));
        elementCache.clear();

        XmlInteractionEvidenceWriter writer = new XmlInteractionEvidenceWriter(createStreamWriter(), this.elementCache);
        writer.write(interaction);
        streamWriter.flush();

        Assert.assertEquals(this.interaction_causal_relationship, output.toString());
    }
}