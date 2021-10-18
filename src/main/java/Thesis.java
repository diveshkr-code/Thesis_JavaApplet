import java.awt.*;
import java.text.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import javax.swing.table.*;
import javax.swing.border.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;
import org.jfree.data.statistics.*;

public class Thesis extends JApplet implements FocusListener, ActionListener {

        // class variables
        // panels
        private JPanel mainPanel = null;
        private JPanel inputPanel = null;
        private JPanel stochasticPanel = null;
        private JPanel bsPanel = null;
        private JPanel bsstochPanel = null;
        private JPanel svstochdataPanel = null;
        private JPanel powerPanel = null;

        private JPanel outputPanel = null;
        private JPanel output1Panel = null;
        private JPanel buttonPanel = null;
        private JPanel button1Panel = null;
        private JPanel dataPanel = null;
        private JPanel data1Panel = null;
        private JPanel data2Panel = null;
        private JPanel data3Panel = null;
        private JPanel data4Panel = null;
        private JPanel button2Panel = null;
        private JPanel button3Panel = null;
        private JPanel button4Panel = null;

        // private ChartPanel graphPanel = null;
        // button groups
        private ButtonGroup fileGroup = null;
        private ButtonGroup methodGroup = null;
        private ButtonGroup graphGroup = null;
        private ButtonGroup typeGroup = null;
        private ButtonGroup normalDistributionGroup = null;
        // radio buttons
        private JRadioButton callButton = null;
        private JRadioButton putButton = null;
        private JRadioButton normalDistributionAButton = null;
        private JRadioButton normalDistributionBButton = null;
        // buttons
        private JButton calculateButton = null;
        private JButton calculate1Button = null;
        private JButton calculate2Button = null;
        private JButton calculate3Button = null;
        private JButton calculate4Button = null;
        private JButton resetButton = null;
        // string constants
        private final String CALL = "Call";
        private final String PUT = "Put";
        private final String normalDistributionA = " CND A";
        private final String normalDistributionB = "CND B";
        private final String CALCULATE = "Calculate All";
        private final String CALCULATE1 = "Calc. BS";
        private final String CALCULATE2 = "Calc. SV";
        private final String CALCULATE3 = "Calc. SSV";
        private final String CALCULATE4 = "Calc. PS";
        private final String RESET = "Reset";
        private final String INPUT = "Input";
        private final String OUTPUT = "Output";
        private final String ERROR = "Error";

        // texts of labels
        private final String SIMULATIONS1_LABEL = " Simulations1";
        private JTextField simulations1Field = null;
        private final String SIMULATIONS2_LABEL = " Simulations2";
        private JTextField simulations2Field = null;
        private final String RHO_LABEL = "Rho";
        private JTextField rhoField = null;

        //THE EXTRA
        private final String ALPHAONE_LABEL = "Alpha One";
        private JTextField alphaoneField = null;
        private final String ALPHATWO_LABEL = "Alpha Two";
        private JTextField alphatwoField = null;
        private final String ALPHATHREE_LABEL = "Alpha Three";
        private JTextField alphathreeField = null;
        private final String SIGMASTAR1_LABEL = "Sigma Star1";
        private JTextField sigmastar1Field = null;
        private final String SIGMASTAR2_LABEL = "Sigma Star2";
        private JTextField sigmastar2Field = null;
        private final String KSI1_LABEL = " Ksi1";
        private JTextField ksi1Field = null;
        private final String KSI2_LABEL = " Ksi2";
        private JTextField ksi2Field = null;
        private final String KSI3_LABEL = " Ksi3";
        private JTextField ksi3Field = null;
        private final String STOCKPRICE_LABEL = "Stock price";
        private JTextField stockpriceField = null;
        private final String STRIKEPRICE_LABEL = "Strike price";
        private JTextField strikepriceField = null;
        private final String VOLATILITY_LABEL = "Volatility(%)";
        private JTextField volatilityField = null;
        private final String TIMETOMATURITY_LABEL = "Maturity (days)";
        private JTextField timetomaturityField = null;
        private final String INTERESTRATE_LABEL = "Interest(%)";
        private JTextField interestrateField = null;
        private final String INTERVALS1_LABEL = "Intervals1";
        private JTextField intervals1Field = null;
        private final String INTERVALS2_LABEL = "Intervals2";
        private JTextField intervals2Field = null;
        private final String BSPRICE_LABEL = "BS Price";
        private JTextField bspriceField = null;

        private final String SVPRICE_LABEL = "SV Price";
        private JTextField svpriceField = null;
        private final String SSVPRICE_LABEL = "SSV Price";
        private JTextField ssvpriceField = null;
        private final String PSPRICE_LABEL = "PS Price";
        private JTextField pspriceField = null;
        private final String TYPEOFOPTION_LABEL = "Type of Option";
        private final String NORMALDISTRIBUTION_LABEL = "Cumulative Normal Distribution";

        // Tooltips
        private final String SIMULATIONS_TOOLTIP ="The Recommended value is >20000";
        private final String STOCKPRICE_TOOLTIP = "Current price of the underlying";
        private final String STRIKEPRICE_TOOLTIP = "Exercise price";
        private final String VOLATILITY_TOOLTIP = "Insert the volatility";
        private final String TIMETOMATURITY_TOOLTIP = "Time expressed in days";
        private final String INTERESTRATE_TOOLTIP = "Interest rate expressed in %";
        private final String ALPHAONE_TOOLTIP = "Estimated or observed value of alpha one";
        private final String ALPHATWO_TOOLTIP = "Estimated or observed value of alpha two";
        private final String BSPRICE_TOOLTIP = "DO NOT INSERT ANY VALUE HERE";
        private final String SVPRICE_TOOLTIP = "DO NOT INSERT ANY VALUE HERE";
        private final String SSVPRICE_TOOLTIP = "DO NOT INSERT ANY VALUE HERE";
        private final String PSPRICE_TOOLTIP = "DO NOT INSERT ANY VALUE HERE";
        private final String INTERVALS1_TOOLTIP = "Integer number of time intervals ";
        private final String INTERVALS2_TOOLTIP = "Integer number of time intervals ";
        private final String SIMULATIONS1_TOOLTIP = "Integer number of simulations ";
        private final String SIMULATIONS2_TOOLTIP = "Integer number of simulations ";
        private final String RHO_TOOLTIP = "Estimated or observed value of rho";
        private final String SIGMASTAR1_TOOLTIP = "Integer number of time intervals ";
        private final String SIGMASTAR2_TOOLTIP = "Integer number of time intervals ";
        private final String KSI1_TOOLTIP = "Integer number of time intervals ";
        private final String KSI2_TOOLTIP = "Integer number of time intervals ";
        private final String KSI3_TOOLTIP = "Integer number of time intervals ";

        //Error messages
        private final String NOT_A_NUMBER = " Enter a number";
        private final String NOT_INTEGER = " Enter an integer number";
        private final String NOT_DOUBLE = " Enter a double number";
        private final String NOT_POSITIVE = "Enter a positive number";
        private final String NOT_ACCEPTED = "Enter a number between -1 AND 1";
        private final String CAUTION = "CAUTION";

        // numerical constants
        private static double STOCKPRICE = 100.0;
        private static double STRIKEPRICE = 100.0;
        private static double VOLATILITY = 40;
        private static int TIMETOMATURITY = 180;
        private static double INTERESTRATE =4.25;
        private static double ALPHAONE = 1;
        private static double ALPHATWO = 1;
        private static double KSI1 = 0.20;
        private static double KSI2 = 0.20;
        private static double KSI3 = 0.20;
        private static double RHO = 0.12;
        private static double SIGMASTAR1 = 0.40;
        private static double SIGMASTAR2 = 0.40;
        private static int INTERVALS1=50;
        private static int INTERVALS2=50;
        private static int SIMULATIONS1=20000;
        private static int SIMULATIONS2=20000;
        private static double BSPRICE = 0.0;
        private static double SVPRICE = 0.0;
        private static double SSVPRICE = 0.0;
        private static double PSPRICE = 0.0;
        // numerical variables
        private static double stockprice = STOCKPRICE;
        private static double strikeprice = STRIKEPRICE;
        private static double volatility = VOLATILITY;
        private static int timetomaturity = TIMETOMATURITY;
        private double interestrate = INTERESTRATE;
        private double alphaone = ALPHAONE;
        private double alphatwo = ALPHATWO;
        private double sigmastar1=SIGMASTAR1;
        private double sigmastar2=SIGMASTAR2;
        private static int simulations1 = SIMULATIONS1;
        private static int simulations2 = SIMULATIONS2;
        private static int intervals1 = INTERVALS1;
        private static int intervals2 = INTERVALS2;
        private double rho=RHO;
        private double ksi1= KSI1;
        private double ksi2= KSI2;
        private double ksi3= KSI3;
        private static double bsprice = BSPRICE;
        private static double svprice = SVPRICE;
        private static double ssvprice = SSVPRICE;
        private static double psprice = PSPRICE;

        // number formatters
        private DecimalFormat numberFormatter = null;
        private DecimalFormat numberFormatter1 = null;

        // Methods of the class

        // The init method: for initialising
        public void init () {
            // Initialise formatter
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            numberFormatter = new DecimalFormat("##.######",symbols);
            numberFormatter1 = new DecimalFormat("##.###",symbols);
            // get content pane
            Container contentPane = getContentPane();
            // create main panel
            mainPanel =new JPanel(new BorderLayout());
            // set box layout
            //mainPanel.setBorder(new TitledBorder(CAPPRICING));
            //mainPanel.setLayout(new GridLayout(0,1));
            mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
            // add main panel to content pane
            contentPane.add(mainPanel);
            // create input panel
            inputPanel = new JPanel(new BorderLayout());
            inputPanel.setPreferredSize(new Dimension(550,250));
            inputPanel.setBorder(new TitledBorder(INPUT));
            // add it to the main panel
            mainPanel.add(inputPanel);
            button2Panel = new JPanel();
            button2Panel.setLayout(new BoxLayout(button2Panel,BoxLayout.X_AXIS));
            button2Panel.setPreferredSize(new Dimension(550,20));
            inputPanel.add(button2Panel,BorderLayout.PAGE_START);
            button3Panel = new JPanel();
            button3Panel.setLayout(new BoxLayout(button3Panel,BoxLayout.X_AXIS));
            button3Panel.setPreferredSize(new Dimension(220,20));
            button2Panel.add(button3Panel);
            JLabel label= new JLabel(TYPEOFOPTION_LABEL);
            //forwardratefileField = new JTextField();
            button3Panel.add(label);
            // create button group
            typeGroup = new ButtonGroup();
            // create oneday button
            callButton = new JRadioButton(CALL);
            typeGroup.add(callButton);
            // add action listener
            callButton.addActionListener(this);
            callButton.setSelected(true);
            // add it to button panel
            button3Panel.add(callButton);
            // create put button
            putButton = new JRadioButton(PUT);
            typeGroup.add(putButton);
            // add action listener
            putButton.addActionListener(this);
            // add it to button panel
            button3Panel.add(putButton);
            label= new JLabel(NORMALDISTRIBUTION_LABEL);
            button3Panel.add(label);
            normalDistributionGroup= new ButtonGroup();
            normalDistributionAButton = new JRadioButton(normalDistributionA );
            normalDistributionGroup.add(normalDistributionAButton);
            // add action listener
            normalDistributionAButton.addActionListener(this);
            // add it to button panel
            button3Panel.add(normalDistributionAButton);
            normalDistributionBButton = new JRadioButton(normalDistributionB );
            normalDistributionGroup.add(normalDistributionBButton);
            // add action listener
            normalDistributionBButton.addActionListener(this);
            normalDistributionBButton.setSelected(true);
            // add it to button panel
            button3Panel.add(normalDistributionBButton);
            // create button 1 panel
            button1Panel = new JPanel();
            // add it to the input panel
            inputPanel.add(button1Panel,BorderLayout.PAGE_END);
            //button2Panel.add(button1Panel);
            // create button group
            methodGroup = new ButtonGroup();
            // create calculate button
            calculate1Button = new JButton(CALCULATE1);
            // add action listener
            calculate1Button.addActionListener(this);
            // add it to button panel
            button1Panel.add(calculate1Button);
            // create calculate button
            calculate2Button = new JButton(CALCULATE2);
            // add action listener
            calculate2Button.addActionListener(this);
            // add it to button panel
            button1Panel.add(calculate2Button);
            // create calculate button
            calculate3Button = new JButton(CALCULATE3);
            // add action listener
            calculate3Button.addActionListener(this);
            // add it to button panel
            button1Panel.add(calculate3Button);
            // create calculate button
            calculate4Button = new JButton(CALCULATE4);
            // add action listener
            calculate4Button.addActionListener(this);
            // add it to button panel
            button1Panel.add(calculate4Button);
            // create calculate button
            calculateButton = new JButton(CALCULATE);
            // add action listener
            calculateButton.addActionListener(this);
            // add it to button panel
            button1Panel.add(calculateButton);
            //create reset button
            resetButton = new JButton(RESET);
            //add action listener
            resetButton.addActionListener(this);
            // add it to button panel
            button1Panel.add(resetButton);
            // create bs panel
            bsPanel = new JPanel(new BorderLayout());
            bsPanel.setPreferredSize(new Dimension(150,100));
            bsPanel.setBorder(new TitledBorder("BS model"));
            // add it to the main panel
            inputPanel.add(bsPanel,BorderLayout.LINE_START);
            // create data panel for the inputs
            dataPanel = new JPanel(new GridLayout(0,2));
            dataPanel.setPreferredSize(new Dimension(140,100));
            // add it to input panel
            bsPanel.add(dataPanel,BorderLayout.LINE_START);
            // create labels,create text field,add focus listener and then add
            // the labels and text field to data panel
            label = new JLabel(STOCKPRICE_LABEL);
            stockpriceField = new JTextField();
            dataPanel.add(label);
            stockpriceField.addFocusListener(this);
            dataPanel.add(stockpriceField);
            label = new JLabel(STRIKEPRICE_LABEL);
            strikepriceField = new JTextField();
            dataPanel.add(label);
            strikepriceField.addFocusListener(this);
            dataPanel.add(strikepriceField);
            label= new JLabel(VOLATILITY_LABEL);
            volatilityField = new JTextField();
            dataPanel.add(label);
            volatilityField.addFocusListener(this);
            dataPanel.add(volatilityField);
            label= new JLabel(TIMETOMATURITY_LABEL);
            timetomaturityField = new JTextField();

            dataPanel.add(label);
            timetomaturityField.addFocusListener(this);
            dataPanel.add(timetomaturityField);
            label = new JLabel(INTERESTRATE_LABEL);
            interestrateField = new JTextField();
            dataPanel.add(label);
            interestrateField.addFocusListener(this);
            dataPanel.add(interestrateField);
            // create stochastic panel
            stochasticPanel =new JPanel(new BorderLayout());
            stochasticPanel.setLayout(new GridLayout(1,0));
            // add main panel to content pane
            inputPanel.add(stochasticPanel,BorderLayout.LINE_END);
            bsstochPanel = new JPanel(new BorderLayout());
            bsstochPanel.setPreferredSize(new Dimension(100,100));
            bsstochPanel.setBorder(new TitledBorder("SV model"));
            // add it to the main panel
            stochasticPanel.add(bsstochPanel,BorderLayout.LINE_START);
            data2Panel = new JPanel(new GridLayout(0,2));
            data2Panel.setPreferredSize(new Dimension(100,100));
            // add it to input panel
            bsstochPanel.add(data2Panel,BorderLayout.CENTER);
            label = new JLabel(ALPHAONE_LABEL);
            alphaoneField = new JTextField();
            data2Panel.add(label);
            alphaoneField.addFocusListener(this);
            data2Panel.add(alphaoneField);
            label = new JLabel(SIGMASTAR1_LABEL);
            sigmastar1Field = new JTextField();
            data2Panel.add(label);
            sigmastar1Field.addFocusListener(this);
            data2Panel.add(sigmastar1Field);
            label = new JLabel(KSI1_LABEL);
            ksi1Field = new JTextField();
            data2Panel.add(label);
            ksi1Field.addFocusListener(this);
            data2Panel.add(ksi1Field);
            label = new JLabel(INTERVALS1_LABEL);
            intervals1Field = new JTextField();
            data2Panel.add(label);
            intervals1Field.addFocusListener(this);
            data2Panel.add(intervals1Field);
            label = new JLabel(SIMULATIONS1_LABEL);
            simulations1Field = new JTextField();
            data2Panel.add(label);
            simulations1Field.addFocusListener(this);
            data2Panel.add(simulations1Field);
            svstochdataPanel = new JPanel(new BorderLayout());
            svstochdataPanel.setPreferredSize(new Dimension(100,100));
            svstochdataPanel.setBorder(new TitledBorder("SSV model"));
            // add it to the main panel
            stochasticPanel.add(svstochdataPanel,BorderLayout.CENTER);
            data3Panel = new JPanel(new GridLayout(0,2));
            data3Panel.setPreferredSize(new Dimension(100,100));
            // add it to input panel
            svstochdataPanel.add(data3Panel,BorderLayout.CENTER);
            label = new JLabel(ALPHATWO_LABEL);
            alphatwoField = new JTextField();
            data3Panel.add(label);
            alphatwoField.addFocusListener(this);
            data3Panel.add(alphatwoField);
            label = new JLabel(SIGMASTAR2_LABEL);
            sigmastar2Field = new JTextField();
            data3Panel.add(label);
            sigmastar2Field.addFocusListener(this);
            data3Panel.add(sigmastar2Field);
            label = new JLabel(KSI2_LABEL);
            ksi2Field = new JTextField();
            data3Panel.add(label);
            ksi2Field.addFocusListener(this);
            data3Panel.add(ksi2Field);
            label = new JLabel(RHO_LABEL);
            rhoField = new JTextField();
            data3Panel.add(label);
            rhoField.addFocusListener(this);
            data3Panel.add(rhoField);
            label = new JLabel(INTERVALS2_LABEL);
            intervals2Field = new JTextField();
            data3Panel.add(label);
            intervals2Field.addFocusListener(this);
            data3Panel.add(intervals2Field);
            label = new JLabel(SIMULATIONS2_LABEL);
            simulations2Field = new JTextField();
            data3Panel.add(label);
            simulations2Field.addFocusListener(this);
            data3Panel.add(simulations2Field);
            //NEW
            powerPanel = new JPanel(new BorderLayout());
            powerPanel.setPreferredSize(new Dimension(140,100));
            powerPanel.setBorder(new TitledBorder("PS model"));
            // add it to the main panel
            stochasticPanel.add(powerPanel,BorderLayout.LINE_END);
            data4Panel = new JPanel(new GridLayout(0,2));
            data4Panel.setPreferredSize(new Dimension(80,20));
            // add it to input panel
            powerPanel.add(data4Panel,BorderLayout.NORTH);
            // create labels,create text field,add focus listener and then add
            // the labels and text field to data panel
            label = new JLabel(KSI3_LABEL);
            ksi3Field = new JTextField();
            data4Panel.add(label);
            ksi3Field.addFocusListener(this);
            data4Panel.add(ksi3Field);
            //create data panel
            data1Panel = new JPanel(new BorderLayout());
            data1Panel.setPreferredSize(new Dimension(150,100));
            // add it to input panel
            inputPanel.add(data1Panel,BorderLayout.EAST);
            // create output panel
            outputPanel = new JPanel();
            outputPanel.setBorder(new TitledBorder(OUTPUT));
            outputPanel.setPreferredSize(new Dimension(550,100));
            // add it to the main panel
            mainPanel.add(outputPanel);
            // create output1 panel
            output1Panel = new JPanel();
            output1Panel.setLayout(new BoxLayout(output1Panel,BoxLayout.X_AXIS));
            output1Panel.setPreferredSize(new Dimension(550,20));
            // add it to the output panel
            outputPanel.add(output1Panel,BorderLayout.NORTH);
            // add label and field to output1 panel
            label = new JLabel(BSPRICE_LABEL);
            bspriceField = new JTextField();
            output1Panel.add(label);
            output1Panel.add(bspriceField);
            label = new JLabel(SVPRICE_LABEL);
            svpriceField = new JTextField();
            output1Panel.add(label);
            output1Panel.add(svpriceField);
            label = new JLabel(SSVPRICE_LABEL);
            ssvpriceField = new JTextField();
            output1Panel.add(label);
            output1Panel.add(ssvpriceField);
            label = new JLabel(PSPRICE_LABEL);
            pspriceField = new JTextField();
            output1Panel.add(label);
            output1Panel.add(pspriceField);
            // add tooltip
            stockpriceField.setToolTipText(STOCKPRICE_TOOLTIP);
            strikepriceField.setToolTipText(STRIKEPRICE_TOOLTIP);
            volatilityField.setToolTipText(VOLATILITY_TOOLTIP);
            timetomaturityField.setToolTipText(TIMETOMATURITY_TOOLTIP);
            interestrateField.setToolTipText(INTERESTRATE_TOOLTIP);

            alphaoneField.setToolTipText(ALPHAONE_TOOLTIP);
            alphatwoField.setToolTipText(ALPHATWO_TOOLTIP);
            sigmastar1Field.setToolTipText(SIGMASTAR1_TOOLTIP);
            sigmastar2Field.setToolTipText(SIGMASTAR2_TOOLTIP);
            simulations1Field.setToolTipText(SIMULATIONS1_TOOLTIP);
            simulations2Field.setToolTipText(SIMULATIONS2_TOOLTIP);
            rhoField.setToolTipText(RHO_TOOLTIP);
            intervals1Field.setToolTipText(INTERVALS1_TOOLTIP);
            intervals2Field.setToolTipText(INTERVALS2_TOOLTIP);
            ksi1Field.setToolTipText(KSI1_TOOLTIP);
            ksi2Field.setToolTipText(KSI2_TOOLTIP);
            ksi3Field.setToolTipText(KSI3_TOOLTIP);
            bspriceField.setToolTipText(BSPRICE_TOOLTIP);
            svpriceField.setToolTipText(SVPRICE_TOOLTIP);
            ssvpriceField.setToolTipText(SSVPRICE_TOOLTIP);
            pspriceField.setToolTipText(PSPRICE_TOOLTIP);
            //set value
            stockpriceField.setText(numberFormatter.format(STOCKPRICE));
            strikepriceField.setText(numberFormatter.format(STRIKEPRICE));
            volatilityField.setText(numberFormatter.format(VOLATILITY));
            timetomaturityField.setText(numberFormatter.format(TIMETOMATURITY));
            interestrateField.setText(numberFormatter.format(INTERESTRATE));
            alphaoneField.setText(numberFormatter.format(ALPHAONE));
            alphatwoField.setText(numberFormatter.format(ALPHATWO));
            rhoField.setText(numberFormatter.format(RHO));
            sigmastar1Field.setText(numberFormatter.format(SIGMASTAR1));
            sigmastar2Field.setText(numberFormatter.format(SIGMASTAR2));
            ksi1Field.setText(numberFormatter.format(KSI1));
            ksi2Field.setText(numberFormatter.format(KSI2));
            ksi3Field.setText(numberFormatter.format(KSI3));
            simulations1Field.setText(numberFormatter.format(SIMULATIONS1));
            simulations2Field.setText(numberFormatter.format(SIMULATIONS2));
            intervals1Field.setText(numberFormatter.format(INTERVALS1));
            intervals2Field.setText(numberFormatter.format(INTERVALS2));
            bspriceField.setText(numberFormatter.format(BSPRICE));
            svpriceField.setText(numberFormatter.format(SVPRICE));
            ssvpriceField.setText(numberFormatter.format(SSVPRICE));
            pspriceField.setText(numberFormatter.format(PSPRICE));
        }

        // method of Action listener
        public void actionPerformed(ActionEvent e){
            //determine,who called action listener
            Object source = e.getSource();

            if(source == resetButton){
                //reset all TextFields and variables to the initial values
                stockprice = STOCKPRICE;
                stockpriceField.setText(numberFormatter.format(STOCKPRICE));
                strikeprice= STRIKEPRICE;
                strikepriceField.setText(numberFormatter.format(STRIKEPRICE));
                volatility=VOLATILITY;
                volatilityField.setText(numberFormatter.format(VOLATILITY));
                timetomaturity = TIMETOMATURITY;
                timetomaturityField.setText(numberFormatter.format(TIMETOMATURITY));
                interestrate = INTERESTRATE;
                interestrateField.setText(numberFormatter.format(INTERESTRATE));
                alphaone= ALPHAONE;
                alphaoneField.setText(numberFormatter.format(ALPHAONE));
                alphatwo= ALPHATWO;
                alphatwoField.setText(numberFormatter.format(ALPHATWO));
                ksi1= KSI1;
                ksi1Field.setText(numberFormatter.format(KSI1));
                ksi2= KSI2;
                ksi2Field.setText(numberFormatter.format(KSI2));
                ksi3= KSI3;
                ksi3Field.setText(numberFormatter.format(KSI3));
                sigmastar1= SIGMASTAR1;
                sigmastar1Field.setText(numberFormatter.format(SIGMASTAR1));
                sigmastar2= SIGMASTAR2;
                sigmastar2Field.setText(numberFormatter.format(SIGMASTAR2));
                rho = RHO;
                rhoField.setText(numberFormatter.format(RHO));
                intervals1=INTERVALS1;
                intervals1Field.setText(numberFormatter.format(INTERVALS1));
                intervals2=INTERVALS2;
                intervals2Field.setText(numberFormatter.format(INTERVALS2));
                simulations1=SIMULATIONS1;
                simulations1Field.setText(numberFormatter.format(SIMULATIONS1));
                simulations2=SIMULATIONS2;
                simulations2Field.setText(numberFormatter.format(SIMULATIONS2));
                bsprice = BSPRICE;
                bspriceField.setText(numberFormatter.format(BSPRICE));
                svprice = SVPRICE;
                svpriceField.setText(numberFormatter.format(SVPRICE));
                ssvprice = SSVPRICE;
                ssvpriceField.setText(numberFormatter.format(SSVPRICE));
                psprice = PSPRICE;
                pspriceField.setText(numberFormatter.format(PSPRICE));
            }

            if (source == calculate1Button) {
                if (callButton.isSelected()){
                    if(normalDistributionAButton.isSelected()){
                        double bscall = new
                                BlackScholesFormula().BlackScholesCallA(stockprice,strikeprice,volatility,
                                timetomaturity,interestrate);
                        bspriceField.setText(numberFormatter.format(bscall));
                    }
                    else {
                        for(stockprice=0.75; stockprice<1.26; stockprice=stockprice+0.01){
                            double bscall = new
                                    BlackScholesFormula().BlackScholesCallB(stockprice,strikeprice,volatility,
                                    timetomaturity,interestrate);
                            bspriceField.setText(numberFormatter.format(bscall));
                        // to check -System.out.println(bscall);
                        }
                    }
                }
                else {
                    if(normalDistributionAButton.isSelected()){
                        double bsput = new
                                BlackScholesFormula().BlackScholesPutA(stockprice,strikeprice,volatility,
                                timetomaturity,interestrate);
                        bspriceField.setText(numberFormatter.format(bsput));
                    }
                    else {
                        double bsput = new
                                BlackScholesFormula().BlackScholesPutB(stockprice,strikeprice,volatility,
                                timetomaturity,interestrate);
                        bspriceField.setText(numberFormatter.format(bsput));
                    }
                }
            }
            if (source == calculate2Button) {
                if (callButton.isSelected()){
                    if(normalDistributionAButton.isSelected()){
                        double stvol1=new
                                StochasticVolatilityFile1().simulateVolatility1(alphaone,ksi1,sigmastar1,
                                intervals1,simulations1,volatility,timetomaturity);
                        double svcall1=new BlackScholesFormula().BlackScholesCallA(stockprice,
                                strikeprice,stvol1,timetomaturity,interestrate);
                        double stvol2=new
                                StochasticVolatilityFile1().simulateVolatility2(alphaone,ksi1,
                                sigmastar1, intervals1,simulations1,volatility,timetomaturity);
                        double svcall2=new BlackScholesFormula().BlackScholesCallA(stockprice,
                                strikeprice,stvol2,timetomaturity,interestrate);
                        double svcall=(svcall1+svcall2)/2;
                        svpriceField.setText(numberFormatter.format(svcall));
                    }

                    // call with Normal-B
                    else {
                        for(stockprice=0.75; stockprice<1.26; stockprice=stockprice+0.01){
                            double stvol1=new
                                    StochasticVolatilityFile1().simulateVolatility1(alphaone,ksi1,sigmastar1,
                                    intervals1,simulations1,volatility,timetomaturity);
                            double svcall1=new
                                    BlackScholesFormula().BlackScholesCallB(stockprice,
                                    strikeprice, stvol1, timetomaturity, interestrate);
                            double stvol2=new
                                    StochasticVolatilityFile1().simulateVolatility2(alphaone,ksi1,sigmastar1,
                                    intervals1,simulations1,volatility,timetomaturity);
                            double svcall2=new
                                    BlackScholesFormula().BlackScholesCallB(stockprice, strikeprice, stvol2,
                                    timetomaturity, interestrate);
                            double svcall=(svcall1+svcall2)/2;
                            svpriceField.setText(numberFormatter.format(svcall));
                            // System.out.println(svcall);
                        }
                    }
                }
                else{
                    if(normalDistributionAButton.isSelected()){
                        double stvol1=new
                                StochasticVolatilityFile1().simulateVolatility1(alphaone,ksi1,
                                sigmastar1,intervals1,simulations1,volatility,timetomaturity);
                        double svput1=new BlackScholesFormula().BlackScholesPutA(stockprice,
                                strikeprice, stvol1, timetomaturity, interestrate);

                        double stvol2=new
                                StochasticVolatilityFile1().simulateVolatility2(alphaone,ksi1,
                                sigmastar1,intervals1,simulations1,volatility,timetomaturity);
                        double svput2=new BlackScholesFormula().BlackScholesPutA(stockprice,
                                strikeprice, stvol2, timetomaturity, interestrate);
                        double svput=(svput1+svput2)/2;
                        svpriceField.setText(numberFormatter.format(svput));
                    }
                    else {
                        double stvol1=new
                                StochasticVolatilityFile1().simulateVolatility1(alphaone,ksi1,
                                sigmastar1,intervals1,simulations1,volatility,timetomaturity);
                        double svput1=new BlackScholesFormula().BlackScholesPutB(stockprice,
                                strikeprice,stvol1,timetomaturity, interestrate);
                        double stvol2=new
                                StochasticVolatilityFile1().simulateVolatility2(alphaone,ksi1,
                                sigmastar1,intervals1,simulations1,volatility,timetomaturity);
                        double svput2=new BlackScholesFormula().BlackScholesPutB(stockprice,
                                strikeprice, stvol2, timetomaturity, interestrate);
                        double svput=(svput1+svput2)/2;
                        svpriceField.setText(numberFormatter.format(svput));
                    }
                }
            }
            if (source == calculate3Button) {
                if (callButton.isSelected()){
                    for(stockprice=0.75; stockprice<1.26; stockprice=stockprice+0.01){
                        double ssv1=new StochasticPriceVolatility().getP1Call(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv2=new StochasticPriceVolatility().getP2Call(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv3=new StochasticPriceVolatility().getP3Call(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv4=new StochasticPriceVolatility().getP4Call(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv5=new StochasticPriceVolatility().getQ1Call(intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice);
                        double ssv6=new StochasticPriceVolatility().getQ2Call(intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice);
                        ssvpriceField.setText(numberFormatter.format((ssv1+ssv2+ssv3+ssv4+ssv5+ssv6
                        )/6));
    //System.out.println((ssv1+ssv2+ssv3+ssv4+ssv5+ssv6)/6);
                    }
                }
                else
                {
                    double ssv1=new StochasticPriceVolatility().getP1Put(alphatwo,
                            sigmastar2,
                            ksi2,
                            rho,
                            intervals2,
                            simulations2,
                            timetomaturity,
                            volatility,
                            stockprice,
                            interestrate,
                            strikeprice );
                    double ssv2=new StochasticPriceVolatility().getP2Put(alphatwo,
                            sigmastar2,
                            ksi2,
                            rho,
                            intervals2,
                            simulations2,
                            timetomaturity,
                            volatility,
                            stockprice,
                            interestrate,
                            strikeprice );
                    double ssv3=new StochasticPriceVolatility().getP3Put(alphatwo,
                            sigmastar2,
                            ksi2,
                            rho,
                            intervals2,
                            simulations2,
                            timetomaturity,
                            volatility,
                            stockprice,
                            interestrate,
                            strikeprice );
                    double ssv4=new StochasticPriceVolatility().getP4Put(alphatwo,
                            sigmastar2,
                            ksi2,
                            rho,
                            intervals2,
                            simulations2,
                            timetomaturity,
                            volatility,
                            stockprice,
                            interestrate,
                            strikeprice );
                    double ssv5=new StochasticPriceVolatility(). getQ1Put(intervals2,
                            simulations2,
                            timetomaturity,
                            volatility,
                            stockprice,
                            interestrate,
                            strikeprice);
                    double ssv6 =new StochasticPriceVolatility(). getQ2Put(intervals2,
                            simulations2,
                            timetomaturity,
                            volatility,
                            stockprice,
                            interestrate,
                            strikeprice);
                    ssvpriceField.setText(numberFormatter.format((ssv1+ssv2+ssv3+ssv4+ssv5+ssv6)/6));
                }
            }
            if (source == calculate4Button) {
                if (callButton.isSelected()){
                    if(normalDistributionAButton.isSelected()){
                        double bscall3 = new
                                PowerSeries().calculatePSA(stockprice,strikeprice,volatility,
                                timetomaturity,interestrate,ksi3);
                        pspriceField.setText(numberFormatter.format(bscall3));
                    }
                    else {
                        for(stockprice=0.75; stockprice<1.26; stockprice=stockprice+0.01){
                            double bscall3 = new
                                    PowerSeries().calculatePSB(stockprice,strikeprice,volatility,
                                    timetomaturity,interestrate,ksi3);
                            pspriceField.setText(numberFormatter.format(bscall3));
    // to check - System.out.println(bscall3);
                        }
                    }
                }
                else {
                    if(normalDistributionAButton.isSelected()){
                        double bscall3 = new
                                PowerSeries().calculatePSA(stockprice,strikeprice,volatility,
                                timetomaturity,interestrate,ksi3);
                        double putPrice=(strikeprice*Math.exp(- interestrate/100*
                                timetomaturity/360)+(bscall3)-stockprice);
                        pspriceField.setText(numberFormatter.format(putPrice));
                    }
                    else {
                        double bscall3 = new
                                PowerSeries().calculatePSB(stockprice,strikeprice,volatility,
                                timetomaturity,interestrate,ksi3);
                        double putPrice=(strikeprice*Math.exp(- interestrate/100*
                                timetomaturity/360)+(bscall3)-stockprice);
                        pspriceField.setText(numberFormatter.format(putPrice));
                    }
                }
            }

            // CALCULATE ALL BUTTON
            if (source == calculateButton) {
                if (callButton.isSelected()){
                    if(normalDistributionAButton.isSelected()){
                        // BS Model
                        double bscall = new
                                BlackScholesFormula().BlackScholesCallA(stockprice,strikeprice,volatility,
                                timetomaturity,interestrate);
                        bspriceField.setText(numberFormatter.format(bscall));
                        // SV Model
                        double stvol1=new
                                StochasticVolatilityFile1().simulateVolatility1(alphaone,ksi1,
                                sigmastar1, intervals1, simulations1, volatility, timetomaturity);
                        double svcall1=new
                                BlackScholesFormula().BlackScholesCallA(stockprice, strikeprice, stvol1,
                                timetomaturity, interestrate);
                        double stvol2=new
                                StochasticVolatilityFile1().simulateVolatility2(alphaone,ksi1,
                                sigmastar1, intervals1, simulations1, volatility, timetomaturity);
                        double svcall2=new
                                BlackScholesFormula().BlackScholesCallA(stockprice,
                                strikeprice, stvol2, timetomaturity, interestrate);
                        double svcall=(svcall1+svcall2)/2 ;
                        svpriceField.setText(numberFormatter.format(svcall));

                        // SSV
                        double ssv1=new StochasticPriceVolatility().getP1Call(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv2=new StochasticPriceVolatility().getP2Call(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv3=new StochasticPriceVolatility().getP3Call(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv4=new StochasticPriceVolatility().getP4Call(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv5=new StochasticPriceVolatility(). getQ1Call(intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice);
                        double ssv6=new StochasticPriceVolatility(). getQ2Call(intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice);
                        ssvpriceField.setText(numberFormatter.format((ssv1+ssv2+ssv3+ssv4+ssv5+ssv6)/6));
    // Series Solution
                        double bscall3 = new
                                PowerSeries().calculatePSA(stockprice,strikeprice,volatility,
                                timetomaturity,interestrate,ksi3);
                        pspriceField.setText(numberFormatter.format(bscall3));
                    }
                    // normaldistribution B
                    else {
                        // BS
                        double bscall = new BlackScholesFormula().BlackScholesCallB(stockprice,strikeprice,
                            volatility,timetomaturity,interestrate);
                        bspriceField.setText(numberFormatter.format(bscall));
                        // SV
                        double stvol1=new StochasticVolatilityFile1().simulateVolatility1(alphaone,ksi1,
                                sigmastar1, intervals1, simulations1, volatility, timetomaturity);
                        double svcall1=new BlackScholesFormula().BlackScholesCallB(stockprice, strikeprice, stvol1,
                                timetomaturity, interestrate);
                        double stvol2=new StochasticVolatilityFile1().simulateVolatility2(alphaone,ksi1, sigmastar1,
                                intervals1, simulations1, volatility, timetomaturity);

                        double svcall2=new BlackScholesFormula().BlackScholesCallB(stockprice, strikeprice,
                                stvol2, timetomaturity, interestrate);
                        double svcall=(svcall1+svcall2)/2 ;
                        svpriceField.setText(numberFormatter.format(svcall));

                        // SSV
                        double ssv1=new StochasticPriceVolatility().getP1Call(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv2=new StochasticPriceVolatility().getP2Call(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv3=new StochasticPriceVolatility().getP3Call(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv4=new StochasticPriceVolatility().getP4Call(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv5=new StochasticPriceVolatility(). getQ1Call(intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice);
                        double ssv6=new StochasticPriceVolatility(). getQ2Call(intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice);
                        ssvpriceField.setText(numberFormatter.format((ssv1+ssv2+ssv3+ssv4+ssv5+ssv6)/6));
                        // Series Solution
                        double bscall3 = new
                                PowerSeries().calculatePSB(stockprice,strikeprice,volatility,
                                timetomaturity,interestrate,ksi3);
                        pspriceField.setText(numberFormatter.format(bscall3));
                    }
                }
                // if put button is selected
                else{
                    if(normalDistributionAButton.isSelected()){
                        // BS
                        double bsput = new
                                BlackScholesFormula().BlackScholesPutA(stockprice,strikeprice,volatility,
                                timetomaturity,interestrate);
                        bspriceField.setText(numberFormatter.format(bsput));
                        // SV
                        double stvol1=new
                                StochasticVolatilityFile1().simulateVolatility1(alphaone,ksi1,
                                sigmastar1, intervals1, simulations1, volatility,timetomaturity);
                        double svput1=new BlackScholesFormula().BlackScholesPutA(stockprice,
                                strikeprice, stvol1, timetomaturity, interestrate);
                        double stvol2=new
                                StochasticVolatilityFile1().simulateVolatility2(alphaone,ksi1,
                                sigmastar1, intervals1, simulations1, volatility, timetomaturity);
                        double svput2=new BlackScholesFormula().BlackScholesPutA(stockprice,
                                strikeprice, stvol2, timetomaturity, interestrate);
                        double svput=(svput1+svput2)/2 ;
                        svpriceField.setText(numberFormatter.format(svput));
                        // SSV
                        double ssv1=new StochasticPriceVolatility().getP1Put(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv2=new StochasticPriceVolatility().getP2Put(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv3=new StochasticPriceVolatility().getP3Put(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv4=new StochasticPriceVolatility().getP4Put(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv5=new StochasticPriceVolatility(). getQ1Put(intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice);
                        double ssv6=new StochasticPriceVolatility(). getQ2Put(intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice);
                        ssvpriceField.setText(numberFormatter.format((ssv1+ssv2+ssv3+ssv4+ssv5+ssv6)/6));
                        // Series Solution
                        double bscall3 = new
                                PowerSeries().calculatePSA(stockprice,strikeprice,volatility,
                                timetomaturity,interestrate,ksi3);
                        double putPrice=(strikeprice*Math.exp(- interestrate/100*
                                timetomaturity/360)+(bscall3)-stockprice);
                        pspriceField.setText(numberFormatter.format(putPrice));
                    }
                // is normaldistribution B is used (put)
                    else {
    // BS
                        double bsput = new
                                BlackScholesFormula().BlackScholesPutB(stockprice,strikeprice,volatility,
                                timetomaturity,interestrate);
                        bspriceField.setText(numberFormatter.format(bsput));
    // SV
                        double stvol1=new
                                StochasticVolatilityFile1().simulateVolatility1(alphaone,ksi1,
                                sigmastar1, intervals1, simulations1, volatility, timetomaturity);
                        double svput1=new BlackScholesFormula().BlackScholesPutB(stockprice,
                                strikeprice, stvol1, timetomaturity, interestrate);
                        double stvol2=new
                                StochasticVolatilityFile1().simulateVolatility2(alphaone,ksi1,
                                sigmastar1, intervals1, simulations1, volatility, timetomaturity);
                        double svput2=new BlackScholesFormula().BlackScholesPutB(stockprice,
                                strikeprice, stvol2, timetomaturity, interestrate);
                        double svput=(svput1+svput2)/2 ;
                        svpriceField.setText(numberFormatter.format(svput));
    // SSV
                        double ssv1=new StochasticPriceVolatility().getP1Put(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv2=new StochasticPriceVolatility().getP2Put(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv3=new StochasticPriceVolatility().getP3Put(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv4=new StochasticPriceVolatility().getP4Put(alphatwo,
                                sigmastar2,
                                ksi2,
                                rho,
                                intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice );
                        double ssv5=new StochasticPriceVolatility(). getQ1Put(intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice);
                        double ssv6=new StochasticPriceVolatility(). getQ2Put(intervals2,
                                simulations2,
                                timetomaturity,
                                volatility,
                                stockprice,
                                interestrate,
                                strikeprice);
                        ssvpriceField.setText(numberFormatter.format((ssv1+ssv2+ssv3+ssv4+ssv5+ssv6)/6));
    // Series Solution
                        double bscall3 = new
                                PowerSeries().calculatePSB(stockprice,strikeprice,volatility,
                                timetomaturity,interestrate,ksi3);
                        double putPrice=(strikeprice*Math.exp(- interestrate/100*
                                timetomaturity/360)+(bscall3)-stockprice);
                        pspriceField.setText(numberFormatter.format(putPrice));
                    }
                }
            } // closing calculate all
        }
        // End of Actionlistener

        public void focusGained(FocusEvent e){
            //if focus is gained, do nothing
        }

        //if focus is lost, do something
        public void focusLost(FocusEvent e){
            //find the source which called focus lost
            Object source = e.getSource();

            //if the source is timetomaturity
            if (source == timetomaturityField){
                timetomaturity=readInt( timetomaturityField,
                        timetomaturity,
                        "Time to maturity");
                return;
            }

            //if the source is the intervals1
            if (source == intervals1Field){
                intervals1=readInt(intervals1Field, intervals1, "Intervals1 Field");
                return;
            }

            //if the source is the intervals2
            if (source == intervals2Field){
                intervals2=readInt(intervals2Field, intervals2, "Intervals2Field");
                return;
            }

            //if the source is the simulations1
            if (source == simulations1Field){
                simulations1=readInt(simulations1Field, simulations1, "Simulations1 Field");
                return;
            }

            //if the source is the simulations2
            if (source == simulations2Field){
                simulations2=readInt(simulations2Field, simulations2, "Simulations2 Field");
                return;
            }

            //if the source is the stockprice
            if (source == stockpriceField){
                stockprice=readPositive(stockpriceField, stockprice, "Stock price");
                return;
            }

            //if the source is strikeprice
            if (source == strikepriceField) {
                strikeprice = readPositive(strikepriceField, strikeprice, "Strike price");
                return;
            }

            //if the source is volatility
            if (source == volatilityField){
                    volatility=readPositive(volatilityField, volatility, "Volatility Field");
                    return;
            }

            //if the source is interest rate
            if (source == interestrateField){
                    interestrate=readPositive(interestrateField, interestrate, "Interest rate");
                    return;
            }

            //if the source is alphaone
            if (source == alphaoneField){
                    alphaone=readPositive(alphaoneField,
                            alphaone,
                            "Alpha one field");
                    return;
            }

            //if the source is alphatwo
            if (source == alphatwoField){
                    alphatwo=readPositive(alphatwoField, alphatwo, "Alpha two field");
                    return;
            }

            //if the source is sigmastar1
            if (source == sigmastar1Field){
                    sigmastar1=readPositive(sigmastar1Field, sigmastar1, "Sigma star1 field");
                    return;
            }

            //if the source is sigmastar2
            if (source == sigmastar2Field){
                    sigmastar2=readPositive(sigmastar2Field, sigmastar2, "Sigma star2 field");
                    return;
            }

            //if the source is ksi1
            if (source == ksi1Field){
                    ksi1=readPositive(ksi1Field, ksi1, "Ksi1 field");
                    return;
            }

            //if the source is ksi2
            if (source == ksi2Field) {
                ksi2 = readPositive(ksi2Field, ksi2, "Ksi2field");
                return;
            }

            //if the source is ksi3
            if (source == ksi3Field){
                ksi3=readPositive(ksi3Field, ksi3, "Ksi3 field");
                return;
            }

            //if the source is rho
            if (source == rhoField){
                rho=readPositive1(rhoField, rho, "Rho field");
                return;
            }
        }

        //read positive double numbers
        private double readPositive(JTextField field, double oldValue, String title) {
            boolean isOK = true;
            double newValue = 1;
            try{
                //test input
                newValue = Double.parseDouble(field.getText());
            }
            catch (NumberFormatException e){
                //Error message
                JOptionPane.showMessageDialog(null,
                        NOT_A_NUMBER,
                        title,
                        JOptionPane.ERROR_MESSAGE);
                isOK = false;
            }
            if (newValue <=0){
                //ERROR message
                JOptionPane.showMessageDialog(null,
                        NOT_POSITIVE,
                        title,
                        JOptionPane.ERROR_MESSAGE);
                isOK = false;
            }
            if (isOK)
                return newValue;

            else {
                field.setText(numberFormatter.format(oldValue));
                return oldValue;
            }
        }

        //read rho -1 t0 1
        private double readPositive1(JTextField field, double oldValue, String title) {
            boolean isOK = true;
            double newValue = 1;
            try{
                //test input
                newValue = Double.parseDouble(field.getText());
            }
            catch (NumberFormatException e){
                //Error message
                JOptionPane.showMessageDialog(null,
                        NOT_A_NUMBER,
                        title,
                        JOptionPane.ERROR_MESSAGE);
                isOK = false;
            }
            if (newValue <-1 ^ newValue >1){
                //ERROR message
                JOptionPane.showMessageDialog(null,
                        NOT_ACCEPTED,
                        title,
                        JOptionPane.ERROR_MESSAGE);
                isOK = false;
            }
            if (isOK){
                return newValue;
            }
            else {
                field.setText(numberFormatter.format(oldValue));
                return oldValue;
            }

        }

        //read double numbers
        private double readDouble(JTextField field, double oldValue, String title){
            boolean isOK = true;
            double newValue = 1;
            try {
                // test input
                newValue = Double.parseDouble(field.getText());
            }
            catch (NumberFormatException e){
                // ERROR message
                JOptionPane.showMessageDialog(null,
                        NOT_A_NUMBER,
                        title,
                        JOptionPane.ERROR_MESSAGE);
                isOK = false;
            }
            if (isOK) {
                return newValue;
            }
            else {
                field.setText(numberFormatter.format(oldValue));
                return oldValue;
            }
        }

        //Read integer numbers
        private int readInt(JTextField field, int oldValue, String title) {
            boolean isOK = true;
            int newValue = 1;
            try {
                // test input
                newValue = Integer.parseInt(field.getText());
            }
            catch (NumberFormatException e){
                // ERROR message
                JOptionPane.showMessageDialog(null,
                        NOT_INTEGER,
                        title,
                        JOptionPane.ERROR_MESSAGE);
                isOK = false;
            }
            if (newValue <=0) {
                //ERROR message
                JOptionPane.showMessageDialog(null,
                        NOT_POSITIVE,
                        title,
                        JOptionPane.ERROR_MESSAGE);
                isOK = false;
            }
            if (isOK) {
                return newValue;
            }
            else {
                field.setText(numberFormatter.format(oldValue));
                return oldValue;
            }
        }
}
















