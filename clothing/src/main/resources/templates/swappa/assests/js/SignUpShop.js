$(document).ready(function () {
    // code bên giao diện userpage
    const checkShop = async () => {
        await axios.get('/get-shop-by-user-email')
            .then(response => {
                if (response.data.data != null) {
                    $('#shopContainer').html(`
                        <div class="card-body p-0 mx-3 pb-2">
                            <div class="table-responsive p-0">
                                <div class="header mt-1 mb-1">
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item bg-body-secondary rounded-3">
                                            <h3 class="m-0">Sign In Shop</h3>
                                            <!--                                            <span>Manage profile information for account security</span>-->
                                        </li>
                                    </ul>
                                </div>
                                <div class="container" >
                                    <div class="row text-center justify-content-center">
                                        <h3>Welcome to M2M Clothing</h3>
                                        <h5>Click sign in if you want to go to the shop</h5>
                                        <button id="btnSignInShop" class="btn btn-primary w-25">Sign In Shop</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    `);
                } else {
                    $('#shopContainer').html(`
                        <div class="card-body p-0 mx-3 pb-2">
                            <div class="table-responsive p-0">
                                <div class="header mt-1 mb-1">
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item bg-body-secondary rounded-3">
                                            <h3 class="m-0">Register to become a Seller</h3>
                                            <!--                                            <span>Manage profile information for account security</span>-->
                                        </li>
                                    </ul>
                                </div>
                                <div class="container" >
                                    <div class="row text-center justify-content-center">
                                        <h3>Welcome to M2M Clothing</h3>
                                        <h5>Click Register Shop if you want to become a seller</h5>
                                        <button id="btnRegisterShop" class="btn btn-primary w-25 me-1">Register Shop</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    `);
                }
            })
            .catch(error => {
                alert(error);
            })
    }
    checkShop();

    const getShopByUserEmail = async () => {
        let timerInterval;
        Swal.fire({
            title: "Please wait a moment !",
            html: "I will close in <b></b> milliseconds.",
            timer: 3000,
            timerProgressBar: true,
            didOpen: () => {
                Swal.showLoading();
                const timer = Swal.getPopup().querySelector("b");
                timerInterval = setInterval(() => {
                    timer.textContent = `${Swal.getTimerLeft()}`;
                }, 100);
            },
            willClose: () => {
                clearInterval(timerInterval);
            }
        }).then((result) => {
            /* Read more about handling dismissals below */
            if (result.dismiss === Swal.DismissReason.timer) {
                console.log("I was closed by the timer");
            }
        });
        await axios.get('/get-shop-by-user-email-and-send-otp')
            .then(response => {
                if (response.data.data != null) {
                    Swal.fire({
                        icon: "success",
                        title: `OTP has been sent to your email <br>` +
                            `<span style="color: #bb2025">OTP time limit is 1 minute</span>`,
                        showConfirmButton: true,
                        timer: 5000
                    }).then((result) => {
                        if (result.dismiss === Swal.DismissReason.timer || result.isConfirmed) {
                            $('#v-pills-home-tab').click();
                            window.open('/admin/shop/otp');
                        }
                    });
                }
            })
            .catch(error => {
                alert(error);
            })
    }
    $(document).on('click', '#btnSignInShop', function () {
        getShopByUserEmail();
    })

    //nếu user chưa tạo shop
    const sendOTPForRegisterShop = async () => {
        let timerInterval;
        Swal.fire({
            title: "Please wait a moment !",
            html: "I will close in <b></b> milliseconds.",
            timer: 3000,
            timerProgressBar: true,
            didOpen: () => {
                Swal.showLoading();
                const timer = Swal.getPopup().querySelector("b");
                timerInterval = setInterval(() => {
                    timer.textContent = `${Swal.getTimerLeft()}`;
                }, 100);
            },
            willClose: () => {
                clearInterval(timerInterval);
            }
        }).then((result) => {
            /* Read more about handling dismissals below */
            if (result.dismiss === Swal.DismissReason.timer) {
                console.log("I was closed by the timer");
            }
        });
        await axios.get('/api/user/sendOTPForRegisterShop')
            .then(response => {
                if (response.data.message === 'Call Api Success') {
                    Swal.fire({
                        icon: "success",
                        title: `OTP has been sent to your email <br>` +
                            `<span style="color: #bb2025">OTP time limit is 1 minute</span>`,
                        showConfirmButton: true,
                        timer: 5000
                    }).then((result) => {
                        /* Read more about handling dismissals below */
                        if (result.dismiss === Swal.DismissReason.timer || result.isConfirmed) {
                            $('#v-pills-home-tab').click();
                            window.open('/acc/shop/otp');
                        }
                    });
                }
            })
            .catch(error => {
                alert(error);
            })
    }

    $(document).on('click', '#btnRegisterShop', function () {
        Swal.fire({
            title: "Are you sure?",
            text: "You want to become a seller!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes"
        }).then((result) => {
            if (result.isConfirmed) {
                sendOTPForRegisterShop();
            }
        });
    })


    // code bên giao diện admin_shop_OTP
    const verifyOTP = async () => {
        let otp = $('#otp1').val() + $('#otp2').val() + $('#otp3').val() + $('#otp4').val() + $('#otp5').val() + $('#otp6').val();
        if (otp.length < 6) {
            Swal.fire({
                title: "Error!",
                text: "Please enter OTP",
                icon: "error",
                showConfirmButton: true,
                timer: 2000
            })
            return;
        }
        if (isNaN(otp)) {
            Swal.fire({
                title: "Error!",
                text: "OTP must be a number",
                icon: "error",
                showConfirmButton: true,
                timer: 2000
            })
            return;
        }
        await axios
            .post('/api/user/verify-otp', null, {
                params: {
                    otp: otp
                }
            })
            .then(response => {
                let data = response.data;
                console.log(data.message)
                if (data.message === 'OTP is correct') {
                    Swal.fire({
                        title: "Success!",
                        text: "OTP is correct",
                        icon: "success",
                        showConfirmButton: true,
                        timer: 2000
                    }).then((result) => {
                        if (result.dismiss === Swal.DismissReason.timer || result.isConfirmed) {
                            window.location.href = '/admin/shop';
                        }
                    })
                    return;
                } else if (data.message === 'OTP is incorrect') {
                    Swal.fire({
                        title: "Error!",
                        text: "OTP is incorrect",
                        icon: "error",
                        showConfirmButton: true,
                        timer: 2000
                    })
                    return;
                } else if (data.message === 'OTP is expired') {
                    Swal.fire({
                        title: "Error!",
                        text: "OTP is expired",
                        icon: "error",
                        showConfirmButton: true,
                        timer: 2000
                    })
                    return;

                }
            })
            .catch(error => {
                alert(error);
            })
    }
    $('#btnConFirmOtp').click(function () {
        verifyOTP();
    })

    const resendOtp = async () => {
        await axios.post('/api/user/resend-otp')
            .then(response => {
                let responseData = response.data;
                if (responseData.message === 'Call Api Success') {
                    Swal.fire({
                        icon: "success",
                        title: "OTP has been sent to your email",
                        showConfirmButton: true,
                        timer: 2000
                    });
                }
            })
    }
    $('#btnResendOtp').click(function () {
        resendOtp();
    })

    // code bên giao diện acc_register_shop_OTP
    const saveShop = async () => {
        await axios
            .post('/api/public/shopSignUp')
            .then(response => {
            })
            .catch(error => {
                alert(error);
            })
    }
    const shopRegisterOtp = async () => {
        let otp = $('#otp1').val() + $('#otp2').val() + $('#otp3').val() + $('#otp4').val() + $('#otp5').val() + $('#otp6').val();
        if (otp.length < 6) {
            Swal.fire({
                title: "Error!",
                text: "Please enter OTP",
                icon: "error",
                showConfirmButton: true,
                timer: 2000
            })
            return;
        }
        if (isNaN(otp)) {
            Swal.fire({
                title: "Error!",
                text: "OTP must be a number",
                icon: "error",
                showConfirmButton: true,
                timer: 2000
            })
            return;
        }
        await axios
            .post('/api/user/shopRegisterOtp', null, {
                params: {
                    otp: otp
                }
            })
            .then(response => {
                let data = response.data;
                console.log(data.message);
                if (data.message === 'OTP is correct') {
                    saveShop();
                    Swal.fire({
                        title: "Success!",
                        text: "Create shop successfully",
                        icon: "success",
                        showConfirmButton: true,
                        timer: 2000
                    }).then((result) => {
                        if (result.dismiss === Swal.DismissReason.timer || result.isConfirmed) {
                            window.location.href = '/admin/shop';
                        }
                    })
                    return;
                } else if (data.message === 'OTP is incorrect') {
                    Swal.fire({
                        title: "Error!",
                        text: "OTP is incorrect",
                        icon: "error",
                        showConfirmButton: true,
                        timer: 2000
                    })
                    return;
                } else if (data.message === 'OTP is expired') {
                    Swal.fire({
                        title: "Error!",
                        text: "OTP is expired",
                        icon: "error",
                        showConfirmButton: true,
                        timer: 2000
                    })
                    return;

                }
            })
            .catch(error => {
                alert(error);
            })
    }
    $('#btnConFirmRegisterShopOtp').click(function () {
        shopRegisterOtp();
    })
    const resendRegisterShopOtp = async () => {
        await axios.post('/api/user/resendOtpForRegister')
            .then(response => {
                let responseData = response.data;
                if (responseData.message === 'Call Api Success') {
                    Swal.fire({
                        icon: "success",
                        title: "OTP has been sent to your email",
                        showConfirmButton: true,
                        timer: 2000
                    });
                }
            })
    }
    $('#btnResendRegisterShopOtp').click(function () {
        resendRegisterShopOtp();
    })

    //code o ca hai giao dien
    // Chuyển sang ô nhập tiếp theo khi nhập xong một ký tự và quay lại ô trước khi xóa
    $('.otp-input').on('input', function () {
        const currentInput = $(this);
        if (currentInput.val().length === 1) {
            const nextInput = currentInput.next('.otp-input');
            if (nextInput.length) {
                nextInput.focus();
            }
        }
    });

    $('.otp-input').on('keydown', function (event) {
        const currentInput = $(this);
        if (event.key === 'Enter') {
            event.preventDefault();
            if (window.location.pathname === '/admin/shop/otp') verifyOTP();
            if (window.location.pathname === '/acc/shop/otp') shopRegisterOtp();
        }
        if (event.key === 'Backspace' && currentInput.val().length === 0) {
            const prevInput = currentInput.prev('.otp-input');
            if (prevInput.length) {
                prevInput.focus();
            }
        }
    });
    $('#otp1').focus();
})