package gui;

import TennisTounament.GameDataCollector;
import TennisTounament.TControl;
import TennisTounament.TournamentDataCollector;
import myUsefulPackage.CSVReader;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class App {
    private JButton button1;
    private JPanel mainPanel;
    private JLabel superLabel;
    private JTextField testlabel;
    private JTabbedPane tabs;
    private JComboBox comboBox1;
    private JList list1;
    private JList list2;
    private JComboBox comboBox2;
    private JList list3;
    protected TournamentDataCollector[] dataArray;
    protected int index1, index2;


    public App() {
        DefaultListModel model1 = new DefaultListModel();
        DefaultListModel model2 = new DefaultListModel();
        DefaultListModel model3 = new DefaultListModel();
        list1.setModel(model1);
        list2.setModel(model2);
        list3.setModel(model3);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker<TControl, Void> worker = new SwingWorker<TControl, Void>() {
                    @Override
                    protected TControl doInBackground() throws Exception {
                        int amount = Integer.parseInt(comboBox2.getSelectedItem().toString());
                        int playerAmount = Integer.parseInt(comboBox1.getSelectedItem().toString());
                        TControl wantedT = new TControl(playerAmount, CSVReader.staticRead("players.csv", 128), amount);

                        return wantedT;
                    }
                    protected void done(){
                        try {
                            TControl wantedT = get();
                            dataArray = wantedT.data;
                            tabs.setSelectedIndex(0);
                            model1.clear();
                            model2.clear();
                            for (int i = 0; i< dataArray.length; i ++) {
                            model1.add(i,"tournament " + (i+1) + " winner: " + wantedT.data[i].getWinner().getFirstName() + " "
                                    + wantedT.data[i].getWinner().getLastname());
                            }
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        } catch (ExecutionException executionException) {
                            executionException.printStackTrace();
                        }

                    }
                };
                worker.execute();
            }
        });







        list1.addListSelectionListener(new ListSelectionListener() {
            // this is the listener method for the list
            @Override
            public void valueChanged(ListSelectionEvent e) {
                model2.clear();
                index1 = list1.getSelectedIndex();
                ArrayList<GameDataCollector> games = dataArray[index1].getTournamentGames();
                for (int i = 0; i< games.size(); i ++) {
                    model2.add(i,"game " + (i+1) + " winner: " + games.get(i).getWinner().getFirstName() + " " +
                            games.get(i).getWinner().getLastname());
                }
                tabs.setSelectedIndex(1);
            }
        });
        list2.addListSelectionListener(new ListSelectionListener() {
            // this is the listener method for the list
            @Override
            public void valueChanged(ListSelectionEvent e) {
                model3.clear();
                index2 = list2.getSelectedIndex();
                ArrayList<GameDataCollector> games= dataArray[index1].getTournamentGames();
                GameDataCollector temp = games.get(index2);
                ArrayList<String> points = temp.getPoints();
                for (int i = 0; i< points.size(); i ++) {
                    model3.add(i, points.get(i));
                    //System.out.println(points.get(i));
                }
                tabs.setSelectedIndex(2);
            }
        });

        }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1080, 720);

        frame.setVisible(true);

    }

}