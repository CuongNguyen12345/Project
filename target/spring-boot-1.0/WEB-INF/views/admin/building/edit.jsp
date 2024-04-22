<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var = "buildingEditURL" value="/admin/building-edit"/>
<c:url var = "buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Thêm tòa nhà</title>
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
                <li class="active">Thêm tòa nhà</li>
            </ul><!-- /.breadcrumb -->

        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Dashboard
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->
            <div class="row" style= "font-family: 'Times New Roman', Times, serif;">
                <form:form modelAttribute="buildingEdit" action="${buildingEditURL}" id="listForm" method="GET">
                    <div class="col-xs-12">
                        <form class="form-horizontal" role="form" id ="form-edit">
                            <div class="form-group row">
                                <label class="col-sm-3" >Tên tòa nhà</label>
                                <div class="col-sm-9">
                                    <form:input path="name" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for="district">Quận</label>
                                <div class="col-sm-3">
                                    <form:select path="district" cssClass="form-control">
                                        <form:option value="" label="---Chọn quận---"/>
                                        <form:options items="${districts}"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Phường</label>
                                <div class="col-sm-9">
                                    <form:input path="ward" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Đường</label>
                                <div class="col-sm-9">
                                    <form:input path="street" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Kết cấu</label>
                                <div class="col-sm-9">
                                    <form:input path="structure" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Số tầng hầm</label>
                                <div class="col-sm-9">
                                    <form:input path="numberOfBasement" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Diện tích sàn</label>
                                <div class="col-sm-9">
                                    <form:input path="floorArea" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Hướng</label>
                                <div class="col-sm-9">
                                    <form:input path="direction" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Hạng</label>
                                <div class="col-sm-9">
                                    <form:input path="level" cssClass="form-control" />
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-sm-3">Diện tích thuê</label>
                                <div class="col-sm-9">
                                    <form:input path="rentArea" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Giá thuê</label>
                                <div class="col-sm-9">
                                    <form:input path="rentPrice" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Mô tả giá</label>
                                <div class="col-sm-9">
                                    <form:input path="rentPriceDescription" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Phí dịch vụ</label>
                                <div class="col-sm-9">
                                    <form:input path="serviceFee" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Phí ô tô</label>
                                <div class="col-sm-9">
                                    <form:input path="carFee" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Phí mô tô</label>
                                <div class="col-sm-9">
                                    <form:input path="motoFee" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Phí ngoài giờ</label>
                                <div class="col-sm-9">
                                    <form:input path="overtimeFee" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Tiền điện</label>
                                <div class="col-sm-9">
                                    <form:input path="electricityFee" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Đặt cọc</label>
                                <div class="col-sm-9">
                                    <form:input path="deposit" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Thanh toán</label>
                                <div class="col-sm-9">
                                    <form:input path="payment" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Thời hạn thuê</label>
                                <div class="col-sm-9">
                                    <form:input path="rentTime" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Thời gian trang trí</label>
                                <div class="col-sm-9">
                                    <form:input path="decorationTime" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Tên quản lý</label>
                                <div class="col-sm-9">
                                    <form:input path="managerName" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">SĐT quản lý</label>
                                <div class="col-sm-9">
                                    <form:input path="managerPhone" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Phí môi giới</label>
                                <div class="col-sm-9">
                                    <form:input path="brokerageFee" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row" >
                                <label class="col-sm-3">Loại tòa nhà</label>
                                <div class="col-xs-3"
                                     style="display: flex; flex-direction: row; justify-content: space-between" id="typeCode" name="checkList">
                                    <c:if test="${empty buildingEdit.id}">
                                        <form:checkboxes path="typeCode" name="form-field-checkbox" items="${typeCodes}" cssStyle=""/>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3">Ghi chú</label>
                                <div class="col-sm-9">
                                    <form:input path="note" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 no-padding-right">Hình đại diện</label>
                                <input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>
                                <div class="col-sm-9">
                                    <c:if test="${not empty buildingEdit.image}">
                                        <c:set var="imagePath"  value="/repository${buildingEdit.image}"/>
                                        <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                    </c:if>
                                    <c:if test="${empty buildingEdit.image}">
                                        <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                    </c:if>
                                </div>
                            </div>

                            <div class="">
                                <label class="col-sm-3"></label>
                                <div class="col-sm-9">
                                    <c:if test="${not empty buildingEdit.id}">

                                        <button type="button" id="btnAddOrUpdateBuilding" class="btn btn-primary">
                                            Cập nhật tòa nhà
                                        </button>
                                        <a class="btn btn-primary"
                                           href="<c:url value="/admin/building-list"/>">
                                            Hủy thao tác
                                        </a>
                                    </c:if>
                                    <c:if test="${empty buildingEdit.id}">
                                        <button type="button" id="btnAddOrUpdateBuilding" class="btn btn-primary">
                                            Thêm tòa nhà
                                        </button>
                                        <a class="btn btn-primary"
                                           href="<c:url value="/admin/building-list"/>">
                                            Hủy thao tác
                                        </a>
                                    </c:if>
                                </div>
                            </div>
                            <form:hidden path="id" id="buildingId"/>
                        </form>
                    </div>
                </form:form>

            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
<script>
    var imageBase64 = '';
    var imageName = '';
    $("#btnAddOrUpdateBuilding").click(function(e){
        e.preventDefault();
        var data = {}; //object
        var typeCode = [];
        var formData = $("#listForm").serializeArray();
        $.each(formData, function(idx, item){
            if(item.name != 'typeCode'){
                data["" + item.name + ""] = item.value;
            }
            else{
                typeCode.push(item.value);
            }
            if ('' !== imageBase64) {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }
        });
        var buildingId = data.id;
        var type = "";
        for(var i = 0; i < typeCode.length; i++){
            type += typeCode[i];
            if(i < typeCode.length - 1) type += ',';
        }
        data['typeCode'] = type;
        $('#loading_image').show();
        if(type.length != 0){
            createOrUpdateBuilding(data);
        }
        else{
            window.location.href = "<c:url value='/admin/building-edit-"+buildingId+"?typeCode=typeCode_required'/>";
        }
    });
    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. vd: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });
    $('#loading_image').show();

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    function createOrUpdateBuilding(data){
        $.ajax({
            url:"${buildingAPI}",
            type:"POST",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function(response){
                console.log("success");
                window.location.href = "<c:url value="/admin/building-list?message-success"/>";
                console.log(response);
            },
            error: function(response){
                console.log("failed");
                window.location.href = "<c:url value="/admin/building-list?message-error"/>";
                console.log(response);
            }
        });

    }


    window.onload = function loadTypeCode(){
        var data = [];
        var building = $('#buildingId').val();
        data["building"] = building;
        if(building!==""){
            $.ajax({
                url: '${buildingAPI}/' + building + '/typeCodes',
                type: 'GET',
                success: function (response){
                    var row = '';
                    $.each(response.data, function (index, item) {
                        row += '<span>';
                        row += '<input type="checkbox" name = "typeCode" value=' + item.typeCode + ' '+item.checked+'>';
                        row += '<label>'+item.fullName + '</label>';
                        row += '</span>';
                    });
                    $('#typeCode').html(row);
                    console.log("success");
                },
                error: function (response){
                    console.log("fail")
                    window.location.href = "<c:url value='/admin/building-list?message-error'/>";
                    console.log(response.constructor)
                }
            });
        }
    }

</script>
</body>
</html>