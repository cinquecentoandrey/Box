const urlGet = 'http://localhost:8080/boxes'
const urlPost = 'http://localhost:8080/boxes/createBox'

function sendRequset(method, url, body = null) {
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

// sendRequest('POST', urlPost, body)
// .then(data => console.log(data))
// .catch(err => console.log(err))

sendRequset('GET', urlGet)
.then(data => showData(data))
.catch(err => console.log(err))

const showData = json => {
    element = json.map(element => {
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
            td.innerText= x[y],
            fragment.appendChild(td)
        });
        tr.appendChild(fragment);
        thead.appendChild(tr);
        table.appendChild(thead);
    })
}