package com;
import inputter.Inputter;
import java.io.*;
import java.util.*;

public class CarList extends ArrayList<Car> {
    private BrandList brandList;

    public CarList(BrandList bList) {
        brandList = bList;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Car x : this)
            s.append(String.format("<%s,%s,%s,%s,%s>\n", x.getCarID(), x.brand.getBrandID(), x.getColor(), x.getFrameID(), x.getEngineID()));
        return s.toString();
    }
    public String screenString() {
        StringBuilder s = new StringBuilder();
        for (Car car : this) 
            s.append(car.getBrand().getBrandName()).append("\n").append(String.format("<%s, %s, %s, %s>\n", car.getCarID(), car.getColor(), car.getFrameID(), car.getEngineID()));
        return s.toString();
    }
    public boolean loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) return false;
        try ( Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String[] parts = sc.nextLine().split(",");
                if (parts.length == 5) {
                    Brand brand = null;
                    for (Brand b : brandList) 
                        if (b.getBrandID().equalsIgnoreCase(parts[1].trim())) {
                            brand = b;
                            break; 
                        }
                    if (brand != null) {
                        Car car = new Car(parts[0].trim(), brand, parts[2].trim(), parts[3].trim(), parts[4].trim());
                        this.add(car);
                    }
                }
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public boolean saveToFile(String filename) {
        try ( FileWriter f = new FileWriter(filename)) {
            for (Car c : this) {
                String line = c.getCarID() + ", " + c.getColor() + ", " + c.getEngineID() + ", " + c.getFrameID() + ", " + c.getBrand().getBrandID() + "\n";
                f.append(line);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public int searchID(String cID) {
        for (int i = 0; i < this.size(); i++)
            if (this.get(i).getCarID().equalsIgnoreCase(cID))
                return i;
        return -1;
    }
    public int searchFrame(String fID) {
        for (int i = 0; i < this.size(); i++) 
            if (this.get(i).getFrameID().equalsIgnoreCase(fID)) 
                return i;
        return -1;
    }
    public int searchEngine(String eID) {
        for (int i = 0; i < this.size(); i++)
            if (this.get(i).getEngineID().equalsIgnoreCase(eID))
                return i;
        return -1;
    }
    public void addCar() {
        String cID = Inputter.inputNonBlankStr("Enter Car ID: ");
        if (searchID(cID) != -1) 
            System.out.println("Car exist!");
        else inputCar(cID);
    }
    public void inputCar(String cID) {
        String color = Inputter.inputNonBlankStr("Enter Color: ");
        Brand chosenBrand = brandList.getUserChoice();
        String frameID = Inputter.inputStr("Enter Frame ID (in F00000 format): ", "F\\d{5}");
        if (searchFrame(frameID) != -1)
            System.out.println("Frame ID already exists!");
        else {
            String engineID = Inputter.inputStr("Enter Engine ID (in E00000 format): ", "E\\d{5}");
            if (searchEngine(engineID) != -1)
                System.out.println("Engine ID already exists!");
            else {
                Car newCar = new Car(cID, chosenBrand, color, frameID, engineID);
                this.add(newCar);
            }
        }
    }
    public void printBasedBrandName(String aPartOfBrandName) {
        if (this.isEmpty()) System.out.println("No car is detected!!!");
        for (Car a : this) {
            if (a.getBrand().getBrandName().contains(aPartOfBrandName)) {
                System.out.println(a.toString());
            }
        }
    }
    public boolean removeCar(String cID) {
        if (searchID(cID) < 0) return false;
        else this.remove(searchID(cID));
        return true;
    }
    public boolean updateCar(String uID) {
        if (searchID(uID) < 0) return false;
        else inputCar(uID);
        return true;
    }
    public void listCars() {
        Collections.sort(this, (o1, o2) -> o1.getBrand().getBrandName().compareTo(o2.getBrand().getBrandName()));
        for (Car car : this) 
            System.out.println( car.toString() );
    }
}
