package courseworkgraph;

//<editor-fold defaultstate="collapsed" desc="Imports">
import static courseworkgraph.AuxiliaryClass.*;
import static courseworkgraph.TableData.*;
//</editor-fold>

public class Secant extends CourseworkGraph {

  public static void firstFunction() {
    removeDataInArray(secantArrayData); //Clear the Array of Secant data if it is not empty
    removeDataInTable(secantTableData); //Clear the Table of Secant data if it is not empty

    int index = 0, rowID = 1;
    double tolerance = Double.parseDouble(accuracyText.getText());
    int maxConvergeIteration = (Integer) maxConvergence.getValue();

    double x_Value = Double.parseDouble(x_ValueSecantOne.getText());
    double x_ValueOld = Double.parseDouble(x_ValueSecantTwo.getText());
    double difference = 0.0;

    printDecimalPlaces(); //Print decimal places

    do {
      //<editor-fold defaultstate="collapsed" desc="Calculation">
      double functionOld = x_ValueOld - Math.pow(x_ValueOld, 2);
      double function = x_Value - Math.pow(x_Value, 2);

      double numerator = functionOld * (x_ValueOld - x_Value);
      double denominator = functionOld - function;

      double x_ValueNew = x_ValueOld - (numerator / denominator);

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
      difference = Math.abs(x_Value - x_ValueOld);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Plot Secant">
      secantGraph.add(x_ValueNew, functionNew);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add calculations to Table">
      secantTableData[index][0] = rowID;
      secantTableData[index][1] = setDecimalPoint(x_ValueOld);
      secantTableData[index][2] = setDecimalPoint(x_Value);
      secantTableData[index][3] = setDecimalPoint(difference);
      secantTableData[index][4] = setDecimalPoint(x_ValueNew);
      secantTableData[index][5] = setDecimalPoint(function);
      secantTableData[index][6] = setDecimalPoint(functionOld);
      //</editor-fold>       

      //<editor-fold defaultstate="collapsed" desc="Add calculation to Array">
      secantArrayData[index] = Double.parseDouble(setDecimalPoint(x_ValueNew));
      //</editor-fold>

      //Update values for next iteration
      x_ValueOld = x_Value;
      x_Value = x_ValueNew;

      System.out.println(rowID + ": [" + setDecimalPoint(x_ValueNew) + "]");

      index++;
      rowID++;
    } while ((difference > tolerance) && (rowID <= maxConvergeIteration));
    printIterationCount(rowID);
  }

  public static void secondFunction() {
    removeDataInArray(secantArrayData); //Clear the Array of Secant data if it is not empty
    removeDataInTable(secantTableData); //Clear the Table of Secant data if it is not empty

    int index = 0, rowID = 1;
    double tolerance = Double.parseDouble(accuracyText.getText());
    int maxConvergeIteration = (Integer) maxConvergence.getValue();

    double x_Value = Double.parseDouble(x_ValueSecantOne.getText());
    double x_ValueOld = Double.parseDouble(x_ValueSecantTwo.getText());
    double difference = 0.0;

    printDecimalPlaces(); //Print decimal places

    do {
      //<editor-fold defaultstate="collapsed" desc="Calculation">
      double functionOld = Math.log(x_ValueOld + 1) + 1;
      double function = Math.log(x_Value + 1) + 1;

      double numerator = functionOld * (x_ValueOld - x_Value);
      double denominator = functionOld - function;

      double x_ValueNew = x_ValueOld - (numerator / denominator);

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
      difference = Math.abs(x_Value - x_ValueOld);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Plot Secant">
      secantGraph.add(x_ValueNew, functionNew);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add calculations to Table">
      secantTableData[index][0] = rowID; //Iteration
      secantTableData[index][1] = setDecimalPoint(x_ValueOld); //Add Secant X[n-1] values to array
      secantTableData[index][2] = setDecimalPoint(x_Value); //Add Secant X[n] values to array
      secantTableData[index][3] = setDecimalPoint(difference); //Add Secant Difference values to array
      secantTableData[index][4] = setDecimalPoint(x_ValueNew); //Add Secant X[n+1] values to array
      secantTableData[index][5] = setDecimalPoint(function); //Add Secant f(X[n]) values to array
      secantTableData[index][6] = setDecimalPoint(functionOld); //Add Newton Raphson f(X[n-1]) values to array
      //</editor-fold>  

      //<editor-fold defaultstate="collapsed" desc="Add calculation to Array">
      secantArrayData[index] = Double.parseDouble(setDecimalPoint(x_ValueNew));
      //</editor-fold>

      //Update values for next iteration
      x_ValueOld = x_Value;
      x_Value = x_ValueNew;

      System.out.println(rowID + ": [" + setDecimalPoint(x_ValueNew) + "]");

      index++;
      rowID++;
    } while ((difference > tolerance) && (rowID <= maxConvergeIteration));
    printIterationCount(rowID);
  }

  public static void thirdFunction() {
    removeDataInArray(secantArrayData); //Clear the Array of Secant data if it is not empty
    removeDataInTable(secantTableData); //Clear the Table of Secant data if it is not empty

    int index = 0, rowID = 1;
    double tolerance = Double.parseDouble(accuracyText.getText());
    int maxConvergeIteration = (Integer) maxConvergence.getValue();

    double x_Value = Double.parseDouble(x_ValueSecantOne.getText());
    double x_ValueOld = Double.parseDouble(x_ValueSecantTwo.getText());
    double difference = 0.0;

    printDecimalPlaces(); //Print decimal places

    do {
      //<editor-fold defaultstate="collapsed" desc="Calculation">
      double functionOld = (Math.exp(x_ValueOld) - (3 * x_ValueOld));
      double function = (Math.exp(x_Value) - (3 * x_Value));

      double numerator = functionOld * (x_ValueOld - x_Value);
      double denominator = functionOld - function;

      double x_ValueNew = x_ValueOld - (numerator / denominator);

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
      difference = Math.abs(x_Value - x_ValueOld);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Plot Secant">
      secantGraph.add(x_ValueNew, functionNew);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add calculations to Table">
      secantTableData[index][0] = rowID; //Iteration
      secantTableData[index][1] = setDecimalPoint(x_ValueOld); //Add Secant X[n-1] values to array
      secantTableData[index][2] = setDecimalPoint(x_Value); //Add Secant X[n] values to array
      secantTableData[index][3] = setDecimalPoint(difference); //Add Secant Difference values to array
      secantTableData[index][4] = setDecimalPoint(x_ValueNew); //Add Secant X[n+1] values to array
      secantTableData[index][5] = setDecimalPoint(function); //Add Secant f(X[n]) values to array
      secantTableData[index][6] = setDecimalPoint(functionOld); //Add Newton Raphson f(X[n-1]) values to array
      //</editor-fold>  

      //<editor-fold defaultstate="collapsed" desc="Add calculation to Array">
      secantArrayData[index] = Double.parseDouble(setDecimalPoint(x_ValueNew));
      //</editor-fold>

      //Update values for next iteration
      x_ValueOld = x_Value;
      x_Value = x_ValueNew;

      System.out.println(rowID + ": [" + setDecimalPoint(x_ValueNew) + "]");

      index++;
      rowID++;
    } while ((difference > tolerance) && (rowID <= maxConvergeIteration));
    printIterationCount(rowID);
  }

  private static void printDecimalPlaces() {
    System.out.println("Secant (To " + ((Integer) decimalSpinner.getValue()) + "dp):");
  }
}
