/*
 * Copyright 2001-2007 The European Bioinformatics Institute.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package psidev.psi.mi.jami.bridges.uniprot.rest;

/**
 * TODO: Work in progress to adapt the databases
 */
public enum SearchDatabase {

    EMBL("EMBL"),
    EMBLWGS("EMBLWGS"),
    EMBL_ANNCON("EMBL_ANNCON"),
    EMBL_TPA("EMBL_TPA"),
//    NO_BEFORE     EMBL_TSA("EMBL_TSA"),
    ENSEMBL("Ensembl"),
//    ENSEMBL_ARMADILLO(""),
//    ENSEMBL_BUSHBABY(""),
//    ENSEMBL_CAT(""),
//    ENSEMBL_CBRIGGSAE(""),
//    ENSEMBL_CELEGANS(""),
//    ENSEMBL_CHICKEN(""),
//    ENSEMBL_CHIMP(""),
//    ENSEMBL_CIONA(""),
//    ENSEMBL_COMMON_SHREW(""),
//    ENSEMBL_COW(""),
//    ENSEMBL_DOG(""),
//    ENSEMBL_ELEPHANT(""),
//    ENSEMBL_ERINACEUS(""),
//    ENSEMBL_FLY(""),
//    ENSEMBL_FUGU(""),
//    ENSEMBL_GUINEA_PIG(""),
//    ENSEMBL_HEDGEHOG(""),
//    ENSEMBL_HONEYBEE(""),
//    ENSEMBL_HUMAN(""),
//    ENSEMBL_MEDAKA(""),
//    ENSEMBL_MICROBAT(""),
//    ENSEMBL_MOSQUITO(""),
//    ENSEMBL_MOUSE(""),
//    ENSEMBL_OPOSSUM(""),
//    ENSEMBL_PLATYPUS(""),
//    ENSEMBL_RABBIT(""),
//    ENSEMBL_RAT(""),
//    ENSEMBL_RHESUS_MACAQUE(""),
//    ENSEMBL_SQUIRREL(""),
//    ENSEMBL_STICKLEBACK(""),
//    ENSEMBL_TETRAODON(""),
//    ENSEMBL_TREE_SHREW(""),
//    ENSEMBL_XENOPUS(""),
//    ENSEMBL_YF_MOSQUITO(""),
//    ENSEMBL_ZEBRAFISH(""),
    EPO("EPO"),
    FLYBASE("FlyBase"),
    H_INV("H-InvDB"),
    IPI("IPI"),
    JPO("JPO"),
    PDB("PDB"),
    PIR("PIR"),
    PIRARC("PIRARC"),
    PRF("PRF"),
    REFSEQ("RefSeq"),
//    REFSEQ_HUMAN(""),
//    REFSEQ_MOUSE(""),
//    REFSEQ_RAT(""),
//    REFSEQ_ZEBRAFISH(""),
//    REMTREMBL("REMTREMBL"),
    SGD("SGD"),
    SWISSPROT("UniProtKB/Swiss-Prot"),
//    SWISSPROT_VARSPLIC("UniProtKB protein isoforms"),
    SWISSPROT_VARSPLIC("UniProtKB/Swiss-Prot protein isoforms"),
    TAIR_ARABIDOPSIS("TAIR"),
    TREMBL("UniProtKB/TrEMBL"),
//    NO_BEFORE TREMBLNEW("TREMBLNEW"),
//    TREMBL_VARSPLIC(""),
//    NO_IN_PICR TROME("TROME"),
//    TROME_CE(""),
//    TROME_DM(""),
//    TROME_HS(""),
//    TROME_MM(""),
    UNIMES("UNIMES"),
    USPTO("USPTO");
//    NO_BEFORE   VEGA("VEGA"),
//    VEGA_DOG(""),
//    VEGA_HUMAN(""),
//    VEGA_MOUSE(""),
//    VEGA_ZEBRAFISH(""),
//    WORMBASE("");


    private String dbName;

    private SearchDatabase(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    public static String toEnumString(String dbName) {
        for (SearchDatabase searchDatabase : SearchDatabase.values()) {
            if (searchDatabase.getDbName().equalsIgnoreCase(dbName)) {
                return searchDatabase.toString();
            }
        }

        return null;
    }

}