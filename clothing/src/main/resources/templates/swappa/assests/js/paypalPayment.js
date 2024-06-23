loadSwalForPaypalContainer = (id, amount) => {
    Swal.fire({
        html: `<div id="paypal-button-container"></div>`,
        showConfirmButton: false
    });
    paypalButtonRendering('#paypal-button-container', id, amount);
}
paypalButtonRendering = (btn, id, amount) => {
    paypal.Buttons({
        createOrder: (data, actions) => {
            let obj = actions.order.create({
                purchase_units: [{
                    amount: {
                        value: amount
                    }
                }]
            });
            obj.then(res => initPayment(res, id, amount));
            return obj;
        },
        onApprove: (data, actions) => {
            return actions.order.capture().then(details => {
                succeedPayment(details, data, id);
            });
        },
        onError: err => errorPayment(err),
        onCancel: data => cancelledPayment(data)
    }).render(btn); // Render the PayPal button into #paypal-button-container
}

initPayment = (paymentID, orderID, totalAmount) => {
    let currentTime = getCurrentFormattedTime();
    let payload = {
        "paymentId": paymentID,
        "totalAmount": totalAmount,
        "currency": "USD",
        "method": "paypal",
        "description": "Payment for order #" + orderID,
        "paymentStatus": "PENDING",
        "dateCreated": currentTime,
        "orderId": orderID
    }
    let config = {
        headers: {
            'Content-Type': 'application/json'
        }
    }
    axios.post('/api-user/payment/save', payload, config)
        .then(response => {
        })
        .catch(error => {
            console.error(error);
        })
}
succeedPayment = (details, data, orderID) => {
    let payload = {
        "paymentId": details.id,
        "payerId": data.payerID,
        "totalAmount": details.purchase_units[0].amount.value,
        "currency": details.purchase_units[0].amount.currency_code,
        "intent": details.intent,
        "method": data.paymentSource,
        "description": "Payment for order #" + orderID,
        "paymentStatus": details.status,
        "dateCreated": details.create_time,
        "dateUpdated": details.update_time,
        "orderId": orderID
    }
    let config = {
        headers: {
            'Content-Type': 'application/json'
        }
    }
    axios.post('/api-user/payment/save', payload, config)
        .then(response => {
            $('.swal2-container').remove();
            Swal.fire({
                title: 'Order has paid successfully!\nWill be delivered to your hands soon :>',
                icon: 'success',
                allowOutsideClick: false
            }).then(result => {
                if (result.isConfirmed) {
                    location.reload();
                }
            })
        })
        .catch(error => {
            console.error(error);
        })
}
errorPayment = (err) => {
    console.error('An error occurred during the transaction', err);
}
cancelledPayment = (data) => {
    let config = {
        headers: {
            'Content-Type': 'application/json'
        }
    }
    axios.patch('/api-user/payment/cancel', {paymentId: data.orderID}, config)
        .then(response => {
            $('.swal2-container').remove();
            Swal.fire({
                title: response.data.message,
                icon: 'success'
            })
        })
        .catch(error => {
            console.error(error)
        })
}
getCurrentFormattedTime = () => {
    const date = new Date();
    const year = date.getUTCFullYear();
    const month = String(date.getUTCMonth() + 1).padStart(2, '0');
    const day = String(date.getUTCDate()).padStart(2, '0');
    const hours = String(date.getUTCHours()).padStart(2, '0');
    const minutes = String(date.getUTCMinutes()).padStart(2, '0');
    const seconds = String(date.getUTCSeconds()).padStart(2, '0');
    return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}Z`;
}