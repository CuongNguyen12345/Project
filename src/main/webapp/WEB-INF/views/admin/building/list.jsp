<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp"%>
<c:url var = "buildingListURL" value="/admin/building-list"/>;
<c:url var = "buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
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
                        Quản lý tòa nhà
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
                            <form:form modelAttribute="modelSearch" action="${buildingListURL}" id="listForm" method="GET">
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <label class="name">Tên tòa nhà</label>
                                                    <%--                                                <input type="text" class="form-control" name="name" value="${modelSearch.name}">--%>
                                                <form:input path="name" cssClass="form-control" />
                                            </div>
                                            <div class="col-xs-6">
                                                <label class="name">Diện tích sàn</label>
                                                    <%--                                                <input type="number" class="form-control" name="floorArea" value="${modelSearch.floorArea}">--%>
                                                <form:input path="floorArea" cssClass="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-2">
                                                <label>Quận Hiện có</label>
                                                <form:select path="district" class="form-control">
                                                    <form:option value="" label="---Chọn Quận---"></form:option>
                                                    <form:options items="${districts}"/>
                                                </form:select>
                                            </div>
                                            <div class="col-xs-5">
                                                <label class="name">Phường</label>
                                                <form:input path="ward" cssClass="form-control"/>
                                            </div>
                                            <div class="col-xs-5">
                                                <label class="name">Đường</label>
                                                <form:input path="street" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <label class="name">Số tầng hầm</label>
                                                    <%--                                                <input type="text" class="form-control" name="numberOfBasement" value="${modelSearch.numberOfBasement}">--%>
                                                <form:input path="numberOfBasement" cssClass="form-control"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label class="name">Hướng</label>
                                                <form:input path="direction" cssClass="form-control"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label class="name">Hạng</label>
                                                <form:input path="level" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-3">
                                                <label class="name">Diện tích từ</label>
                                                <form:input path="rentAreaFrom" cssClass="form-control"/>
                                                    <%--                                                <input type="text" class="form-control">--%>
                                            </div>
                                            <div class="col-xs-3">
                                                <label class="name">Diện tích đến</label>
                                                <form:input path="rentAreaTo" cssClass="form-control"/>
                                                    <%--                                                <input type="text" class="form-control">--%>
                                            </div>
                                            <div class="col-xs-3">
                                                <label class="name">Giá thuê từ</label>
                                                <form:input path="rentPriceFrom" cssClass="form-control"/>
                                                    <%--                                                <input type="text" class="form-control">--%>
                                            </div>
                                            <div class="col-xs-3">
                                                <label class="name">Giá thuê đến</label>
                                                <form:input path="rentPriceTo" cssClass="form-control"/>
                                                    <%--                                                <input type="text" class="form-control">--%>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <label class="name">Tên quản lý</label>
                                                <form:input path="managerName" cssClass="form-control"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label class="name">Điện thoại quản lý</label>
                                                <form:input path="managerPhone" cssClass="form-control"/>
                                            </div>
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
                                                <form:checkboxes path="typeCode" items="${typeCodes}"/>
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
                        <a href='<c:url value='/admin/building-edit'/>'>
                            <button class="btn btn-info btn-white btn-bold" title="Thêm tòa nhà">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                    <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </button>
                        </a>
                        <button class="btn btn-danger btn-white btn-bold" title="Xóa tòa nhà" id="deleteBuilding">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-dash" viewBox="0 0 16 16">
                                <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                            </svg>
                        </button>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12" style="font-family: 'Times New Roman', Times, serif;">
                    <display:table name="model.listResult" cellspacing="0" cellpadding="0"
                                   requestURI="${buildingListURL}" partialList="true" sort="external"
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

                        <display:column headerClass="text-left" property="name" title="Tên tòa nhà"/>
                        <display:column headerClass="text-left" property="address" title="Địa chỉ"/>
                        <display:column headerClass="text-left" property="numberOfBasement" title="Số tầng hầm"/>
                        <display:column headerClass="text-left" property="managerName" title="Tên quản lý"/>
                        <display:column headerClass="text-left" property="managerPhone" title="Số điện thoại"/>
                        <display:column headerClass="text-left" property="floorArea" title="DT sàn"/>
                        <display:column headerClass="text-left" property="emptyArea" title="DT trống"/>
                        <display:column headerClass="text-left" property="rentArea" title="DT thuê"/>
                        <display:column headerClass="text-left" property="rentPrice" title="Giá thuê"/>
                        <display:column headerClass="text-left" property="brokerageFee" title="Phí môi giới"/>
                        <display:column headerClass="col-actions" title="Thao tác">
                            <div class="hidden-sm hidden-xs btn-group">
                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-success" title="Giao tòa nhà"
                                            onclick="assignmentBuilding(${tableList.id})">
                                        <i class="ace-icon glyphicon glyphicon-list"></i>
                                    </button>
                                    <a class="btn btn-xs btn-info" title="Sửa tòa nhà"
                                       href= '<c:url value = "/admin/building-edit-${tableList.id}"/>'>
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </a>
                                </security:authorize>
                                <button type="button" class="btn btn-xs btn-danger" title="Xóa tòa nhà" onclick="deleteBuilding(${tableList.id})">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>
                            </div>
                        </display:column>
                    </display:table>
                </div>
            </div>
        </div>
    </div><!-- /.page-content -->

</div><!-- /.main-content -->
<div class="modal fade" id="assignmentBuildingModal" role="dialog">
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
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignmentBuilding"> Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<script>
    function assignmentBuilding(buildingID){
        assignmentModalOpen();
        loadStaff(buildingID);
        $('#buildingId').val(buildingID);
        console.log($('#buildingId').val());
    }
    function assignmentModalOpen(){
        $('#assignmentBuildingModal').modal();
    }
    function loadStaff(buildingId){
        $.ajax({
            type:"GET",
            url: '${buildingAPI}/' + buildingId + '/staffs',
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

    $('#btnAssignmentBuilding').click(function (e) {
        e.preventDefault();
        var data = {}; //object
        data['id'] = $('#buildingId').val();
        var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function() {
            return $(this).val();
        }).get();
        data['staffId'] = staffs;

        $.ajax({
            type: "POST",
            url: '${buildingAPI}/' + $('#buildingId').val() + '/' + staffs,
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
        window.location.href = "<c:url value="/admin/building-list?message-success"/>";
    });

    $('#btnSearch').click(function (e){
        e.preventDefault();
        $('#listForm').submit();
    });

    function deleteBuilding(buildingID) {
        var data = {};
        data["id"] = buildingID;
        $.ajax({
            url: '${buildingAPI}/' + buildingID,
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
        window.location.href = "<c:url value="/admin/building-list?message-success"/>";
    }
    $('#deleteBuilding').click(function (e) {
        e.preventDefault();
        var data = {}; //object
        var building_id = {};
        building_id = $("#tableList").find('tbody input[type = checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        data['id'] = building_id;
        console.log(data);
        $.ajax({
            url: "${buildingAPI}/" + building_id,
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
        window.location.href = "<c:url value="/admin/building-list?message-success"/>";
    });

</script>
</body>
</html>