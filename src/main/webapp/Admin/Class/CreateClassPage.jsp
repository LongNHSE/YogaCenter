<%-- 
    Document   : signup
    Created on : Mar 2, 2023, 12:03:46 PM
    Author     : Oalskad
--%>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Admin/cssAdmin/style.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create class</title>
    </head>


    <body>
        <form action="<%=url%>/ClassController" method="POST">
            <section class="vh-100 gradient-custom">
                <div class="container py-5 h-100">
                    <div class="row justify-content-center align-items-center h-100">
                        <div class="col-12 col-lg-9 col-xl-7">
                            <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                                <div class="card-body p-4 p-md-5">
                                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Create Class</h3>


                                    <div class="row">
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">

                                                <select name="listLoaiLopHoc"  onchange="getHocPhi(this.value)" class="selected-control " onchange="getHocPhi(this.value)" required>
                                                    <option class="form-label" value=""> Please choose type of class</option>
                                                    <c:forEach items="${listLoaiLopHoc}" var="LoaiLopHoc">
                                                        <option class="form-label" value="${LoaiLopHoc.maLoaiLopHoc}">${LoaiLopHoc.tenLoaiLopHoc}</option>
                                                    </c:forEach>
                                                </select>
                                                <label class="form-label" for="LoaiLopHoc">Type of class</label>
                                            </div>
                                            <div class="form-outline">
                                                <input type="number" id="soLuongHV" class="form-control form-control-lg" name="soLuongHV" required="required" />
                                                <label class="form-label" for="soLuongHV">Number of Trainees</label>
                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                <p id="price" class="form-control-lg form-control"></p>
                                                <label class="form-label" for="price">Price</label>
                                            </div>
                                            <div class="form-outline">
                                                <input type="number" id="soBuoi" class="form-control form-control-lg" name="soBuoi" required="required" />
                                                <label class="form-label" for="soBuoi">Number of Slots</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row" id="lichHoc" >
                                        <div class="col-md-6 mb-4 ">


                                            <div class="form-outline">


                                                <c:forEach items="${weekdays}" var="weekdays" varStatus="loop" >
                                                    <input type="text" id="weekdays" class="form-control form-control-lg" name="weekdays" value="${weekdays}"  readonly/>
                                                </c:forEach>

                                                <label class="form-label" for="Slot">Slot</label>
                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4 ">



                                            <input type="text" id="slot" class="form-control form-control-lg" name="slot" value="${slot}" readonly />



                                        </div>


                                    </div>
                                </div>
                                <p class="form-check-label"  id="checkRoom"></p>
                                <div style="margin-bottom: 50px"></div>
                                <div class="row">
                                    <div class="form-outline datepicker w-100">
                                        <input type="Date" class="form-control form-control-lg" id="initializeDate" name="initializeDate" required="required" onchange="checkDate()" />
                                        <label for="initializeDate" class="form-label">Initialize Date</label>
                                        <div style="color: red; font-weight: BOLD">
                                            <p id="checkDate"></p>
                                        </div>
                                    </div>
                                </div>


                                <div class="mt-4 pt-2">
                                    <input class="btn btn-outline-danger" type="submit" value="Submit" id="submit" disabled />
                                    <input class="btn btn-outline-danger" type="hidden" value="CreateClass" id="submit" name="action"  />
                                </div>




                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </section>
        </form>

    </body>

    <script>
        function checkAvailability() {
            var slot = document.getElementById("Slot").value;
            var checkboxes = document.querySelectorAll('#dayInput input[type="checkbox"]:checked');
            var selectedDays = Array.from(checkboxes).map(function (checkbox) {
                return checkbox.value;
            });

            if (slot !== '' && selectedDays.length === 2) {
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'Admin/Class/checkAvailability.jsp?slot=' + slot + "&weekday=" + selectedDays.join(','), true);
                xhr.responseType = 'document'; // Set the response type to 'document' to handle the response as XML
                xhr.responseType = 'text';
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        var responseText = xhr.responseText.trim();

                        var availability = (responseText == 'true');

                        // Now you can use the 'availability' boolean as needed
                        if (availability) {
                            var checkRoomElement = document.getElementById('checkRoom');
                            checkRoomElement.textContent = "There are available rooms";
                            checkRoomElement.style.fontWeight = 'bold';
                            checkRoomElement.style.color = 'green';
                            var button = document.getElementById('submit'); // Replace 'yourButtonId' with the actual ID of your button
                            button.disabled = false;
                        } else {
                            var checkRoomElement = document.getElementById('checkRoom');
                            checkRoomElement.textContent = "There are no available rooms";
                            checkRoomElement.style.fontWeight = 'bold';
                            checkRoomElement.style.color = 'red';
                            var button = document.getElementById('submit'); // Replace 'yourButtonId' with the actual ID of your button
                            button.disabled = true;
                        }
                    }
                };
                xhr.send();
            } else {
                var checkRoomElement = document.getElementById('checkRoom');
                checkRoomElement.textContent = '';
            }
        }
        function checkDate() {

            var dateElement = document.getElementById('initializeDate');
            var initializeDate = dateElement.value;
            if (initializeDate !== '') {
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'Admin/Class/checkDate.jsp?initializeDate=' + initializeDate, true);
                xhr.responseType = 'document'; // Set the response type to 'document' to handle the response as XML
                xhr.responseType = 'text';
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        var responseText = xhr.responseText.trim();

                        var availability = (responseText == 'true');

                        // Now you can use the 'availability' boolean as needed
                        if (!availability) {
                            var checkDateElement = document.getElementById('checkDate');
                            checkDateElement.textContent = "Classes must be initialized 1 weeks before it is publicized for attendance";
                            checkDateElement.style.fontWeight = 'bold';
                            checkDateElement.style.color = 'red';

                            var button = document.getElementById('submit'); // Replace 'yourButtonId' with the actual ID of your button
                            button.disabled = true;
                        } else {
                            var checkDateElement = document.getElementById('checkDate');
                            checkDateElement.textContent = '';
                            var button = document.getElementById('submit'); // Replace 'yourButtonId' with the actual ID of your button
                            button.disabled = false;
                        }
                    }
                };
                xhr.send();
            } else {
                var checkDateElement = document.getElementById('checkDate');
                checkDateElement.textContent = '';
            }
        }
    //        function checkAvailability() {
    //            var slot = document.getElementById("Slot").value;
    //            var checkboxes = document.querySelectorAll('#dayInput input[type="checkbox"]:checked');
    //            var selectedDays = Array.from(checkboxes).map(function (checkbox) {
    //                return checkbox.value;
    //
    //            });
    //
    //            if (slot !== '' && selectedDays.length == 2) {
    //            var xhr = new XMLHttpRequest();
    //                    xhr.open('GET', 'Admin/Class/checkAvailability.jsp?slot=' + slot + "&weekday=" + selectedDays.join(','), true);
    //                    xhr.onreadystatechange = function () {
    //                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
    //                    var checkRoomElement = document.getElementById('checkRoom');
    //                            var availabilityElement = responseXml.querySelector('check');
    //                            if (availabilityElement) {
    //                    var availability = availabilityElement.textContent === 'true';
    //                            // Now you can use the 'availability' boolean as needed
    //                            if (availability) {
    //                    console.log('The room is available.');
    //                    } else {
    //                    console.log('The room is not available.');
    //                    }
    //
    //                    }
    //                    };
    //                            xhr.send();
    //                    } else {
    //            var checkRoomElement = document.getElementById('checkRoom');
    //            checkRoomElement.textContent = '';
    //            }
    //        }
        function getHocPhi(maLoaiLopHoc) {
            if (maLoaiLopHoc !== '') {
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'Admin/Class/getPrice.jsp?maLoaiLopHoc=' + maLoaiLopHoc, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        var priceElement = document.getElementById('price');
                        priceElement.textContent = xhr.responseText + 'VND';

                    }
                };
                xhr.send();
            } else {
                var priceElement = document.getElementById('price');
                priceElement.textContent = '';

            }
        }


        var check = function () {
            if (document.getElementById('password').value ==
                    document.getElementById('confirmPassword').value) {
                document.getElementById('message').style.color = 'green';
                document.getElementById('message').innerHTML = 'Matching';
                document.getElementById('message').style.fontWeight = 'bold';
                document.getElementById('submit').disabled = false;
            } else {
                document.getElementById('message').style.color = 'red';
                document.getElementById('message').innerHTML = 'Not matching';
                document.getElementById('message').style.fontWeight = 'bold';
                document.getElementById('submit').disabled = true;
            }
        }
        const checkboxes = document.querySelectorAll('input[name="weekday"]');
        const limit = 2; // Set the desired limit

        let checkedCount = 0;
        checkboxes.forEach(function (checkbox) {
            checkbox.addEventListener("change", function () {
                if (this.checked) {
                    checkedCount++;
                } else {
                    checkedCount--;
                }

                if (checkedCount >= limit) {
                    checkboxes.forEach(function (checkbox) {
                        if (!checkbox.checked) {
                            checkbox.disabled = true;
                        }
                    });
                } else {
                    checkboxes.forEach(function (checkbox) {
                        checkbox.disabled = false;
                    });
                }
            });
        });
    </script>
</html>
