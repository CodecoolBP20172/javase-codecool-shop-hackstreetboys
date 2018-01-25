
$(document).ready(function() {
    addEventListenerToAddToCartButton();
    addEventListenerToFilterButton();
    addEventListenerToCartModal();
});


function addEventListenerToCartModal() {
    $("#modal").click(function () {
        $.get("/shoppingCart", {},
            function(data, status) {
                console.log(data);
                document.getElementById("content").innerHTML = data;
            });
    })
}


function addEventListenerToFilterButton() {
    $("#button").click(function () {
        alert("VALAMI BAZD MEG")
        $.post("/filter",
            {
                categoryFilter: $("#categoryFilter").find("option:selected").text(),
                supplierFilter: $("#supplierFilter").find("option:selected").text()
            },

        function(data, status) {
            console.log(data)
            let products = JSON.parse(data);
            console.log(products)
            let template = "";

            for (const product of products) {


                var string = "\n" +
                    "<div class=\"item col-xs-4 col-lg-4\">\n" +
                    "    <div class=\"thumbnail\">\n" +
                    "        <img class=\"group list-group-image\" src='/img/product_{{id}}.jpg'/>\n" +
                    "        <div class=\"caption\">\n" +
                    "            <h4 class=\"group inner list-group-item-heading\">{{name}}</h4>\n" +
                    "            <p class=\"group inner list-group-item-text\">{{description}}</p>\n" +
                    "            <div class=\"row\">\n" +
                    "                <div class=\"col-xs-12 col-md-6\">\n" +
                    "                    <p class=\"lead\">{{defaultPrice}} {{defaultCurrency}}</p>\n" +
                    "                </div>\n" +
                    "                <div class=\"col-xs-12 col-md-6\">\n" +
                    "                    <button id={{id}} class=\"btn btn-success addToCart\">Add to cart</button>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>"

                template = template.concat(string);
                template = Mustache.to_html(template, product);
            }
            document.getElementById("products").innerHTML = template;

            addEventListenerToAddToCartButton();
        })
    });
}


function addEventListenerToAddToCartButton() {
    $(".addToCart").unbind();
    $(".addToCart").click(function () {
        $.post("/addToCart",
            {
                productId : this.id
            },
            function(data, status) {
                numberOfItemsInShoppingCart = JSON.parse(data);
                document.getElementById("shoppingCartItems").innerHTML = " (" + numberOfItemsInShoppingCart + ")";
            });
    })
}