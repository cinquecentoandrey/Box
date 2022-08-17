const urlBoxGet = 'http://localhost:8080/boxes'
const urlCustomerGet = 'http://localhost:8080/customers'

function sendRequest(method, url, body = null) {
    return new Promise ((resolve, reject) => {
        const xhr = new XMLHttpRequest()

        xhr.open(method, url);
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.responseType = 'json';

        xhr.onload = () => {
            if(xhr.status >= 400) {
                reject(xhr.response)
            } else {
                resolve(xhr.response)
            }
        }

        xhr.onerror = () => {
            reject(xhr.response)
        }

        xhr.send(JSON.stringify(body))
    })
}

const path = window.location.pathname.split("/").pop();

if(path === 'boxes.html') {
    sendRequest('GET', urlBoxGet)
        .then(data => showData(data))
        .catch(err => console.log(err))
} else if (path === 'costumers.html'){
    sendRequest('GET', urlCustomerGet)
        .then(data => showData(data))
        .catch(err => console.log(err))
}

const showData = json => {
    let element = json.map(element => {
        return element;
    });
    getTableData(element);
}

const getTableData = data => {
    data.forEach(x => {
        const table = document.querySelector(".table");
        const thead = document.createElement("thead");
        const tr = document.createElement("tr");
        const fragment = document.createDocumentFragment();
        const keys = Object.keys(data[0]);
        keys.forEach(y => {
            const td = document.createElement("td");
            td.innerText= x[y];
            fragment.appendChild(td);
        });
        tr.appendChild(fragment);
        thead.appendChild(tr);
        table.appendChild(thead);
    })
}