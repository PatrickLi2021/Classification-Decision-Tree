package sol;

/**
 * This class allows us to instantiate Edge objects, which connect nodes in
 * the tree to leaves. Each Edge object has access to an attributeValue
 * field (represented as a String), and a treeItem field which represents the
 * item in the tree that the edge points to
 */

public class Edge {

    private String attributeValue;
    private ITreeNode treeItem;

    /**
     * Constructor for an Edge object
     *
     * @param attributeValue - The particular attribute value that an edge
     *                       in the decision tree stores
     * @param treeItem       - The tree item (either leaf or node) that the Edge
     *                       object points to
     */

    public Edge(String attributeValue, ITreeNode treeItem) {
        this.attributeValue = attributeValue;
        this.treeItem = treeItem;
    }

    /**
     * Returns the attribute value for a particular Edge object
     *
     * @return a String representing the attribute value for an edge
     */

    public String getAttributeValue() {
        return this.attributeValue;
    }

    /**
     * Returns the tree item that a particular Edge object is pointing to
     *
     * @return an ITreeNode object representing the tree item that the edge
     * is pointing to
     */

    public ITreeNode getTreeItem() {
        return this.treeItem;
    }
}