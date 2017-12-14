
$(document).ready(function() {

    console.log($("#categoryFilter option:selected").text())

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

        $("#modal").click(function () {

            $.post("/shoppingCart",
                {},
                function(data, status) {
                    document.getElementById("content").innerHTML = data;

                    console.log(data);
                });

        })
    })

}