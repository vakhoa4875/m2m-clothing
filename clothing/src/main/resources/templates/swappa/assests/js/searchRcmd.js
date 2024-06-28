var listSearchRecommendation = [];
var matchedSearch = [];
const tenSp = $('#tenSp');
const tenShop = $('#tenShop');
const searchInput = $('#navbar_search_input');
const searchRecommendationContainer = $('#input-container');

getListProducts = async () => {
    await axios
        .get("/api-public/getProductsInfoForSearchRecommend")
        .then(response => {
            listSearchRecommendation = response.data.data;
        })
        .catch(error => {
            console.error(error);
        })
}

searchFunction = (array, keyword) => {
    let idealResults = [];
    let alternativeResult = [];
    array.forEach((value, index) => {
        let val = value[0].toLowerCase();
        if (val.startsWith(keyword)) {
            if (idealResults.length < 4) {
                idealResults.push(value);
                if (idealResults.length === 4) return idealResults;
            }
        }
        if (val.includes(keyword)) {
            if (alternativeResult.length < 4) {
                alternativeResult.push(value);
            }
        }
    });
    idealResults.push(...alternativeResult);
    return idealResults.length > 4 ? idealResults.slice(0, 4) : idealResults;
}

loadSearchRecommendation = () => {
    tenSp.html('');
    tenShop.html('');
    matchedSearch = [];

    var keyword = searchInput.val().trim();
    if (keyword.length > 0) {
        searchRecommendationContainer.css('display', 'block');
        matchedSearch = searchFunction(listSearchRecommendation, keyword);
        tenShop.append(`<a onclick="searchShop('${keyword}')" class="ms-2 text-white text-start link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover w-100" >
                            ${keyword}
                        </a>`
        );
        matchedSearch.forEach((e, index) => {
            $('#tenSp').append(`<a href="${'/p/product?slug_url=' + e[1]}" class="ms-2 p-1 pb-2 text-white text-start link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover">
                                    ${e[0]}
                                </a>`
            );
        })
    } else {
        searchRecommendationContainer.css('display', 'none');
    }
}
searchShop = async (nameShop) => {
    try {
        const response = await axios.get(`/api/search/shop/q?nameShop=${nameShop}`);
        const shopData = {
            keyword: nameShop,
            data: response.data.data
        };
        localStorage.setItem('shopData', JSON.stringify(shopData));
        window.location.href = '"/p/viewSearchShop"';
    } catch (error) {
        console.error(error);
    }
}
$(document).ready(async () => {
    await getListProducts();
})
searchInput.on('input', () => {
    loadSearchRecommendation();
});
