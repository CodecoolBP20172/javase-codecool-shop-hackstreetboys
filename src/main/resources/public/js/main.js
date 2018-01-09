
$(document).ready(function() {
    addEventListenerToAddToCartButton();
    addEventListenerToFilterButton();
    addEventListenerToCartModal();
});


function addEventListenerToCartModal() {
    $("#modal").click(function () {
        $.get("/shoppingCart", {},
            function(data, status) {
                document.getElementById("content").innerHTML = data;
                console.log(data);
            });
    })
}


function addEventListenerToFilterButton() {
    $("#button").click(function () {
        $.get("/filter",
            {
                categoryFilter: $("#categoryFilter option:selected").text(),
                supplierFilter: $("#supplierFilter option:selected").text()
            },
            function(data, status) {
                document.getElementById("products").innerHTML = data;
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
                document.getElementById("shoppingCartItems").innerHTML = " (" + data + ")";
            });
    })
}
