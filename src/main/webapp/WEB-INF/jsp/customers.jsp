<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Management</title>
    <!-- Bootstrap CSS 추가 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet"> <!-- Add Roboto font -->
    <style>
        body {
            background-color: #f5f5f7; /* Light gray background */
            font-family: 'Roboto', sans-serif; /* Set Roboto font */
            margin: 0;
            padding: 0;
        }
        .container {
            margin-top: 50px;
            max-width: 900px;
        }
        h2, .card-title {
            font-weight: 700; /* Bold font for headings */
            text-align: center;
            color: #333;
        }
        .card {
            border-radius: 12px;
            box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
        }
        .card-header {
            background-color: #0071e3; /* Apple-like blue */
            color: white;
            font-weight: 700;
            text-align: center;
            border-top-left-radius: 12px;
            border-top-right-radius: 12px;
        }
        .table {
            background-color: white;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
        }
        th, td {
            text-align: center;
            padding: 12px;
        }
        th {
            background-color: #f7f7f7;
            color: #0071e3;
        }
        .btn-custom {
            width: 100%;
            padding: 15px;
            font-size: 16px;
            font-weight: 600;
            border-radius: 8px;
            transition: background-color 0.3s, transform 0.2s ease-in-out;
        }
        .btn-primary {
            background-color: #0071e3; /* Apple-like blue */
            border-color: #0071e3;
            color: white;
        }
        .btn-warning {
            background-color: #ffcc00; /* Apple-like yellow */
            border-color: #ffcc00;
            color: white;
        }
        .btn-danger {
            background-color: #e24e42; /* Apple-like red */
            border-color: #e24e42;
            color: white;
        }
        /* Hover effect */
        .btn-primary:hover {
            background-color: #005bb5;
            transform: translateY(-3px);
        }
        .btn-warning:hover {
            background-color: #e6b800;
            transform: translateY(-3px);
        }
        .btn-danger:hover {
            background-color: #c83933;
            transform: translateY(-3px);
        }
        .btn-custom:focus {
            outline: none;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.15);
        }
        .table-hover tbody tr:hover {
            background-color: #f7f7f7;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Customer Management</h2>

        <!-- Customer List -->
        <div class="card shadow-sm p-3 mb-4 bg-white rounded">
            <div class="card-header">
                <h5 class="card-title">Customer List</h5>
            </div>
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>Customer ID</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Customer Type</th>
                    </tr>
                </thead>
                <tbody id="customerTbody">              
                </tbody>
            </table>
        </div>

        <!-- Customer Information Input Form -->
        <div class="card shadow-sm p-3 mb-4 bg-white rounded">
            <div class="card-header">
                <h5 class="card-title">Customer Information Input</h5>
            </div>
            <form class="row g-3">
                <div class="col-md-6">
                    <label for="custid" class="form-label">Customer ID</label>
                    <input type="text" class="form-control" id="custid" placeholder="Customer ID">
                </div>
                <div class="col-md-6">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" placeholder="Name">
                </div>
                <div class="col-md-6">
                    <label for="address" class="form-label">Address</label>
                    <input type="text" class="form-control" id="address" placeholder="Address">
                </div>
                <div class="col-md-6">
                    <label for="phone" class="form-label">Phone</label>
                    <input type="text" class="form-control" id="phone" placeholder="Phone">
                </div>
                <div class="col-md-6">
                    <label for="customerType" class="form-label">Customer Type</label>
                    <input type="text" class="form-control" id="customerType" placeholder="Customer Type">
                </div>
            </form>
        </div>

        <!-- Button Group -->
        <div class="d-grid gap-2 d-md-flex justify-content-md-center">
            <button type="button" class="btn btn-primary me-2" id="btnInsert">Insert</button>
            <button type="button" class="btn btn-warning me-2" id="btnUpdate">Update</button>
            <button type="button" class="btn btn-danger" id="btnDelete">Delete</button>
        </div>
    </div>

    <!-- Bootstrap JS 추가 (필요 시) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        window.onload = function(){
            listCustomer();
            document.querySelector("#btnInsert").onclick = insertCustomer;
            document.querySelector("#btnUpdate").onclick = updateCustomer;
            document.querySelector("#btnDelete").onclick = deleteCustomer;
        }

        async function listCustomer(){
            let url = '/customers/list';
            try{
                let response = await fetch(url);
                let data = await response.json();
                
                makeListHtml(data);
                
            }catch(error){
                console.log(error);
                alert('Error occurred while retrieving customer list!');
            }
        }

        async function makeListHtml(list){
            let listHTML = ``;
            list.forEach(el => {
                listHTML +=
                    `<tr style="cursor:pointer" data-custid=\${el.custid}>
                        <td>\${el.custid}</td>
                        <td>\${el.name}</td>
                        <td>\${el.address}</td>
                        <td>\${el.phone}</td>
                        <td>\${el.customerType}</td>
                    </tr>`;
            });

            document.querySelector("#customerTbody").innerHTML = listHTML;

            document.querySelectorAll("#customerTbody tr").forEach(el => {
                el.onclick = function(){
                    let custid = this.getAttribute("data-custid");    
                    detailCustomer(custid);
                }
            });
        }

        async function detailCustomer(custid){
            let url = '/customers/detail/' + custid;
            try{
                let response = await fetch(url);
                let data = await response.json();
                
                document.querySelector("#custid").value = data.custid;
                document.querySelector("#name").value = data.name;
                document.querySelector("#address").value = data.address;
                document.querySelector("#phone").value = data.phone;
                document.querySelector("#customerType").value = data.customerType;
                
            }catch( error ){
                console.error( error );
                alert('Error occurred while retrieving customer details!');
            }           
        }

        async function insertCustomer(){
            let urlParams = new URLSearchParams({
                custid: document.querySelector("#custid").value,
                name: document.querySelector("#name").value,
                address: document.querySelector("#address").value,
                phone: document.querySelector("#phone").value,
                customerType: document.querySelector("#customerType").value,
            });

            let fetchOptions = {
                method: "POST",
                body: urlParams,
            };

            let url = '/customers/insert';

            try{
                let response = await fetch(url, fetchOptions);
                let data = await response.json();
                
                if(data.result == "success"){
                    alert('Customer inserted successfully!');
                }else {
                    alert('Failed to insert customer!');
                }
                
                listCustomer();
                
            }catch(error){
                console.error(error);
                alert('Error occurred while inserting customer!');
            }
        }

        async function updateCustomer(){
            let urlParams = new URLSearchParams({
                custid: document.querySelector("#custid").value,
                name: document.querySelector("#name").value,
                address: document.querySelector("#address").value,
                phone: document.querySelector("#phone").value,
                customerType: document.querySelector("#customerType").value,
            });

            let fetchOptions = {
                method: "POST",
                body: urlParams,
            };

            let url = '/customers/update';

            try{
                let response = await fetch(url, fetchOptions);
                let data = await response.json();

                if(data.result == "success"){
                    alert('Customer updated successfully!');
                }else {
                    alert('Failed to update customer!');
                }

                listCustomer();
                
            }catch(error){
                console.error(error);
                alert('Error occurred while updating customer!');
            }
        }

        async function deleteCustomer(){
            let custid = document.querySelector("#custid").value;

            let fetchOptions = {
                method: "POST",
            };

            let url = '/customers/delete/' + custid;

            try{
                let response = await fetch(url, fetchOptions);
                let data = await response.json();
                
                if(data.result == "success"){
                    alert('Customer deleted successfully!');
                }else {
                    alert('Failed to delete customer!');
                }

                listCustomer();
                
            }catch(error){
                console.error(error);
                alert('Error occurred while deleting customer!');
            }
        }
    </script>
</body>
</html>
