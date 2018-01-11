import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.OrderController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static Integer userId = 0;

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();

        get("/", (Request req, Response res) -> new ThymeleafTemplateEngine().render( ProductController.renderProducts(req, res, userId) ));

        post("/filter", ProductController::renderProductsByFilter);

        post("/addToCart", (Request req, Response res) -> OrderController.renderOrder(req, res, userId));

        get("/shoppingCart", (Request req, Response res) -> new ThymeleafTemplateEngine().render(new ModelAndView(OrderController.renderModal(req, res, userId), "product/modal")));


        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier apple = new Supplier("Apple", "Electronic devices");
        supplierDataStore.add(apple);
        Supplier nokia = new Supplier("Nokia", "Electronic devices");
        supplierDataStore.add(nokia);


        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "A phone computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(phone);
        ProductCategory computer = new ProductCategory("Computer", "Hardware", "A computer is a  computer");
        productCategoryDataStore.add(computer);


        //setting up products and printing it
        Product product = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        productDataStore.add(product);
        product = new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo);
        productDataStore.add(product);
        product = new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon);
        productDataStore.add(product);
        product = new Product("Apple Iphone 5S",178,"USD", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.", phone, apple);
        productDataStore.add(product);
        product = new Product("Apple MacBook Air", 898,"USD", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.", computer, apple);
        productDataStore.add(product);
        product = new Product("Apple Ipad", 311, "USD", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.", tablet, apple);
        productDataStore.add(product);
        product = new Product("Nokia 3310", 60, "USD", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.", phone, nokia);
        productDataStore.add(product);

        //setting up default order
        Order order = new Order(userId);
    }


}
