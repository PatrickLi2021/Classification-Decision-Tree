package sol;

import src.Row;

/**
 * The Leaf class allows us to create Leaf objects, each of which has access
 * to a target attribute value field represented as a String.
 */

public class Leaf implements ITreeNode {

    private String targetAttributeValue;

    /**
     * Constructor for the Leaf class
     *
     * @param targetAttributeValue - The target attribute value that is
     *                             stored in the Leaf object
     */

    public Leaf(String targetAttributeValue) {
        this.targetAttributeValue = targetAttributeValue;
    }

    /**
     * Returns the final decision for an input Row based on a specific target
     * attribute
     *
     * @param forDatum the datum to look up a decision for
     * @return a String representation of the final decision retrieved for
     * the input row (in this case, the leaf just returns its target
     * attribute value)
     */

    @Override
    public String getDecision(Row forDatum) {
        return this.targetAttributeValue;
    }
}