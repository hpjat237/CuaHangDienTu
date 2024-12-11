<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách sản phẩm</title>
</head>
<body>
	<h1>Danh sách sản phẩm</h1>
    <input type="text" id="search" placeholder="Tìm kiếm sản phẩm">
    <div id="product-list"></div>
    <script>
        let page = 0;

        function loadProducts() {
            fetch(`/products?page=${page}`)
                .then(response => response.json())
                .then(data => {
                    const productList = document.getElementById('product-list');
                    data.content.forEach(product => {
                        const productDiv = document.createElement('div');
                        productDiv.innerHTML = `
                            <h2>${product.name}</h2>
                            <img src="${product.imageUrl}" alt="Product Image">
                            <p>${product.price}</p>
                        `;
                        productList.appendChild(productDiv);
                    });
                    if (!data.last) {
                        page++;
                    }
                });
        }

        window.onscroll = () => {
            if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
                loadProducts();
            }
        };

        loadProducts();
    </script>
</body>
</html>