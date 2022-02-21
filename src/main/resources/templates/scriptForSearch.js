const nav = document.querySelector('.nav');
const page = document.querySelectorAll('.link');

function pageOpen() {
    page.forEach(item => item.classList.toggle('page'));
}

nav.addEventListener('click', pageOpen);

const params = {
    'id': 'Id',
    'fullName': 'ФИО',
    'department': 'Отдел',
    'phoneNumber': 'Номер телефона',
    'salary': 'Зарплата',
    'name': 'Название отдела',
    'chief': 'Глава отдела'
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
    let e = document.createElement('div');
    e.className = 'empData';
    for (const [key, value] of Object.entries(jso).filter(noBlackList)) {
        let p = `<p class="p">${_(key)} ${value} </p>`;
        e.insertAdjacentHTML('beforeend', p);
        cont.appendChild(e);
    }
}

function getAllEmployee() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', "http://localhost:8080/api/v1/employee/");
    xhr.responseType = 'json';
    xhr.send();
    xhr.onload = () => {
        xhr.response.employees.forEach(showInfo);
    }
}

function getAllDepartment() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', "http://localhost:8080/api/v1/department/");
    xhr.responseType = 'json';
    xhr.send();
    xhr.onload = () => {
        xhr.response.departments.forEach(showInfo);
    }
}

if (container.hasAttribute("data-searchEmpl")) getAllEmployee();
if (container.hasAttribute("data-searchDep")) getAllDepartment();
