package sol;

import src.ITreeGenerator;
import src.Row;

import java.util.ArrayList;

import java.util.List;

import java.util.Random;


/**
 * A class that implements the ITreeGenerator interface
 * used to generate a tree
 */

public class TreeGenerator implements ITreeGenerator<Dataset> {

    private String targetAttribute;
    private ITreeNode tree;

    public TreeGenerator() {
    }

    /**
     * Generates the tree from the input dataset and target attribute and
     * then stores it in the tree field
     *
     * @param data            - The dataset containing rows used to construct the tree
     * @param targetAttribute the attribute to predict
     * @throws RuntimeException if the input dataset is empty
     */

    @Override
    public void generateTree(Dataset data, String targetAttribute) {
        this.targetAttribute = targetAttribute;
        List<String> listOfAttributes = data.getAttributeList();
        listOfAttributes.remove(this.targetAttribute);
        if (data.getDataObjects().isEmpty())
            throw new RuntimeException("Dataset is empty");
        else
            this.tree = this.buildTree(data, listOfAttributes);
    }

    /**
     * Returns a String representation of the random attribute to split on
     *
     * @param listOfAttributes A list of Strings containing the attributes of
     *                         the given dataset
     * @return a String that represents an attribute to split on chosen at
     * random
     */

    public String getRandomAttribute(List<String> listOfAttributes) {
        Random random = new Random();
        int upperBound = listOfAttributes.size();
        int randomNum = random.nextInt(upperBound);
        String randomAttribute = listOfAttributes.get(randomNum);
        return randomAttribute;
    }

    /**
     * Returns an ITreeNode object that represents the entire decision tree
     * consisting of nodes, branches/edges, and leaves
     *
     * @param dataset - A Dataset object representing the data needed to
     *                initially construct the tree before a decision is made
     * @return an ITreeNode object that represents the full decision tree
     * @throws RuntimeException if the input dataset is empty (i.e. contains
     *                          no rows)
     */

    public ITreeNode buildTree(Dataset dataset, List<String> listOfAttributes) {

        // BASE CASE - Input dataset is empty
        if (dataset.getDataObjects().isEmpty()) {
            throw new RuntimeException("No data found");
        }

        // BASE CASE - All values for the target attribute are the same
        else if (dataset.checkTargetAttributeValue(dataset,
                this.targetAttribute)) {
            String leafAttribute = dataset.getDataObjects().get(0).
                    getAttributeValue(this.targetAttribute);
            return new Leaf(leafAttribute);
        }

        // BASE CASE - List of attributes for the input dataset is empty
        else if (dataset.getAttributeList().isEmpty()) {
            String leafAttribute = dataset.findDefault(dataset.
                    findListOfAttributeValues(dataset, this.targetAttribute));
            Leaf leafNode = new Leaf(leafAttribute);
            return leafNode;
        }

        // RECURSIVE CALL
        else {
            // Getting a random attribute to split on AND finding default
            String currentAttribute = this.getRandomAttribute(listOfAttributes);
            List<String> removedList = new ArrayList<>();
            removedList.add(currentAttribute);
            List<String> defaultValueList = new ArrayList<>();
            String defaultValue = dataset.findDefault(dataset.
                    findListOfAttributeValues(dataset, this.targetAttribute));
            defaultValueList.add(defaultValue);
            // Creating the partitioned list of datasets, organized by
            // specific attribute value (i.e. orange, green, yellow, etc.
            List<Dataset> partitionedData = dataset.partition(currentAttribute);
            // Creating a new list of branches to store these attribute values
            List<Edge> listOfBranches = new ArrayList<>();
            for (Dataset currentDataset : partitionedData) {
                // Finding list of rows for current dataset
                List<Row> currentListOfRows = currentDataset.getDataObjects();
              /* Finding the attribute value that these rows share in
                 common (i.e. all orange, all green, all yellow, etc.) */
                String attributeValue = currentListOfRows.get(0).
                        getAttributeValue(currentAttribute);
              /* Adding branches w/ a particular attribute value to the list
                 of branches */
                listOfBranches.add(new Edge(attributeValue, this.
                        buildTree(currentDataset, listOfAttributes)));
            }
            // Creating a new Node for this attribute w/ a list of edges
            Node n = new Node(removedList.get(0), defaultValueList.get(0),
                    listOfBranches);
            return n;
        }
    }

    /**
     * Returns the final decision for an input Row based on a specific target
     * attribute
     *
     * @param datum the datum to look up a decision for
     * @return a String representation of the final decision retrieved for
     * the input row
     */

    @Override
    public String getDecision(Row datum) {
        return this.tree.getDecision(datum);
    }
}