<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../cabecalho.jsp" %>
<div class="card">
    <div class="card-header">
        <h5 class="title">Adicionar Série</h5>
    </div>
    <div class="card-body">
        <!--MODIFICAR PARA ADD-->
        <form action="UploadWS" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="urldestino" value="SerieWS">
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Nome</label>
                        <input type="text" name="txtNome" required class="form-control" placeholder="Nome" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <label class="form-group">Gênero:</label>
                </div>
                <div class="col-lg-9">
                    <div class="form-select-list">
                        <select class="form-control custom-select-value" multiple name="txtGeneros">
                            <c:forEach items="${generos}" var="obj">
                                <option value="${obj.id}">${obj.nome}</option>                                                                
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Data de Estreia</label>
                        <input type="date" name="txtData" required class="form-control" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Temporadas</label>
                        <input type="number" name="txtTemporada" required class="form-control" placeholder="Temporadas" >
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
                        <label>Status</label>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="i-checks pull-left">
                                    <label>
                                        <input name="txtEncerrado" type="radio" value="Sim" > <i></i> Em andamento
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="i-checks pull-left">
                                    <label>
                                        <input name="txtEncerrado" type="radio" value="Não" checked> <i></i> Encerrado
                                    </label>
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
                        <input type="text" name="txtCriador" required class="form-control" placeholder="Criador" >
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Sinopse</label>
                        <textarea rows="4" name="txtSinopse" cols="80" class="form-control" placeholder="Sinopse" ></textarea>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="form-group-file">
                        <label for="file">Capa da Série</label>
                        <input type="file" name="txtFoto" required class="form-control form-control-file">
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
