<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="customerEditUrl" value="/admin/customer-edit" />
<c:url var="customerAPI" value="/api/customer" />
<html>
<head>
    <title>Thông tin khách hàng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb" style="font-family: 'Times New Roman', Times, serif;">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Thêm khách hàng</li>
            </ul><!-- /.breadcrumb -->

        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Thông tin Khách Hàng
                </h1>
            </div><!-- /.page-header -->

            <div class="row" style= "font-family: 'Times New Roman', Times, serif;">
                <form:form modelAttribute="customerEdit" action="${customerEditUrl}" id="listForm" method="GET">
                    <div class="col-xs-12">
                        <form class="form-horizontal" role="form" id = "form-edit">
                            <div class="form-group">
                                <label class="col-sm-3" >Tên khách hàng</label>
                                <div class="col-sm-9">
                                    <form:input path="fullName" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3">Số điện thoại</label>
                                <div class="col-sm-9">
                                    <form:input path="customerPhone" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3">Email</label>
                                <div class="col-sm-9">
                                    <form:input path="email" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3">Tên công ty</label>
                                <div class="col-sm-9">
                                    <form:input path="companyName" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3">Nhu cầu</label>
                                <div class="col-sm-9">
                                    <form:input path="demand" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3">Tình trạng</label>
                                <div class="col-sm-3">
                                    <form:select path="status" cssClass="form-control">
                                        <form:option value="" label="--Chọn tình trạng--"/>
                                        <form:options items="${status}"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3"></label>
                                <div class="col-sm-9">
                                    <c:if test="${empty customerEdit.id}">
                                        <button type="button" id="btnAddOrUpdateCustomer" class="btn btn-primary">
                                            Thêm Khách Hàng
                                        </button>
                                    </c:if>
                                    <c:if test="${not empty customerEdit.id}">
                                        <button type="button" id="btnAddOrUpdateCustomer" class="btn btn-primary">
                                            Cập nhật thông tin
                                        </button>
                                    </c:if>
                                    <a class="btn btn-primary"
                                       href="<c:url value="/admin/customer-list"/>">
                                        Hủy thao tác
                                    </a>
                                </div>
                            </div>
                            <form:hidden path="id" id="customerId"/>
                        </form>
                    </div>
                </form:form>
            </div>
        </div><!-- /.page-content -->


        <c:if test="${not empty customerEdit.id}">
            <%--CSKH--%>
            <div class="col-xs-12">
                <div class="col-sm-12">
                    <h3 class="header smaller lighter blue">Chăm sóc khách hàng</h3>

                    <button type="button" class="btn btn-lg btn-primary" onclick="transactionType('CSKH', ${customerEdit.id})">
                        <i class="orange ace-icon fa fa-location-arrow bigger-130"></i>Add
                    </button>
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Ngày tạo</th>
                            <th>Người tạo</th>
                            <th>Ngày sửa</th>
                            <th>Người sửa</th>
                            <th>Chi tiết giao dịch</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="items" items="${transactionCSKH}">
                            <tr>
                                <td>${items.createdDate}</td>
                                <td>${items.createdBy}</td>
                                <td>${items.modifiedDate}</td>
                                <td>${items.modifiedBy}</td>
                                <td>${items.note}</td>
                                <td>
                                    <button class="btn btn-xs btn-info" title="Sửa giao dịch" onclick="updateTransaction('${items.code}', ${items.id})">
                                        <i class="ace-icon fa fa-pencil"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <%--DDX--%>
            <div class="col-xs-12">
                <div class="col-sm-12">
                    <h3 class="header smaller lighter blue">Dẫn đi xem</h3>
                    <button type="button" class="btn btn-lg btn-primary" onclick="transactionType('DDX', ${customerEdit.id})">
                        <i class="orange ace-icon fa fa-location-arrow bigger-130"></i>Add
                    </button>
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Ngày tạo</th>
                            <th>Người tạo</th>
                            <th>Ngày sửa</th>
                            <th>Người sửa</th>
                            <th>Chi tiết giao dịch</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="items" items="${transactionDDX}">
                            <tr>
                                <td>${items.createdDate}</td>
                                <td>${items.createdBy}</td>
                                <td>${items.modifiedDate}</td>
                                <td>${items.modifiedBy}</td>
                                <td>${items.note}</td>
                                <td>
                                    <button class="btn btn-xs btn-info" title="Sửa giao dịch" onclick="updateTransaction('${items.code}', ${items.id})">
                                        <i class="ace-icon fa fa-pencil"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>

    </div>
</div>
<!-- /.main-content -->

<div class="modal fade" id="transactionTypeModal" role="dialog">
    <div class="modal-dialog">
        <!--Modal Content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">x</button>
                <h4 class="modal-title">Nhập giao dịch</h4>
            </div>

            <div class="modal-body">
                <div class="form-group has-success" id="loadTransaction">
                    <label class="col-xs-12 col-sm-3 control-label no-padding-right">Chi tiết giao dịch</label>
                    <div class="col-xs-12 col-sm-9">
                        <span class="block input-icon input-icon-right">

                        </span>
                    </div>
                </div>
                <input type="hidden" name="customerId" id="customerId" value="">
                <input type="hidden" name="code" id="code" value="">
                <input type="hidden" name="id" id="id" value="">
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAddUpdateTransaction">Thêm giao dịch</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<script>
    $("#btnAddOrUpdateCustomer").click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $("#listForm").serializeArray();
        $.each(formData, function (idx, item) {
            data["" + item.name + ""] = item.value;
        });
        data["isActive"] = 1;
        var customerId = data.id;
        if(data['status'] == '') {
            if(customerId == '') window.location.href = "<c:url value='/admin/customer-edit?status=status_required'/>";
            else window.location.href = "<c:url value='/admin/customer-edit-" + customerId + "?status=status_required'/>";
        }

        else {
            addOrUpdateCustomer(data);
        }

    });

    function addOrUpdateCustomer(data) {
        $.ajax({
            url: "${customerAPI}",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function(){
                console.log("success");
                alert("Success");
                window.location.href = "<c:url value="/admin/customer-list"/>";
            },
            error: function(){
                console.log("failed");
                window.location.href = "<c:url value="/admin/customer-list?message-error"/>";
            }
        });
    }


    function transactionType(code, customerId) {
        $('#transactionTypeModal').modal();
        $('#customerId').val(customerId);
        $('#code').val(code);
        loadTransaction(0);
    }

    function updateTransaction(code, id) {
        $('#transactionTypeModal').modal();
        $('#id').val(id);
        $('#code').val(code);
        loadTransaction(id);
    }

    function loadTransaction(id) {
        $.ajax({
            type: "GET",
            url: '${customerAPI}/' + id + '/transaction',
            datatype: "json",
            contentType: "application/json",
            success: function (response) {
                var row = '';
                $.each(response.data, function (index, item) {
                    // row = '<input type="text" value=' + item.note + ' id="transactionDetail" class="width-100">';
                    row += '<input type="text" id="transactionDetail" class="width-100" value= ' + "'" + item.note.toString() + "'" + ' >';

                });

                $('#loadTransaction span').html(row);
                console.log("success");
            },
            error: function () {
                console.log("failed");
                console.log(response);
            }
        });
    }

    $('#btnAddUpdateTransaction').click(function (e) {
        e.preventDefault;
        var data = {};
        data['id'] = $('#id').val();
        data['customerId'] = $('#customerId').val();
        data['code'] = $('#code').val();
        data['transactionDetail'] = $('#transactionDetail').val();
        addOrUpdateTransaction(data);
    });

    function addOrUpdateTransaction(data) {
        $.ajax({
            url: "${customerAPI}/transaction",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function () {
                console.log("success");
                alert("Success");
                window.location.reload();
            },
            error: function () {
                console.log("failed");
                window.location.href = "<c:url value='/admin/customer-list?error'/>";
            }
        });
    }

</script>
</body>
</html>
