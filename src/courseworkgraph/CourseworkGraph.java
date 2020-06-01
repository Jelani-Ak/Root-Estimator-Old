package courseworkgraph;

//<editor-fold defaultstate="collapsed" desc="Imports">
import static courseworkgraph.AuxiliaryClass.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import slinkedlist.SLinkedList;
import static courseworkgraph.PlotFunctions.clearPlotData;
import static courseworkgraph.TableData.*;
import java.awt.BasicStroke;
import org.jfree.chart.plot.SeriesRenderingOrder;
import org.jfree.chart.plot.XYPlot;
//</editor-fold>

public class CourseworkGraph extends JFrame implements ActionListener {

  static SLinkedList newtonRaphsonLinkedList = new SLinkedList();
  static SLinkedList steffensenLinkedList = new SLinkedList();
  static Double[] secantArrayData = new Double[500];
  static Double[] bisectionArrayData = new Double[500];

  static JTextField x_ValueNewton = new JTextField("3");

  static JTextField x_ValueSecantOne = new JTextField("2");
  static JTextField x_ValueSecantTwo = new JTextField("8");

  static JTextField accuracyText = new JTextField("0.00001");

  static JTextField lowerBoundText = new JTextField("0.5");
  static JTextField upperBoundText = new JTextField("3");

  static JTextField x_ValueSteffensen = new JTextField("0.5");

  static JSpinner plotAccuracy = new JSpinner(new SpinnerNumberModel(0.05, 0.01, null, 0.05));
  static JSpinner plotIterationSpinner = new JSpinner(new SpinnerNumberModel(3, 1, null, 1));
  static JSpinner maxConvergence = new JSpinner(new SpinnerNumberModel(100, 1, 500, 1));
  static JSpinner decimalSpinner = new JSpinner(new SpinnerNumberModel(5, 0, null, 1));

  static JCheckBox newtonRaphsonCheckBox = new JCheckBox("Newton Raphson");
  static JCheckBox secantCheckBox = new JCheckBox("Secant");
  static JCheckBox bisectionCheckBox = new JCheckBox("Bisection");
  static JCheckBox steffensenCheckBox = new JCheckBox("Steffensen");

  XYSeriesCollection allGraph = new XYSeriesCollection();
  static XYSeries functionGraph = new XYSeries("Function");
  static XYSeries newtonRaphsonGraph = new XYSeries("Newton Raphson");
  static XYSeries secantGraph = new XYSeries("Secant");
  static XYSeries bisectionGraph = new XYSeries("Bisection");
  static XYSeries steffensenGraph = new XYSeries("Steffensen");

  static JTextField resultsText = new JTextField("Awaiting function selection");

  JFreeChart lineChart = ChartFactory.createXYLineChart(
          null, //Graph Name
          null, //X-Axis Name
          null, //Y-Axis Name
          allGraph, //Data collection to display
          PlotOrientation.VERTICAL, //Orientation
          true, //Legend
          true, //Tooltips
          false //URLs
  );

  boolean plotVisible = true;
  boolean plotRenderer = true;
  boolean functionVisible = true;
  boolean functionPlotVisible = false;

  XYLineAndShapeRenderer straightRenderer = new XYLineAndShapeRenderer();
  XYLineAndShapeRenderer curvedRenderer = new XYSplineRenderer();

  public static void main(String[] args) {
    CourseworkGraph cg = new CourseworkGraph();
  }

  public CourseworkGraph() {
    view();
  }

  private void view() {
    //<editor-fold defaultstate="collapsed" desc="North Panel Content">
    JLabel x_ValueNewtonLabel = new JLabel("Newton X");
    JLabel x_ValueSecantOneLabel = new JLabel("<html>Secant X<sub>n</sub></html>");
    JLabel x_ValueSecantTwoLabel = new JLabel("<html>Secant X<sub>n+1</sub></html>");

    x_ValueNewton.setPreferredSize(new Dimension(50, 27));
    x_ValueNewton.setHorizontalAlignment(JTextField.RIGHT);
    x_ValueNewton.setFont(arial);

    x_ValueSecantOne.setPreferredSize(new Dimension(50, 27));
    x_ValueSecantOne.setHorizontalAlignment(JTextField.RIGHT);
    x_ValueSecantOne.setFont(arial);

    x_ValueSecantTwo.setPreferredSize(new Dimension(50, 27));
    x_ValueSecantTwo.setHorizontalAlignment(JTextField.RIGHT);
    x_ValueSecantTwo.setFont(arial);

    lowerBoundText.setPreferredSize(new Dimension(50, 27));
    lowerBoundText.setHorizontalAlignment(JTextField.RIGHT);
    lowerBoundText.setFont(arial);

    upperBoundText.setPreferredSize(new Dimension(50, 27));
    upperBoundText.setHorizontalAlignment(JTextField.RIGHT);
    upperBoundText.setFont(arial);

    JLabel decimalPointsLabel = new JLabel("Decimal Places:");

    decimalSpinner.setPreferredSize(new Dimension(50, 27));
    decimalSpinner.setFont(arial);

    JLabel functionLabel = new JLabel("Functions:");
    JButton functionOne = makeButton(
            "<html>x - x<sup>2</sup></hmtl>",
            "FunctionOne",
            "Plot x - x^2");
    functionOne.addActionListener(this);

    JButton functionTwo = makeButton(
            "ln(x + 1) + 1",
            "FunctionTwo",
            "Plot ln(x + 1) + 1");
    functionTwo.setPreferredSize(new Dimension(100, 31));
    functionTwo.addActionListener(this);

    JButton functionThree = makeButton(
            "<html>e<sup>x</sup> - 3x</hmtl>",
            "FunctionThree",
            "Plot e^x - 3x");
    functionThree.addActionListener(this);

    JLabel lowerBoundLabel = new JLabel("Lower Bound:");
    JLabel upperBoundLabel = new JLabel("Upper Bound:");

    JLabel steffensenLabel = new JLabel("Steffensen X");

    x_ValueSteffensen.setPreferredSize(new Dimension(50, 27));
    x_ValueSteffensen.setHorizontalAlignment(JTextField.RIGHT);
    x_ValueSteffensen.setFont(arial);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Center[North] Panel Content">
    resultsText.setEditable(false);
    resultsText.setFont(arial);
    resultsText.setHorizontalAlignment(JTextField.CENTER);
    resultsText.setBorder(BorderFactory.createEmptyBorder(10, 0, 9, 0));

    ChartPanel graphPanel = new ChartPanel(lineChart);

    XYPlot plot = lineChart.getXYPlot();
    plot.setRangeGridlinePaint(Color.BLACK);
    plot.setDomainGridlinePaint(Color.BLACK);
    plot.setBackgroundPaint(Color.WHITE);
    plot.setSeriesRenderingOrder(SeriesRenderingOrder.FORWARD);
    plot.setRenderer(straightRenderer);

    straightRenderer.setSeriesPaint(0, Color.BLACK);
    straightRenderer.setSeriesPaint(1, Color.BLUE);
    straightRenderer.setSeriesPaint(2, Color.GREEN);
    straightRenderer.setSeriesPaint(3, Color.RED);
    straightRenderer.setSeriesPaint(4, Color.ORANGE);

    curvedRenderer.setSeriesPaint(0, Color.BLACK);
    curvedRenderer.setSeriesPaint(1, Color.BLUE);
    curvedRenderer.setSeriesPaint(2, Color.GREEN);
    curvedRenderer.setSeriesPaint(3, Color.RED);
    curvedRenderer.setSeriesPaint(4, Color.ORANGE);

    straightRenderer.setSeriesStroke(0, new BasicStroke(2));
    curvedRenderer.setSeriesStroke(0, new BasicStroke(2));

    allGraph.addSeries(functionGraph);
    allGraph.addSeries(newtonRaphsonGraph);
    allGraph.addSeries(secantGraph);
    allGraph.addSeries(bisectionGraph);
    allGraph.addSeries(steffensenGraph);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Center[South] Content">
    JLabel plotAccuracyLabel = new JLabel("Plot Ratio:");
    plotAccuracy.setPreferredSize(new Dimension(50, 27));
    plotAccuracy.setFont(arial);

    JLabel plotIterationLabel = new JLabel("Function Range:");
    plotIterationSpinner.setPreferredSize(new Dimension(50, 27));
    plotIterationSpinner.setFont(arial);

    JLabel maxConvergenceLabel = new JLabel("Max Converge Iterations:");

    maxConvergence.setPreferredSize(new Dimension(50, 27));
    maxConvergence.setFont(arial);

    JButton toggleRenderer = makeButton(
            "Toggle Renderer",
            "ToggleRenderer",
            "Toggles Between Straight or Curved Lines");
    toggleRenderer.addActionListener(this);

    JButton toggleFunction = makeButton(
            "Toggle Function",
            "ToggleFunction",
            "Toggles Function Visibliity");
    toggleFunction.addActionListener(this);

    JButton toggleFunctionShape = makeButton(
            "Toggle Function Plot",
            "ToggleFunctionShape",
            "Toggles Function Plot Shape Visibliity");
    toggleFunctionShape.addActionListener(this);

    JButton togglePlots = makeButton(
            "Toggle Method Plots",
            "TogglePlots",
            "Toggles Plot Shape Visibility");
    togglePlots.addActionListener(this);

    JButton tableData = makeButton(
            "Table",
            "TableDataButton",
            "Open Tabular Data window");
    tableData.addActionListener(this);

    JLabel accuracyTextLabel = new JLabel("Convergance:");

    accuracyText.setPreferredSize(new Dimension(100, 27));
    accuracyText.setHorizontalAlignment(JTextField.RIGHT);
    accuracyText.setFont(arial);

    JButton clearGraphAndTable = makeButton(
            "Clear All",
            "ClearAll",
            "Clear the Graph and Table");
    clearGraphAndTable.addActionListener(this);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Panel Instancing and Adding">
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel northPanel = new JPanel();

    JPanel centerPanel = new JPanel(new BorderLayout());
    centerPanel.setBorder((BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)));

    JPanel centerPanelUpper = new JPanel(new BorderLayout());
    JPanel centerPanelLower = new JPanel();
    centerPanelLower.setBorder((BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)));

    add(mainPanel);
    mainPanel.add(northPanel, BorderLayout.NORTH);
    mainPanel.add(centerPanel, BorderLayout.CENTER);

    northPanel.add(x_ValueNewtonLabel);
    northPanel.add(x_ValueNewton);
    northPanel.add(x_ValueSecantOneLabel);
    northPanel.add(x_ValueSecantOne);
    northPanel.add(x_ValueSecantTwoLabel);
    northPanel.add(x_ValueSecantTwo);

    northPanel.add(decimalPointsLabel);
    northPanel.add(decimalSpinner);

    northPanel.add(functionLabel);
    northPanel.add(functionOne);
    northPanel.add(functionTwo);
    northPanel.add(functionThree);

    northPanel.add(newtonRaphsonCheckBox);
    northPanel.add(secantCheckBox);
    northPanel.add(bisectionCheckBox);
    northPanel.add(steffensenCheckBox);

    northPanel.add(lowerBoundLabel);
    northPanel.add(lowerBoundText);
    northPanel.add(upperBoundLabel);
    northPanel.add(upperBoundText);

    northPanel.add(steffensenLabel);
    northPanel.add(x_ValueSteffensen);

    centerPanel.add(centerPanelUpper, BorderLayout.CENTER);
    centerPanel.add(centerPanelLower, BorderLayout.SOUTH);

    centerPanelUpper.add(resultsText, BorderLayout.NORTH);
    centerPanelUpper.add(graphPanel);

    centerPanelLower.add(plotAccuracyLabel);
    centerPanelLower.add(plotAccuracy);

    centerPanelLower.add(plotIterationLabel);
    centerPanelLower.add(plotIterationSpinner);

    centerPanelLower.add(maxConvergenceLabel);
    centerPanelLower.add(maxConvergence);

    centerPanelLower.add(toggleRenderer);
    centerPanelLower.add(toggleFunction);
    centerPanelLower.add(toggleFunctionShape);
    centerPanelLower.add(togglePlots);

    centerPanelLower.add(tableData);

    centerPanelLower.add(accuracyTextLabel);
    centerPanelLower.add(accuracyText);

    centerPanelLower.add(clearGraphAndTable);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Default Window Configurations">
    setSize(1650, 1050);
    setTitle("Root Finder");
    setExtendedState(MAXIMIZED_BOTH);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    //</editor-fold>
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    //<editor-fold defaultstate="collapsed" desc="Plotting Function and Numerical Methods">
    if (ae.getActionCommand().equals("FunctionOne") || ae.getActionCommand().equals("FunctionTwo") || ae.getActionCommand().equals("FunctionThree")) {
      try {
        setResultsText(ae);
        setTabbedPaneText(ae);
        switch (ae.getActionCommand()) {
          case "FunctionOne":
            double startTimerOne = System.nanoTime();
            PlotFunctions.firstFunction();
            double endTimerOne = System.nanoTime();
            double nanoSecondsTakenOne = (endTimerOne - startTimerOne);
            double milliSecondsTakenOne = ((endTimerOne - startTimerOne) / 1_000_000);
            double secondsTakenOne = ((endTimerOne - startTimerOne) / 1_000_000_000);

            methodRuntimeData[0][1] = convertNoDecimal(nanoSecondsTakenOne);
            methodRuntimeData[0][2] = convertBigDecimal(milliSecondsTakenOne);
            methodRuntimeData[0][3] = convertBigDecimal(secondsTakenOne);

            //<editor-fold defaultstate="collapsed" desc="Function One Checkbox Selection">
            if (newtonRaphsonCheckBox.isSelected()) {
              double startTimerTwo = System.nanoTime();
              NewtonRaphson.firstFunction();
              double endTimerTwo = System.nanoTime();
              double nanoSecondsTakenTwo = (endTimerTwo - startTimerTwo);
              double milliSecondsTakenTwo = ((endTimerTwo - startTimerTwo) / 1_000_000);
              double secondsTakenTwo = ((endTimerTwo - startTimerTwo) / 1_000_000_000);

              methodRuntimeData[1][1] = convertNoDecimal(nanoSecondsTakenTwo);
              methodRuntimeData[1][2] = convertBigDecimal(milliSecondsTakenTwo);
              methodRuntimeData[1][3] = convertBigDecimal(secondsTakenTwo);
            }

            if (secantCheckBox.isSelected()) {
              double startTimerThree = System.nanoTime();
              Secant.firstFunction();
              double endTimerThree = System.nanoTime();
              double nanoSecondsTakenThree = (endTimerThree - startTimerThree);
              double milliSecondsTakenThree = ((endTimerThree - startTimerThree) / 1_000_000);
              double secondsTakenThree = ((endTimerThree - startTimerThree) / 1_000_000_000);

              methodRuntimeData[2][1] = convertNoDecimal(nanoSecondsTakenThree);
              methodRuntimeData[2][2] = convertBigDecimal(milliSecondsTakenThree);
              methodRuntimeData[2][3] = convertBigDecimal(secondsTakenThree);
            }

            if (bisectionCheckBox.isSelected()) {
              double startTimerFour = System.nanoTime();
              Bisection.firstFunction();
              double endTimerFour = System.nanoTime();
              double nanoSecondsTakenFour = (endTimerFour - startTimerFour);
              double milliSecondsTakenFour = ((endTimerFour - startTimerFour) / 1_000_000);
              double secondsTakenFour = ((endTimerFour - startTimerFour) / 1_000_000_000);

              methodRuntimeData[3][1] = convertNoDecimal(nanoSecondsTakenFour);
              methodRuntimeData[3][2] = convertBigDecimal(milliSecondsTakenFour);
              methodRuntimeData[3][3] = convertBigDecimal(secondsTakenFour);
            }

            if (steffensenCheckBox.isSelected()) {
              double startTimerFour = System.nanoTime();
              Steffensen.firstFunction();
              double endTimerFour = System.nanoTime();
              double nanoSecondsTakenFour = (endTimerFour - startTimerFour);
              double milliSecondsTakenFour = ((endTimerFour - startTimerFour) / 1_000_000);
              double secondsTakenFour = ((endTimerFour - startTimerFour) / 1_000_000_000);

              methodRuntimeData[4][1] = convertNoDecimal(nanoSecondsTakenFour);
              methodRuntimeData[4][2] = convertBigDecimal(milliSecondsTakenFour);
              methodRuntimeData[4][3] = convertBigDecimal(secondsTakenFour);
            }
            //</editor-fold>
            break;

          case "FunctionTwo":
            double startTimerFive = System.nanoTime();
            PlotFunctions.secondFunction();
            double endTimerFive = System.nanoTime();
            double nanoSecondsTakenFive = (endTimerFive - startTimerFive);
            double milliSecondsTakenFive = ((endTimerFive - startTimerFive) / 1_000_000);
            double secondsTakenFive = ((endTimerFive - startTimerFive) / 1_000_000_000);

            methodRuntimeData[5][1] = convertNoDecimal(nanoSecondsTakenFive);
            methodRuntimeData[5][2] = convertBigDecimal(milliSecondsTakenFive);
            methodRuntimeData[5][3] = convertBigDecimal(secondsTakenFive);

            //<editor-fold defaultstate="collapsed" desc="Function Two Checkbox Selection">
            if (newtonRaphsonCheckBox.isSelected()) {
              double startTimerSix = System.nanoTime();
              NewtonRaphson.secondFunction();
              double endTimerSix = System.nanoTime();
              double nanoSecondsTakenSix = (endTimerSix - startTimerSix);
              double milliSecondsTakenSix = ((endTimerSix - startTimerSix) / 1_000_000);
              double secondsTakenSix = ((endTimerSix - startTimerSix) / 1_000_000_000);

              methodRuntimeData[6][1] = convertNoDecimal(nanoSecondsTakenSix);
              methodRuntimeData[6][2] = convertBigDecimal(milliSecondsTakenSix);
              methodRuntimeData[6][3] = convertBigDecimal(secondsTakenSix);
            }

            if (secantCheckBox.isSelected()) {
              double startTimerSeven = System.nanoTime();
              Secant.secondFunction();
              double endTimerSeven = System.nanoTime();
              double nanoSecondsTakenSeven = (endTimerSeven - startTimerSeven);
              double milliSecondsTakenSeven = ((endTimerSeven - startTimerSeven) / 1_000_000);
              double secondsTakenSeven = ((endTimerSeven - startTimerSeven) / 1_000_000_000);

              methodRuntimeData[7][1] = convertNoDecimal(nanoSecondsTakenSeven);
              methodRuntimeData[7][2] = convertBigDecimal(milliSecondsTakenSeven);
              methodRuntimeData[7][3] = convertBigDecimal(secondsTakenSeven);
            }

            if (bisectionCheckBox.isSelected()) {
              double startTimerEight = System.nanoTime();
              Bisection.secondFunction();
              double endTimerEight = System.nanoTime();
              double nanoSecondsTakenEight = (endTimerEight - startTimerEight);
              double milliSecondsTakenEight = ((endTimerEight - startTimerEight) / 1_000_000);
              double secondsTakenEight = ((endTimerEight - startTimerEight) / 1_000_000_000);

              methodRuntimeData[8][1] = convertNoDecimal(nanoSecondsTakenEight);
              methodRuntimeData[8][2] = convertBigDecimal(milliSecondsTakenEight);
              methodRuntimeData[8][3] = convertBigDecimal(secondsTakenEight);
            }

            if (steffensenCheckBox.isSelected()) {
              double startTimerFour = System.nanoTime();
              Steffensen.secondFunction();
              double endTimerFour = System.nanoTime();
              double nanoSecondsTakenFour = (endTimerFour - startTimerFour);
              double milliSecondsTakenFour = ((endTimerFour - startTimerFour) / 1_000_000);
              double secondsTakenFour = ((endTimerFour - startTimerFour) / 1_000_000_000);

              methodRuntimeData[9][1] = convertNoDecimal(nanoSecondsTakenFour);
              methodRuntimeData[9][2] = convertBigDecimal(milliSecondsTakenFour);
              methodRuntimeData[9][3] = convertBigDecimal(secondsTakenFour);
            }
            //</editor-fold>
            break;

          case "FunctionThree":
            double startTimerNine = System.nanoTime();
            PlotFunctions.thirdFunction();
            double endTimerThreeNine = System.nanoTime();
            double nanoSecondsTakenNine = (endTimerThreeNine - startTimerNine);
            double milliSecondsTakenNine = ((endTimerThreeNine - startTimerNine) / 1_000_000);
            double secondsTakenNine = ((endTimerThreeNine - startTimerNine) / 1_000_000_000);

            methodRuntimeData[10][1] = convertNoDecimal(nanoSecondsTakenNine);
            methodRuntimeData[10][2] = convertBigDecimal(milliSecondsTakenNine);
            methodRuntimeData[10][3] = convertBigDecimal(secondsTakenNine);

            //<editor-fold defaultstate="collapsed" desc="Function Three Checkbox Selection">
            if (newtonRaphsonCheckBox.isSelected()) {
              double startTimerTen = System.nanoTime();
              NewtonRaphson.thirdFunction();
              double endTimerTen = System.nanoTime();
              double nanoSecondsTakenTen = (endTimerTen - startTimerTen);
              double milliSecondsTakenTen = ((endTimerTen - startTimerTen) / 1_000_000);
              double secondsTakenTen = ((endTimerTen - startTimerTen) / 1_000_000_000);

              methodRuntimeData[11][1] = convertNoDecimal(nanoSecondsTakenTen);
              methodRuntimeData[11][2] = convertBigDecimal(milliSecondsTakenTen);
              methodRuntimeData[11][3] = convertBigDecimal(secondsTakenTen);
            }

            if (secantCheckBox.isSelected()) {
              double startTimerEleven = System.nanoTime();
              Secant.thirdFunction();
              double endTimerEleven = System.nanoTime();
              double nanoSecondsTakenEleven = (endTimerEleven - startTimerEleven);
              double milliSecondsTakenEleven = ((endTimerEleven - startTimerEleven) / 1_000_000);
              double secondsTakenEleven = ((endTimerEleven - startTimerEleven) / 1_000_000_000);

              methodRuntimeData[12][1] = convertNoDecimal(nanoSecondsTakenEleven);
              methodRuntimeData[12][2] = convertBigDecimal(milliSecondsTakenEleven);
              methodRuntimeData[12][3] = convertBigDecimal(secondsTakenEleven);
            }

            if (bisectionCheckBox.isSelected()) {
              double startTimerTwelve = System.nanoTime();
              Bisection.thirdFunction();
              double endTimerTwelve = System.nanoTime();
              double nanoSecondsTakenTwelve = (endTimerTwelve - startTimerTwelve);
              double milliSecondsTakenTwelve = ((endTimerTwelve - startTimerTwelve) / 1_000_000);
              double secondsTakenTwelve = ((endTimerTwelve - startTimerTwelve) / 1_000_000_000);

              methodRuntimeData[13][1] = convertNoDecimal(nanoSecondsTakenTwelve);
              methodRuntimeData[13][2] = convertBigDecimal(milliSecondsTakenTwelve);
              methodRuntimeData[13][3] = convertBigDecimal(secondsTakenTwelve);
            }

            if (steffensenCheckBox.isSelected()) {
              double startTimerFour = System.nanoTime();
              Steffensen.thirdFunction();
              double endTimerFour = System.nanoTime();
              double nanoSecondsTakenFour = (endTimerFour - startTimerFour);
              double milliSecondsTakenFour = ((endTimerFour - startTimerFour) / 1_000_000);
              double secondsTakenFour = ((endTimerFour - startTimerFour) / 1_000_000_000);

              methodRuntimeData[14][1] = convertNoDecimal(nanoSecondsTakenFour);
              methodRuntimeData[14][2] = convertBigDecimal(milliSecondsTakenFour);
              methodRuntimeData[14][3] = convertBigDecimal(secondsTakenFour);
            }
            //</editor-fold>
            break;
        }
      } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(null, nfe.getMessage(),
                "Non-integer found",
                JOptionPane.ERROR_MESSAGE);
      }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Toggle Function Visibility">
    if (ae.getActionCommand().equals("ToggleFunction")) {
      if (functionVisible) {
        straightRenderer.setSeriesVisible(0, false);
        curvedRenderer.setSeriesVisible(0, false);

        functionVisible = false;

        System.out.println("Function Visibility off");
      } else if (!functionVisible) {
        straightRenderer.setSeriesVisible(0, true);
        curvedRenderer.setSeriesVisible(0, true);

        functionVisible = true;

        System.out.println("Function Visibility on");
      }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Toggle Function Plot Shape Visibility">
    if (ae.getActionCommand().equals("ToggleFunctionShape")) {
      if (functionPlotVisible) {
        straightRenderer.setSeriesShapesVisible(0, false);
        curvedRenderer.setSeriesShapesVisible(0, false);

        functionPlotVisible = false;

        System.out.println("Function Shapes Turned Off");
      } else if (!functionPlotVisible) {
        straightRenderer.setSeriesShapesVisible(0, true);
        curvedRenderer.setSeriesShapesVisible(0, true);

        functionPlotVisible = true;

        System.out.println("Function Shapes Turned On");
      }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Toggle Numerical Method Plot Shapes">
    if (ae.getActionCommand().equals("TogglePlots")) {
      if (plotVisible) {
        for (int i = 1; i <= allGraph.getSeriesCount(); i++) {
          straightRenderer.setSeriesShapesVisible(i, false);
          curvedRenderer.setSeriesShapesVisible(i, false);
        }

        plotVisible = false;

        System.out.println("Numerical Method Shapes Turned Off");
      } else if (!plotVisible) {
        for (int i = 1; i <= allGraph.getSeriesCount(); i++) {
          straightRenderer.setSeriesShapesVisible(i, true);
          curvedRenderer.setSeriesShapesVisible(i, true);
        }

        plotVisible = true;

        System.out.println("Numerical Method Shapes Turned On");
      }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Toggle Straight and Curved Lines">
    if (ae.getActionCommand().equals("ToggleRenderer")) {
      if (plotRenderer) {
        lineChart.getXYPlot().setRenderer(curvedRenderer);
        plotRenderer = false;

        System.out.println("Renderer set to Curved Lines");
      } else if (!plotRenderer) {
        lineChart.getXYPlot().setRenderer(straightRenderer);
        plotRenderer = true;

        System.out.println("Renderer set to Straight Lines");
      }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Open Table Data Window">
    if (ae.getActionCommand().equals("TableDataButton")) {
      TableData td = new TableData();
      System.out.println("Table Data opened");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Clear Graph">
    if (ae.getActionCommand().equals("ClearAll")) {
      clearPlotData();
      clearAllTableData();
      setTabbedPaneText(ae);
      defaultMethodRuntimeData();
      System.out.println("Graph and Table Cleared");
    }
    //</editor-fold>
  }
}
