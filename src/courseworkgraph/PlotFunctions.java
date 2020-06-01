package courseworkgraph;

public class PlotFunctions extends CourseworkGraph {

  public static void firstFunction() {
    clearPlotData();

    int iterations = (Integer) plotIterationSpinner.getValue() + 1;
    double accuracy = (Double) plotAccuracy.getValue();

    //<editor-fold defaultstate="collapsed" desc="Plot function onwards for (Iterations) times">
    for (double i = 0; i < iterations; i += accuracy) {
      double negative_i = (i * -1) + 1;

      double positive_Y = (i - (i * i));
      double negative_Y = (negative_i - (negative_i * negative_i));

      if (Double.isNaN(positive_Y) || Double.isNaN(negative_Y)) {
        break;
      } else if (positive_Y == Double.POSITIVE_INFINITY
              || negative_Y == Double.POSITIVE_INFINITY) {
        break;
      } else if (positive_Y == Double.NEGATIVE_INFINITY
              || negative_Y == Double.NEGATIVE_INFINITY) {
        break;
      }
      functionGraph.add(i, positive_Y);
      functionGraph.add(negative_i, negative_Y);
    }
    //</editor-fold>

    System.out.println("Function 'x - x^2' plotted");
  }

  public static void secondFunction() {
    clearPlotData();

    int iterations = (Integer) plotIterationSpinner.getValue();
    double accuracy = (Double) plotAccuracy.getValue();

    //<editor-fold defaultstate="collapsed" desc="Plot function onwards for (Iterations) times"> 
    for (double i = -0.9; i < iterations; i += accuracy) {
      double positive_Y = Math.log(i + 1) + 1;

      if (Double.isNaN(positive_Y)) {
        break;
      } else if (positive_Y == Double.POSITIVE_INFINITY) {
        break;
      } else if (positive_Y == Double.NEGATIVE_INFINITY) {
        break;
      }
      functionGraph.add(i, positive_Y);
    }
    //</editor-fold>

    System.out.println("Function 'ln(x + 1) + 1' plotted");
  }

  public static void thirdFunction() {
    clearPlotData();

    int iterations = (Integer) plotIterationSpinner.getValue();
    double accuracy = (Double) plotAccuracy.getValue();

    //<editor-fold defaultstate="collapsed" desc="Plot function onwards for (Iterations) times">
    for (double i = 0; i < iterations; i += accuracy) {
      double negative_i = (i * -1);

      double positive_Y = (Math.exp(i) - (3 * i));
      double negative_Y = (Math.exp(negative_i) - (3 * negative_i));

      if (Double.isNaN(positive_Y) || Double.isNaN(negative_Y)) {
        break;
      } else if (positive_Y == Double.POSITIVE_INFINITY
              || negative_Y == Double.POSITIVE_INFINITY) {
        break;
      } else if (positive_Y == Double.NEGATIVE_INFINITY
              || negative_Y == Double.NEGATIVE_INFINITY) {
        break;
      }
      functionGraph.add(i, positive_Y);
      functionGraph.add(negative_i, negative_Y);
    }
    //</editor-fold>

    System.out.println("Function 'e^x - 3x' plotted");
  }

  public static void clearPlotData() {
    functionGraph.clear();
    newtonRaphsonGraph.clear();
    secantGraph.clear();
    bisectionGraph.clear();
    steffensenGraph.clear();
  }
}