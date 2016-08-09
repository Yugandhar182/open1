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
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${products}" var="product">
            <tr>
                <!-- <th scope="row">1</th> -->
                <td><img src="<c:url value="/resources/images/${product.productId}.png"/>" alt="image"
                         style="width:100%"/></td>
                <td>${product.productName}</td>
                <td>${product.productCategory}</td>
                <td>${product.productCondition}</td>
                <td>${product.productPrice} TL</td>
                <td><a href="<spring:url value="/productList/viewProduct/${product.productId}"/>"><span class="glyphicon glyphicon-info-sign"></span></a></td>
            </tr>
            </c:forEach>
        </table>

<%@include file="/WEB-INF/views/template/footer.jsp"%>