<!-- header gibi dosyaları almak için üstteki eklenmeli -->
<%@include file="/WEB-INF/views/template/header.jsp"%>

<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Administrator Page</h1>

            <p class="lead">This is the administration page!</p>
        </div>

        <h3>
            <a href="<c:url value="/admin/productInventory"></c:url> ">Product Inventory</a>
        </h3>

        <p>Here  you can view, modify the products in the inventory !</p>

<%@include file="/WEB-INF/views/template/footer.jsp"%>