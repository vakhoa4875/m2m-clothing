$(document).ready(function () {
    // code bên giao diện userpage
    const getShopByUserEmail = async () => {
        await axios.get('/get-shop-by-user-email-and-send-otp')
            .then(response => {
                if (response.data.data != null) {
                    Swal.fire({
                        icon: "success",
                        title: `OTP has been sent to your email <br>` +
                            `<span style="color: #bb2025">OTP time limit is 1 minute</span>`,
                        showConfirmButton: false,
                        timer: 2000
                    }).then((result) => {
                        /* Read more about handling dismissals below */
                        if (result.dismiss === Swal.DismissReason.timer) {
                            $('#v-pills-home-tab').click();
                            window.open('/admin/shop/otp', '_blank');
                        }
                    });
                }
            })
            .catch(error => {
                alert(error);
            })
    }
    $('#v-pills-home-shop').click(function () {
        getShopByUserEmail();
    })
    const saveShop = async () => {
        await axios
            .post('/api/public/shopSignUp')
            .then(response => {
            })
            .catch(error => {
                alert(error);
            })
    }
    $('#submitShop').click(function () {
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
                saveShop();
                Swal.fire({
                    title: "Success!",
                    text: "You have become a seller",
                    icon: "success"
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.open('/admin/shop', '_blank');
                    }
                })

            }
        });
    })


    // code bên giao diện acc_register_shop_OTP

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
                if (data.message === 'OTP is correct') {
                    Swal.fire({
                        title: "Success!",
                        text: "OTP is correct",
                        icon: "success",
                        showConfirmButton: false,
                        timer: 2000
                    }).then((result) => {
                        if (result.dismiss === Swal.DismissReason.timer) {
                            window.open('/admin/shop', '_blank');
                        }
                    })
                    return;
                } else if (data.message === 'OTP is incorrect') {
                    Swal.fire({
                        title: "Error!",
                        text: "OTP is incorrect",
                        icon: "error",
                        showConfirmButton: false,
                        timer: 2000
                    })
                    return;
                } else if (data.message === 'OTP is expired') {
                    Swal.fire({
                        title: "Error!",
                        text: "OTP is expired",
                        icon: "error",
                        showConfirmButton: false,
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
        if (event.key === 'Backspace' && currentInput.val().length === 0) {
            const prevInput = currentInput.prev('.otp-input');
            if (prevInput.length) {
                prevInput.focus();
            }
        }
    });

    // Automatically focus on the first input box when the page loads
    $('#otp1').focus();
})