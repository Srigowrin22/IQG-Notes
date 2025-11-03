package training.bosch.tms.ui;

import java.sql.Timestamp;

import java.util.ArrayList;

 

import javax.swing.*;

import javax.swing.table.*;

 

import training.bosch.tms.dao.OffenceDAO;

import training.bosch.tms.dao.OffenceDetailsDAO;

import training.bosch.tms.factory.OffenceDAOFactory;

import training.bosch.tms.factory.OffenceDetailsDAOFactory;

import training.bosch.tms.model.Offence;

import training.bosch.tms.model.OffenceDetails;

 

public class TableCheckBox extends JFrame {

 

    private static final long serialVersionUID = 1L;

    private JTable table;

 

    public TableCheckBox() {

        //Object[] columnNames = {"Type", "Company", "Shares", "Price", "Boolean"};

      final String[] columnNames={"Offence Type","Place","Time","Reported By","Penalty","Check to clear"};

     

     

//        Object[][] data = {

//            {"Buy", "IBM", new Integer(1000), new Double(80.50), false},

//            {"Sell", "MicroSoft", new Integer(2000), new Double(6.25), true},

//            {"Sell", "Apple", new Integer(3000), new Double(7.35), true},

//            {"Buy", "Nortel", new Integer(4000), new Double(20.00), false}

//        };

       

        

        OffenceDAO offDAO = OffenceDAOFactory.create();

 
        OffenceDetailsDAO offenceDetailsDAO = OffenceDetailsDAOFactory.create();
      

        ArrayList<OffenceDetails> arrListOffDetails = offenceDetailsDAO.findByVehNo("KA01Z4917");


        ArrayList<OffenceDetails> copyArrListOffDetails = (ArrayList<OffenceDetails>) arrListOffDetails.clone();

       

        ArrayList<Offence> tempOffence = new ArrayList();

       

        for(OffenceDetails temp  : copyArrListOffDetails)

        {

            // Now I Have to Query Via OffenceId

            Offence offRef = offDAO.findByOffenceId(temp.getOffenceId());

            tempOffence.add(offRef);

        }

       

        

        // Now Combine Result Object

        

        Object[][] data = new Object[1][6];

       

//        for (int i = 0; i<data.length; i++){

//            for (int j = 0; j<data[i].length; j++){

//             

//                string += data[i][j];

//            }

//            System.out.println(string)

//

//       }

           

        data[0][0] = new String(tempOffence.get(0).getOffenceType());

        data[0][1] = new String(arrListOffDetails.get(0).getPlace());

        data[0][2] = new Timestamp(arrListOffDetails.get(0).getTime().getDate(),arrListOffDetails.get(0).getTime().getMonth(),arrListOffDetails.get(0).getTime().getYear(),arrListOffDetails.get(0).getTime().getHours(),arrListOffDetails.get(0).getTime().getMinutes(),0,0);

        data[0][3] = new String(arrListOffDetails.get(0).getReportedBy());

        data[0][4] = new Integer(tempOffence.get(0).getPenalty());

        data[0][5] = false;

       

        

        

        

       // ArrayList<Offence> arrListPreDefined = offDAO.findByOffenceId(offenceId);

       

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        table = new JTable(model) {

 

            private static final long serialVersionUID = 1L;

 

            /*@Override

            public Class getColumnClass(int column) {

            return getValueAt(0, column).getClass();

            }*/

            @Override
            public Class getColumnClass(int column) {

                switch (column) {

                    case 0:

                        return String.class;

                    case 1:

                        return String.class;

                    case 2:

                        return Timestamp.class;

                    case 3:

                        return String.class;

                    case 4:

                        return Integer.class;

                    default:

                        return Boolean.class;

                }

            }

        };

        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().add(scrollPane);

       

        

 

 

        for (int i = 0; i < table.getRowCount(); i++) {

             Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 4).toString());
             if (isChecked) {

                //get the values of the columns you need.
                   System.out.println(" Clicked .. ");

            } else {

                System.out.printf("Row %s is not checked \n", i);

            }

        }

    }

 

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

 

            public void run() {

                TableCheckBox frame = new TableCheckBox();

                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

                frame.pack();

                frame.setLocation(150, 150);

                frame.setVisible(true);

            }

        });

    }

}