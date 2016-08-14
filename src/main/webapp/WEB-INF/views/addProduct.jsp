<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- header gibi dosyaları almak için üstteki eklenmeli -->
<%@include file="/WEB-INF/views/template/header.jsp"%>

<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Add a Product</h1>

            <p class="lead">Fill the form for adding a product</p>
        </div>

    <form:form action="${pageContext.request.contextPath}/admin/productInventory/addProduct" method="post"
               commandName="product" enctype="multipart/form-data">       

        <div class="form-group">
            <label for="name">Name</label>
            <form:input path="productName" id="name" class="form-Control" />
            </div>

        <div class="form-group">
            <label for="category">Category</label>
            <label class="checkbox-inline"><form:radiobutton path="productCategory" id="category" value="software"></form:radiobutton>Software</label>
            <label class="checkbox-inline"><form:radiobutton path="productCategory" id="category" value="hardware"></form:radiobutton>Hardware</label>
            <label class="checkbox-inline"><form:radiobutton path="productCategory" id="category" value="accessory"></form:radiobutton>Accessory</label>
        </div>

        <div class="form-group">
            <label for="description">Description</label>
            <form:textarea path="productDescription" id="description" class="form-Control" />
        </div>

        <div class="form-group">
            <label for="price">Price</label>
            <form:input path="productPrice" id="price" class="form-Control" />
        </div>

        <div class="form-group">
            <label for="condition">Condition</label>
            <label class="checkbox-inline"><form:radiobutton path="productCondition" id="condition" value="new"></form:radiobutton>New</label>
            <label class="checkbox-inline"><form:radiobutton path="productCondition" id="condition" value="used"></form:radiobutton>Used</label>
        </div>

        <div class="form-group">
            <label for="status">Status</label>
            <label class="checkbox-inline"><form:radiobutton path="productStatus" id="status" value="active"></form:radiobutton>Active</label>
            <label class="checkbox-inline"><form:radiobutton path="productStatus" id="status" value="inactive"></form:radiobutton>Inactive</label>
        </div>

        <div class="form-group">
            <label for="unitInStock">Unit In Stock</label>
            <form:input path="unitInStock" id="unitInStock" class="form-Control" />
        </div>

        <div class="form-group">
            <label for="manufacturer">Manufacturer</label>
            <form:input path="productManufacturer" id="manufacturer" class="form-Control" />
        </div>

        <div class="form-group">
            <label class="control-label" for="productImage">Upload Product Image</label>
            <form:input path="productImage" id="productImage" type="file" class="form:input-large" />
        </div>

        <br><br>
        <input type="submit" value="submit" class="btn btn-default">
        <a href="<c:url value="/admin/productInventory"/>" class="btn btn-default">Cancel</a>

    </form:form>


<%@include file="/WEB-INF/views/template/footer.jsp"%>