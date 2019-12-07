
<%@include file="../cabecalho.jsp" %>
<div class="card">
    <div class="card-header">
        <h5 class="title">Editar Streaming</h5>
    </div>
    <div class="card-body">
        <!--MODIFICAR PARA ADD-->
        <form action="PlataformaWS" method="POST">
            <div class="row">
               <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="form-group">
                        <label>Id</label>
                        <input type="text" name="txtId" required class="form-control" readonly value="${obj.id}">
                    </div>
                </div>
            </div>
                <div class="col-md-5 pr-md-1">
                    <div class="form-group">
                        <label>Nome</label>
                        <input type="text" class="form-control" name="txtNome" placeholder="Nome" value="${obj.nome}">
                    </div>
                </div>
            </div>
             <div class="row">
                <div class="col-md-12">
                    <div class="form-group-file">
                        <label for="file">Logo</label>
                        <input type="file" name="txtLogo" required class="form-control form-control-file">
                    </div>
                </div>
            </div>       
                    
            <button class="btn btn-primary btn-round text-center" type="submit">
                <i class="tim-icons icon-cloud-upload-94"></i> Salvar
            </button>
            <a class="btn btn-primary btn-round text-center" href="PlataformaWS?txtAcao=list">
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