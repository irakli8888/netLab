let previous = document.querySelector('.add');
let container = document.querySelector('#container');

function changeSelect() {
    container.innerHTML = '';
    let active = document.querySelector('.active');
    previous.classList.remove('open');
    active.classList.remove('active');
    let selectValue = document.querySelector('.select').value;
    let valueContainer = document.querySelector(`.${selectValue}`);
    valueContainer.classList.add('open');
    valueContainer.classList.add('active');
    previous = valueContainer;
}

const nav = document.querySelector('.nav');
const page = document.querySelectorAll('.link');

function pageOpen() {
    page.forEach(item => item.classList.toggle('page'));
}

nav.addEventListener('click', pageOpen);

function sendJSON(json) {
    let form = document.querySelector('.active');
    let xhr = new XMLHttpRequest();
    let url = form.action; //
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4) {
            console.log('Ошибка');
        } else if (xhr.status !== 200) {
            console.log('Ошибка');
        } else {
            console.log('Отправлено');
            console.log(xhr.response);
            form.reset();
        }
    };
    xhr.send(json);
}

function deleteHTTP(id) {
    let form = document.querySelector('.active');
    let xhr = new XMLHttpRequest();
    let url = form.action + id;
    xhr.open("DELETE", url, true);
    xhr.send(null);
}

function putHTTP(json, id) {
    let form = document.querySelector('.active');
    let xhr = new XMLHttpRequest();
    let url = form.action + id;
    xhr.open("PUT", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(json);
}

const params = {
    'id': 'Id:',
    'fullName': 'ФИО:',
    'department': 'Отдел:',
    'phoneNumber': 'Номер телефона:',
    'salary': 'Зарплата:',
    'name': 'Название отдела:',
    'chief': 'Глава отдела:'
}
const blackList = ['managedDepartment', 'employees'];

function _(key) {
    return params[key] ?? key;
}

function noBlackList(entry) {
    return !blackList.includes(entry[0]);
}

function showInfo(jso) {
    const cont = document.getElementById('container');
    cont.innerHTML = '';
    for (const [key, value] of Object.entries(jso).filter(noBlackList)) {
        let p = `<p class="p">${_(key)} ${value} </p>`;
        cont.insertAdjacentHTML('beforeend', p);
        let e = document.createElement('div');
        cont.appendChild(e);
    }
}

function getEmpById(id) {
    let form = document.querySelector('.active');
    let xhr = new XMLHttpRequest();
    let url = form.action + id;
    xhr.open('GET', url);
    xhr.responseType = 'json';
    xhr.send();
    xhr.onload = () => {
        const jso = xhr.response.employeeDto;
        showInfo(jso);
    }
}

function getDepById(id) {
    let form = document.querySelector('.active');
    let xhr = new XMLHttpRequest();
    let url = form.action + id;
    xhr.open('GET', url);
    xhr.responseType = 'json';
    xhr.send();
    xhr.onload = () => {
        const jso = xhr.response.departmentDto;
        showInfo(jso);
    }
}

let form = document.querySelector('.container-select');

async function submitForm(event) {
    console.log('зашли');
    let data = {};
    let formd = document.querySelector('.active');
    console.log(formd);
    event.preventDefault();
    for (let i = 0; i < formd.length; i++) {
        let input = formd[i];
        if (input.name) {
            data[input.name] = input.value;
        }
    }
    if (formd.hasAttribute("data-deleteHTTP")) {
        deleteHTTP(data["id"]);
        return;
    }
    if (formd.hasAttribute("data-getByIdHTTP")) {
        getEmpById(data["id"]);
        return;
    }
    if (formd.hasAttribute("data-getByIdDepHTTP")) {
        getDepById(data["id"]);
        return;
    }
    let jsonStr = JSON.stringify(data);
    console.log(jsonStr);
    if (formd.hasAttribute("data-putHTTP")) {
        putHTTP(jsonStr, data["id"]);
        return;
    }
    sendJSON(jsonStr);
}

form.addEventListener('submit', submitForm);
