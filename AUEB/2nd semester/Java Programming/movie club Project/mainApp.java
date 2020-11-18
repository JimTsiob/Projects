import java.util.Scanner;
import java.util.ArrayList;
public class mainApp {
    private static Scanner input = new Scanner(System.in);
    private static Catalog catMovie = Fmc.get_cat("1mg_data.txt", "MOVIE");
    private static Catalog catGame = Fmc.get_cat("1mg_data.txt", "GAME");
    private static CatalogEni catEni = Fmc.get_cateni("1mgeni_data.txt", catMovie, catGame);
    private static boolean canContinue = true;

    public static void main(String[] args) {
    //Store
    while (canContinue) {
        System.out.println(Product.LINE + "\n\t|Welcome|\n" + Product.LINE + "\n-> 0 | Movie overview\n-> 1 | Game overview\n-> 2 | Enikiasi overview\n-> 3 | Save Information\n-> 4 | Exit");
        printEnterNumber();
        int x = input.nextInt();
        switch (x) {
            case 0: //Movie
                do { //x = [0, 1]
                    System.out.println(Product.LINE + "\n\t|Select a type|\n" + Product.LINE + "\n-> 0 | BLUE RAY\n-> 1 | DVD");
                    printEnterNumber();
                    x = input.nextInt();
                } while (!(x == 0 || x == 1));
                ArrayList<Product> m_list = listTypeMovie(catMovie, x);
                selectProduct(m_list, catMovie);
                break;
            case 1: //Game
                do { //x = [0, 2]
                    System.out.println(Product.LINE + "\n\t|Select a type|\n" + Product.LINE + "\n-> 0 | XBOX\n-> 1 | NINTENDO\n-> 2 | PLAY STATION");
                    printEnterNumber();
                    x = input.nextInt();
                } while (!(x >= 0 && x <= 2));
                ArrayList<Product> g_list = listTypeGame(catGame, x);
                selectProduct(g_list, catGame);
                break;
            case 2: //Enikiasi
                if (catEni.getArray().isEmpty()) {
                    System.out.println(Product.LINE + "\n\t|There is no enikiasi|\n" + Product.LINE);
                    break;
                } else {
                    int count;
                    do { //x = [0, count)
                        System.out.println(Product.LINE + "\n\t|Select an enikiasi|\n" + Product.LINE);
                        count = 0;
                        for (Enikiasi eni : catEni.getArray()) {
                            System.out.printf("-> %d | ", count++);
                            System.out.println(eni.getEniProduct().getName());
                        }
                        printEnterNumber();
                        x = input.nextInt();
                    } while (!(x >= 0 && x < count));
                    Enikiasi _eni = catEni.get(x);
                    System.out.print(_eni);

                    do { //x = [0, 1]
                        System.out.println(Product.LINE + "\n\tDo you want to return this?\n" + Product.LINE);
                        printYesNo();
                        x = input.nextInt();
                    } while (!(x == 0 || x == 1));
                    if (x == 0) {
                        System.out.println("\n>> Full cost: " + _eni.getCost() + "$ <<\n");
                        catEni.remove(_eni);
                    }
                    break;
                }
            case 3:
                Fmc.save("1mgeni_data.txt", catEni);
                System.out.println(Product.LINE + "\n>> Saved! <<");
                break;
            case 4:
                Fmc.save("1mgeni_data.txt", catEni);
                System.out.print(Product.LINE + "\nBye :D\n" + Product.LINE);
                canContinue = false;
            }
        }
    }

    private static void selectProduct(ArrayList<Product> p_list, Catalog cat) {
        int x, count;
        do { //x = [0, count)
            count = 0;
            System.out.println(Product.LINE + "\n\t|Select product|\n" + Product.LINE);
            for (Product p : p_list) { //Print the products
                System.out.printf("-> %d | ", count++);
                System.out.println(p.getName());
            }
            printEnterNumber();
            x = input.nextInt();
        } while (!(x >= 0 && x < count));
        Product selectedProduct = p_list.get(x);
        
        System.out.println(Product.LINE + "\n\t|Product information|");
        System.out.println(selectedProduct);

        if (cat.has(selectedProduct)){
            do { //x = [0, 1]
                System.out.println(Product.LINE + "\n\t|Do you want to rent this product?|\n" + Product.LINE);
                printYesNo();
                x = input.nextInt();
            } while (!(x == 0 || x == 1));
            if (x == 0) rent(selectedProduct, cat);
        } else {
            System.out.println(Product.LINE + "\n\t|There is no copy available|");
        }
    }

    private static void rent(Product selectedProduct, Catalog cat) {
        String name, phone, date;
        int timeEni, cost;
        cost = 4;
        System.out.print(Product.LINE);

        do { //Name
            name = "";
            System.out.print("\nEnter your name(Only characters): ");
            name = input.next();
        } while (!name.matches("[A-Za-z]+")); //Has only characters

        do { //Phone number
            phone = "";
            System.out.print("\nEnter your phone number(Only numbers): ");
            phone = input.next();
        } while (!phone.matches("\\d+")); //Has only numbers
        String[] namePhone = {name, phone};

        do { //Date
            date = "";
            System.out.print("Enter the current date(Only numbers)\n  Enter day(Only numbers): ");
            date = input.next();
            System.out.print("  Enter month(Only numbers): ");
            date += "/" + input.next();
            System.out.print("  Enter year(Only numbers): ");
            date += "/" + input.next();
            date = date.trim();
        } while (!date.matches("\\d+/\\d+/\\d+"));

        if (selectedProduct instanceof Movie) { //Rent time(weeks or days). The time is the same, but the output to the consoleP will change
            Movie _m = (Movie)selectedProduct;
            if (_m.isBr() || _m.isLatestRelease()) {
                System.out.print("Enter the number of days you want to rent this movie: ");
                timeEni = input.nextInt();
            } else {
                System.out.print("Enter the number of weeks you want to rent this movie: ");
                timeEni = input.nextInt();
            }
        } else {
            System.out.print("Enter the number of weeks you want to rent this game: ");
            timeEni = input.nextInt();
        }

        int x; //Discount
        do { //x = [0, 1]
            System.out.println("Are you a parent?");
            printYesNo();
            x = input.nextInt();
        } while (!(x == 0 || x == 1));
        if (x == 0){
            cost /= 2;
            System.out.println("You have a discount. (50%)");
        }

        Enikiasi eni_p; //Create new enikiasi. (true) = is week, (false) = is day
        if (selectedProduct instanceof Movie) {
            Movie _m = (Movie)selectedProduct;
            if (_m.isBr() || _m.isLatestRelease()) {
                eni_p = new Enikiasi(cat, selectedProduct, namePhone, date, timeEni, cost, false);
            } else {
                eni_p = new Enikiasi(cat, selectedProduct, namePhone, date, timeEni, cost, true); 
            }
        } else {
            eni_p = new Enikiasi(cat, selectedProduct, namePhone, date, timeEni, cost, true);            
        }
        catEni.add(eni_p);
        System.out.println("\n>> Your product code is: " + eni_p.getCode() + " <<\n");
    }

    private static ArrayList<Product> listTypeMovie(Catalog catMovie, int x) { //Create sub list for (x) category
        ArrayList<Product> list_m = new ArrayList<>();
        switch (x) {
            case 0: //Add only Blue ray to list
                for (Product p : catMovie.getArray()) {
                    Movie p_m = (Movie)p;
                    if (p_m.isBr()) list_m.add(p);
                }
                break;
            case 1: //Add only DVD to list
                for (Product p : catMovie.getArray()) {
                    Movie p_m = (Movie)p;
                    if (!p_m.isBr()) list_m.add(p);
                }
        }
        return list_m;
    }

    private static ArrayList<Product> listTypeGame(Catalog catGame, int x) { //Create sub list for (x) category
        ArrayList<Product> list_g = new ArrayList<>();
        switch (x) {
            case 0: //Add only Xbox to list
                for (Product p : catGame.getArray()) {
                    Game p_g = (Game)p;
                    if (p_g.getPlatform().equals("XBOX")) list_g.add(p);
                }
                break;
            case 1: //Add only Nintendo to list
                for (Product p : catGame.getArray()) {
                    Game p_g = (Game)p;
                    if (p_g.getPlatform().equals("NINTENDO")) list_g.add(p);
                }
                break;
            case 2: //Add only Play Station to list
                for (Product p : catGame.getArray()) {
                    Game p_g = (Game)p;
                    if (p_g.getPlatform().equals("PLAY STATION")) list_g.add(p);
                }
        }
        return list_g;
    }

    private static void printYesNo() {
        System.out.println("-> 0 | Yes\n-> 1 | No");
        printEnterNumber();
    }

    private static void printEnterNumber() {
        System.out.print("Enter one of the numbers above: ");
    }
}