<%@include file="../cabecalho.jsp" %>
<div class="card">
    <div class="card-header">
        <h5 class="title">Adicionar Usuário</h5>
    </div>
    <div class="card-body">
        <!--MODIFICAR PARA ADD-->
        <form action="UploadWS" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="urldestino" value="UsuarioWS">
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="faorm-group">
                        <label>Nome do Usuário</label>
                        <input type="text" name="txtNome" required class="form-control" placeholder="Usuario" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="faorm-group">
                        <label>Email do Usuário</label>
                        <input type="email" name="txtEmail" required class="form-control" placeholder="Usuario" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="faorm-group">
                        <label>Senha</label>
                        <input type="password" name="txtSenha" required class="form-control" placeholder="Usuario" >
                    </div>
                </div>
            </div>
           <div class="row">
                <div class="col-md-12 pr-md-1">
                    <div class="faorm-group">
                        <label>Confirmar Senha</label>
                        <input type="password" name="txtConfirma" required class="form-control" placeholder="Usuario" >
                    </div>
                </div>
            </div>
            <button class="btn btn-primary btn-round text-center" type="submit">
                <i class="tim-icons icon-cloud-upload-94"></i> Salvar
            </button>
            <a class="btn btn-primary btn-round text-center" href="UsuarioWS?txtAcao=list">
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