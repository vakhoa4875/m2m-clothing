

        $(document).ready(function () {
        $('#navbar_search_input').on('keydown', function (e) {
            if (e.which == 13) {
                searchProducts();
            }
        });
        function searchProducts() {
        var keyword = $('#navbar_search_input').val();
        var type = $('#select_loai').val();
        $.ajax({
        url: '/api/search',
        type: 'POST',
        data: {
        keyword: keyword,
        type: type
    },
        success: function (response) {
        if (response.success) {
        console.log('Gọi API thành công:', response.data);
        sessionStorage.setItem('searchResults', JSON.stringify(response.data));
        window.location.href = '/search-Product';
    } else {
        console.log('Gọi API thất bại:', response.message);
    }
    },
        error: function (xhr, status, error) {
        console.log('Lỗi:', error);
    }
    });
    }
    });

