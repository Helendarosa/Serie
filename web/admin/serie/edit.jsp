
<%@include file="../cabecalho.jsp" %>
<div class="card">
    <div class="card-header">
        <h5 class="title">Editar Série</h5>
    </div>
    <div class="card-body">
        <!--MODIFICAR PARA ADD-->
        <form action="UploadWS" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="urldestino" value="SerieWS">
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>id</label>
                        <input readonly type="text" name="txtId" required class="form-control" placeholder="Autor" value="${obj.id}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Nome</label>
                        <input type="text" name="txtNome" required class="form-control" placeholder="Serie" value="${obj.nome}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Gêneros</label>
                        <div class="row">
                            <c:forEach items="${generos}" var="obj">
                                <div class="col-md-3">   
                                    <div class="custom-control custom-checkbox">
                                        <input value="${obj.id}" type="checkbox" class="custom-control-input" id="u${obj.id}" name="txtGeneros" >
                                        <label class="custom-control-label" for="u${obj.id}">${obj.nome}</label>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                </div>
            </div>  
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Data de Estreia</label>
                        <input type="date" name="txtData" required class="form-control" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${obj.dataestreia}"/>" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Temporadas</label>
                        <input type="number" name="txtTemporada" required class="form-control" placeholder="Serie" value="${obj.temporada}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Streaming</label>
                        <select name="txtPlataforma" class="form-control">
                            <c:forEach items="${plataformas}" var="obj">
                                <option value="${obj.id}">${obj.nome}</option>                                                                
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>    
                        <div class="row">
                            <div class="col-md-12 pr-md-1">
                                <div class="form-group">
                                    <label>Status</label><br/>
                                    <div class="bt-df-checkbox pull-left">
                                        <div class="row">
                                            <div class="col-md-12 pr-md-1">
                                            <div class="i-checks pull-left">
                                                <label>
                                                    <input name="txtStatus" type="radio" value="Sim" <c:if test="${serie.encerrado == true}">checked</c:if> > <i></i>  Em andamento
                                                    </label>
                                                </div>
                                            </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12 pr-md-1">
                                                    <div class="i-checks pull-left">
                                                    <label>
                                                      <input name="txtStatus" type="radio" value="Não" <c:if test="${serie.encerrado == false}">checked</c:if> > <i></i> Encerrado 
                                                    </label><br/><br/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                                            <div class="row">
                                                <div class="col-md-12 pr-md-1">
                                                    <div class="form-group">
                                                        <label>Criadores</label>
                                                        <input type="text" name="txtCriador" required class="form-control" placeholder="Serie" value="${obj.criador}">
                                                </div>
                                            </div>
                                        </div>   
                    <div class="row">
                        <div class="col-md-12 ">
                            <div class="form-group-file">
                                <label for="file">Foto da Capa</label>
                                <input type="file" name="txtFoto" required class="form-control form-control-file">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 pr-md-1">
                            <div class="form-group">
                                <label>Sinopse</label>
                                <textarea rows="4" name="txtSinopse" cols="80" class="form-control" placeholder="Serie" value="${obj.sinopse}"></textarea>
                            </div>
                        </div>
                    </div>
                    <button class="btn btn-primary btn-round text-center" type="submit">
                        <i class="tim-icons icon-cloud-upload-94"></i> Salvar
                    </button>
                    <a class="btn btn-primary btn-round text-center" href="SerieWS?txtAcao=list">
                        <i class="tim-icons icon-bullet-list-67"></i> Listar
                    </a>
                    </form>
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
    </div>
    <%@include file="../rodape.jsp" %>