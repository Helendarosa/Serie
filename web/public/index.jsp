<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file='cabecalho.jsp' %>
<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="left-sidebar">
                    <div class="brands_products"><!--brands_products-->
                        <h2>Genero</h2>
                        <div class="brands-name">
                          <ul class="nav nav-pills nav-stacked">
                          
                                <c:forEach  items="${obj.generos}" var="gen">
                                    <li>
                                        <a href="PublicWS?txtAcao=listGenero&id=${gen.id}"> ${gen.nome}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div><!--/brands_products-->
                    <br><br>
                    <div class="brands_products"><!--brands_products-->
                        <h2>Streaming</h2>
                        <div class="brands-name">
                            <ul class="nav nav-pills nav-stacked">
                                <c:forEach items="${lista}" var="obj">
                                    <li>
                                        <a href="PublicWS?txtAcao=listPlataforma&id=${obj.id}"> <span class="pull-right">${obj.series.size()}</span>${obj.nome}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div><!--/brands_products-->
                    <br><br>
                    <!--pnataly lred12345678901112131415151717176jhjyjyyropaganda
                    <div class="shipping text-center">
                        <img src="images/home/shipping.jpg" alt="" />
                    </div>
                    -->
                </div>
            </div>

            <div class="col-sm-9 padding-right">
                <div class="features_items"><!--features_items-->
                    <h2 class="title text-center">Series</h2>
                    <c:forEach items="${lista}" var="obj">
                        <div class="col-sm-4">
                            <div class="product-image-wrapper">
                                <div class="single-products">
                                    <div class="productinfo text-center">
                                        <div style="height: 200px;">
                                            <img style="max-height: 100%;width: auto;" src="../arquivos/${obj.foto}" alt="" />
                                        </div>
                                        <h2>${obj.nome}</h2>
                                        <p>
                                            <c:forEach items="${obj.generos}" var="genero">
                                                ${genero.nome}
                                            </c:forEach>
                                        </p>
                                        
                                    </div>
                                    <div class="product-overlay">
                                        <div class="overlay-content">
                                            <h2>${serie.nome}</h2>
                                            <p>
                                                <c:forEach items="${serie.generos}" var="genero">
                                                    ${genero.nome}
                                                </c:forEach>
                                            </p>
                                            <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Mais Info</a>
                                            <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Carrinho</a>
                                        </div>
                                    </div>
                                </div>                                    
                            </div>
                        </div>
                    </c:forEach>
                </div><!--features_items--> 
            </div>
        </div>
    </div>
</section>
<%@include file='rodape.jsp' %>