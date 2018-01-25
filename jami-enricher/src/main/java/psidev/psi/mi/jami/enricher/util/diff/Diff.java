/**
 * Copyright 2008 The European Bioinformatics Institute, and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package psidev.psi.mi.jami.enricher.util.diff;

/**
 * Extension of diff_match_patch.Diff
 *
 * @author Bruno Aranda (baranda@ebi.ac.uk)
 * @version $Id: Diff.java 11644 2008-06-23 16:36:27Z baranda $
 */
public class Diff extends diff_match_patch.Diff {

    private int indexInString1;
    private int indexInString2;

    /**
     * <p>Constructor for Diff.</p>
     *
     * @param diff a {@link psidev.psi.mi.jami.enricher.util.diff.diff_match_patch.Diff} object.
     * @param indexInString1 a int.
     * @param indexInString2 a int.
     */
    protected Diff(diff_match_patch.Diff diff, int indexInString1, int indexInString2) {
        super(diff.operation, diff.text);
        this.indexInString1 = indexInString1;
        this.indexInString2 = indexInString2;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "["+operation+"] '"+text+"'"+" idxA:"+ indexInString1 +", idxB:"+ indexInString2;
    }

    /**
     * <p>getOperation.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.util.diff.Operation} object.
     */
    public Operation getOperation() {
        return Operation.valueOfDiffOperation(operation);
    }

    /**
     * <p>getText.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getText() {
        return text;
    }

    /**
     * <p>Getter for the field <code>indexInString1</code>.</p>
     *
     * @return a int.
     */
    public int getIndexInString1() {
        return indexInString1;
    }

    /**
     * <p>Getter for the field <code>indexInString2</code>.</p>
     *
     * @return a int.
     */
    public int getIndexInString2() {
        return indexInString2;
    }
}

