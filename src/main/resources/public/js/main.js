
$(document).ready(function() {

    $("#button").click(function () {

        $.post("/filter",
          {
              categoryFilter: $("#categoryFilter option:selected").text(),
              supplierFilter: $("#supplierFilter option:selected").text()
          },
          function(data, status) {
            // TODO. HANDLE STATUS

              document.getElementById("products").innerHTML = data;

              addEventListener();
          })
    });

    addEventListener();

});

function addEventListener() {

    $(".addToCart").unbind();

    $(".addToCart").click(function () {
        $.post("/addToCart",
            {
                productId : this.id
            },
            function(data, status) {
                document.getElementById("shoppingCartItems").innerHTML = data;
            });
    })

}