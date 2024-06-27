class Statistic {
    hadSelect = false;
    listActiveMonths = [];
    myChart = undefined;
    myChartVouchersInMonth = undefined;

    constructor() {
        this.self = this;
    }

    init = async () => {
        await this.renderDataToSelect();
        await this.renderVoucherUsedInMonth();
        // await this.renderTopMostVoucherUsed();
        // await this.renderTop10MostSoldProducts();
    }
    getMonthsName = (monthNumber) => {
        const months = [
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        ];
        return months[monthNumber - 1];
    }
    renderDataToSelect = async () => {
        let selectPeriod = $('#select-period');
        let voucherSelect = $('#select-voucher-period');
        await axios.get('/api-admin/getActiveMonths')
            .then((response) => {
                response.data.forEach((e, index) => {
                    var split = e.split('/');
                    var monthName = this.getMonthsName(split[0]) + ' ' + split[1];
                    selectPeriod.append(`<option value="` + e + `" ${index === 0 ? 'selected' : ''}>` + monthName + `</option>`);
                    voucherSelect.append(`<option value="` + e + `" ${index === 0 ? 'selected' : ''}>` + monthName + `</option>`);
                })
                this.listActiveMonths = response.data;
                this.hadSelect = true;
            })
            .catch((error) => {
                console.error(error);
            })
    }
    renderTop10MostSoldProducts = async () => {
        let productsContainer = $('#top-10-sold-product-container');
        let val = $('#select-period').val();
        if (!val) {
            return;
        }
        let selectedValue = val.val().split('/');
        let month = selectedValue[0];
        let year = selectedValue[1];
        await axios
            .get('/api-admin/getTop10SoldProduct', {
                params: {
                    month: month,
                    year: year
                }
            })
            .then(response => {
                productsContainer.html('');
                response.data.forEach((e, index) => {
                    let thumbnail = (e.pictures.split(','))[0];
                    let html = `<tr class="text-center">
                                                <th class="align-middle" scope="row">${index + 1}</th>
                                                <td class="align-middle"><a href="/product?slug_url=${e.slugUrl}"><img src="../media/${thumbnail}" class="table-img"/></a></td>
                                                <td class="text-start align-middle"><a href="/product?slug_url=${e.slugUrl}" class="text-reset">${e.productName}</a></td>
                                                <td class="align-middle">$${e.price.toFixed(2)}</td>
                                                <td class="align-middle">${e.sold}</td>
                                                <td class="align-middle text-end">${e.quantity}</td>
                                            </tr>`;
                    productsContainer.append(html);
                })
            })
            .catch(error => {
                alert(error);
            })
    }
    renderTopMostVoucherUsed = async () => {
        let val = $('#select-voucher-period').val();
        if (!val) {
            return;
        }
        let voucherSelect = val.val().split('/');
        let chartData = {
            labels: [],
            data: []
        }
        await axios
            .get('/api-admin/getTopUsedVoucher', {
                params: {
                    month: voucherSelect[0],
                    year: voucherSelect[1]
                }
            })
            .then(response => {
                response.data.forEach((e, index) => {
                    chartData.labels.push(e[0].voucherName);
                    chartData.data.push(e[1]);
                });
            })
            .catch(error => {
                console.error(error);
            });
        var data = {
            labels: chartData.labels,
            datasets: [{
                label: "Used count",
                data: chartData.data,
                backgroundColor: 'rgb(126,90,76)', // Màu nền
                // borderColor: 'rgba(255, 99, 132, 1)', // Màu viền
                // borderWidth: 1 // Độ dày viền
            }]
        };

        // Tạo biểu đồ
        var ctx = $('#myChart').get(0).getContext('2d');
        if (this.myChart) {
            this.myChart.destroy(); // Destroy the existing chart instance
        }
        this.myChart = await new Chart(ctx, {
            type: 'bar', // Loại biểu đồ
            data: data, // Dữ liệu
            options: {
                indexAxis: 'y',
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true // Bắt đầu từ 0
                        }
                    }]
                }
            }
        });
    }
    renderVoucherUsedInMonth = async () => {
        let chartData = {
            labels: [],
            data: []
        }
        await axios
            .get('/api-admin/getVoucherUsedInMonth')
            .then(response => {
                response.data.forEach((e, index) => {
                    var split = e[0].split('/');
                    var monthName = this.getMonthsName(split[0]) + ' ' + split[1];
                    chartData.labels.push(monthName);
                    chartData.data.push(e[1]);
                });
            })
            .catch(error => {
                console.error(error);
            });
        var data = {
            labels: chartData.labels,
            datasets: [{
                label: "Used count",
                data: chartData.data,
                backgroundColor: 'rgb(126,90,76)', // Màu nền
                // borderColor: 'rgba(255, 99, 132, 1)', // Màu viền
                // borderWidth: 1 // Độ dày viền
            }]
        };
        let ctx = $('#monthVoucherChart').get(0).getContext('2d');
        if (this.myChartVouchersInMonth) {
            this.myChartVouchersInMonth.destroy(); // Destroy the existing chart instance
        }
        this.myChartVouchersInMonth = await new Chart(ctx, {
            type: 'bar', // Loại biểu đồ
            data: data, // Dữ liệu
            options: {
                // indexAxis: 'y',
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true // Bắt đầu từ 0
                        }
                    }]
                }
            }
        });
    }
}