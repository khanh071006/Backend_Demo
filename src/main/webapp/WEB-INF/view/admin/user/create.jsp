<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--@elvariable id="newUser" type="com.example.Project2.domain.User"--%>


<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content="Khanh Dong Gia - Dự án laptopshop"/>
    <meta name="author" content="Khanh Dong Gia"/>
    <title>Dashboard - Khanh Dong Gia</title>
    <link href="/css/styles.css" rel="stylesheet"/>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <script>
        $(document).ready(() => {
            const avatarFile = $("#avatarFile");
            avatarFile.change(function (e) {
                const imgURL = URL.createObjectURL(e.target.files[0]);
                $("#avatarPreview").attr("src", imgURL);
                $("#avatarPreview").css({"display": "block"});
            });
        });
    </script>

</head>

<body class="sb-nav-fixed">
<jsp:include page="../layout/header.jsp"></jsp:include>
<div id="layoutSidenav">
    <jsp:include page="../layout/sidebar.jsp"></jsp:include>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Dashboard</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                    <li class="breadcrumb-item active">Manage Users</li>
                </ol>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">

                            <h3>Create A User</h3>
                            <hr/>
                            <form:form action="/admin/user/create" method="post" modelAttribute="newUser"
                                       enctype="multipart/form-data">
                                <div class="row g-3">
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Email address:</label>
                                        <form:input type="email" class="form-control" path="email"/>
                                    </div>

                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Password:</label>
                                        <form:input type="password" class="form-control" path="password"/>
                                    </div>

                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Phone Number:</label>
                                        <form:input type="text" class="form-control" path="phone"/>
                                    </div>

                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Full Name:</label>
                                        <form:input type="text" class="form-control" path="fullName"/>
                                    </div>

                                    <div class="mb-3 col-12 col-md-12">
                                        <label class="form-label">Address:</label>
                                        <form:input type="text" class="form-control" path="address"/>
                                    </div>

                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">ROLE:</label>
                                        <form:select class="form-select" aria-label="Role" path="role.id">
                                            <form:option value="1">ADMIN</form:option>
                                            <form:option value="2">USER</form:option>
                                        </form:select>
                                    </div>

                                    <div class="mb-3 col-12 col-md-6">
                                        <label for="formFile" class="form-label">Avatar</label>
                                        <input class="form-control" type="file" id="avatarFile"
                                               accept="image/png, image/jpeg, image/jpg"
                                               name="imageFile"/>
                                    </div>

                                    <div class="mb-3 col-12">
                                        <img style="display:none; max-height: 340px;"
                                             id="avatarPreview" alt="Avatar Preview"/>
                                    </div>


                                </div>

                                <!-- Nếu bạn đang bị 403 (Spring Security) thì thêm dòng này -->
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                <button type="submit" class="btn btn-primary">Submit</button>
                            </form:form>
                        </div>

                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="../layout/footer.jsp"></jsp:include>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="/js/scripts.js"></script>
</body>

</html>