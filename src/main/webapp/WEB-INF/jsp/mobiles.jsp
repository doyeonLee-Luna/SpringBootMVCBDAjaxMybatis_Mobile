<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mobile Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f4f7;
            font-family: 'Roboto', sans-serif;
        }
        .container {
            margin-top: 40px;
            max-width: 900px;
        }
        .card {
            border-radius: 12px;
        }
        table {
            background-color: white;
        }
        th, td {
            text-align: center;
        }
        th {
            background-color: #343a40;
            color: white;
        }
        button {
            border-radius: 8px;
            padding: 10px 20px;
            font-size: 16px;
        }
        button:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center mb-4">Mobile Management</h2>

        <!-- Mobile List -->
        <div class="card shadow-sm p-3 mb-4 bg-white rounded">
            <h5 class="card-title text-center">Mobile List</h5>
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>Mobile ID</th>
                        <th>Brand</th>
                        <th>Model</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th>Mobile Type</th>
                    </tr>
                </thead>
                <tbody id="mobileTbody"></tbody>
            </table>
        </div>

        <!-- Mobile Info Form -->
        <div class="card shadow-sm p-3 mb-4 bg-white rounded">
            <h5 class="card-title text-center">Enter Mobile Information</h5>
            <form class="row g-3">
                <div class="col-md-6">
                    <label for="mobileid" class="form-label">Mobile ID</label>
                    <input type="text" class="form-control" id="mobileid" placeholder="Mobile ID">
                </div>
                <div class="col-md-6">
                    <label for="brand" class="form-label">Brand</label>
                    <input type="text" class="form-control" id="brand" placeholder="Brand">
                </div>
                <div class="col-md-6">
                    <label for="model" class="form-label">Model</label>
                    <input type="text" class="form-control" id="model" placeholder="Model">
                </div>
                <div class="col-md-6">
                    <label for="price" class="form-label">Price</label>
                    <input type="text" class="form-control" id="price" placeholder="Price">
                </div>
                <div class="col-md-6">
                    <label for="stock" class="form-label">Stock</label>
                    <input type="text" class="form-control" id="stock" placeholder="Stock">
                </div>
                <div class="col-md-6">
                    <label for="mobileType" class="form-label">Mobile Type</label>
                    <input type="text" class="form-control" id="mobileType" placeholder="Mobile Type">
                </div>
            </form>
        </div>

        <!-- Button Group -->
        <div class="d-grid gap-2 d-md-flex justify-content-md-center">
            <button type="button" class="btn btn-primary me-2" id="btnInsert">Register</button>
            <button type="button" class="btn btn-warning me-2" id="btnUpdate">Update</button>
            <button type="button" class="btn btn-danger" id="btnDelete">Delete</button>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        window.onload = function(){
            listMobile();
            document.querySelector("#btnInsert").onclick = insertMobile;
            document.querySelector("#btnUpdate").onclick = updateMobile;
            document.querySelector("#btnDelete").onclick = deleteMobile;
        }

        async function listMobile(){
            let url = '/mobiles/list';
            try{
                let response = await fetch(url);
                let data = await response.json();
                makeListHtml(data);
            }catch(error){
                console.error(error);
                alert('Error occurred while retrieving mobile list!');
            }
        }

        function makeListHtml(list){
            let listHTML = ``;
            list.forEach(el => {
                listHTML +=
                    `<tr style="cursor:pointer" data-mobileid=\${el.mobileid}>
                        <td>\${el.mobileid}</td>
                        <td>\${el.brand}</td>
                        <td>\${el.model}</td>
                        <td>\${el.price}</td>
                        <td>\${el.stock}</td>
                        <td>\${el.mobileType}</td>
                    </tr>`;
            });

            document.querySelector("#mobileTbody").innerHTML = listHTML;

            document.querySelectorAll("#mobileTbody tr").forEach(el => {
                el.onclick = function(){
                    let mobileid = this.getAttribute("data-mobileid");
                    detailMobile(mobileid);
                }
            });
        }

        async function detailMobile(mobileid){
            let url = '/mobiles/detail/' + mobileid;
            try{
                let response = await fetch(url);
                let data = await response.json();

                document.querySelector("#mobileid").value = data.mobileid;
                document.querySelector("#brand").value = data.brand;
                document.querySelector("#model").value = data.model;
                document.querySelector("#price").value = data.price;
                document.querySelector("#stock").value = data.stock;
                document.querySelector("#mobileType").value = data.mobileType;
            }catch(error){
                console.error(error);
                alert('Error occurred while retrieving mobile details!');
            }
        }

        async function insertMobile(){
            let urlParams = new URLSearchParams({
                mobileid: document.querySelector("#mobileid").value,
                brand: document.querySelector("#brand").value,
                model: document.querySelector("#model").value,
                price: document.querySelector("#price").value,
                stock: document.querySelector("#stock").value,
                mobileType: document.querySelector("#mobileType").value,
            });

            let fetchOptions = {
                method: "POST",
                body: urlParams,
            };

            let url = '/mobiles/insert';

            try{
                let response = await fetch(url, fetchOptions);
                let data = await response.json();

                if(data.result == "success"){
                    alert('Mobile registration successful!');
                }else {
                    alert('Failed to register mobile!');
                }

                listMobile();
            }catch(error){
                console.error(error);
                alert('Error occurred while registering mobile!');
            }
        }

        async function updateMobile(){
            let urlParams = new URLSearchParams({
                mobileid: document.querySelector("#mobileid").value,
                brand: document.querySelector("#brand").value,
                model: document.querySelector("#model").value,
                price: document.querySelector("#price").value,
                stock: document.querySelector("#stock").value,
                mobileType: document.querySelector("#mobileType").value,
            });

            let fetchOptions = {
                method: "POST",
                body: urlParams,
            };

            let url = '/mobiles/update';

            try{
                let response = await fetch(url, fetchOptions);
                let data = await response.json();

                if(data.result == "success"){
                    alert('Mobile update successful!');
                }else {
                    alert('Failed to update mobile!');
                }

                listMobile();
            }catch(error){
                console.error(error);
                alert('Error occurred while updating mobile!');
            }
        }

        async function deleteMobile(){
            let mobileid = document.querySelector("#mobileid").value;
            let url = '/mobiles/delete/' + mobileid;

            try{
                let response = await fetch(url);
                let data = await response.json();

                if(data.result == "success"){
                    alert('Mobile deleted successfully!');
                }else {
                    alert('Failed to delete mobile!');
                }

                listMobile();
            }catch(error){
                console.error(error);
                alert('Error occurred while deleting mobile!');
            }
        }
    </script>
</body>
</html>
