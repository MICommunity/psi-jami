package psidev.psi.mi.jami.xml.io.writer.expanded.extended;

import junit.framework.Assert;
import org.junit.Test;
import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.model.Complex;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.model.Preassembly;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.model.impl.DefaultPreassemby;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.model.extension.binary.XmlModelledBinaryInteraction;
import psidev.psi.mi.jami.xml.model.extension.xml254.DefaultXmlComplex;
import psidev.psi.mi.jami.xml.model.extension.xml254.XmlModelledParticipant;
import psidev.psi.mi.jami.xml.model.extension.xml254.XmlProtein;
import psidev.psi.mi.jami.xml.model.extension.xml254.DefaultXmlSource;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.stream.XMLStreamException;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Unit tester for ExpandedXmlModelledBinaryWriter
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>26/11/13</pre>
 */

public class ExpandedXml25ModelledBinaryWriterTest {
    private String interaction = "<?xml version='1.0' encoding='UTF-8'?>\n" +
            "<entrySet xmlns=\"http://psi.hupo.org/mi/mif\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xsi:schemaLocation=\"http://psi.hupo.org/mi/mif https://raw.githubusercontent.com/HUPO-PSI/miXML/master/2.5/src/MIF254.xsd\" " +
            "level=\"2\" version=\"5\" minorVersion=\"4\">\n" +
            "  <entry>\n" +
            "    <interactionList>\n" +
            "      <interaction id=\"1\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"2\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"3\">\n" +
            "            <interactor id=\"4\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "    </interactionList>\n"+
            "  </entry>\n" +
            "</entrySet>";
    private String interaction_multiple = "<?xml version='1.0' encoding='UTF-8'?>\n" +
            "<entrySet xmlns=\"http://psi.hupo.org/mi/mif\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xsi:schemaLocation=\"http://psi.hupo.org/mi/mif https://raw.githubusercontent.com/HUPO-PSI/miXML/master/2.5/src/MIF254.xsd\" " +
            "level=\"2\" version=\"5\" minorVersion=\"4\">\n" +
            "  <entry>\n" +
            "    <interactionList>\n" +
            "      <interaction id=\"1\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"2\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"3\">\n" +
            "            <interactor id=\"4\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "      <interaction id=\"5\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"6\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"7\">\n" +
            "            <interactor id=\"8\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test2</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "    </interactionList>\n"+
            "  </entry>\n" +
            "</entrySet>";
    private String interaction_same_experiment_interactors = "<?xml version='1.0' encoding='UTF-8'?>\n" +
            "<entrySet xmlns=\"http://psi.hupo.org/mi/mif\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xsi:schemaLocation=\"http://psi.hupo.org/mi/mif https://raw.githubusercontent.com/HUPO-PSI/miXML/master/2.5/src/MIF254.xsd\" " +
            "level=\"2\" version=\"5\" minorVersion=\"4\">\n" +
            "  <entry>\n" +
            "    <interactionList>\n" +
            "      <interaction id=\"1\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"2\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n"+
            "        <participantList>\n" +
            "          <participant id=\"3\">\n" +
            "            <interactor id=\"4\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "      <interaction id=\"5\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"6\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"7\">\n" +
            "            <interactor id=\"8\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "    </interactionList>\n"+
            "  </entry>\n" +
            "</entrySet>";
    private String interaction_complexes = "<?xml version='1.0' encoding='UTF-8'?>\n" +
            "<entrySet xmlns=\"http://psi.hupo.org/mi/mif\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xsi:schemaLocation=\"http://psi.hupo.org/mi/mif https://raw.githubusercontent.com/HUPO-PSI/miXML/master/2.5/src/MIF254.xsd\" " +
            "level=\"2\" version=\"5\" minorVersion=\"4\">\n" +
            "  <entry>\n" +
            "    <interactionList>\n" +
            "      <interaction id=\"1\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"2\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"3\">\n" +
            "            <interactionRef>4</interactionRef>\n" +
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "      <interaction id=\"4\">\n" +
            "        <names>\n" +
            "          <shortLabel>test complex</shortLabel>\n"+
            "        </names>\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"5\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"6\">\n" +
            "            <interactor id=\"7\">\n" +
            "              <names>\n" +
            "                <shortLabel>test protein</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "    </interactionList>\n"+
            "  </entry>\n" +
            "</entrySet>";
    private String interaction_complexes_as_interactor = "<?xml version='1.0' encoding='UTF-8'?>\n" +
            "<entrySet xmlns=\"http://psi.hupo.org/mi/mif\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xsi:schemaLocation=\"http://psi.hupo.org/mi/mif https://raw.githubusercontent.com/HUPO-PSI/miXML/master/2.5/src/MIF254.xsd\" " +
            "level=\"2\" version=\"5\" minorVersion=\"4\">\n" +
            "  <entry>\n" +
            "    <interactionList>\n" +
            "      <interaction id=\"1\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"2\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"3\">\n" +
            "            <interactor id=\"4\">\n" +
            "              <names>\n" +
            "                <shortLabel>test complex</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>complex</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0314\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "    </interactionList>\n"+
            "  </entry>\n" +
            "</entrySet>";
    private String interaction_different_entries1 = "<?xml version='1.0' encoding='UTF-8'?>\n" +
            "<entrySet xmlns=\"http://psi.hupo.org/mi/mif\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xsi:schemaLocation=\"http://psi.hupo.org/mi/mif https://raw.githubusercontent.com/HUPO-PSI/miXML/master/2.5/src/MIF254.xsd\" " +
            "level=\"2\" version=\"5\" minorVersion=\"4\">\n" +
            "  <entry>\n" +
            "    <interactionList>\n" +
            "      <interaction id=\"1\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"2\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"3\">\n" +
            "            <interactor id=\"4\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "    </interactionList>\n"+
            "  </entry>\n" +
            "  <entry>\n" +
            "    <interactionList>\n" +
            "      <interaction id=\"1\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"2\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"3\">\n" +
            "            <interactor id=\"4\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "    </interactionList>\n"+
            "  </entry>\n" +
            "</entrySet>";
    private String interaction_different_entries2 = "<?xml version='1.0' encoding='UTF-8'?>\n" +
            "<entrySet xmlns=\"http://psi.hupo.org/mi/mif\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xsi:schemaLocation=\"http://psi.hupo.org/mi/mif https://raw.githubusercontent.com/HUPO-PSI/miXML/master/2.5/src/MIF254.xsd\" " +
            "level=\"2\" version=\"5\" minorVersion=\"4\">\n" +
            "  <entry>\n" +
            "    <interactionList>\n" +
            "      <interaction id=\"1\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"2\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"3\">\n" +
            "            <interactor id=\"4\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "      <interaction id=\"5\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"6\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"7\">\n" +
            "            <interactor id=\"8\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test2</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "    </interactionList>\n"+
            "  </entry>\n" +
            "  <entry>\n" +
            "    <interactionList>\n" +
            "      <interaction id=\"1\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"2\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"3\">\n" +
            "            <interactor id=\"4\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "      <interaction id=\"5\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"6\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"7\">\n" +
            "            <interactor id=\"8\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test2</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "    </interactionList>\n"+
            "  </entry>\n" +
            "</entrySet>";
    private String interaction_source = "<?xml version='1.0' encoding='UTF-8'?>\n" +
            "<entrySet xmlns=\"http://psi.hupo.org/mi/mif\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xsi:schemaLocation=\"http://psi.hupo.org/mi/mif https://raw.githubusercontent.com/HUPO-PSI/miXML/master/2.5/src/MIF254.xsd\" " +
            "level=\"2\" version=\"5\" minorVersion=\"4\">\n" +
            "  <entry>\n" +
            "    <source releaseDate=\"2013-09-02Z\">\n" +
            "      <names>\n" +
            "        <shortLabel>intact</shortLabel>\n"+
            "      </names>\n"+
            "    </source>\n"+
            "    <interactionList>\n" +
            "      <interaction id=\"1\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"2\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"3\">\n" +
            "            <interactor id=\"4\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "    </interactionList>\n"+
            "  </entry>\n" +
            "</entrySet>";
    private String interaction_different_source = "<?xml version='1.0' encoding='UTF-8'?>\n" +
            "<entrySet xmlns=\"http://psi.hupo.org/mi/mif\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xsi:schemaLocation=\"http://psi.hupo.org/mi/mif https://raw.githubusercontent.com/HUPO-PSI/miXML/master/2.5/src/MIF254.xsd\" " +
            "level=\"2\" version=\"5\" minorVersion=\"4\">\n" +
            "  <entry>\n" +
            "    <source releaseDate=\"2013-09-02Z\">\n" +
            "      <names>\n" +
            "        <shortLabel>mint</shortLabel>\n"+
            "      </names>\n"+
            "    </source>\n"+
            "    <interactionList>\n" +
            "      <interaction id=\"1\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"2\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"3\">\n" +
            "            <interactor id=\"4\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "      <interaction id=\"5\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"6\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"7\">\n" +
            "            <interactor id=\"8\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test2</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "    </interactionList>\n"+
            "  </entry>\n" +
            "  <entry>\n" +
            "    <source releaseDate=\"2013-09-02Z\">\n" +
            "      <names>\n" +
            "        <shortLabel>intact</shortLabel>\n"+
            "      </names>\n"+
            "    </source>\n"+
            "    <interactionList>\n" +
            "      <interaction id=\"1\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"2\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"3\">\n" +
            "            <interactor id=\"4\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "      <interaction id=\"5\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"6\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"7\">\n" +
            "            <interactor id=\"8\">\n" +
            "              <names>\n" +
            "                <shortLabel>protein test2</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "    </interactionList>\n"+
            "  </entry>\n" +
            "</entrySet>";
    private String interaction_cooperative = "<?xml version='1.0' encoding='UTF-8'?>\n" +
            "<entrySet xmlns=\"http://psi.hupo.org/mi/mif\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xsi:schemaLocation=\"http://psi.hupo.org/mi/mif https://raw.githubusercontent.com/HUPO-PSI/miXML/master/2.5/src/MIF254.xsd\" " +
            "level=\"2\" version=\"5\" minorVersion=\"4\">\n" +
            "  <entry>\n" +
            "    <interactionList>\n" +
            "      <interaction id=\"1\">\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"2\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"3\">\n" +
            "            <interactor id=\"4\">\n" +
            "              <names>\n" +
            "                <shortLabel>test protein</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "        <attributeList>\n" +
            "          <attribute name=\"pre-assembly\" nameAc=\"MI:1158\"/>\n" +
            "          <attribute name=\"positive cooperative effect\" nameAc=\"MI:1154\"/>\n" +
            "          <attribute name=\"configurational pre-organization\" nameAc=\"MI:1174\"/>\n"+
            "          <attribute name=\"affected interaction\" nameAc=\"MI:1150\">5</attribute>\n" +
            "        </attributeList>\n" +
            "      </interaction>\n"+
            "      <interaction id=\"5\">\n" +
            "        <names>\n" +
            "          <shortLabel>test complex</shortLabel>\n"+
            "        </names>\n" +
            "        <experimentList>\n" +
            "          <experimentDescription id=\"6\">\n" +
            "            <names>\n" +
            "              <fullName>Mock publication and experiment for abstract interactions that are not interaction evidences.</fullName>\n" +
            "            </names>\n" +
            "            <bibref>\n" +
            "              <attributeList>\n" +
            "                <attribute name=\"publication title\" nameAc=\"MI:1091\">Mock publication and experiment for abstract interactions that are not interaction evidences.</attribute>\n" +
            "              </attributeList>\n" +
            "            </bibref>\n" +
            "            <interactionDetectionMethod>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified method</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0686\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </interactionDetectionMethod>\n" +
            "          </experimentDescription>\n"+
            "        </experimentList>\n" +
            "        <participantList>\n" +
            "          <participant id=\"7\">\n" +
            "            <interactor id=\"8\">\n" +
            "              <names>\n" +
            "                <shortLabel>test protein</shortLabel>\n" +
            "              </names>\n" +
            "              <interactorType>\n" +
            "                <names>\n" +
            "                  <shortLabel>protein</shortLabel>\n" +
            "                </names>\n" +
            "                <xref>\n" +
            "                  <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0326\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "                </xref>\n" +
            "              </interactorType>\n" +
            "            </interactor>\n"+
            "            <biologicalRole>\n" +
            "              <names>\n" +
            "                <shortLabel>unspecified role</shortLabel>\n" +
            "              </names>\n" +
            "              <xref>\n" +
            "                <primaryRef db=\"psi-mi\" dbAc=\"MI:0488\" id=\"MI:0499\" refType=\"identity\" refTypeAc=\"MI:0356\"/>\n" +
            "              </xref>\n" +
            "            </biologicalRole>\n" +
            "          </participant>\n"+
            "        </participantList>\n" +
            "      </interaction>\n"+
            "    </interactionList>\n"+
            "  </entry>\n" +
            "</entrySet>";

    @Test(expected = IllegalStateException.class)
    public void test_not_initialised_writer() {
        ExpandedXmlModelledBinaryWriter writer = new ExpandedXmlModelledBinaryWriter(PsiXmlVersion.v2_5_4);
        writer.write(new XmlModelledBinaryInteraction());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_not_initialised_no_options() {
        ExpandedXmlModelledBinaryWriter writer = new ExpandedXmlModelledBinaryWriter(PsiXmlVersion.v2_5_4);
        writer.initialiseContext(null);
    }

    @Test
    public void test_single_interaction() throws XMLStreamException {
        StringWriter stringWriter = new StringWriter();

        ExpandedXmlModelledBinaryWriter writer = new ExpandedXmlModelledBinaryWriter(PsiXmlVersion.v2_5_4, stringWriter);
        ModelledBinaryInteraction interaction = new XmlModelledBinaryInteraction();
        ModelledParticipant participant = new XmlModelledParticipant(new XmlProtein("protein test"));
        interaction.addParticipant(participant);

        writer.start();
        writer.write(interaction);
        writer.end();
        writer.close();
        Assert.assertEquals(this.interaction, stringWriter.toString());
    }

    @Test
    public void test_several_interactions1() throws XMLStreamException {
        StringWriter stringWriter = new StringWriter();

        ExpandedXmlModelledBinaryWriter writer = new ExpandedXmlModelledBinaryWriter(PsiXmlVersion.v2_5_4, stringWriter);

        ModelledBinaryInteraction interaction = new XmlModelledBinaryInteraction();
        ModelledParticipant participant = new XmlModelledParticipant(new XmlProtein("protein test"));
        interaction.addParticipant(participant);
        ModelledBinaryInteraction interaction2 = new XmlModelledBinaryInteraction();
        ModelledParticipant participant2 = new XmlModelledParticipant(new XmlProtein("protein test2"));
        interaction2.addParticipant(participant2);

        writer.start();
        writer.write(Arrays.asList(interaction, interaction2));
        writer.end();
        writer.close();

        Assert.assertEquals(this.interaction_multiple, stringWriter.toString());
    }

    @Test
    public void test_several_interactions2() throws XMLStreamException {
        StringWriter stringWriter = new StringWriter();

        ExpandedXmlModelledBinaryWriter writer = new ExpandedXmlModelledBinaryWriter(PsiXmlVersion.v2_5_4, stringWriter);
        ModelledBinaryInteraction interaction = new XmlModelledBinaryInteraction();
        ModelledParticipant participant = new XmlModelledParticipant(new XmlProtein("protein test"));
        interaction.addParticipant(participant);
        ModelledBinaryInteraction interaction2 = new XmlModelledBinaryInteraction();
        ModelledParticipant participant2 = new XmlModelledParticipant(new XmlProtein("protein test2"));
        interaction2.addParticipant(participant2);

        writer.start();
        writer.write(Arrays.asList(interaction, interaction2).iterator());
        writer.end();
        writer.close();

        Assert.assertEquals(this.interaction_multiple, stringWriter.toString());
    }

    @Test
    public void test_interactions_same_interactors1() throws XMLStreamException {
        StringWriter stringWriter = new StringWriter();

        ExpandedXmlModelledBinaryWriter writer = new ExpandedXmlModelledBinaryWriter(PsiXmlVersion.v2_5_4, stringWriter);

        ModelledBinaryInteraction interaction = new XmlModelledBinaryInteraction();
        ModelledParticipant participant = new XmlModelledParticipant(new XmlProtein("protein test"));
        interaction.addParticipant(participant);
        ModelledBinaryInteraction interaction2 = new XmlModelledBinaryInteraction();
        ModelledParticipant participant2 = new XmlModelledParticipant(participant.getInteractor());
        interaction2.addParticipant(participant2);

        writer.start();
        writer.write(Arrays.asList(interaction, interaction2));
        writer.end();
        writer.close();

        Assert.assertEquals(this.interaction_same_experiment_interactors, stringWriter.toString());
    }

    @Test
    public void test_interactions_same_interactors2() throws XMLStreamException {
        StringWriter stringWriter = new StringWriter();

        ExpandedXmlModelledBinaryWriter writer = new ExpandedXmlModelledBinaryWriter(PsiXmlVersion.v2_5_4, stringWriter);

        ModelledBinaryInteraction interaction = new XmlModelledBinaryInteraction();
        ModelledParticipant participant = new XmlModelledParticipant(new XmlProtein("protein test"));
        interaction.addParticipant(participant);
        ModelledBinaryInteraction interaction2 = new XmlModelledBinaryInteraction();
        ModelledParticipant participant2 = new XmlModelledParticipant(participant.getInteractor());
        interaction2.addParticipant(participant2);

        writer.start();
        writer.write(Arrays.asList(interaction, interaction2).iterator());
        writer.end();
        writer.close();

        Assert.assertEquals(this.interaction_same_experiment_interactors, stringWriter.toString());
    }

    @Test
    public void test_single_interaction_complexes() throws XMLStreamException {
        StringWriter stringWriter = new StringWriter();

        Complex complex = new DefaultXmlComplex("test complex");
        complex.getParticipants().add(new XmlModelledParticipant(new XmlProtein("test protein")));

        ExpandedXmlModelledBinaryWriter writer = new ExpandedXmlModelledBinaryWriter(PsiXmlVersion.v2_5_4, stringWriter);
        ModelledBinaryInteraction interaction = new XmlModelledBinaryInteraction();
        ModelledParticipant participant = new XmlModelledParticipant(complex);
        interaction.addParticipant(participant);

        writer.start();
        writer.write(interaction);
        writer.end();
        writer.close();

        Assert.assertEquals(this.interaction_complexes, stringWriter.toString());
    }

    @Test
    public void test_single_interaction_complexes_as_Interactor() throws XMLStreamException {
        StringWriter stringWriter = new StringWriter();

        Complex complex = new DefaultXmlComplex("test complex");
        complex.getParticipants().add(new XmlModelledParticipant(new XmlProtein("test protein")));

        ExpandedXmlModelledBinaryWriter writer = new ExpandedXmlModelledBinaryWriter(PsiXmlVersion.v2_5_4, stringWriter);
        writer.setWriteComplexesAsInteractors(true);

        ModelledBinaryInteraction interaction = new XmlModelledBinaryInteraction();
        ModelledParticipant participant = new XmlModelledParticipant(complex);
        interaction.addParticipant(participant);

        writer.start();
        writer.write(interaction);
        writer.end();
        writer.close();

        Assert.assertEquals(this.interaction_complexes_as_interactor, stringWriter.toString());
    }

    @Test
    public void test_interactions_different_entries1() throws XMLStreamException {
        StringWriter stringWriter = new StringWriter();

        ExpandedXmlModelledBinaryWriter writer = new ExpandedXmlModelledBinaryWriter(PsiXmlVersion.v2_5_4, stringWriter);
        ModelledBinaryInteraction interaction = new XmlModelledBinaryInteraction();
        ModelledParticipant participant = new XmlModelledParticipant(new XmlProtein("protein test"));
        interaction.addParticipant(participant);

        writer.start();
        writer.write(interaction);
        writer.write(interaction);
        writer.end();
        writer.close();

        Assert.assertEquals(this.interaction_different_entries1, stringWriter.toString());
    }

    @Test
    public void test_interactions_different_entries2() throws XMLStreamException {
        StringWriter stringWriter = new StringWriter();

        ExpandedXmlModelledBinaryWriter writer = new ExpandedXmlModelledBinaryWriter(PsiXmlVersion.v2_5_4, stringWriter);

        ModelledBinaryInteraction interaction = new XmlModelledBinaryInteraction();
        ModelledParticipant participant = new XmlModelledParticipant(new XmlProtein("protein test"));
        interaction.addParticipant(participant);
        ModelledBinaryInteraction interaction2 = new XmlModelledBinaryInteraction();
        ModelledParticipant participant2 = new XmlModelledParticipant(new XmlProtein("protein test2"));
        interaction2.addParticipant(participant2);

        writer.start();
        writer.write(Arrays.asList(interaction, interaction2));
        writer.write(Arrays.asList(interaction, interaction2));
        writer.end();
        writer.close();

        Assert.assertEquals(this.interaction_different_entries2, stringWriter.toString());
    }

    @Test
    public void test_interactions_different_entries3() throws XMLStreamException {
        StringWriter stringWriter = new StringWriter();

        ExpandedXmlModelledBinaryWriter writer = new ExpandedXmlModelledBinaryWriter(PsiXmlVersion.v2_5_4, stringWriter);

        ModelledBinaryInteraction interaction = new XmlModelledBinaryInteraction();
        ModelledParticipant participant = new XmlModelledParticipant(new XmlProtein("protein test"));
        interaction.addParticipant(participant);
        ModelledBinaryInteraction interaction2 = new XmlModelledBinaryInteraction();
        ModelledParticipant participant2 = new XmlModelledParticipant(new XmlProtein("protein test2"));
        interaction2.addParticipant(participant2);

        writer.start();
        writer.write(Arrays.asList(interaction, interaction2).iterator());
        writer.write(Arrays.asList(interaction, interaction2).iterator());
        writer.end();
        writer.close();

        Assert.assertEquals(this.interaction_different_entries2, stringWriter.toString());
    }

    @Test
    public void test_interaction_source() throws XMLStreamException {
        StringWriter stringWriter = new StringWriter();

        ExpandedXmlModelledBinaryWriter writer = new ExpandedXmlModelledBinaryWriter(PsiXmlVersion.v2_5_4, stringWriter);
        ModelledBinaryInteraction interaction = new XmlModelledBinaryInteraction();
        ModelledParticipant participant = new XmlModelledParticipant(new XmlProtein("protein test"));
        interaction.addParticipant(participant);
        Source source = new DefaultXmlSource("intact");
        interaction.setSource(source);
        try {
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            writer.setDefaultReleaseDate(datatypeFactory.newXMLGregorianCalendar("2013-09-02+00:00"));

        } catch (DatatypeConfigurationException e) {
            System.out.println(e);
        }

        writer.start();
        writer.write(interaction);
        writer.end();
        writer.close();
        Assert.assertEquals(this.interaction_source, stringWriter.toString());
    }

    @Test
    public void test_interactions_different_sources() throws XMLStreamException {
        StringWriter stringWriter = new StringWriter();

        ExpandedXmlModelledBinaryWriter writer = new ExpandedXmlModelledBinaryWriter(PsiXmlVersion.v2_5_4, stringWriter);

        ModelledBinaryInteraction interaction = new XmlModelledBinaryInteraction();
        ModelledParticipant participant = new XmlModelledParticipant(new XmlProtein("protein test"));
        interaction.addParticipant(participant);
        ModelledBinaryInteraction interaction2 = new XmlModelledBinaryInteraction();
        ModelledParticipant participant2 = new XmlModelledParticipant(new XmlProtein("protein test2"));
        interaction2.addParticipant(participant2);
        Source source = new DefaultXmlSource("mint");
        interaction.setSource(source);
        interaction2.setSource(source);
        ModelledBinaryInteraction interaction3 = new XmlModelledBinaryInteraction();
        interaction3.addParticipant(participant);
        ModelledBinaryInteraction interaction4 = new XmlModelledBinaryInteraction();
        interaction4.addParticipant(participant2);
        Source source2 = new DefaultXmlSource("intact");
        interaction3.setSource(source2);
        interaction4.setSource(source2);
        try {
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            writer.setDefaultReleaseDate(datatypeFactory.newXMLGregorianCalendar("2013-09-02+00:00"));

        } catch (DatatypeConfigurationException e) {
            System.out.println(e);
        }

        writer.start();
        writer.write(Arrays.asList(interaction, interaction2, interaction3, interaction4));
        writer.end();
        writer.close();

        Assert.assertEquals(this.interaction_different_source, stringWriter.toString());
    }

    @Test
    public void test_single_interaction_cooperative() throws XMLStreamException {
        StringWriter stringWriter = new StringWriter();

        Complex complex = new DefaultXmlComplex("test complex");
        complex.getParticipants().add(new XmlModelledParticipant(new XmlProtein("test protein")));

        ExpandedXmlModelledBinaryWriter writer = new ExpandedXmlModelledBinaryWriter(PsiXmlVersion.v2_5_4, stringWriter);
        ModelledBinaryInteraction interaction = new XmlModelledBinaryInteraction();
        ModelledParticipant participant = new XmlModelledParticipant(complex.getParticipants().iterator().next().getInteractor());
        interaction.addParticipant(participant);
        Preassembly assembly = new DefaultPreassemby(CvTermUtils.createMICvTerm("positive cooperative effect", "MI:1154"));
        assembly.setResponse(CvTermUtils.createMICvTerm("configurational pre-organization", "MI:1174"));
        assembly.getAffectedInteractions().add(complex);
        interaction.getCooperativeEffects().add(assembly);

        writer.start();
        writer.write(interaction);
        writer.end();
        writer.close();

        Assert.assertEquals(this.interaction_cooperative, stringWriter.toString());
    }
}
