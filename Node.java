package sol;

import src.Row;

import java.util.List;

/**
 * The Node class allows us to create Node objects which represent certain
 * attributes where a tree splits. Each of which contains a default value, an
 * attribute, and a list of branches that extend from it.
 */

public class Node implements ITreeNode {

    private String defaultValue;
    private String attribute;
    private List<Edge> listOfBranches;

    /**
     * Constructor for a Node object
     *
     * @param defaultValue   - The default value that stores the result for a
     *                       new attribute value
     * @param attribute      - A particular attribute in the dataset where the
     *                       tree will split
     * @param listOfBranches - A list of Branch objects representing the
     *                       edges that wil extend from the node
     */

    public Node(String attribute, String defaultValue,
                List<Edge> listOfBranches) {
        this.attribute = attribute;
        this.defaultValue = defaultValue;
        this.listOfBranches = listOfBranches;
    }

    /**
     * Returns the final decision for an input Row based on a specific target
     * attribute
     *
     * @param forDatum the datum to look up a decision for
     * @return a String representation of the final decision retrieved for
     * the input row
     */

    @Override
    public String getDecision(Row forDatum) {
        // Finding the input datum's value for the attribute at this Node
        String datumAttributeValue = forDatum.getAttributeValue(this.attribute);

        /* Loop through the Node's list of edges to see if the datum's
           attribute value matches one of the edges */
        for (int i = 0; i < this.listOfBranches.size(); i++) {
            /* If the datum attribute value matches, then we recurse down
               that branch and call getDecision on the subsequent tree item */
            if (this.listOfBranches.get(i).getAttributeValue().
                    equals(datumAttributeValue)) {
                return this.listOfBranches.get(i).getTreeItem().
                        getDecision(forDatum);
            }
        }

        /* If the datum's attribute value doesn't match any branch values,
           return the default value */
        return this.defaultValue;
    }
}