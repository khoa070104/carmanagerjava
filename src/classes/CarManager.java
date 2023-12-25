package classes;
import com.*;
import inputter.Inputter;
import java.util.ArrayList;

public class CarManager {

    public static void main(String[] args) {
        ArrayList<String> ops = new ArrayList<>();
        ops.add("List all brands");
        ops.add("Add a new brand");
        ops.add("Search a brand based on its ID");
        ops.add("Update a brand");
        ops.add("Save brands to the file, named brands.txt");
        ops.add("List all cars in ascending order of brand names");
        ops.add("List cars based on a part of an input brand name");
        ops.add("Add a car");
        ops.add("Remove a car based on its ID");
        ops.add("Update a car based on its ID");
        ops.add("Save cars to file, named cars.txt");

        BrandList brandList = new BrandList();
        brandList.loadFromFile("src\\carprj\\Brands.txt");

        CarList carList = new CarList(brandList);
        carList.loadFromFile("src\\carprj\\Cars.txt");

        int choice;
        Menu<String> menu = new Menu<>();

        do {
            choice = menu.int_getChoice(ops);

            switch (choice) {
                case 1:
                    brandList.listBrands();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    String brandIDToSearch = Inputter.inputStr("Enter Brand ID to search: ");
                    int searchResult = brandList.searchID(brandIDToSearch);
                    if (searchResult != -1) {
                        System.out.println("Brand found: " + brandList.get(searchResult).toString());
                    } else {
                        System.out.println("Brand not found.");
                    }
                    break;
                case 4:
                    brandList.updateBrand();
                    break;
                case 5:
                    brandList.saveToFile("src\\carprj\\Brands.txt", brandList);
                    System.out.println("Brands saved to brands.txt");
                    break;
                case 6:
                    carList.listCars();
                    break;
                case 7:
                    String partialBrandName = Inputter.inputStr("Enter a part of a brand name: ");
                    carList.printBasedBrandName(partialBrandName);
                    break;
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    String carIDToRemove = Inputter.inputStr("Enter Car ID to remove: ");
                    if (carList.removeCar(carIDToRemove)) {
                        System.out.println("Car removed successfully.");
                    } else {
                        System.out.println("Car not found.");
                    }
                    break;
                case 10:
                    String carIDToUpdate = Inputter.inputStr("Enter Car ID to update: ");
                    if (carList.updateCar(carIDToUpdate)) {
                        System.out.println("Car updated successfully.");
                    } else {
                        System.out.println("Car not found.");
                    }
                    break;
                case 11:
                    carList.saveToFile("src\\carprj\\Cars.txt");
                    System.out.println("Cars saved to cars.txt");
                    break;
            }
        } while (choice > 0 && choice <= ops.size());
    }
}
