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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import static slinkedlist.SLinkedList.*;
//</editor-fold>

class TableData extends JFrame implements ActionListener {

  static JTextField functionCurrentTabOne = new JTextField("Awaiting function selection");
  static JTextField functionCurrentTabTwo = new JTextField("Awaiting function selection");
  static JTextField functionCurrentTabThree = new JTextField("Awaiting function selection");
  static JTextField functionCurrentTabFour = new JTextField("Awaiting function selection");
  static JTextField functionCurrentTabFive = new JTextField("Awaiting function selection");

  String[] newtonColumnNames = {"Iteration", "x[n-1]", "x[n]", "x[n+1] (Root)", "Difference", "f(x)", "f(x[n+1]", "(dy/dx)"};
  String[] secantColumnNames = {"Iteration", "x[n-1]", "x[n]", "Difference", "x[n+1] (Root)", "f(x[n])", "f(x[n-1])"};
  String[] bisectionColumnNames = {"Iteration", "f(m)", "Difference", "Lower-Bound", "Midpoint (Root)", "Upper-Bound"};
  String[] steffensenColumnNames = {"Iteration", "x[n-1]", "x[n]", "x[n+1] (Root)", "Difference", "f(x[n])"};
  String[] methodRuntimeColumnNames = {"Method", "Time (Nanoseconds)", "Time (Milliseconds)", "Time (Seconds)"};

  static Object[][] newtonTableData = new Object[500][8];
  static Object[][] secantTableData = new Object[500][7];
  static Object[][] bisectionTableData = new Object[500][6];
  static Object[][] steffensenTableData = new Object[500][6];
  static Object[][] methodRuntimeData = new Object[44][4];

  public TableData() {
    model();
    view();
  }

  private void model() {
    //<editor-fold defaultstate="collapsed" desc="Default Method Runtime Table Data">
    defaultMethodRuntimeData();
    //</editor-fold>
  }

  private void view() {
    //<editor-fold defaultstate="collapsed" desc="[Referrenced code] Alternating Table Row Colours">
    UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    if (defaults.get("Table.alternateRowColor") == null) {
      defaults.put("Table.alternateRowColor", new Color(240, 240, 240));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Center Panel Content">
    functionCurrentTabOne.setEditable(false);
    functionCurrentTabOne.setFont(arial);
    functionCurrentTabOne.setHorizontalAlignment(JTextField.CENTER);
    functionCurrentTabOne.setBorder(BorderFactory.createEmptyBorder(10, 0, 9, 0));

    functionCurrentTabTwo.setEditable(false);
    functionCurrentTabTwo.setFont(arial);
    functionCurrentTabTwo.setHorizontalAlignment(JTextField.CENTER);
    functionCurrentTabTwo.setBorder(BorderFactory.createEmptyBorder(10, 0, 9, 0));

    functionCurrentTabThree.setEditable(false);
    functionCurrentTabThree.setFont(arial);
    functionCurrentTabThree.setHorizontalAlignment(JTextField.CENTER);
    functionCurrentTabThree.setBorder(BorderFactory.createEmptyBorder(10, 0, 9, 0));

    functionCurrentTabFour.setEditable(false);
    functionCurrentTabFour.setFont(arial);
    functionCurrentTabFour.setHorizontalAlignment(JTextField.CENTER);
    functionCurrentTabFour.setBorder(BorderFactory.createEmptyBorder(10, 0, 9, 0));

    functionCurrentTabFive.setEditable(false);
    functionCurrentTabFive.setFont(arial);
    functionCurrentTabFive.setHorizontalAlignment(JTextField.CENTER);
    functionCurrentTabFive.setBorder(BorderFactory.createEmptyBorder(10, 0, 9, 0));

    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel tabOne = new JPanel(new BorderLayout());
    JPanel tabTwo = new JPanel(new BorderLayout());
    JPanel tabThree = new JPanel(new BorderLayout());
    JPanel tabFour = new JPanel(new BorderLayout());
    JPanel tabFive = new JPanel(new BorderLayout());

    JTable newtonTable = new JTable(newtonTableData, newtonColumnNames);
    newtonTable.setFont(arial);
    newtonTable.setEnabled(false);
    newtonTable.setShowHorizontalLines(false);
    newtonTable.getColumnModel().getColumn(0).setPreferredWidth(40);

    JTable secantTable = new JTable(secantTableData, secantColumnNames);
    secantTable.setFont(arial);
    secantTable.setEnabled(false);
    secantTable.setShowHorizontalLines(false);
    secantTable.getColumnModel().getColumn(0).setPreferredWidth(40);

    JTable bisectionTable = new JTable(bisectionTableData, bisectionColumnNames);
    bisectionTable.setFont(arial);
    bisectionTable.setEnabled(false);
    bisectionTable.setShowHorizontalLines(false);
    bisectionTable.getColumnModel().getColumn(0).setPreferredWidth(1);

    JTable steffensenTable = new JTable(steffensenTableData, steffensenColumnNames);
    steffensenTable.setFont(arial);
    steffensenTable.setEnabled(false);
    steffensenTable.setShowHorizontalLines(false);
    steffensenTable.getColumnModel().getColumn(0).setPreferredWidth(1);

    JTable methodRuntimeTable = new JTable(methodRuntimeData, methodRuntimeColumnNames);
    methodRuntimeTable.setFont(arial);
    methodRuntimeTable.setEnabled(false);
    methodRuntimeTable.setShowHorizontalLines(false);
    methodRuntimeTable.getColumnModel().getColumn(0).setPreferredWidth(200);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="South[North] Panel Content">
    JPanel buttonsRowOne = new JPanel();
    JPanel buttonsRowTwo = new JPanel();
    buttonsRowOne.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

    int buttonWidth = 192;
    int buttonHeight = 26;

    JButton newtonPrint = makeButton(
            "Print Newton List",
            "PrintNewton",
            "Print Newton Raphson List");
    newtonPrint.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    newtonPrint.addActionListener(this);

    JButton secantPrint = makeButton(
            "Print Secant Array",
            "PrintSecant",
            "Print Secant Array");
    secantPrint.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    secantPrint.addActionListener(this);

    JButton bisectionPrint = makeButton(
            "Print Bisection Array",
            "PrintBisection",
            "Print Bisection Array");
    bisectionPrint.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    bisectionPrint.addActionListener(this);

    JButton steffensenPrint = makeButton(
            "Print Steffensen List",
            "PrintSteffensen",
            "Print Steffensen List");
    steffensenPrint.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    steffensenPrint.addActionListener(this);
    //</editor-fold> 

    //<editor-fold defaultstate="collapsed" desc="South[South] Panel Content">
    JButton newtonClear = makeButton(
            "Clear Newton List",
            "ClearNewton",
            "Clear Newton Raphson List");
    newtonClear.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    newtonClear.addActionListener(this);

    JButton secantClear = makeButton(
            "Clear Secant Array",
            "ClearSecant",
            "Clear Secant Array");
    secantClear.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    secantClear.addActionListener(this);

    JButton bisectionClear = makeButton(
            "Clear Bisection Array",
            "ClearBisection",
            "Clear Bisection Array");
    bisectionClear.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    bisectionClear.addActionListener(this);

    JButton steffensenClear = makeButton(
            "Clear Steffensen List",
            "ClearSteffensen",
            "Clear Steffensen List");
    steffensenClear.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    steffensenClear.addActionListener(this);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Panel Instancing and Adding">
    JPanel mainPanel = new JPanel();
    JPanel southPanel = new JPanel(new BorderLayout());

    add(mainPanel, BorderLayout.NORTH);
    add(tabbedPane, BorderLayout.CENTER);
    add(southPanel, BorderLayout.SOUTH);

    southPanel.add(buttonsRowOne, BorderLayout.NORTH);
    southPanel.add(buttonsRowTwo, BorderLayout.SOUTH);

    buttonsRowOne.add(newtonPrint);
    buttonsRowOne.add(secantPrint);
    buttonsRowOne.add(bisectionPrint);
    buttonsRowOne.add(steffensenPrint);

    buttonsRowTwo.add(newtonClear);
    buttonsRowTwo.add(secantClear);
    buttonsRowTwo.add(bisectionClear);
    buttonsRowTwo.add(steffensenClear);

    tabOne.add(functionCurrentTabOne, BorderLayout.NORTH);
    tabOne.add(new JScrollPane(newtonTable));

    tabTwo.add(functionCurrentTabTwo, BorderLayout.NORTH);
    tabTwo.add(new JScrollPane(secantTable));

    tabThree.add(functionCurrentTabThree, BorderLayout.NORTH);
    tabThree.add(new JScrollPane(bisectionTable));

    tabFour.add(functionCurrentTabFour, BorderLayout.NORTH);
    tabFour.add(new JScrollPane(steffensenTable));

    tabFive.add(functionCurrentTabFive, BorderLayout.NORTH);
    tabFive.add(new JScrollPane(methodRuntimeTable));

    tabbedPane.add("Newton Raphson", tabOne);
    tabbedPane.add("Secant", tabTwo);
    tabbedPane.add("Bisection", tabThree);
    tabbedPane.add("Steffensen", tabFour);
    tabbedPane.add("Method Runtime", tabFive);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Default Window Configurations">
    setSize(825, 903); //Window Dimensions
//    setResizable(false);
    setLocationRelativeTo(null); //Centers the window
    setTitle("Tabular Data"); //Title
    setVisible(true); //Display everything
    //</editor-fold>
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    //<editor-fold defaultstate="collapsed" desc="Print Newton Button">
    if (ae.getActionCommand().equals("PrintNewton")) { //If Print List button is pressed
      if (countListTotal(newtonRaphsonLinkedList) == 1) { //If size of linked list is 1
        System.out.println("[Newton Raphon List] Empty: Nothing to Show"); //Print 'list is empty'
      } else if (countListTotal(newtonRaphsonLinkedList) > 1) { //Else if size of list is greater than 1
        System.out.print("[Newton Raphon List] inkedList Data: "); //Print linked list content
        printList(newtonRaphsonLinkedList); //Print the linked list
      }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Print Steffesnsen Button">
    if (ae.getActionCommand().equals("PrintSteffensen")) { //If Print List button is pressed
      if (countListTotal(steffensenLinkedList) == 1) {
        System.out.println("[Steffensen List] Empty: Nothing to Show"); //Print 'list is empty'
      } else if (countListTotal(steffensenLinkedList) > 1) {
        System.out.print("[Steffensen List] Data: "); //Print linked list content
        printList(steffensenLinkedList); //Print the linked list
      }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Print Secant Button">
    if (ae.getActionCommand().equals("PrintSecant")) {
      if (secantArrayData[0] == null) {
        System.out.println("[Secant Array] Empty: Nothing to show");

      } else if (secantArrayData[0] != null) {
        System.out.print("[Secant Array] Data: ");

        for (int i = 0; i < secantArrayData.length; i++) {
          if (secantArrayData[i] == null) {
            break;
          }
          System.out.print("[" + secantArrayData[i] + "] ");
        }
        System.out.println();
      }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Print Bisection Button">
    if (ae.getActionCommand().equals("PrintBisection")) {
      if (bisectionArrayData[0] == null) {
        System.out.println("[Bisection Array] Empty: Nothing to show");

      } else if (bisectionArrayData[0] != null) {
        System.out.print("[Bisection Array] Data: ");

        for (int i = 0; i < bisectionArrayData.length; i++) {
          if (bisectionArrayData[i] == null) {
            break;
          }
          System.out.print("[" + bisectionArrayData[i] + "] ");
        }
        System.out.println();
      }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Clear Newton Button">
    if (ae.getActionCommand().equals("ClearNewton")) { //If Clear List button is pressed
      if (countListTotal(newtonRaphsonLinkedList) == 1) { //If size of linked list is 1
        System.out.println("[Newton Raphon List] Empty: Nothing to Clear"); //Print 'nothing to clear'
      } else if (countListTotal(newtonRaphsonLinkedList) > 1) { //Else if size of linked list is greater than 1
        System.out.println("[Newton Raphon List] Data Cleared"); //Print linked list data cleared
        removeDataInLinkedList(newtonRaphsonLinkedList); //Remove first node
      }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Clear Steffensen Button">
    if (ae.getActionCommand().equals("ClearSteffensen")) {
      if (countListTotal(steffensenLinkedList) == 1) { //If size of linked list is 1
        System.out.println("[Steffensen List] Empty: Nothing to Clear"); //Print 'nothing to clear'
      } else if (countListTotal(steffensenLinkedList) > 1) { //Else if size of linked list is greater than 1
        System.out.println("[Steffensen List] Data Cleared"); //Print linked list data cleared
        removeDataInLinkedList(steffensenLinkedList); //Remove first node
      }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Clear Secant Button">
    if (ae.getActionCommand().equals("ClearSecant")) {
      if (secantArrayData[0] == null) {
        System.out.println("[Secant Array] Empty: Nothing to Clear");
      } else if (secantArrayData[0] != null) {
        System.out.println("[Secant Array] Data Cleared");
        removeDataInArray(secantArrayData);
      }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Clear Bisection Button">
    if (ae.getActionCommand().equals("ClearBisection")) {
      if (bisectionArrayData[0] == null) {
        System.out.println("[Bisection Array] Empty: Nothing to Clear");
      } else if (bisectionArrayData[0] != null) {
        System.out.println("[Bisection Array] Data Cleared");
        removeDataInArray(bisectionArrayData);
      }
    }
    //</editor-fold>
  }
}
