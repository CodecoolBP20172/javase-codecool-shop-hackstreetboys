
$(document).ready(function() {
    addEventListenerToCart();
    addEventListenerToOk();
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


function addEventListenerToOk() {
    $("#button").click(function () {
        $.post("/filter",
            {
                categoryFilter: $("#categoryFilter option:selected").text(),
                supplierFilter: $("#supplierFilter option:selected").text()
            },
            function(data, status) {
                document.getElementById("products").innerHTML = data;
                addEventListenerToCart();
            })
    });
}


function addEventListenerToCart() {
    $(".addToCart").unbind();
    $(".addToCart").click(function () {
        $.post("/addToCart",
            {productId : this.id}, function(data, status) {
                document.getElementById("shoppingCartItems").innerHTML = data;
            });
    })
}
