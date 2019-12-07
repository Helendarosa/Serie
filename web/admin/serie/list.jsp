<%@include file="../cabecalho.jsp" %>
 <div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="searchModal" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
                <form action="SerieWS" method="GET" role="search" class="">
              <input name="txtFiltro" type="text" placeholder="Search..." class="form-control">
              <button type="submit" class="btn-search" ><i class="fa fa-search"></i></button>
           </form>
                <i class="tim-icons icon-simple-remove"></i>
              </button>
            </div>
            <div class="modal-footer">
            </div>
          </div>
        </div>
      </div>
<div class="card ">
    <div class="card-header">
        <h4 class="card-title">Série</h4>
    </div>
    <div class="card-body">
        <a class="btn btn-primary btn-round text-center" href="SerieWS?txtAcao=add">
            <i class="tim-icons icon-simple-add"></i> Adiciona
        </a>
        <div class="table-responsive">
            <table class="table tablesorter " id="">
                <thead class=" text-primary">
                    <tr>
                       
                        <th>
                            Nome
                        </th>
                        <th>
                            Gênero
                        </th>
                        <th>
                          Data de Estreia
                        </th>
                         <th>
                          Temporadas
                        </th>
                         <th>
                          Streaming
                        </th>
                        <th>
                            Status
                        </th>
                        <th>
                            Criadores
                        </th>
                          <th>
                              Foto da capa
                          </th>
                          <th>
                              Sinopse
                          </th>
                        <th>
                            Edita
                        </th>
                        <th>
                            Deleta
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lista}" var="obj">
                    <tr>
                        <td>${obj.nome}</td> 
                         <td>
                            <c:forEach items="${obj.generos}" var="gen">
                                ${gen.nome}<br>
                            </c:forEach>
                        </td>
                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${obj.dataestreia}"></fmt:formatDate></td>
                        <td>${obj.temporada}</td> 
                        <td>${obj.plataforma.nome}</td>
                         <td>
                              <c:if test="${obj.encerrado == true}"><span class="fa fa-check"></span>i</c:if>
                              <c:if test="${obj.encerrado == false}"><span class="fa fa-close"></span>iii</c:if>
                          </td>
                        <td>${obj.criador}</td> 
                        <td><img src="../../arquivos/${obj.foto}" style="height: 40px;"></td>
                       
                        <td>${obj.sinopse}</td>
                         
                        
                        <td>
                            <a class="btn btn-info btn-fab btn-icon btn-round" href="SerieWS?txtAcao=edit&txtId=${obj.id}">
                                <i class="tim-icons icon-pencil"></i>
                            </a>
                        </td>
                        <td>
                                <a class="btn btn-primary btn-fab btn-icon btn-round" href="SerieWS?txtAcao=del&txtId=${obj.id}">
                                <i class="tim-icons icon-trash-simple"></i>
                            </a>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="card-footer">
        <c:if test="${msg != null}">
        <div class="alert alert-primary alert-dismissible fade show" role="alert">
            ${msg}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <i class="tim-icons icon-simple-remove"></i>
            </button>
        </div>
        </c:if>
    </div>
</div>

<%@include file="../rodape.jsp" %>