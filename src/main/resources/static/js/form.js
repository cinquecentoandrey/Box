const urlPost = 'http://localhost:8080/boxes/createBox'

const formElement = document.getElementById('boxForm');

formElement.addEventListener('submit', (e) => {
  e.preventDefault();
  const formData = new FormData(formElement); 
  const body = {
    boxName : formData.get('name'),
    boxLength : parseInt(formData.get('length')),
    boxWidth : parseInt(formData.get('width')),
    boxHeight : parseInt(formData.get('height')),
    boxPrice : parseFloat(formData.get('price')),
    boxInStock : parseInt(formData.get('inStock') === '' ? 0 : formData.get('inStock'))
  }
  sendRequset('POST', urlPost, body);
});

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



