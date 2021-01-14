package Controller;

import Controller.Observable.StockDatabaseSystem;
import Model.Admin.Observers.IOrders;
import Model.Admin.Observers.Orders;
import Admin.Admin;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StockDatabaseController {


    public String filepath = "Data\\StockLists.txt";
    public String separator = "\\|";


    DefaultListModel<String> dm = new DefaultListModel<>();

    private final StockDatabaseSystem sdss;
    private final Admin av;

    public StockDatabaseController(StockDatabaseSystem sdss, Admin adminV){
        this.sdss = sdss;
        this.av = adminV;


        av.getBtnAddItem().addActionListener(e -> Add());
        av.getBtnEditItem().addActionListener(e -> Edit());
        av.getBtnRemoveItem().addActionListener(e -> Remove());
    }


    public void Add() {
        IOrders NewStockItem = new Orders(sdss);


        NewStockItem.setBarcode(Integer.parseInt(av.getTxtAdminBarcode().getText()));
        NewStockItem.setStockName(av.getTxtAdminStockName().getText());
        NewStockItem.setQuantity(Integer.parseInt(av.getTxtAdminQuantity().getText()));
        NewStockItem.setStockPrice(Double.parseDouble(av.getTxtAdminPrice().getText()));

        sdss.Add(NewStockItem);


        saveKioskStock();


        JOptionPane.showMessageDialog(null, "New Item Added","Stock Addition", JOptionPane.INFORMATION_MESSAGE);
    }

    public void Edit() {
        IOrders editItem;


        editItem = getStockAt(av.lstStockEditDisplay.getSelectedIndex());
        editItem.setBarcode(Integer.parseInt(av.txtEditBarcode.getText()));
        editItem.setStockName(av.txtEditName.getText());
        editItem.setQuantity(Integer.parseInt(av.txtEditQuantity.getText()));
        editItem.setStockPrice(Double.parseDouble(av.txtEditPrice.getText()));

        saveKioskStock();
    }

    public void Remove() {

        IOrders removeValue = getStockAt(av.lstStockEditDisplay.getSelectedIndex());


        sdss.Remove(removeValue);


        saveKioskStock();
    }



    public void LoadKioskData() {
        try{
            File file = new File(filepath);

            Scanner scanner = new Scanner(file);


            while (scanner.hasNextLine()) {
                String tableRow = scanner.nextLine();


                String[] StockItemDetails = tableRow.split(separator);


                IOrders stockItem = new Orders(sdss);

                int BarcodeToInt = Integer.parseInt(StockItemDetails[0]);
                stockItem.setBarcode(BarcodeToInt);


                stockItem.setStockName(StockItemDetails[1]);

                int quantityToInt = Integer.parseInt(StockItemDetails[2]);
                stockItem.setQuantity(quantityToInt);

                double priceToDouble = Double.parseDouble(StockItemDetails[3]);
                stockItem.setStockPrice(priceToDouble);

                System.out.println(stockItem.getStockName());


                sdss.stockItems.add(stockItem);
            }

            Refresh();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void saveKioskStock(){
        try{
            FileWriter writer = new FileWriter(filepath);


            for(int index = 0; index < sdss.stockItems.size(); index++){
                String dataRow = "";

                if(index > 0){
                    dataRow += "\n";
                }

                dataRow += sdss.stockItems.get(index).getBarcode();

                String nameToString = sdss.stockItems.get(index).getStockName();
                dataRow += "|" + nameToString;

                String quantityToString = Integer.toString(sdss.stockItems.get(index).getStockQuantity());
                dataRow += "|" + quantityToString;

                String priceToString = Double.toString(sdss.stockItems.get(index).getStockPrice());
                dataRow += "|" + priceToString;


                writer.write(dataRow);
            }
            writer.close();
            System.out.println("Stock File has been saved!");
            Refresh();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public IOrders getStockAt(int index){
        if(index >= sdss.stockItems.size()){
            return null;
        }
        return sdss.stockItems.get(index);
    }


    public void Refresh(){

        dm.removeAllElements();


        av.lstStockEditDisplay.setModel(dm);
        av.lstDisplayStock.setModel(dm);


        for (int i = 0; i < sdss.stockItems.size(); i++)
        {
            String Name = sdss.stockItems.get(i).getStockName();
            dm.addElement(Name);

        }
    }

}
