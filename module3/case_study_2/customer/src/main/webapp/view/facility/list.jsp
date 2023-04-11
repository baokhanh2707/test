<%--
  Created by IntelliJ IDEA.
  User: win
  Date: 09/11/2022
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 class="align-bottom text-center">Danh Sách Dịch Vụ</h1>
<a href="/facility?action=add" class="btn btn-outline-success my-2 my-sm-0">
    Create
</a>
<form action="/facility?action=search" method="post">
    <input type="text" placeholder="Search by name" name="name">
    <input class="btn btn-outline-success my-2 my-sm-0" type="submit" value="search">
</form>
<table class="table" id="tableFacility">
    <thead>
    <tr>
        <th scope="col">STT</th>
        <th scope="col">Name</th>
        <th scope="col">Area</th>
        <th scope="col">Cost</th>
        <th scope="col">Max People</th>
        <th scope="col">Rent Type Id</th>
        <th scope="col">Type Id</th>
        <th scope="col">Standard Room</th>
        <th scope="col">Description</th>
        <th scope="col">Pool Area</th>
        <th scope="col">Number Of Floor</th>
        <th scope="col">Text</th>
        <th scope="col">Xóa</th>


    </tr>
    </thead>
    <tbody>
    <c:forEach var="facility" items="${facilityList}">
        <tr>
            <td scope="row">${facility.getId()}</td>
            <td>${facility.getName()}</td>
            <td>${facility.getArea()}</td>
            <td>${facility.getCost()}</td>
            <td>${facility.getMaxPeople()} </td>
            <td>${facility.getRentTypeName()}</td>
            <td>${facility.getTypeName()}</td>
            <td>${facility.getStandardRoom()}</td>
            <td>${facility.getDescription()}</td>
            <td>${facility.getPoolArea()}</td>
            <td>${facility.getNumberOfFloors()}</td>
            <td>${facility.getText()}</td>

            <td>
                <button onclick="infoDelete(${facility.getId()})" type="button"
                        class="btn btn-outline-success my-2 my-sm-0" data-bs-toggle="modal"
                        data-bs-target="#exampleModal">
                    Delete
                </button>
            </td>
            <td>
                <a href="/facility?action=edit&id=${facility.getId()}">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Edit</button>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <form action="/facility" method="post">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Delete Facility</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body text-black">
                    <input type="text" hidden name="action" value="delete">
                    <input type="text" hidden id="deleteId" name="deleteId">
                    Are you want to delete this Facility?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Close
                    </button>
                    <button type="submit" class="btn btn-danger btnDelete">Delete</button>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</body>
<script>
    function infoDelete(id) {
        document.getElementById("deleteId").value = id;
    }
</script>
</html>
