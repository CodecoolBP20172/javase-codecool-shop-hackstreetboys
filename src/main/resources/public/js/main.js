
$(document).ready(function() {
    addEventListenerToCartButton();
    addEventListenerToOkButton();
    addEventListenerToModal();
});


function addEventListenerToModal() {
    $("#modal").click(function () {
        $.post("/shoppingCart", {},
            function(data, status) {
                document.getElementById("content").innerHTML = data;
                console.log(data);
            });
    })
}


function addEventListenerToOkButton() {
    $("#button").click(function () {
        $.post("/filter",
            {
                categoryFilter: $("#categoryFilter option:selected").text(),
                supplierFilter: $("#supplierFilter option:selected").text()
            },
            function(data, status) {
                document.getElementById("products").innerHTML = data;
                addEventListenerToCartButton();
            })
    });
}


function addEventListenerToCartButton() {
    $(".addToCart").unbind();
    $(".addToCart").click(function () {
        $.post("/addToCart",
            {productId : this.id}, function(data, status) {
                document.getElementById("shoppingCartItems").innerHTML = data;
            });
    })
}
