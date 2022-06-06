package sol;

import src.IDataset;
import src.Row;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * A class that implements the IDataset interface,
 * representing a training data set.
 */

public class Dataset implements IDataset {
    private List<Row> dataObjects;
    private List<String> attributeNames;

    public Dataset(List<String> attributeNames, List<Row> dataObjects) {
        this.dataObjects = dataObjects;
        this.attributeNames = attributeNames;
    }

    /**
     * Gets list of attributes in the dataset
     *
     * @return a list of strings
     */

    public List<String> getAttributeList() {
        return this.attributeNames;
    }

    /**
     * Gets list of data objects (row) in the dataset
     *
     * @return a list of Rows
     */

    public List<Row> getDataObjects() {
        return this.dataObjects;
    }

    /**
     * finds the size of the dataset (number of rows)
     *
     * @return the number of rows in the dataset
     */

    public int size() {
        return this.dataObjects.size();
    }

    /**
     * Returns a String that is the most common occurrence in a list of
     * attribute values
     *
     * @param listOfAttributeValues A list of Strings containing values of a
     *                              certain attribute
     * @return the String in the original list that occurs most frequently
     */

    public String findDefault(List<String> listOfAttributeValues) {
        if (listOfAttributeValues.size() == 0) {
            return "leaf";
        }
        int maximumCount = 1;
        String mostFrequentElement = listOfAttributeValues.get(0);
        int currentCount = 1;

        for (int i = 1; i < listOfAttributeValues.size(); i++) {
            if (listOfAttributeValues.get(i).equals(listOfAttributeValues.
                    get(i - 1)))
                currentCount++;
            else {
                if (currentCount > maximumCount) {
                    maximumCount = currentCount;
                    mostFrequentElement = listOfAttributeValues.get(i - 1);
                }
                currentCount = 1;
            }
        }
        // If last element is most frequent
        if (currentCount > maximumCount) {
            mostFrequentElement = listOfAttributeValues.
                    get(listOfAttributeValues.size() - 1);
        }
        return mostFrequentElement;
    }

    /**
     * Returns a list of Strings that only contains unique values (i.e. all
     * duplicate values are removed)
     *
     * @param attributeList - A list of Strings containing the different
     *                      values for a particular attribute
     * @return A list of Strings that contains only unique values for a
     * particular attribute
     */

    public List<String> removeDuplicates(List<String> attributeList) {
        ArrayList<String> uniqueAttributes = new ArrayList<>();
        for (String attribute : attributeList) {
            if (!uniqueAttributes.contains(attribute)) {
                uniqueAttributes.add(attribute);
            }
        }
        return uniqueAttributes;
    }

    /**
     * Returns a list of attribute values for a specific attribute (i.e. if
     * the attribute is color, returns instances of orange, green, yellow, etc.)
     *
     * @param dataset   - A Dataset object that contains Row objects with
     *                  various attribute values
     * @param attribute - A String representing a particular attribute in the
     *                  dataset
     * @return a list of attribute values for the input attribute
     */

    public List<String> findListOfAttributeValues(Dataset dataset,
                                                  String attribute) {
        List<String> targetAttributeList = new ArrayList();
        List<Row> listOfRowObjects = dataset.getDataObjects();
        for (int i = 0; i < listOfRowObjects.size(); i++) {
            targetAttributeList.add(listOfRowObjects.get(i).
                    getAttributeValue(attribute));
        }
        return targetAttributeList;
    }


    /**
     * Returns a list of Datasets where the Rows in each Dataset in the
     * list contain the same value for the input attribute (i.e. creates
     * individual datasets containing orange, green, and yellow food items)
     *
     * @param attribute - The specific attribute that we're partitioning
     * @return A list of Datasets where each Dataset in a list contains the
     * same values for a particular attribute
     */

    public List<Dataset> partition(String attribute) {
        List<Dataset> partitionedData = new ArrayList<Dataset>();
        // stores the list of datasets that this method returns; empty to begin
        List<String> uniqueAttributeValues =
                this.removeDuplicates(this.
                        findListOfAttributeValues(this, attribute));
        // list of the different attribute values for the input attribute
        for (int i = 0; i < uniqueAttributeValues.size(); i++) {
            partitionedData.add(this.filterDataset(this, attribute,
                    uniqueAttributeValues.get(i)));
        }
        List<String> attributeNamesCopy = new ArrayList<>();
        attributeNamesCopy.addAll(this.getAttributeList());
        attributeNamesCopy.remove(attribute);
        return partitionedData;
    }


    /**
     * Returns a filtered dataset removing rows that don't contain that
     * particular attribute value
     *
     * @param dataset        - The current dataset at that point in the tree
     *                       traversal/creation
     * @param attribute      - The splitting attribute at that point in the tree
     * @param attributeValue - The specific attribute value that you want to
     *                       continue going down the tree with
     * @return A new subset of the original dataset with rows removed
     */

    public Dataset filterDataset(Dataset dataset, String attribute,
                                 String attributeValue) {
        List<Row> subset = new ArrayList<Row>();
        List<String> attributeNamesCopy = new ArrayList<>();
        for (int i = 0; i < dataset.getDataObjects().size(); i++) {
            Row currentRow = dataset.getDataObjects().get(i);
            if (currentRow.getAttributeValue(attribute).equals(attributeValue))
                //adding rows containing that particular attribute value to a
                // new list
                subset.add(currentRow);
        }
        attributeNamesCopy.addAll(dataset.getAttributeList());
        attributeNamesCopy.remove(attribute);
        return new Dataset(attributeNamesCopy, subset);
    }

    /**
     * Checks whether all the Row objects in the input dataset have the same
     * target attribute value
     *
     * @param dataset   - The current dataset that is being used in the tree
     * @param attribute - The attribute whose values will be checked
     * @return a boolean, true, if all rows have the same target attribute
     * value, and false otherwise
     */

    public boolean checkTargetAttributeValue(Dataset dataset,
                                             String attribute) {
        List<Row> rowsInDataset = dataset.getDataObjects();
        for (int i = 0; i < rowsInDataset.size() - 1; i++) {
            if (!rowsInDataset.get(i).getAttributeValue(attribute).
                    equals(rowsInDataset.get(i + 1).
                            getAttributeValue(attribute)))
                return false;
        }
        return true;
    }
}