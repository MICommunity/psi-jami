# JAMI-CrossLink-CSV

This JAMI module reads CSV files containing data from Cross-Linking / Mass Spectrometry (CLMS) experiments.

This allows CLMS data to be translated into PSI-MI XML. Manually creating PSI-MI XML for CLMS data is difficult/tedious.

### CSV File format

*   **Protein identifiers** are assumed to be six character [UniprotKB](http://www.uniprot.org/manual/accession_numbers) accession numbers. SwissProt style identifiers of the format: `sp|accession|name` are also accepted.

*   Column names are required as the first line of the CSV file.
*   Column names are case-sensitive.
*   The order of the columns is unspecified.


COLUMN NAME|REQUIRED?|NOTES
------------ | ------------ | --------------------------------------
Protein1|Yes|Identifier for protein 1
PepPos1|No|One-based residue number for peptide 1 start position in protein 1.
PepSeq1|No|Sequence for peptide 1,  lowercase characters ignored.
LinkPos1|If PepSeq1 is present|One-based residue number for linkage site in peptide 1, or absolute position for  link in Protein 1 if peptide position is ommitted.
Protein2|See note|Identifier for protein 2. This value is omitted for a linker modified peptide or an internally cross-linked peptide.
PepPos2|No|One-based residue number for peptide 2 start position in protein 2.
PepSeq2|No|Sequence for peptide 2,  lowercase characters ignored.
LinkPos2|If PepSeq2 is present (also see note)|One-based residue number for linkage site in peptide 2, or absolute position for link in Protein 2 if peptide position is ommitted. Ommitted for linker modified peptides (mono-links).

### Audience

Those needing to curate CLMS data should find this useful. 

### Examples

[https://github.com/MICommunity/psi-jami/blob/master/jami-examples/src/main/java/psidev/psi/mi/jami/examples/crosslinkCSV/ReadWriteCrosslinkCsvWithJami.java](https://github.com/MICommunity/psi-jami/blob/master/jami-examples/src/main/java/psidev/psi/mi/jami/examples/crosslinkCSV/ReadWriteCrosslinkCsvWithJami.java)

