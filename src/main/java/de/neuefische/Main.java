package de.neuefische;

import de.neuefische.exceptions.ProductNotFoundException;
import de.neuefische.model.*;
import de.neuefische.repository.OrderRepo;
import de.neuefische.repository.ProductRepo;
import de.neuefische.service.ShopService;

import java.time.LocalDateTime;
import java.util.*;

public class Main {

    private static ShopService shopService;

    public static void main(String[] args) {

        shopService = initializeOrderSystem();

        startOrderSystem();
    }

    public static ShopService initializeOrderSystem() {
        //PRODUCT CREATION
        //-----------------
        Product product1 = new PhysicalProduct("P1", "Vacuum Cleaner", Colour.RED);     // Staubsauger
        Product product2 = new PhysicalProduct("P2", "Audio Equipment", Colour.BLUE);   // Stereoanlage
        Product product3 = new PhysicalProduct("P3", "USB Stick", Colour.BLACK);        // USB Stick
        Product product4 = new PhysicalProduct("P4", "Record Player", Colour.BLACK);    // Plattenspieler
        Product product5 = new PhysicalProduct("P5", "Lawn mover", Colour.GREEN);       // Rasenmäher
        Product product6 = new PhysicalProduct("P6", "Couch", Colour.BLUE);             // Couch
        Product product7 = new PhysicalProduct("P7", "Wine Rack", Colour.RED);          // Weinregal
        Product product8 = new PhysicalProduct("P8", "Siemens Fridge", Colour.GREEN);   // Kühlschrank
        Product product9 = new PhysicalProduct("P9", "Bathroom cabinet", Colour.WHITE); // Badezimmerschrank
        Product product10 = new PhysicalProduct("P10", "Mirror", Colour.BLUE);          // Spiegel
        Product product11 = new DigitalProduct("P11", "IntelliJ IDEA Ultimate",
                        "https://www.jetbrains.com/idea/business/download");          // IntelliJ IDEA Ultimate

        Map<String, Product> productsAll = new HashMap<>();
        productsAll.put(product1.getId(), product1);
        productsAll.put(product2.getId(), product2);
        productsAll.put(product3.getId(), product3);
        productsAll.put(product4.getId(), product4);
        productsAll.put(product5.getId(), product5);
        productsAll.put(product6.getId(), product6);
        productsAll.put(product7.getId(), product7);
        productsAll.put(product8.getId(), product8);
        productsAll.put(product9.getId(), product9);
        productsAll.put(product10.getId(), product10);
        productsAll.put(product11.getId(), product11);

        ProductRepo productRepo = new ProductRepo();
        productRepo.setProducts(productsAll);


        //ORDER CREATION
        //----------------
        Order order1 = new Order("O1");
        List<Product> orderProducts1 = new ArrayList<>();
        orderProducts1.add(product1);
        orderProducts1.add(product4);
        orderProducts1.add(product1);
        orderProducts1.add(product9);
        orderProducts1.add(product10);
        order1.setProducts(orderProducts1);
        order1.setOrderstatus(OrderStatus.CANCELED);

        Order order2 = new Order("O2");
        List<Product> orderProducts2 = new ArrayList<>();
        orderProducts2.add(product3);
        orderProducts2.add(product5);
        orderProducts2.add(product1);
        orderProducts2.add(product7);
        orderProducts2.add(product8);
        order2.setProducts(orderProducts2);
        order2.setOrderstatus(OrderStatus.IN_DELIVERY);

        OrderRepo orderRepo = new OrderRepo();
        orderRepo.add(order1);
        orderRepo.add(order2);

        //SHOP SERVICE INSTANTIATION & INITIALIZATION
        //
        ShopService shopService = new ShopService();
        shopService.setOrderRepo(orderRepo);
        shopService.setProductRepo(productRepo);

        return shopService;
    }


    public static void startOrderSystem() {

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println(generateShopMenue());
            String input = scanner.next();

            switch (input) {
                case "a":
                    System.out.println("Geben Sie bitte die gewünschte Produkt-ID an.");
                    input = scanner.next();
                    shopService.getProduct(input); // TODO question: which hierarchical level is responsible for sout()?
                    break;
                case "b":
                    shopService.listProducts();
                    break;
                case "c":
                    System.out.println("Geben Sie bitte die Produkt-IDs der Produkte an, die Sie bestellen möchten");
                    scanner.nextLine(); // TODO ohne diese Zeile geht es nicht. Warum?
                    input = scanner.nextLine();
                    String[] ids = input.split(" ");
                    Order orderToAdd = new Order();
                    List<Product> products = new ArrayList<>();
                    for (int i = 0; i < ids.length; i++) {
                        Optional<Product> productToAdd = null;
                        try {
                            productToAdd = shopService.getProductRepo().get(ids[i]);
                        } catch (ProductNotFoundException e) {
                            System.out.println("Product with id " + ids[i] + " not found");;
                        }
                        if(productToAdd.isPresent())
                            products.add(productToAdd.get());
                    }
                    if(!products.isEmpty()) {
                        orderToAdd.setProducts(products);
                        orderToAdd.setOrderstatus(OrderStatus.COMPLETED);
                        orderToAdd.setDateTime(LocalDateTime.now());
                        shopService.addOrder(orderToAdd);
                    }
                    break;
                case "d":
                    System.out.println("Geben Sie bitte die gewünschte Order-ID an.");
                    input = scanner.next();
                    shopService.getOrder(input);
                    break;
                case "e":
                    shopService.listOrders();
                    break;
                case "f":
                    System.out.println("Beenden...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ungültige Auswahl! Bitte treffen Sie eine gültige Auswahl");
                    break;
            }
        }
    }

    private static Map<String, String> generateShopMenue() {
        System.out.println();
        System.out.println("Bestellsystem");
        System.out.println("Welche Serviceleistung wollen Sie in Anspruch nehmen?");
        Map<String, String> menue = new HashMap<>();
        menue.put("a", "Einzelnes Produkt anzeigen");
        menue.put("b", "Alle Produkte anzeigen");
        menue.put("c", "Neue Bestellung aufgeben");
        menue.put("d", "Einzelne Bestellung anzeigen");
        menue.put("e", "Alle Bestellungen anzeigen");
        menue.put("f", "Bestellsystem beenden");
        return menue;
    }

}