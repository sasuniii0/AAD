$(document).ready(function () {
  loadAllEmployees();

  var email = localStorage.getItem("email");
  if (!email) {
    window.location.href = "signin.html";
  } else {
    alert("Welcome to the dashboard, " + email);
  }
});

$("#btnLogOut").on("click", function () {
  localStorage.removeItem("email");
  window.location.href = "signin.html";
});

function loadEmployeeData() {
  const tableBody = $("#employeeTbody");
  tableBody.empty();

  employees.forEach((employee) => {
    const row = `
                    <tr>
                        <td>${employee.empId}</td>
                        <td>${employee.empName}</td>
                        <td>${employee.empAddress}</td>
                        <td>${employee.empEmail}</td>
                    </tr>
                `;
    tableBody.append(row);
  });
}
