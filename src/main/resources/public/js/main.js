
$(document).ready(function() {
    $("#button").click(function () {
        $.post("/filter",
          {
              categoryFilter: $("#categoryFilter option:selected").text(),
              supplierFilter: $("#supplierFilter option:selected").text()
          },
          function(data, status) {
              document.getElementById("products").innerHTML = data;
          });
    })
});
