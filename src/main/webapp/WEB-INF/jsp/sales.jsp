<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Mobile Sales Record</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 40px;
            max-width: 800px;
        }
        table {
            background-color: white;
        }
        th, td {
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center mb-4">Mobile Sales Record</h2>

        <!-- 판매 목록 -->
        <div class="card shadow-sm p-3 mb-4 bg-white rounded">
            <h5 class="card-title text-center">Mobile Sales List</h5>
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>Sale ID</th>
                        <th>Customer ID</th>
                        <th>Mobile ID</th>
                        <th>Quantity</th>
                        <th>Total Price</th>
                        <th>Sale Date</th>
                    </tr>
                </thead>
                <tbody id="saleTbody"></tbody>
            </table>
        </div>

        <!-- 판매 정보 입력 폼 -->
        <div class="card shadow-sm p-3 mb-4 bg-white rounded">
            <h5 class="card-title text-center">Enter Sales Information</h5>
            <form class="row g-3">
                <div class="col-md-6">
                    <label for="saleid" class="form-label">Sale ID</label>
                    <input type="text" class="form-control" id="saleid" placeholder="판매 ID">
                </div>
                <div class="col-md-6">
                    <label for="custid" class="form-label">Customer ID</label>
                    <input type="text" class="form-control" id="custid" placeholder="고객 ID">
                </div>
                <div class="col-md-6">
                    <label for="mobileid" class="form-label">Mobile ID</label>
                    <input type="text" class="form-control" id="mobileid" placeholder="모바일 ID">
                </div>
                <div class="col-md-6">
                    <label for="quantity" class="form-label">Quantity</label>
                    <input type="text" class="form-control" id="quantity" placeholder="판매 수량">
                </div>
                <div class="col-md-6">
                    <label for="totalPrice" class="form-label">Total Price</label>
                    <input type="text" class="form-control" id="totalPrice" placeholder="총 가격">
                </div>
                <div class="col-md-6">
                    <label for="saleDate" class="form-label">Sale Date</label>
                    <input type="text" class="form-control" id="saleDate" placeholder="판매일">
                </div>
            </form>
        </div>

        <!-- 버튼 그룹 -->
        <div class="d-grid gap-2 d-md-flex justify-content-md-center">
            <button type="button" class="btn btn-primary me-2" id="btnInsert">Register</button>
            <button type="button" class="btn btn-warning me-2" id="btnUpdate">Update</button>
            <button type="button" class="btn btn-danger" id="btnDelete">Delete</button>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script>
        window.onload = function(){
            listSale();
            document.querySelector("#btnInsert").onclick = insertSale;
            document.querySelector("#btnUpdate").onclick = updateSale;
            document.querySelector("#btnDelete").onclick = deleteSale;
        }

        async function listSale(){
            let url = '/sales/list';
            try{
                let response = await fetch(url);
                let data = await response.json();
                
                makeListHtml(data);
            }catch(error){
                console.error(error);
                alert('판매 목록 조회 중 오류 발생!');
            }
        }

        function makeListHtml(list){
            let listHTML = ``;
            list.forEach(el => {
                listHTML +=
                    `<tr style="cursor:pointer" data-saleid=\${el.saleid}>
                        <td>\${el.saleid}</td>
                        <td>\${el.custid}</td>
                        <td>\${el.mobileid}</td>
                        <td>\${el.quantity}</td>
                        <td>\${el.totalPrice}</td>
                        <td>\${el.saleDate}</td>
                    </tr>`;
            });

            document.querySelector("#saleTbody").innerHTML = listHTML;

            document.querySelectorAll("#saleTbody tr").forEach(el => {
                el.onclick = function(){
                    let saleid = this.getAttribute("data-saleid");    
                    detailSale(saleid);
                }
            });
        }

        async function detailSale(saleid){
            let url = '/sales/detail/' + saleid;
            try{
                let response = await fetch(url);
                let data = await response.json();

                document.querySelector("#saleid").value = data.saleid;
                document.querySelector("#custid").value = data.custid;
                document.querySelector("#mobileid").value = data.mobileid;
                document.querySelector("#quantity").value = data.quantity;
                document.querySelector("#totalPrice").value = data.totalPrice;
                document.querySelector("#saleDate").value = data.saleDate;
                
            }catch(error){
                console.error(error);
                alert('판매 상세 조회 중 오류 발생!');
            }           
        }

        async function insertSale(){
            let urlParams = new URLSearchParams({
                custid: document.querySelector("#custid").value,
                mobileid: document.querySelector("#mobileid").value,
                quantity: document.querySelector("#quantity").value,
                totalPrice: document.querySelector("#totalPrice").value,
                saleDate: document.querySelector("#saleDate").value,
            });

            console.log("전송 데이터:", urlParams.toString()); // 추가

            let fetchOptions = {
                method: "POST",
                body: urlParams,
            };

            let url = '/sales/insert';

            try{
                let response = await fetch(url, fetchOptions);
                let data = await response.json();
                
                console.log("서버 응답:", data); // 추가

                if(data.result == "success"){
                    alert('판매 등록 성공!');
                }else {
                    alert('판매 등록 실패!');
                }
                
                listSale();  // 목록 새로고침
            }catch(error){
                console.error(error);
                alert('판매 등록 처리 중 오류 발생!');
            }
        }

        async function updateSale() {
            let urlParams = new URLSearchParams({
            	saleid: document.querySelector("#saleid").value,
                custid: document.querySelector("#custid").value,
                mobileid: document.querySelector("#mobileid").value,
                quantity: document.querySelector("#quantity").value,
                totalPrice: document.querySelector("#totalPrice").value,
                saleDate: document.querySelector("#saleDate").value,
            });
            
            let fetchOptions = {
                    method: "POST",
                    body: urlParams,
                };
            
            let url = '/sales/update';
            
            try{
            	let response = await fetch(url, fetchOptions);
            	let data = await response.json();
            	
            	if(data.result == "success"){
            		alert('판매 수정 성공!');
            	} else {
            		alert('판매 수정 실패!');
            	}
            	
            	listSale();
            	
            }catch(error){
                console.error(error);
                alert('판매 수정 처리 중 오류 발생!');
            }
        }
        


        async function deleteSale(){
            let saleid = document.querySelector("#saleid").value;
            let url = '/sales/delete/' + saleid;

            try{
                let response = await fetch(url);
                let data = await response.json();

                if(data.result == "success"){
                    alert('판매 삭제 성공!');
                }else {
                    alert('판매 삭제 실패!');
                }
                
                listSale();
                
            }catch(error){
                console.error(error);
                alert('판매 삭제 중 오류 발생!');
            }
        }
    </script>
</body>
</html>
