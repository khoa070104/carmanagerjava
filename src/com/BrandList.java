package com;
import inputter.Inputter;
import java.io.*;
import java.util.*;

public class BrandList extends ArrayList<Brand>{
    
    public BrandList() {
    }

    public boolean loadFromFile(String str) {
        File file = new File(str);
        if (!file.exists()) 
            return false;
        try ( Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String[] part = sc.nextLine().split(",");
                if (part.length == 3) {
                    String[] soundAndPrice = part[2].split(":");
                    if (soundAndPrice.length == 2) 
                        this.add(new Brand(part[0], part[1], soundAndPrice[0], Double.parseDouble(soundAndPrice[1])));
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean saveToFile(String filename, List<Brand> brandList) {
        try ( FileWriter writer = new FileWriter(filename)) {
            for (Brand b : brandList) 
                writer.write(b.getBrandID() + ", " + b.getBrandName() + ", " + b.getSoundBrand() + ":" + b.getPrice() + "\n");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public int searchID(String ID) {
        for (int i = 0; i < this.size(); i++) 
            if (this.get(i).getBrandID().equalsIgnoreCase(ID)) 
                return i;
        return -1;
    }

    public Brand getUserChoice() {
        Menu<Brand> mnu = new Menu();
        ArrayList<Brand> bOp = new ArrayList(this);
        Brand sB = mnu.ref_getChoice(bOp);
        return sB;
    }
    public void addBrand(){
        String ID = Inputter.inputStr("Input Brand ID:");
        if(searchID(ID)<0){
            String brandName = Inputter.inputNonBlankStr("Input Brand Name: ");
            String soundBrand = Inputter.inputNonBlankStr("Input Sound Brand: ");
            double price = Inputter.inputDoublle("Input price: ");
            Brand newBrand = new Brand(ID, brandName, soundBrand, price);
            this.add(newBrand);
        }
    }

    public void updateBrand() {
        String brandID = Inputter.inputNonBlankStr("Input Brand to update: ");
        if (searchID(brandID) < 0) 
            System.out.println("Brand with ID " + brandID + " not found.");
        else {
            Brand exit = this.get(searchID(brandID));
            exit.setBrandName(Inputter.inputNonBlankStr("Input new Brand Name: "));
            exit.setSoundBrand(Inputter.inputNonBlankStr("Input new Sound Brand: "));
            exit.setPrice(Inputter.inputDoublle("Input price (>0): "));
            System.out.println("Brand with ID " + brandID + " has been updated.");
        }
    }

    public void listBrands() {
        if (this.isEmpty()) {
            System.out.println("No brands available.");
            return;
        }
        System.out.println("List of Brands:");
        for (Brand b : this)
            System.out.println( b.toString() );
    }

}
