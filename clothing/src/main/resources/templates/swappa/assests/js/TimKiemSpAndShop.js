//js này nẳm ở trang chủ để chạy (nếu không nằm đây sẽ lỗi không thực thi được)
//muốn sử đổi front-end thì vui lòng quá bên _layout.html để sửa
$(document).ready(function () {
    //data này là có cả tên shop đi chung với tên của sản phẩm luôn
    //tenshop ở dưới là tổng hợp giữa tên sản phẩm và tên shop luôn
    var data_mau = [
        {
            ten_sp_shop: 'Hanes_shop'
        },
        {
            ten_sp_shop: 'Haessss'
        },
        {
            ten_sp_shop: 'Haesss123s'
        },
        {
            ten_sp_shop: 'Haessszxzxvzxs'
        },
        {
            ten_sp_shop: 'Haesss465464s'
        },
        {
            ten_sp_shop: 'Haess234234ss'
        },
        {
            ten_sp_shop: 'Haessjklkjljklss'
        },
        {
            ten_sp_shop: 'Hae25zxcxzvzs'
        }
    ];

    $(document).ready(function () {
        var mangMoi = [];
        if (data_mau.length >= 4) { // if này dùng để kết mảng thành mảng mới có 5 giá trị gồm giá trị đầu là shop
            mangMoi = data_mau.slice(0, 4);
        } else {
            mangMoi = data_mau; // khi data_mau tìm được bé hơn 4 thì lấy data_mau đảy thằng vào mangmoi
        }

        const $searchInput = $('#navbar_search_input');
        const $inputContainer = $('#input-container');

        let timeoutId; // Biến lưu trữ ID của timeout, sử dụng timeout để tránh gửi quá nhiều req về server liên tục

        $searchInput.on('input', function () {
            clearTimeout(timeoutId); // Xóa timeout hiện tại

            timeoutId = setTimeout(function () {
                const keyword = $searchInput.val().trim();

                if (keyword.length > 0) {
                    $inputContainer.css('display', 'block');
                    mangMoi.forEach(function (value, index) {
                        if (index == 0) {
                            $('#tenShop').append(`
                                    <a href="#" class="ms-2 text-white text-start link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover w-100" >
                                                ${value.ten_sp_shop}
                                    </a>`
                            );
                        }
                        $('#tenSp').append(`
                                    <a href="#" class="ms-2 p-1 pb-2 text-white text-start link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover">
                                        ${value.ten_sp_shop}
                                    </a>`
                        );
                    })
                } else {
                    $inputContainer.css('display', 'none');
                    mangMoi = []; //khi mất chữ trong input thì mảng sẽ thành rỗng
                }
            }, 400);
        });
    });
});