package courseworkgraph;

//<editor-fold defaultstate="collapsed" desc="Imports">
import static courseworkgraph.AuxiliaryClass.*;
import static courseworkgraph.TableData.*;
//</editor-fold>

public class NewtonRaphson extends CourseworkGraph {

  public static void firstFunction() {

    removeDataInLinkedList(newtonRaphsonLinkedList);
    removeDataInTable(newtonTableData);

    int index = 0, rowID = 1;
    double tolerance = Double.parseDouble(accuracyText.getText());
    int maxConvergeIteration = (Integer) maxConvergence.getValue();

    double x_Value = Double.parseDouble(x_ValueNewton.getText());
    double x_ValueOld = 0.0, x_ValueNew = 0.0, difference = 0.0;
    final double INITIAL_X_VALUE = x_Value;

    printDecimalPlaces();

    do {
      //<editor-fold defaultstate="collapsed" desc="Calculation">
      double function = x_Value - Math.pow(x_Value, 2);
      double derivative = 1 - (2 * x_Value);

      x_ValueNew = x_Value - (function / derivative);

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

      double functionNew = x_ValueNew - Math.pow(x_ValueNew, 2);
      difference = Math.abs(x_ValueNew - x_Value);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Plot Newton Raphson">
      newtonRaphsonGraph.add(x_ValueNew, functionNew);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add to LinkedList">
      if (rowID == 1) {
        newtonRaphsonLinkedList.head.setNewton(setDecimalPoint(x_ValueNew));
      } else {
        newtonRaphsonLinkedList.addLast(setDecimalPoint(x_ValueNew));
      }
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add to Table">
      newtonTableData[index][0] = rowID;
      newtonTableData[index][1] = setDecimalPoint(x_ValueOld);
      newtonTableData[index][2] = setDecimalPoint(x_Value);
      newtonTableData[index][3] = setDecimalPoint(x_ValueNew);
      newtonTableData[index][4] = setDecimalPoint(difference);
      newtonTableData[index][5] = setDecimalPoint(function);
      newtonTableData[index][6] = setDecimalPoint(functionNew);
      newtonTableData[index][7] = setDecimalPoint(derivative);
      //</editor-fold>

      x_ValueOld = x_Value;
      x_Value = x_ValueNew;

      System.out.println(rowID + ": [" + setDecimalPoint(x_ValueNew) + "]");

      index++;
      rowID++;
    } while ((difference > tolerance) && (rowID <= maxConvergeIteration));
    printIterationCount(rowID);
  }

  public static void secondFunction() {

    //<editor-fold defaultstate="collapsed" desc="Variable Initializing">
    removeDataInLinkedList(newtonRaphsonLinkedList);
    removeDataInTable(newtonTableData);

    int index = 0, rowID = 1;
    double tolerance = Double.parseDouble(accuracyText.getText());
    int maxConvergeIteration = (Integer) maxConvergence.getValue();

    double x_Value = Double.parseDouble(x_ValueNewton.getText());
    double x_ValueOld = 0.0, x_ValueNew = 0.0, difference = 0.0;
    final double INITIAL_X_VALUE = x_Value;

    printDecimalPlaces();
    //</editor-fold>

    do {
      //<editor-fold defaultstate="collapsed" desc="Calculation">
      double function = Math.log(x_Value + 1) + 1;
      double derivative = (1 / (x_Value + 1));

      x_ValueNew = x_Value - (function / derivative);

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

      double functionNew = Math.log(x_ValueNew + 1) + 1;
      difference = Math.abs(x_ValueNew - x_Value);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Plot Newton Raphson">            
      newtonRaphsonGraph.add(x_ValueNew, functionNew);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add to LinkedList">
      if (rowID == 1) {
        newtonRaphsonLinkedList.head.setNewton(setDecimalPoint(x_ValueNew));
      } else {
        newtonRaphsonLinkedList.addLast(setDecimalPoint(x_ValueNew));
      }
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add to Table">
      newtonTableData[index][0] = rowID;
      newtonTableData[index][1] = setDecimalPoint(x_ValueOld);
      newtonTableData[index][2] = setDecimalPoint(x_Value);
      newtonTableData[index][3] = setDecimalPoint(x_ValueNew);
      newtonTableData[index][4] = setDecimalPoint(difference);
      newtonTableData[index][5] = setDecimalPoint(function);
      newtonTableData[index][6] = setDecimalPoint(functionNew);
      newtonTableData[index][7] = setDecimalPoint(derivative);
      //</editor-fold>

      x_Value = x_ValueNew;
      x_ValueOld = x_Value;

      System.out.println(rowID + ": [" + setDecimalPoint(x_ValueNew) + "]");

      index++;
      rowID++;
    } while ((difference > tolerance) && (rowID <= maxConvergeIteration));
    printIterationCount(rowID);
  }

  public static void thirdFunction() {

    //<editor-fold defaultstate="collapsed" desc="Variable Initializing">
    removeDataInLinkedList(newtonRaphsonLinkedList);
    removeDataInTable(newtonTableData);

    int index = 0, rowID = 1;
    double tolerance = Double.parseDouble(accuracyText.getText());
    int maxConvergeIteration = (Integer) maxConvergence.getValue();

    double x_Value = Double.parseDouble(x_ValueNewton.getText());
    double x_ValueOld = 0.0, x_ValueNew = 0.0, difference = 0.0;
    final double INITIAL_X_VALUE = x_Value;

    printDecimalPlaces();
    //</editor-fold>

    do {
      //<editor-fold defaultstate="collapsed" desc="Calculation">
      double function = (Math.exp(x_Value) - (3 * x_Value));
      double derivative = (Math.exp(x_Value));

      x_ValueNew = x_Value - (function / derivative);

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

      double functionNew = (Math.exp(x_ValueNew) - (3 * x_ValueNew));
      difference = Math.abs(x_ValueNew - x_Value);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Plot Newton Raphson">
      newtonRaphsonGraph.add(x_ValueNew, functionNew);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add to LinkedList">
      if (rowID == 1) {
        newtonRaphsonLinkedList.head.setNewton(setDecimalPoint(x_ValueNew));
      } else {
        newtonRaphsonLinkedList.addLast(setDecimalPoint(x_ValueNew));
      }
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add to Table">
      newtonTableData[index][0] = rowID;
      newtonTableData[index][1] = setDecimalPoint(x_ValueOld);
      newtonTableData[index][2] = setDecimalPoint(x_Value);
      newtonTableData[index][3] = setDecimalPoint(x_ValueNew);
      newtonTableData[index][4] = setDecimalPoint(difference);
      newtonTableData[index][5] = setDecimalPoint(function);
      newtonTableData[index][6] = setDecimalPoint(functionNew);
      newtonTableData[index][7] = setDecimalPoint(derivative);
      //</editor-fold>

      x_ValueOld = x_Value;
      x_Value = x_ValueNew;

      System.out.println(rowID + ": [" + setDecimalPoint(x_ValueNew) + "]");

      index++;
      rowID++;
    } while ((difference > tolerance) && (rowID <= maxConvergeIteration));
    printIterationCount(rowID);
  }

  private static void printDecimalPlaces() {
    System.out.println("Newton Raphson (To " + ((Integer) decimalSpinner.getValue()) + "dp):");
  }
}
