$(document).ready(function () {
    function getAllVoucher() {
        $.ajax({
            url: '/api-public/vouchers/getAllVouchers',
            type: 'GET',
            success: function (data) {
                $('#voucherTableBody').empty();
                // Định dạng ngày bằng Moment.js

                $.each(data,function (index,voucher){
                    var formattedStartDate = moment(voucher.startDay).format('DD-MM-YYYY');
                    var formattedEndDate = moment(voucher.endDay).format('DD-MM-YYYY');
                    var newRow = `
                            <tr>
                                <td class="align-middle text-center text-sm">
                                            <div class="d-flex w-100 flex-column justify-content-center">
                                                <h6 class="mb-0 text-sm">${voucher.voucherName}</h6>
                                            </div>
                                </td>
                                <td class="align-middle text-center text-sm">
                                            <span class="text-secondary text-xs font-weight-bold">${voucher.reduce}%</span>
                                </td>
                                <td class="align-middle text-center text-sm">
                                            <span class="text-secondary text-xs font-weight-bold">${voucher.quantity}</span>
                                </td>
                                <td class="align-middle text-center">
                                            <span class="text-secondary text-xs font-weight-bold">${formattedStartDate}</span>
                                </td>
                                <td class="align-middle text-center">
                                            <span class="text-secondary text-xs font-weight-bold">${formattedEndDate}</span>
                                </td>
                                <td class="align-middle text-center">
                                    <button class="btn border-0 rounded btn-outline-info" id="buttonUserOnVoucher" data-voucher-id="${voucher.voucherID}"
                                                    data-bs-toggle="modal" data-bs-target="#exampleModalVoucherSendAcount">
                                                <i class="fa-solid fa-ticket"></i></button>
                                    <button class="btn border-0 rounded btn-outline-info" id="updateVoucherButton" data-voucher-id="${voucher.voucherID}"
                                                    data-bs-toggle="modal" data-bs-target="#exampleModalUpdateVoucher">
                                                <i class="fa-solid fa-pen-to-square"></i></button>
<!--                                    <button class="border border-0 rounded btn-outline-danger"-->
<!--                                                    data-bs-toggle="modal" data-bs-target="#exampleModalDeleteVoucher">-->
<!--                                        <i class="fa-solid fa-trash"></i></button>-->
                                </td>
                            </tr>
                            `;
                    $('#voucherTableBody').append(newRow);
                });

            },
            error: function (xhr, status, error) {
                console.error("Đã xảy ra lỗi khi gọi API: " + error);
            }
        });
    }
    $('#v-pills-home-voucher').click(function () {
        getAllVoucher(); // Gọi hàm updateUser() khi nhấn vào nút "Submit"
    });

    function saveVoucher() {
        var voucherName = $('#insNameVoucher').val();
        var reduce = $('#insReduce').val();
        var quantity = $('#insQuantity').val();
        var startDay = $('#insStartDay').val();
        var endDay = $('#insEndDay').val();
        var startDate = new Date(startDay);
        var endDate = new Date(endDay);
        var today = new Date();
        if(voucherName === '' || reduce === '' || quantity === '' || startDay === '' || endDay === ''){
            $('#alertMessVoucher').text("Please enter complete information").addClass("mb-2 alert alert-danger");
            return;
        }
        else if(reduce <= 0 || reduce > 100){
            $('#alertMessVoucher').text("Please enter reduce must be > 0 and <= 100").addClass("mb-2 alert alert-danger");
            return;
        }
        else if(reduce <= 0){
            $('#alertMessVoucher').text("Please enter quantity must be > 0").addClass("mb-2 alert alert-danger");
            return;
        }
        else if (startDate > endDate) {
            $('#alertMessVoucher').text("Please enter Start Day which is less than End Day").addClass("mb-2 alert alert-danger");
            return;
        }
        else if (startDate < today) {
            $('#alertMessVoucher').text("Please enter a Start Day greater than the current date").addClass("mb-2 alert alert-danger");
            return;
        }
        else if (endDate < today) {
            $('#alertMessVoucher').text("Please enter a End Day greater than the current date").addClass("mb-2 alert alert-danger");
            return;
        }
        // Nếu không có lỗi, loại bỏ lớp CSS alert-danger nếu có và đóng modal
        $('#alertMessVoucher').text("").removeClass("mb-2 alert alert-danger");
        $('#exampleModalToggleVoucher').modal('hide');
        let voucherData = {
            voucherName: voucherName,
            reduce: reduce,
            quantity: quantity,
            startDay: startDay,
            endDay: endDay,
        };

        $.ajax({
            url: '/api-public/vouchers/saveVoucher',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(voucherData),
            success: function (response) {
                Swal.fire({
                    title: 'Notifications',
                    text: "Voucher added successfully",
                    icon: 'success',
                    allowOutsideClick: true,
                })
                getAllVoucher();
            },
            error: function (xhr, status, error) {
                console.error("Đã xảy ra lỗi khi gọi API: " + error);
            }
        });
    }
    $('#insButtonVoucher').click(function () {
        saveVoucher();
    });
    $('#newInsButtonAccount').click(function () {
        $('#insNameVoucher').val('');
        $('#insReduce').val('');
        $('#insQuantity').val('');
        $('#insStartDay').val('');
        $('#insEndDay').val('');
        $('#alertMessVoucher').text("").removeClass("mb-2 alert alert-danger");
    });
    function getVoucherByID(voucherID) {
        $.ajax({
            url: '/api-public/vouchers/getVoucherByID2',
            method: 'GET',
            data: {
                "voucherID": voucherID
            },
            success: function (data) {
                $('#updateNameVoucher').val(data.voucherName);
                $('#updateReduceVoucher').val(data.reduce);
                $('#updateQuantityVoucher').val(data.quantity);
                $('#updateStartDayVoucher').val(data.startDay);
                $('#updateEndDayVoucher').val(data.endDay);
                $('#updateIDVoucher').val(data.voucherID);
            },
            error: function (xhr, status, error) {
                console.error("Đã xảy ra lỗi khi gọi API: " + error);
            }
        });
    }
    $(document).on('click', '#updateVoucherButton', function() {
        $('#alertMessVoucherUpdate').text("").removeClass("mb-2 alert alert-danger");
        var voucherID = $(this).attr('data-voucher-id');
        getVoucherByID(voucherID);
    });


    function updateVoucher() {
        var voucherID = $('#updateIDVoucher').val();
        var voucherName = $('#updateNameVoucher').val();
        var reduce = $('#updateReduceVoucher').val();
        var quantity = $('#updateQuantityVoucher').val();
        var startDay = $('#updateStartDayVoucher').val();
        var endDay = $('#updateEndDayVoucher').val();
        var startDate = new Date(startDay);
        var endDate = new Date(endDay);
        var today = new Date();
        if(voucherName === '' || reduce === '' || startDay === '' || endDay === ''){
            $('#alertMessVoucherUpdate').text("Please enter complete information").addClass("mb-2 alert alert-danger");
            return;
        }
        else if(reduce <= 0 || reduce > 100){
            $('#alertMessVoucherUpdate').text("Please enter reduce must be > 0 and <= 100").addClass("mb-2 alert alert-danger");
            return;
        }
        else if(quantity <= 0){
            $('#alertMessVoucherUpdate').text("Please enter quantity must be > 0").addClass("mb-2 alert alert-danger");
            return;
        }
        else if (startDate < today) {
            $('#alertMessVoucherUpdate').text("Please enter a Start Day greater than the current date").addClass("mb-2 alert alert-danger");
            return;
        }
        else if (endDate < today) {
            $('#alertMessVoucherUpdate').text("Please enter a End Day greater than the current date").addClass("mb-2 alert alert-danger");
            return;
        }
        else if (startDate > endDate) {
            // startDay lớn hơn endDay
            $('#alertMessVoucherUpdate').text("Please enter Start Day which is less than End Day").addClass("mb-2 alert alert-danger");
            return;
        }
        // Nếu không có lỗi, loại bỏ lớp CSS alert-danger nếu có
        $('#alertMessVoucherUpdate').text("").removeClass("mb-2 alert alert-danger");
        $('#exampleModalUpdateVoucher').modal('hide');
        let voucherData = {
            voucherID: voucherID,
            voucherName: voucherName,
            reduce: reduce,
            quantity: quantity,
            startDay: startDay,
            endDay: endDay,
        };

        $.ajax({
            url: '/api-public/vouchers/updateVoucher',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(voucherData),
            success: function (response) {
                Swal.fire({
                    title: 'Notifications',
                    text: "Voucher update successfully",
                    icon: 'success',
                    allowOutsideClick: true,
                })
                getAllVoucher();
            },
            error: function (xhr, status, error) {
                console.error("Đã xảy ra lỗi khi gọi API: " + error);
            }
        });
    }
    $('#buttonUpdateVoucher').click(function () {
        updateVoucher();
    });

    //load all user ở voucher
    var allUser = [];
    var listUserByVoucherID = [];
    var selectedUserIDs = [];
    function getAllUser() {
        $.ajax({
            url: '/api-public/users/getAllUsers',
            type: 'GET',
            success: function (data) {
                allUser = [];
                allUser.push(data);
                $('#userOnVoucher').empty();

                $.each(data,function (index,u){
                    var newRow = `
                                <tr>
                         <td class="align-middle text-center text-sm">
                            <div class="d-flex w-100 flex-column justify-content-center">
                                <h6 class="mb-0 text-sm">${u.id}</h6>
                            </div>
                        </td>
                        <td class="align-middle text-center text-sm">
                            <div class="d-flex w-100 flex-column justify-content-center">
                                <h6 class="mb-0 text-sm">${u.username}</h6>
                            </div>
                        </td>
                        <td class="align-middle text-center text-sm">
                            <div class="d-flex w-100 flex-column justify-content-center">
                                <h6 class="mb-0 text-sm">${u.fullname}</h6>
                            </div>
                        </td>
                        <td class="align-middle text-center text-sm">
                            <span class="text-secondary text-xs font-weight-bold">${u.email}</span>
                        </td>
                        <td class="align-middle text-center">
                            <span class="text-secondary text-xs font-weight-bold">${u.sdt}</span>
                        </td>

                        <td class="align-middle text-center">
                            <div class="form-check">
                               <input class="form-check-input classgetIDUser" type="checkbox"  data-username="${u.username}" data-id="${u.id}" id="flexCheckCheckedVoucher_${u.id}">
                                <label class="form-check-label" for="flexCheckCheckedVoucher">
                                    Voucher
                                </label>
                            </div>
                        </td>
                    </tr>
                            `;
                    $('#userOnVoucher').append(newRow);
                });
                compareAndMarkUsers(allUser, listUserByVoucherID);
                 selectedUserIDs = [];

                $('.classgetIDUser').change(function() {
                    var userID = $(this).data('id');
                    // Thêm ID của người dùng vào mảng mỗi khi ô checkbox thay đổi trạng thái
                    if (selectedUserIDs.indexOf(userID) === -1) {
                        selectedUserIDs.push(userID);
                    } else {
                        var index = selectedUserIDs.indexOf(userID);
                        if (index !== -1) {
                            selectedUserIDs.splice(index, 1);
                        }
                    }
                });
            },
            error: function (xhr, status, error) {
                console.error("Đã xảy ra lỗi khi gọi API: " + error);
            }
        });
    }
    function getListUserNotInVoucher(voucherID) {
        $.ajax({
            url: '/api-public/users/getListUserByVoucherID',
            type: 'GET',
            data: {
                "voucherID": voucherID
            },
            success: function (data) {
                listUserByVoucherID = [];
                listUserByVoucherID.push(data);
                compareAndMarkUsers(allUser, listUserByVoucherID);
            },
            error: function (xhr, status, error) {
                console.error("Đã xảy ra lỗi khi gọi API: " + error);
            }
        });
    }
    function compareAndMarkUsers(allUsers, usersNotInVoucher) {
        if (allUsers.length === 0 || usersNotInVoucher.length === 0) {
            return;
        }

        allUsers[0].forEach(function(user) {
            var found = false;
            usersNotInVoucher[0].forEach(function(userNotInVoucher) {
                if (user.username === userNotInVoucher.username) {
                    found = true;
                    return;
                }
            });
            var checkboxElement = $('input[data-username="' + user.username + '"]');

            if (checkboxElement.length > 0) { // Kiểm tra xem phần tử có tồn tại hay không
                checkboxElement.prop('checked', !found); // Đánh dấu checkbox dựa trên kết quả tìm kiếm
            } else {
                console.warn("Không tìm thấy phần tử checkbox cho người dùng:", user.username);
            }
        });
    }
    var voucherIDForVoucherDetails;
    $(document).on('click', '#buttonUserOnVoucher', function() {
        getAllUser();
        var voucherID = $(this).attr('data-voucher-id');
        getListUserNotInVoucher(voucherID);
        voucherIDForVoucherDetails = voucherID;
        compareAndMarkUsers(allUser, listUserByVoucherID);
    });

    function saveVoucherDetails(voucherID) {

        let voucherDetailsData = [];
        // Duyệt qua mảng các ID người dùng đã chọn và tạo đối tượng voucherDetails cho mỗi ID
        selectedUserIDs.forEach(function(userID) {
            var voucherDetails = {
                voucher: {
                    voucherID: voucherID
                },
                user: {
                    id: userID
                }
            };
            voucherDetailsData.push(voucherDetails);
        });

        $.ajax({
            url: '/api-public/vouchers-details/saveVoucherDetailsList',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(voucherDetailsData),
            success: function (response) {
                $('#exampleModalVoucherSendAcount').modal('hide');
                Swal.fire({
                    title: 'Notifications',
                    text: "Save the voucher for the user successfully",
                    icon: 'success',
                    allowOutsideClick: true,
                })
            },
            error: function (xhr, status, error) {
                console.error("Đã xảy ra lỗi khi gọi API: " + error);
            }
        });
    }
    $(document).on('click', '#saveVoucherDetails', function() {
        saveVoucherDetails(voucherIDForVoucherDetails);
    });

});