package courseworkgraph;

//<editor-fold defaultstate="collapsed" desc="Imports">
import static courseworkgraph.AuxiliaryClass.*;
import static courseworkgraph.TableData.*;
//</editor-fold>

public class Steffensen extends CourseworkGraph {

  public static void firstFunction() {
    removeDataInLinkedList(steffensenLinkedList);
    removeDataInTable(steffensenTableData);

    int index = 0, rowID = 1;
    double tolerance = Double.parseDouble(accuracyText.getText());
    int maxConvergeIteration = (Integer) maxConvergence.getValue();

    double x_Value = Double.parseDouble(x_ValueSteffensen.getText());
    double x_ValueNew = x_Value;
    double difference = 0.0;

    printDecimalPlaces();

    do {
      //<editor-fold defaultstate="collapsed" desc="Calculation">
      x_Value = x_ValueNew;
      double x_ValueOld = x_ValueNew;
      double function = x_Value - Math.pow(x_Value, 2);

      double gFunctionInner = x_Value + function;
      double gFunctionTop = gFunctionInner - (gFunctionInner * gFunctionInner);
      double gFunctionAll = (gFunctionTop / function) - 1;

      x_ValueNew = x_Value - (function / gFunctionAll);

      if (Double.isNaN(x_ValueNew)) {
        System.out.println("NaN value found, ceasing function");
        break;
      } else if (x_ValueNew == Double.NEGATIVE_INFINITY) {
        System.out.println("Value is -Infinity, ceasing function");
        break;
      } else if (x_ValueNew == Double.POSITIVE_INFINITY) {
        System.out.println("Value is Infinity, ceasing function");
        break;
      }

      double x_ValueNewFunction = x_ValueNew - Math.pow(x_ValueNew, 2);
      difference = Math.abs(x_ValueNew - x_ValueOld); //Check the difference between old and new value      
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Plot Steffensen">
      steffensenGraph.add(x_ValueNew, x_ValueNewFunction);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add to LinkedList">
      if (rowID == 1) { //On the first iteration
        steffensenLinkedList.head.setNewton(setDecimalPoint(x_ValueNew)); //Add first newton raphson values to linked list head
      } else { //For remaining iterations
        steffensenLinkedList.addLast(setDecimalPoint(x_ValueNew)); //Add subsequent newton raphson values to linked list
      }
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add to Table">
      steffensenTableData[index][0] = rowID;
      steffensenTableData[index][1] = setDecimalPoint(x_ValueOld);
      steffensenTableData[index][2] = setDecimalPoint(x_Value);
      steffensenTableData[index][3] = setDecimalPoint(x_ValueNew);
      steffensenTableData[index][4] = setDecimalPoint(difference);
      steffensenTableData[index][5] = setDecimalPoint(function);
      //</editor-fold>

      System.out.println(rowID + ": [" + setDecimalPoint(x_ValueNew) + "]");

      index++;
      rowID++;
    } while ((difference > tolerance) && (rowID <= maxConvergeIteration));
    printIterationCount(rowID);
  }

  public static void secondFunction() {
    removeDataInLinkedList(steffensenLinkedList); //Clear LinkedList if it is not empty
    removeDataInTable(steffensenTableData); //Clear Table if it is not empty

    int index = 0, rowID = 1;
    double tolerance = Double.parseDouble(accuracyText.getText());
    int maxConvergeIteration = (Integer) maxConvergence.getValue();

    double x_Value = Double.parseDouble(x_ValueSteffensen.getText());
    double x_ValueNew = x_Value;
    double difference = 0.0;

    printDecimalPlaces(); //Print decimal places

    do {
      //<editor-fold defaultstate="collapsed" desc="Calculation">
      x_Value = x_ValueNew;
      double x_ValueOld = x_ValueNew;
      double function = Math.log(x_Value + 1) + 1;

      double gFunctionInner = x_Value + function;
      double gFunctionTop = Math.log(gFunctionInner + 1) + 1;
      double gFunctionAll = (gFunctionTop / function) - 1;

      x_ValueNew = x_Value - (function / gFunctionAll);

      if (Double.isNaN(x_ValueNew)) {
        System.out.println("NaN value found, ceasing function");
        break;
      } else if (x_ValueNew == Double.NEGATIVE_INFINITY) {
        System.out.println("Value is -Infinity, ceasing function");
        break;
      } else if (x_ValueNew == Double.POSITIVE_INFINITY) {
        System.out.println("Value is Infinity, ceasing function");
        break;
      }

      double x_ValueNewFunction = Math.log(x_ValueNew + 1) + 1;
      difference = Math.abs(x_ValueNew - x_ValueOld); //Check the difference between old and new value      
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Plot Steffensen">
      steffensenGraph.add(x_ValueNew, x_ValueNewFunction);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add to LinkedList">
      if (rowID == 1) { //On the first iteration
        steffensenLinkedList.head.setNewton(setDecimalPoint(x_ValueNew)); //Add first newton raphson values to linked list head
      } else { //For remaining iterations
        steffensenLinkedList.addLast(setDecimalPoint(x_ValueNew)); //Add subsequent newton raphson values to linked list
      }
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add to Table">
      steffensenTableData[index][0] = rowID; //Iteration
      steffensenTableData[index][1] = setDecimalPoint(x_ValueOld); //Add Newton Raphson X[n-1] values to array
      steffensenTableData[index][2] = setDecimalPoint(x_Value); //Add Newton Raphson X[n] values to array
      steffensenTableData[index][3] = setDecimalPoint(x_ValueNew); //Add Newton Raphson X[n] values to array
      steffensenTableData[index][4] = setDecimalPoint(difference); //Add Newton Raphson Difference values to array
      steffensenTableData[index][5] = setDecimalPoint(function); //Add Newton Raphson f(X[n-1]) values to array
      //</editor-fold>

      System.out.println(rowID + ": [" + setDecimalPoint(x_ValueNew) + "]");

      index++;
      rowID++;
    } while ((difference > tolerance) && (rowID <= maxConvergeIteration));
    printIterationCount(rowID);
  }

  public static void thirdFunction() {
    removeDataInLinkedList(steffensenLinkedList); //Clear LinkedList if it is not empty
    removeDataInTable(steffensenTableData); //Clear Table if it is not empty

    int index = 0, rowID = 1;
    double tolerance = Double.parseDouble(accuracyText.getText());
    int maxConvergeIteration = (Integer) maxConvergence.getValue();

    double x_Value = Double.parseDouble(x_ValueSteffensen.getText());
    double x_ValueNew = x_Value;
    double difference = 0.0;

    printDecimalPlaces(); //Print decimal places

    do {
      //<editor-fold defaultstate="collapsed" desc="Calculation">
      x_Value = x_ValueNew;
      double x_ValueOld = x_ValueNew;
      double function = Math.exp(x_Value) - (3 * x_Value);

      double gFunctionInner = x_Value + function;
      double gFunctionTop = Math.exp(gFunctionInner) - (3 * gFunctionInner);
      double gFunctionAll = (gFunctionTop / function) - 1;

      x_ValueNew = x_Value - (function / gFunctionAll);

      if (Double.isNaN(x_ValueNew)) {
        System.out.println("NaN value found, ceasing function");
        break;
      } else if (x_ValueNew == Double.NEGATIVE_INFINITY) {
        System.out.println("Value is -Infinity, ceasing function");
        break;
      } else if (x_ValueNew == Double.POSITIVE_INFINITY) {
        System.out.println("Value is Infinity, ceasing function");
        break;
      }

      double x_ValueNewFunction = Math.exp(x_ValueNew) - (3 * x_ValueNew);
      difference = Math.abs(x_ValueNew - x_ValueOld); //Check the difference between old and new value      
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Plot Steffensen">
      steffensenGraph.add(x_ValueNew, x_ValueNewFunction);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add to LinkedList">
      if (rowID == 1) { //On the first iteration
        steffensenLinkedList.head.setNewton(setDecimalPoint(x_ValueNew)); //Add first newton raphson values to linked list head
      } else { //For remaining iterations
        steffensenLinkedList.addLast(setDecimalPoint(x_ValueNew)); //Add subsequent newton raphson values to linked list
      }
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add to Table">
      steffensenTableData[index][0] = rowID; //Iteration
      steffensenTableData[index][1] = setDecimalPoint(x_ValueOld); //Add Newton Raphson X[n-1] values to array
      steffensenTableData[index][2] = setDecimalPoint(x_Value); //Add Newton Raphson X[n] values to array
      steffensenTableData[index][3] = setDecimalPoint(x_ValueNew); //Add Newton Raphson X[n] values to array
      steffensenTableData[index][4] = setDecimalPoint(difference); //Add Newton Raphson Difference values to array
      steffensenTableData[index][5] = setDecimalPoint(function); //Add Newton Raphson f(X[n-1]) values to array
      //</editor-fold>

      System.out.println(rowID + ": [" + setDecimalPoint(x_ValueNew) + "]");

      index++;
      rowID++;
    } while ((difference > tolerance) && (rowID <= maxConvergeIteration));
    printIterationCount(rowID);
  }

  private static void printDecimalPlaces() {
    System.out.println("Steffensen (To " + ((Integer) decimalSpinner.getValue()) + "dp):");
  }
}
