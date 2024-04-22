<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var = "customerListUrl" value="/admin/customer-list"/>
<c:url var = "customerAPI" value="/api/customer"/>
<html>
<head>
    <title>Danh sách khách hàng</title>
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
                </ul><!-- /.breadcrumb -->

            </div>
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        Dashboard
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            Quản lý khách hàng
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->
                <div class="col-xs-12" style="font-family: 'Times New Roman', Times, serif;">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm Kiếm</h4>
                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form modelAttribute="modelSearch" action="${customerListUrl}" id="listForm" method="GET">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <label class="name">Tên khách hàng</label>
                                                    <form:input path="fullName" cssClass="form-control" />
                                                </div>
                                                <div class="col-xs-4">
                                                    <label class="name">Di động</label>
                                                    <form:input path="phoneNumber" cssClass="form-control" />
                                                </div>
                                                <div class="col-xs-4">
                                                    <label class="name">Email</label>
                                                    <form:input path="email" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <security:authorize access="hasRole('MANAGER')">
                                                        <label class="name">Chọn nhân viên phụ trách</label>
                                                        <form:select path="staffId" cssClass="form-control">
                                                            <form:option value="" label="---Chọn nhân viên---"></form:option>
                                                            <form:options items="${listStaff}"/>
                                                        </form:select>
                                                    </security:authorize>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <button class="btn btn-xs btn-danger" id="btnSearch">
                                                        <i class="ace-icon glyphicon glyphicon-search"></i>
                                                        Tìm Kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div><!-- /.row -->
                                </form:form>

                            </div>
                        </div>
                        <div class="pull-right">
                            <a href="<c:url value="/admin/customer-edit"/>">
                                <button class="btn btn-info btn-white btn-bold" title="Thêm khách hàng">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
                                        <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                                        <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
                                    </svg>
                                </button>
                            </a>
                            <button class="btn btn-danger btn-white btn-bold" title="Xóa khách hàng" id="deleteCustomers">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-dash" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1m0-7a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                                    <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
                                </svg>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12" style="font-family: 'Times New Roman', Times, serif;">
                        <display:table name="model.listResult" cellspacing="0" cellpadding="0"
                                       requestURI="${customerListUrl}" partialList="true" sort="external"
                                       size="${model.totalItems}" defaultsort="2" defaultorder="ascending"
                                       id="tableList" pagesize="${model.maxPageItems}"
                                       export="false"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                       style="margin: 3em 0 1.5em;">
                            <display:column title="<fieldset class='form-group'>
                                                        <input type='checkbox' id='checkAll' class='check-box-element'>
                                                        </fieldset>" class="center select-cell"
                                            headerClass="center select-cell">
                                <fieldset>
                                    <input type="checkbox" name="checkList" value="${tableList.id}"
                                           id="checkbox_${tableList.id}" class="check-box-element"/>
                                </fieldset>
                            </display:column>

                            <display:column headerClass="text-left" property="fullName" title="Tên khách hàng"/>
                            <display:column headerClass="text-left" property="phone" title="Di động"/>
                            <display:column headerClass="text-left" property="email" title="Email"/>
                            <display:column headerClass="text-left" property="demand" title="Nhu cầu"/>
                            <display:column headerClass="text-left" property="modifiedDate" title="Ngày thêm"/>
                            <display:column headerClass="text-left" property="modifiedBy" title="Người thêm"/>
                            <display:column headerClass="text-left" property="status" title="Tình trạng"/>
                            <display:column headerClass="col-actions" title="Thao tác">
                                <div class="hidden-sm hidden-xs btn-group">
                                    <security:authorize access="hasRole('MANAGER')">
                                        <button class="btn btn-xs btn-success" title="Giao khách hàng"
                                                onclick="assignmentCustomer(${tableList.id})">
                                            <i class="ace-icon glyphicon glyphicon-list" ></i>
                                        </button>
                                        <a class="btn btn-xs btn-info" title="Sửa khách hàng"
                                           href= '<c:url value = "/admin/customer-edit-${tableList.id}"/>'>
                                            <i class="ace-icon fa fa-pencil-square-o"></i>
                                        </a>
                                    </security:authorize>
                                    <button class="btn btn-xs btn-danger" title="Xóa khách hàng" id="deletes"
                                            onclick="deleteCustomer(${tableList.id})">
                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                    </button>
                                </div>
                            </display:column>
                        </display:table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="assignmentCustomerModal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Danh sách nhân viên</h4>
                </div>
                <div class="modal-body">
                    <table id="staffList" class="table table-striped table-bordered table-hover" style="font-family: 'Times New Roman', Times, serif;">
                        <thead>
                        <tr>
                            <th class="center">Chọn</th>
                            <th class="center">Tên nhân viên</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                    <input type="hidden" id="customerId" name="customerId" value="">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="btnAssignmentCustomer"> Giao khách hàng</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>
<script>
    function assignmentCustomer(customerId) {
        assignmentModalOpen();
        loadStaff(customerId);
        $('#customerId').val(customerId);
    }
    function assignmentModalOpen() {
        $('#assignmentCustomerModal').modal();
    }
    function loadStaff(customerId){
        $.ajax({
            type:"GET",
            <%--url: '${buildingAPI}/' + buildingId + '/staffs',--%>
            url: '${customerAPI}/' + customerId + '/staffs',
            // data: JSON.stringify(data),
            dataType: "json",
            // contentType: "application/json",
            success: function(response){
                var row = '';
                $.each(response.data, function (index, item) {
                    row += '<tr>';
                    row += '<td class="center">' +
                        '<input type="checkbox" value=' + item.staffId + ' id="checkbox_' + item.staffId + '" ' + item.checked + '>' +
                        '</td>';
                    row += '<td class="text-center"> ' + item.fullName + ' </td>'
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
                console.log("success");
            },
            error: function(response){
                console.log("failed");
                console.log(response);
            }
        });
    }
    $('#btnSearch').click(function (e){
        e.preventDefault();
        $('#listForm').submit();
    });

    function deleteCustomer(customerId) {
        var data = {};
        data['id'] = customerId;
        $.ajax({
            url: '${customerAPI}/' + customerId,
            type: "DELETE",
            dataType: "json",
            success: function(response){
                console.log("success");
                console.log(response);
                window.location.href = "<c:url value="/admin/customer-list?message-success"/>";
            },
            error: function(response){
                console.log("failed");
                console.log(response);
                window.location.href = "<c:url value="/admin/customer-list?message-error"/>";
            }
        });
    }

    $('#deleteCustomers').click(function (e) {
        e.preventDefault();
        var data = {}; //object
        var customer_id = {};
        customer_id = $("#tableList").find('tbody input[type = checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        data['id'] = customer_id;
        console.log(data);
        $.ajax({
            url: "${customerAPI}/" + customer_id,
            type: "DELETE",
            // data: JSON.stringify(data),
            dataType: "json",
            // contentType: "application/json",
            success: function(response){
                console.log("success");
                console.log(response);
            },
            error: function(response){
                console.log("failed");
                console.log(response);
            }
        });
        window.location.href = "<c:url value="/admin/customer-list?message-success"/>";
    });

    $('#btnAssignmentCustomer').click(function (e) {
        e.preventDefault;
        var data = {}; //object
        data['id'] = $('#customerId').val();
        var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function() {
            return $(this).val();
        }).get();
        data['staffId'] = staffs;

        $.ajax({
            type: "POST",
            url: '${customerAPI}/' + $('#customerId').val() + '/' + staffs,
            // data: JSON.stringify(data),
            dataType: "json",
            // contentType: "application/json",
            success: function(response){
                console.log("success");
                console.log(response);

            },
            error: function(response){
                console.log("failed");
                console.log(response);
            }

        });
        window.location.href = "<c:url value="/admin/customer-list?message-success"/>";
    })
</script>
</body>
</html>
