<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- header gibi dosyaları almak için üstteki eklenmeli -->
<%@include file="/WEB-INF/views/template/header.jsp"%>

<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>All Products</h1>

            <p class="lead">Checkout all the awesome products available now!</p>
        </div>

        <table class="table table-striped table-hover">
            <thead>
            <tr class="bg-success">
                <th>Photo Thumb</th>
                <th>Product Name</th>
                <th>Category</th>
                <th>Condition</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
            <tr>
                <!-- <th scope="row">1</th> -->
                <td><img src="#" alt="image" /></td>
                <td>${product.productName}</td>
                <td>${product.productCategory}</td>
                <td>${product.productCondition}</td>
                <td>${product.productPrice}</td>
                <td><a href="<spring:url value="/productList/viewProduct/${product.productId}"/>"><span class="glyphicon glyphicon-info-sign"></span></a></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>

<%@include file="/WEB-INF/views/template/footer.jsp"%>