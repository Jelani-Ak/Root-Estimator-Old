package courseworkgraph;

//<editor-fold defaultstate="collapsed" desc="Imports">
import static courseworkgraph.TableData.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import slinkedlist.SLinkedList;
import static slinkedlist.SLinkedList.*;
import static courseworkgraph.CourseworkGraph.accuracyText;
//</editor-fold>

public class AuxiliaryClass extends CourseworkGraph {

  //Set font size
  public static Font arial = new Font("Arial", Font.PLAIN, 14);

  //Create a button
  public static JButton makeButton(
          String buttonName,
          String actionCommand,
          String toolTipText) {

    JButton button = new JButton(buttonName);
    button.setActionCommand(actionCommand);
    button.setToolTipText(toolTipText);

    return button;
  }

  //Format the decimal places of a number to a user specified value
  public static String setDecimalPoint(Double number) {
    return String.format("%." + (Integer) decimalSpinner.getValue() + "f", number);
  }

  //Display selected function, accuracy and decimal places in each table text field
  public static void setTabbedPaneText(ActionEvent ae) {
    switch (ae.getActionCommand()) {
      case "FunctionOne":
        functionCurrentTabOne.setText("Function: [x - x^2], Accuracy: [" + accuracyText.getText() + "], Decimal Places: [" + decimalSpinner.getValue() + "]");
        functionCurrentTabTwo.setText("Function: [x - x^2], Accuracy: [" + accuracyText.getText() + "], Decimal Places: [" + decimalSpinner.getValue() + "]");
        functionCurrentTabThree.setText("Function: [x - x^2], Accuracy: [" + accuracyText.getText() + "], Decimal Places: [" + decimalSpinner.getValue() + "]");
        functionCurrentTabFour.setText("Function: [x - x^2], Accuracy: [" + accuracyText.getText() + "], Decimal Places: [" + decimalSpinner.getValue() + "]");
        functionCurrentTabFive.setText("Function: [x - x^2], Accuracy: [" + accuracyText.getText() + "], Decimal Places: [" + decimalSpinner.getValue() + "]");
        break;
      case "FunctionTwo":
        functionCurrentTabOne.setText("Function: [ln(x + 1) + 1], Accuracy: [" + accuracyText.getText() + "], Decimal Places: [" + decimalSpinner.getValue() + "]");
        functionCurrentTabTwo.setText("Function: [ln(x + 1) + 1], Accuracy: [" + accuracyText.getText() + "], Decimal Places: [" + decimalSpinner.getValue() + "]");
        functionCurrentTabThree.setText("Function: [ln(x + 1) + 1], Accuracy: [" + accuracyText.getText() + "], Decimal Places: [" + decimalSpinner.getValue() + "]");
        functionCurrentTabFour.setText("Function: [ln(x + 1) + 1], Accuracy: [" + accuracyText.getText() + "], Decimal Places: [" + decimalSpinner.getValue() + "]");
        functionCurrentTabFive.setText("Function: [ln(x + 1) + 1], Accuracy: [" + accuracyText.getText() + "], Decimal Places: [" + decimalSpinner.getValue() + "]");
        break;
      case "FuncionThree":
        functionCurrentTabOne.setText("Function: [e^x - 3x], Accuracy: [" + accuracyText.getText() + "], Decimal Places: [" + decimalSpinner.getValue() + "]");
        functionCurrentTabTwo.setText("Function: [e^x - 3x], Accuracy: [" + accuracyText.getText() + "], Decimal Places: [" + decimalSpinner.getValue() + "]");
        functionCurrentTabThree.setText("Function: [e^x - 3x], Accuracy: [" + accuracyText.getText() + "], Decimal Places: [" + decimalSpinner.getValue() + "]");
        functionCurrentTabFour.setText("Function: [e^x - 3x], Accuracy: [" + accuracyText.getText() + "], Decimal Places: [" + decimalSpinner.getValue() + "]");
        functionCurrentTabFive.setText("Function: [e^x - 3x], Accuracy: [" + accuracyText.getText() + "], Decimal Places: [" + decimalSpinner.getValue() + "]");
        break;
      case "ClearGraph":
        functionCurrentTabOne.setText("Awaiting function selection");
        functionCurrentTabTwo.setText("Awaiting function selection");
        functionCurrentTabThree.setText("Awaiting function selection");
        functionCurrentTabFour.setText("Awaiting function selection");
        functionCurrentTabFive.setText("Awaiting function selection");
        break;
    }
  }

  public static void setResultsText(ActionEvent ae) {
    switch (ae.getActionCommand()) {
      case "FunctionOne":
        resultsText.setText("Function: [x - x^2], Roots: [0] and [1],");
        checkFunction();
        break;
      case "FunctionTwo":
        resultsText.setText("Function: [ln(x + 1) + 1], Root: [-0.63212],");
        checkFunction();
        break;
      case "FunctionThree":
        resultsText.setText("Function: [e^x - 3x], Roots: [0.61906] and [1.5121],");
        checkFunction();
        break;
      case "ClearGraph":
        resultsText.setText("Awaiting function selection");
        break;
    }
  }

  //Print out iteration count taken to converge
  public static void printIterationCount(Integer iteration) {
    int maxConvergeIteration = (Integer) maxConvergence.getValue();
    iteration -= 1; //Real iteration count

    if (iteration == maxConvergeIteration) {
      System.out.println("Max iterations Reached, could not calculate root");
    } else if (iteration > 3 && iteration < maxConvergeIteration) {
      System.out.println("Converged after [" + iteration + "] iterations");
    } else {
      System.out.println("Error, could not calculate root");
    }
  }

  //Remove data in an ArrayList
  public static void removeDataInArray(Double[] arrayData) {
    for (int i = 0; i < arrayData.length; i++) {
      arrayData[i] = null;
    }
  }

  //Clear data in a multi-dimensional array
  public static void removeDataInTable(Object[][] tableData) {
    for (int i = 0; i < tableData.length; i++) {
      for (int j = 0; j < tableData[i].length; j++) {
        tableData[i][j] = null;
      }
    }
  }

  //Remove data in SLinkedList
  public static void removeDataInLinkedList(SLinkedList listData) {
    while (countListTotal(listData) > 1) {
      listData.removeLast();
    }
  }

  public static void defaultMethodRuntimeData() {
    methodRuntimeData[0][0] = "Plotting \"x - x^2\"";
    methodRuntimeData[1][0] = "Plotting \"x - x^2\" Newton Raphson";
    methodRuntimeData[2][0] = "Plotting \"x - x^2\" Secant";
    methodRuntimeData[3][0] = "Plotting \"x - x^2\" Bisection";
    methodRuntimeData[4][0] = "Plotting \"x - x^2\" Steffensen";
    methodRuntimeData[5][0] = "Plotting \"ln(x + 1) + 1\"";
    methodRuntimeData[6][0] = "Plotting \"ln(x + 1) + 1\" Newton Raphson";
    methodRuntimeData[7][0] = "Plotting \"ln(x + 1) + 1\" Secant";
    methodRuntimeData[8][0] = "Plotting \"ln(x + 1) + 1\" Bisection";
    methodRuntimeData[9][0] = "Plotting \"ln(x + 1) + 1\" Steffensen";
    methodRuntimeData[10][0] = "Plotting \"e^x - 3x\"";
    methodRuntimeData[11][0] = "Plotting \"e^x - 3x\" Newton Raphson";
    methodRuntimeData[12][0] = "Plotting \"e^x - 3x\" Secant";
    methodRuntimeData[13][0] = "Plotting \"e^x - 3x\" Bisection";
    methodRuntimeData[14][0] = "Plotting \"e^x - 3x\" Steffensen";
  }

  public static void clearAllTableData() {
    removeDataInTable(newtonTableData);
    removeDataInTable(secantTableData);
    removeDataInTable(bisectionTableData);
    removeDataInTable(steffensenTableData);
    removeDataInTable(methodRuntimeData);
  }

  public static String convertBigDecimal(Double number) {
    return String.format("%.7f", (number));
  }

  public static String convertNoDecimal(Double number) {
    return String.format("%.0f", (number));
  }

  public static void checkFunction() {
    if (newtonRaphsonCheckBox.isSelected()) {
      resultsText.replaceSelection(resultsText.getText().substring(resultsText.getText().length()) + " Newton Raphson root: <[Root Approximation]>,");
    }

    if (secantCheckBox.isSelected()) {
      resultsText.replaceSelection(resultsText.getText().substring(resultsText.getText().length()) + " Secant root: <[Root Approximation]>,");
    }

    if (bisectionCheckBox.isSelected()) {
      resultsText.replaceSelection(resultsText.getText().substring(resultsText.getText().length()) + " Bisection root: <[Root Approximation]>,");
    }

    if (steffensenCheckBox.isSelected()) {
      resultsText.replaceSelection(resultsText.getText().substring(resultsText.getText().length()) + " Steffensen root: <[Root Approximation]>");
    }
  }
}
