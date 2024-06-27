$(document).ready(function(){
    $('#navbar_search_input').on('keydown', function (e) {
        if (e.which == 13) {
            let keyword = $('#navbar_search_input').val();
            let type = $('#select_loai').val();
            window.location.href= "/p/search_Product?key_search="+keyword+"&type_search="+type;
        }
    });
})

