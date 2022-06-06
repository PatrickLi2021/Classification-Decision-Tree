package sol;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.Row;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DecisionTreeTest {

    // Constructor for DecisionTreeTest tester class
    public DecisionTreeTest() {
    }

    // Instantiating Row objects to be used in unit testing
    Row spinach = new Row("spinach");
    Row orange = new Row("orange");
    Row cucumber = new Row("cucumber");
    Row carrot = new Row("carrot");
    Row sweetPotato = new Row("sweet potato");
    Row avocado = new Row("avocado");
    Row banana = new Row("banana");
    Dataset foodData;
    List<Row> foodRow;
    List<Row> emptyRows = new ArrayList<>();

    // Creating sample dataset to be used for unit testing
    @Before
    public void setupData() {

        // Setting attribute values for each Row objects
        this.spinach.setAttributeValue("name", "spinach");
        this.spinach.setAttributeValue("color", "green");
        this.spinach.setAttributeValue("highProtein", "true");
        this.spinach.setAttributeValue("calories", "low");
        this.spinach.setAttributeValue("foodType", "vegetable");

        this.orange.setAttributeValue("name", "orange");
        this.orange.setAttributeValue("color", "orange");
        this.orange.setAttributeValue("highProtein", "false");
        this.orange.setAttributeValue("calories", "high");
        this.orange.setAttributeValue("foodType", "fruit");

        this.cucumber.setAttributeValue("name", "cucumber");
        this.cucumber.setAttributeValue("color", "green");
        this.cucumber.setAttributeValue("highProtein", "false");
        this.cucumber.setAttributeValue("calories", "low");
        this.cucumber.setAttributeValue("foodType", "vegetable");

        this.carrot.setAttributeValue("name", "carrot");
        this.carrot.setAttributeValue("color", "orange");
        this.carrot.setAttributeValue("highProtein", "false");
        this.carrot.setAttributeValue("calories", "medium");
        this.carrot.setAttributeValue("foodType", "vegetable");

        this.sweetPotato.setAttributeValue("name", "sweetPotato");
        this.sweetPotato.setAttributeValue("color", "orange");
        this.sweetPotato.setAttributeValue("highProtein", "true");
        this.sweetPotato.setAttributeValue("calories", "high");
        this.sweetPotato.setAttributeValue("foodType", "vegetable");

        this.avocado.setAttributeValue("name", "avocado");
        this.avocado.setAttributeValue("color", "green");
        this.avocado.setAttributeValue("highProtein", "true");
        this.avocado.setAttributeValue("calories", "high");
        this.avocado.setAttributeValue("foodType", "fruit");

        this.banana.setAttributeValue("name", "banana");
        this.banana.setAttributeValue("color", "yellow");
        this.banana.setAttributeValue("highProtein", "true");
        this.banana.setAttributeValue("calories", "high");
        this.banana.setAttributeValue("foodType", "fruit");

        // Creating dataObjects for this dataset
        List<Row> listOfRows = new ArrayList<>();
        listOfRows.add(this.spinach);
        listOfRows.add(this.orange);
        listOfRows.add(this.cucumber);
        listOfRows.add(this.carrot);
        listOfRows.add(this.sweetPotato);
        listOfRows.add(this.avocado);
        listOfRows.add(this.banana);
        this.foodRow = listOfRows;

        // Creating a list of attributes for this dataset
        List<String> attributes = new ArrayList<>();
        attributes.add("name");
        attributes.add("color");
        attributes.add("highProtein");
        attributes.add("calories");
        attributes.add("foodType");

        this.foodData = new Dataset(attributes, listOfRows);
    }

    // Testing getAttributeList() on the sample dataset
    @Test
    public void testGetAttributeList() {
        System.out.println(foodData.getAttributeList());
        // Should print out [name, color, highProtein, calories, foodType]
    }

    // Testing getDataObjects on the sample dataset
    @Test
    public void testGetDataObjects() {
        System.out.println(foodData.getDataObjects());
        // Should print out a list containing 7 Row objects with different
        // memory addresses
    }

    // Testing if size of list of rows in dataset is correct
    @Test
    public void testSize() {
        assertEquals(foodData.size(), 7);
    }

    // Finding default value in the following list of different attribute values
    @Test
    public void testFindDefault1() {
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("A");
        list1.add("A");
        list1.add("B");
        list1.add("C");
        assertEquals(foodData.findDefault(list1), "A");
    }

    // Finding default value when all attribute values in list are the same
    @Test
    public void testFindDefault2() {
        List<String> list2 = new ArrayList<>();
        list2.add("vegetable");
        list2.add("vegetable");
        list2.add("vegetable");
        list2.add("vegetable");
        list2.add("vegetable");
        assertEquals(foodData.findDefault(list2), "vegetable");
    }

    // Finding default value when there is only one attribute value in the list
    @Test
    public void testFindDefault3() {
        List<String> list3 = new ArrayList<>();
        list3.add("accepted");
        assertEquals(foodData.findDefault(list3), "accepted");
    }

    // Testing findDefault() on a list where no attribute has the majority
    @Test
    public void testFindDefault4() {
        List<String> list4 = new ArrayList<>();
        list4.add("rejected");
        list4.add("deferred");
        list4.add("waitlisted");
        list4.add("accepted");
        list4.add("accepted");
        assertEquals(foodData.findDefault(list4), "accepted");
    }

    // Testing removeDuplicates() on a list of different target attributes
    @Test
    public void testRemoveDuplicates1() {
        List<String> list5 = new ArrayList<>();
        list5.add("fruit");
        list5.add("fruit");
        list5.add("vegetable");
        list5.add("vegetable");
        list5.add("meat");
        System.out.println(foodData.removeDuplicates(list5));
        // Should print out [fruit, vegetable, meat]
    }

    // Testing removeDuplicates() on a list where all attribute values are
    // the same
    @Test
    public void testRemoveDuplicates2() {
        List<String> list6 = new ArrayList<>();
        list6.add("poisonous");
        list6.add("poisonous");
        list6.add("poisonous");
        list6.add("poisonous");
        System.out.println(foodData.removeDuplicates(list6));
        // Should print out [poisonous]
    }

    // Testing removeDuplicates on a list containing only one attribute value
    @Test
    public void testRemoveDuplicates3() {
        List<String> list7 = new ArrayList<>();
        list7.add("rock");
        System.out.println(foodData.removeDuplicates(list7));
        // Should print out [rock]
    }

    // Testing if list of attribute values of dataset is printed correctly
    // for foodType
    @Test
    public void testFindListOfAttributeValues1() {
        List<String> attributeValues1 =
                foodData.findListOfAttributeValues(foodData, "foodType");
        System.out.println(attributeValues1);
        /* Should print out [vegetable, fruit, vegetable, vegetable,
           vegetable, fruit, fruit, fruit] */
    }

    // Testing if list of attribute values of dataset is printed correctly
    // for highProtein
    @Test
    public void testFindListOfAttributeValues2() {
        List<String> attributeValues2 =
                foodData.findListOfAttributeValues(foodData, "highProtein");
        System.out.println(attributeValues2);
        /* Should print out [true, false, false, false, true, true, true] */
    }

    // Testing if all Row objects in food dataset have the same attribute
    // value for calories
    @Test
    public void testCheckTargetAttributeValue1() {
        Assert.assertFalse(foodData.checkTargetAttributeValue(foodData,
                "calories"));
    }

    // Testing if all Row objects in student dataset have the same attribute
    // for name
    @Test
    public void testCheckTargetAttributeValue2() {
        Row student1 = new Row("student1");
        Row student2 = new Row("student2");
        Row student3 = new Row("student3");
        student1.setAttributeValue("name", "John");
        student2.setAttributeValue("name", "John");
        student3.setAttributeValue("name", "John");
        List<Row> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        List<String> stringList = new ArrayList<>();
        stringList.add("name");
        Dataset studentData = new Dataset(stringList, studentList);
        Assert.assertTrue(studentData.checkTargetAttributeValue(studentData,
                "name"));
    }

    // Testing to see if all non-orange items are filtered from the dataset
    @Test
    public void testFilterDataset1() {
        Dataset subset = foodData.filterDataset(foodData, "color", "orange");
        List<Row> orangeItems = subset.getDataObjects();
        for (Row row : orangeItems) {
            System.out.println(row.getAttributeValue("name"));
            assertEquals(row.getAttributeValue("color"), "orange");
        }
        // orange and carrot should be printed out
    }

    // Testing to see if all high protein items are filtered from the dataset
    @Test
    public void testFilterDataset2() {
        Dataset subset2 = foodData.filterDataset(foodData, "highProtein",
                "false");
        List<Row> nonHighProteinItems = subset2.getDataObjects();
        for (Row row : nonHighProteinItems) {
            System.out.println(row.getAttributeValue("name"));
            assertEquals(row.getAttributeValue("highProtein"), "false");
        }
        // orange, cucumber, and carrot should be printed out
    }

    // Testing to see if all low or medium calorie items are filtered from
    // the dataset
    @Test
    public void testFilterDataset3() {
        Dataset subset3 = foodData.filterDataset(foodData, "calories",
                "high");
        List<Row> highCalorieItems = subset3.getDataObjects();
        for (Row row : highCalorieItems) {
            System.out.println(row.getAttributeValue("name"));
            assertEquals(row.getAttributeValue("calories"), "high");
        }
        // orange, sweet potato, avocado, and banana should be printed out
    }

    // Testing to see if all items are partitioned correctly according to color
    @Test
    public void testPartition1() {
        List<Dataset> subset4 = this.foodData.partition("color");
        List<Row> greenItems = subset4.get(0).getDataObjects();
        for (Row row1 : greenItems) {
            System.out.println(row1.getAttributeValue("name"));
        }
        List<Row> orangeItems = subset4.get(1).getDataObjects();
        for (Row row2 : orangeItems) {
            System.out.println(row2.getAttributeValue("name"));
        }
        List<Row> yellowItems = subset4.get(2).getDataObjects();
        for (Row row3 : yellowItems) {
            System.out.println(row3.getAttributeValue("name"));
        }
        // All names of foods should be printed out according to color (i.e.
        // all green items, then all orange items, then all yellow items
    }

    // Testing to see if all items are partitioned correctly according to
    // highProtein
    @Test
    public void testPartition2() {
        List<Dataset> subset6 = this.foodData.partition("highProtein");
        List<Row> highProteinItems = subset6.get(0).getDataObjects();
        for (Row row4 : highProteinItems) {
            System.out.println(row4.getAttributeValue("name"));
        }
        List<Row> nonHighProteinItems = subset6.get(1).getDataObjects();
        for (Row row5 : nonHighProteinItems) {
            System.out.println(row5.getAttributeValue("name"));
        }
        // All names of foods should be printed out according to protein
        // content (i.e. all high protein items, followed by low protein items)
    }

    // Testing if the correct foodType is returned for a banana in the sample
    // dataset
    @Test
    public void testGetDecision1() {
        TreeGenerator generator = new TreeGenerator();
        generator.generateTree(this.foodData, "foodType");
        String decision2 = generator.getDecision(this.banana);
        assertEquals(decision2, "fruit");
    }

    // Testing if the correct foodType is returned for spinach in the sample
    // dataset
    @Test
    public void testGetDecision2() {
        TreeGenerator generator2 = new TreeGenerator();
        generator2.generateTree(this.foodData, "foodType");
        String decision3 = generator2.getDecision(this.spinach);
        assertEquals(decision3, "vegetable");
    }

    // Testing if the correct foodType is returned for an orange in the sample
    // dataset
    @Test
    public void testGetDecision3() {
        TreeGenerator generator3 = new TreeGenerator();
        generator3.generateTree(this.foodData, "foodType");
        String decision4 = generator3.getDecision(this.orange);
        assertEquals(decision4, "fruit");
    }

    // Testing if the correct foodType is returned for an avocado in the sample
    // dataset
    @Test
    public void testGetDecision4() {
        TreeGenerator generator4 = new TreeGenerator();
        generator4.generateTree(this.foodData, "foodType");
        String decision5 = generator4.getDecision(this.avocado);
        assertEquals(decision5, "fruit");
    }

    // Testing if BuildTree compiles and runs
    @Test
    public void testBuildTree1() {
        TreeGenerator generator4 = new TreeGenerator();
        generator4.generateTree(this.foodData, "foodType");
    }

    // Testing on an empty dataset
    @Test
    public void testBuildTree2() {
        TreeGenerator generator5 = new TreeGenerator();
        List<String> attributes = new ArrayList<>();
        attributes.add("color");
        Dataset emptyData = new Dataset(attributes, emptyRows);
        Exception e = assertThrows(RuntimeException.class,
                () -> generator5.generateTree(emptyData, "foodType"));
        assertEquals("Dataset is empty", e.getMessage());
    }
}