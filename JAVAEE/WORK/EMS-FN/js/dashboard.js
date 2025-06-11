$(document).ready(function () {
  const email = localStorage.getItem("email");
  if (!email) {
    window.location.href = "signin.html";
  } else {
    alert("Welcome to the dashboard, " + email);
    fetchEmployee();
  }

  $("#empPicture").on("change", function () {
    const file = this.files[0];
    if (file) {
      currentImageFile = file;

      const reader = new FileReader();
      reader.onload = function (e) {
        $("#imgPreview").attr("src", e.target.result).show();
      };
      reader.readAsDataURL(file);
    } else {
      $("#imgPreview").hide();
      currentImageFile = null;
    }
  });
});

$("#saveBtn").on("click", function () {
  const formData = new FormData();
  formData.append("empName", $("#empName").val());
  formData.append("empAddress", $("#empAddress").val());
  formData.append("empEmail", $("#empEmail").val());

  const fileInput = $("#empPicture")[0];
  if (fileInput.files.length > 0) {
    formData.append("empPicture", fileInput.files[0]); // ✅ This is correct
  } else {
    alert("Please select an image file.");
    return;
  }

  $.ajax({
    method: "POST",
    url: "http://localhost:8080/EMS_war_exploded/employee",
    processData: false,
    contentType: false,
    data: formData,
    success: function (response) {
      if (response.code === "200") {
        alert("Employee added successfully");
        window.location.reload();
        fetchEmployee();
      } else {
        alert("Error adding employee: " + response.message);
      }
    },
    error: function () {
      alert("Failed to add employee");
    },
  });
});

function fetchEmployee() {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/EMS_war_exploded/employee",
    success: function (response) {
      if (response.code === "200") {
        const employees = response.data;
        const employeeTbl = $("#employeeTbody");
        employeeTbl.empty();
        employees.forEach(function (employee) {
          employeeTbl.append(
            `<tr>
              <td>
                <img src="/assets/${employee.empPicture}" alt="Employee Image" width="60" height="60" />
              </td>
              <td>${employee.empId}</td>
              <td>${employee.empName}</td>
              <td>${employee.empAddress}</td>
              <td>${employee.empEmail}</td>
            </tr>`
          );
        });
      } else {
        alert("Error fetching Employees : " + response.message);
      }
    },
    error: function () {
      alert("Failed to fetch employees");
    },
  });
}

$("#btnLogOut").on("click", function () {
  localStorage.removeItem("email");
  window.location.href = "signin.html";
});
