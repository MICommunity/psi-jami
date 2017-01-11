# JAMI-InteractionViewer-JSON

This JAMI module writes a JavaScript Objection Notation (JSON) representation of PSI-MI data.

For use when a concise format containing all the detail is required, for example in a web client. In this case the PSI-MI XML files are too large to transfer in a reasonable amount of time, whilst the MI-TAB format removes most of the detail.

Two versions of the JSON writer are provided.  One outputs binary interactions only, expanding n-ary interaction into binary interactions. The other outputs n-ary interactions directly.

### Audience

Web developers, or those who want PSI-MI data in a concise format containing all the detail.

### Examples

[https://github.com/MICommunity/psi-jami/blob/master/jami-examples/src/main/java/psidev/psi/mi/jami/examples/interactionviewer/ReadWriteJsonWithJami.java](https://github.com/MICommunity/psi-jami/blob/master/jami-examples/src/main/java/psidev/psi/mi/jami/examples/interactionviewer/ReadWriteJsonWithJami.java)

[https://github.com/intermine/intermine/blob/dev/bio/webapp/src/org/intermine/webservice/server/complexes/ExportService.java](https://github.com/intermine/intermine/blob/dev/bio/webapp/src/org/intermine/webservice/server/complexes/ExportService.java)
