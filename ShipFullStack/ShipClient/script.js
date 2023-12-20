const shipsTable = document
    .getElementById("shipsTable")
    .getElementsByTagName("tbody")[0];

const createForm = document.getElementById("createForm");
const updateForm = document.getElementById("updateForm");

function fetchShips() {
    console.log("1");
    fetch("http://localhost:8080/ships", { method: "GET" })
        .then((response) => response.json())
        .then((data) => {
            console.log("2");
            shipsTable.innerHTML = "";
            data.forEach((ship) => {
                const row = shipsTable.insertRow();
                row.innerHTML = `
                    <td class="text-center">${ship.id}</td>
                    <td style="padding-left: 15px;">${ship.type}</td>
                    <td class="text-center">${ship.mass}</td>
                    <td class="text-center">${ship.crew}</td>
                    <td>
                        <div class="center">
                            <button class="btn btn-success" style="margin-right: 10px;" onclick="editShip(${ship.id})">Изменить</button>
                            <button class="btn btn-danger" onclick="deleteShip(${ship.id})">Удалить</button>
                        </div>  
                    </td>
                `;
            });
        });
}

document.addEventListener("DOMContentLoaded", function () {
    fetchShips();
});

createForm.addEventListener("submit", function (event) {
    event.preventDefault();

    const Type = document.getElementById("type");
    const Mass = document.getElementById("mass");
    const Crew = document.getElementById("crew");

    const formData = {
        id: 23243,
        type: Type.value,
        mass: parseInt(Mass.value),
        crew: parseInt(Crew.value),
    };

    console.log(formData);
    fetch("http://localhost:8080/ship/create", {
        method: "POST",
        body: JSON.stringify(formData),
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => response.text())
        .then(() => {
            createForm.reset();
            fetchShips();
        })
        .then((responseText) => {
            console.log(responseText);
        })
        .catch((error) => {
            console.error("Ошибка при отправке данных:", error);
        });
});

let shipID;

updateForm.addEventListener("submit", function (event) {
    event.preventDefault();

    const Type = document.getElementById("updateType");
    const Mass = document.getElementById("updateMass");
    const Crew = document.getElementById("updateCrew");

    const formData = {
        id: shipID,
        type: Type.value,
        mass: parseInt(Mass.value),
        crew: parseInt(Crew.value),
    };
    console.log(formData);

    fetch("http://localhost:8080/ship/update", {
        method: "PUT",
        body: JSON.stringify(formData),
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => response.text())
        .then(() => {
            updateForm.reset();
            fetchShips();
        })
        .then((responseText) => {
            console.log(responseText);
        })
        .catch((error) => {
            console.error("Ошибка при отправке данных:", error);
        });
});

function editShip(id) {
    fetch("http://localhost:8080/ship/" + id, { method: "GET" })
        .then((response) => response.json())
        .then((ship) => {
            shipID = ship.id;
            document.getElementById("updateType").value = ship.type;
            document.getElementById("updateMass").value = ship.mass;
            document.getElementById("updateCrew").value = ship.crew;
        });
}

function deleteShip(id) {
    if (confirm("Удалить этот корабль?")) {
        fetch("http://localhost:8080/ship/delete/" + id, {
            method: "DELETE",
        }).then(() => fetchShips());
    }
}
