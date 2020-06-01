package courseworkgraph;

//<editor-fold defaultstate="collapsed" desc="Imports">
import static courseworkgraph.AuxiliaryClass.*;
import static courseworkgraph.TableData.*;
//</editor-fold>

public class Bisection extends CourseworkGraph {

  public static void firstFunction() {
    removeDataInArray(bisectionArrayData);
    removeDataInTable(bisectionTableData);

    int index = 0, rowID = 1;
    double tolerance = Double.parseDouble(accuracyText.getText());
    int maxConvergeIteration = (Integer) maxConvergence.getValue();

    printDecimalPlaces();

    double difference = 0.0;

    double lowerBound = Double.parseDouble(lowerBoundText.getText());
    double functionLowerBound = lowerBound - (lowerBound * lowerBound);
    double upperBound = Double.parseDouble(upperBoundText.getText());
    double functionUpperBound = upperBound - (upperBound * upperBound);

    do {
      //<editor-fold defaultstate="collapsed" desc="Method Calculation">
      double midpoint = ((upperBound + lowerBound) / 2.0);
      double functionMidpoint = midpoint - (midpoint * midpoint);

      if (Double.isNaN(midpoint) || Double.isNaN(functionMidpoint)) {
        System.out.println("NaN value found, ceasing function");
        break;
      } else if (midpoint == Double.NEGATIVE_INFINITY || functionMidpoint == Double.NEGATIVE_INFINITY) {
        System.out.println("Value is -Infinity, ceasing function");
        break;
      } else if (midpoint == Double.POSITIVE_INFINITY || functionMidpoint == Double.POSITIVE_INFINITY) {
        System.out.println("Value is Infinity, ceasing function");
        break;
      }

      difference = Math.abs((upperBound - lowerBound) / 2.0);

      if ((functionLowerBound * functionMidpoint) > 0) {
        lowerBound = midpoint;
      } else if ((functionUpperBound * functionMidpoint) > 0) {
        upperBound = midpoint;
      }
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Plot Bisection">
      bisectionGraph.add(midpoint, functionMidpoint);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add results to Table">
      bisectionTableData[index][0] = rowID;
      bisectionTableData[index][1] = setDecimalPoint(functionMidpoint);
      bisectionTableData[index][2] = setDecimalPoint(difference);
      bisectionTableData[index][3] = setDecimalPoint(lowerBound);
      bisectionTableData[index][4] = setDecimalPoint(midpoint);
      bisectionTableData[index][5] = setDecimalPoint(upperBound);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add calculation to Array">
      bisectionArrayData[index] = Double.parseDouble(setDecimalPoint(midpoint));
      //</editor-fold>

      System.out.println(rowID + ": [" + setDecimalPoint(midpoint) + "]");

      index++;
      rowID++;
    } while ((difference > tolerance) && (rowID <= maxConvergeIteration));
    printIterationCount(rowID);
  }

  public static void secondFunction() {
    removeDataInArray(bisectionArrayData);
    removeDataInTable(bisectionTableData);

    int index = 0, rowID = 1;
    double tolerance = Double.parseDouble(accuracyText.getText());
    int maxConvergeIteration = (Integer) maxConvergence.getValue();

    printDecimalPlaces();

    double difference = 0.0;

    double lowerBound = Double.parseDouble(lowerBoundText.getText());
    double functionLowerBound = (Math.log(lowerBound + 1)) + 1;
    double upperBound = Double.parseDouble(upperBoundText.getText());
    double functionUpperBound = (Math.log(upperBound + 1)) + 1;

    do {
      //<editor-fold defaultstate="collapsed" desc="Method Calculation">
      double midpoint = ((upperBound + lowerBound) / 2.0);
      double functionMidpoint = (Math.log(midpoint + 1)) + 1;

      if (Double.isNaN(midpoint) || Double.isNaN(functionMidpoint)) {
        System.out.println("NaN value found, ceasing function");
        break;
      } else if (midpoint == Double.NEGATIVE_INFINITY || functionMidpoint == Double.NEGATIVE_INFINITY) {
        System.out.println("Value is -Infinity, ceasing function");
        break;
      } else if (midpoint == Double.POSITIVE_INFINITY || functionMidpoint == Double.POSITIVE_INFINITY) {
        System.out.println("Value is Infinity, ceasing function");
        break;
      }

      difference = Math.abs((upperBound - lowerBound) / 2.0);

      if ((functionLowerBound * functionMidpoint) > 0) {
        lowerBound = midpoint;
      } else if ((functionUpperBound * functionMidpoint) > 0) {
        upperBound = midpoint;
      }
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Plot Bisection">
      bisectionGraph.add(midpoint, functionMidpoint);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add results to Table">
      bisectionTableData[index][0] = rowID;
      bisectionTableData[index][1] = setDecimalPoint(functionMidpoint);
      bisectionTableData[index][2] = setDecimalPoint(difference);
      bisectionTableData[index][3] = setDecimalPoint(lowerBound);
      bisectionTableData[index][4] = setDecimalPoint(midpoint);
      bisectionTableData[index][5] = setDecimalPoint(upperBound);
      //</editor-fold> 

      //<editor-fold defaultstate="collapsed" desc="Add calculation to Array">
      bisectionArrayData[index] = Double.parseDouble(setDecimalPoint(midpoint));
      //</editor-fold>

      System.out.println(rowID + ": [" + setDecimalPoint(midpoint) + "]");

      index++;
      rowID++;
    } while ((difference > tolerance) && (rowID <= maxConvergeIteration));
    printIterationCount(rowID);
  }

  public static void thirdFunction() {
    removeDataInArray(bisectionArrayData);
    removeDataInTable(bisectionTableData);

    int index = 0, rowID = 1;
    double tolerance = Double.parseDouble(accuracyText.getText());
    int maxConvergeIteration = (Integer) maxConvergence.getValue();

    printDecimalPlaces();

    double difference = 0.0;

    double lowerBound = Double.parseDouble(lowerBoundText.getText());
    double functionLowerBound = Math.exp(lowerBound) - (3 * lowerBound);
    double upperBound = Double.parseDouble(upperBoundText.getText());
    double functionUpperBound = Math.exp(upperBound) - (3 * upperBound);

    do {
      //<editor-fold defaultstate="collapsed" desc="Method Calculation">
      double midpoint = (upperBound + lowerBound) / 2.0;
      double functionMidpoint = Math.exp(midpoint) - (3 * midpoint);

      if (Double.isNaN(midpoint) || Double.isNaN(functionMidpoint)) {
        System.out.println("NaN value found, ceasing function");
        break;
      } else if (midpoint == Double.NEGATIVE_INFINITY || functionMidpoint == Double.NEGATIVE_INFINITY) {
        System.out.println("Value is -Infinity, ceasing function");
        break;
      } else if (midpoint == Double.POSITIVE_INFINITY || functionMidpoint == Double.POSITIVE_INFINITY) {
        System.out.println("Value is Infinity, ceasing function");
        break;
      }

      difference = Math.abs((upperBound - lowerBound) / 2.0);

      if ((functionLowerBound * functionMidpoint) > 0) {
        lowerBound = midpoint;
      } else if ((functionUpperBound * functionMidpoint) > 0) {
        upperBound = midpoint;
      }
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Plot Bisection">
      bisectionGraph.add(midpoint, functionMidpoint);
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="Add results to Table">
      bisectionTableData[index][0] = rowID;
      bisectionTableData[index][1] = setDecimalPoint(functionMidpoint);
      bisectionTableData[index][2] = setDecimalPoint(difference);
      bisectionTableData[index][3] = setDecimalPoint(lowerBound);
      bisectionTableData[index][4] = setDecimalPoint(midpoint);
      bisectionTableData[index][5] = setDecimalPoint(upperBound);
      //</editor-fold> 

      //<editor-fold defaultstate="collapsed" desc="Add calculation to Array">
      bisectionArrayData[index] = Double.parseDouble(setDecimalPoint(midpoint));
      //</editor-fold>

      System.out.println(rowID + ": [" + setDecimalPoint(midpoint) + "]");

      index++;
      rowID++;
    } while ((difference > tolerance) && (rowID <= maxConvergeIteration));
    printIterationCount(rowID);
  }

  private static void printDecimalPlaces() {
    System.out.println("Bisection (To " + ((Integer) decimalSpinner.getValue()) + "dp):");
  }
}
