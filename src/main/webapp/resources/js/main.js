$("#addProductModal .addProduct").on("click", function(e) {
	e.preventDefault();
	$("#addProductModal form").submit();
});

$("a.removeProduct").on("click", function(e) {
	if (!confirm("Remover o produto?")) {
		e.preventDefault();
	}
});