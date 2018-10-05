function $(selector) {
    return document.querySelector(selector);
}

function $_value(selector) {
    return $(selector).value;
}

function fetchManager({url, method, body, headers, callback}) {
    fetch(url, {method, body, headers, credentials: "same-origin"})
        .then((response) => {
            callback(response);
    });
}

function postData({url, body, callback}) {
    fetchManager({
        url: url,
        method: "POST",
        body: JSON.stringify(body),
        headers: {"content-type": "application/json"},
        callback: callback
    })
}