const api = 'http://localhost:8080/api/v1/job'
let currentPage = 0;
const size = 5;

$(document).ready(function() {
    loadAllJobs();
    loadJobPage()

    $('#saveJobBtn').click(function () {
        const job = {
            jobTitle: $('#jobTitle').val(),
            company: $('#companyName').val(),
            location: $('#jobLocation').val(),
            type: $('#jobType').val(),
            jobDescription: $('#jobDescription').val(),
            status: 'Active'
        };

        $.ajax({
            url: `${api}/create`,
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(job),
            success: function () {
                clearAddJobForm();
                loadAllJobs();
            },
            error: function () {
                alert('Failed to save job.');
            }
        });
    });

    $('#updateJobBtn').click(function () {
        const id = $('#editJobId').val();
        const job = {
            id: id,
            jobTitle: $('#editJobTitle').val(),
            company: $('#editCompanyName').val(),
            location: $('#editJobLocation').val(),
            type: $('#editJobType').val(),
            jobDescription: $('#editJobDescription').val(),
            status: 'Active'
        };

        $.ajax({
            url: `${api}/update`,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(job),
            success: function () {
                loadAllJobs();
            },
            error: function () {
                alert('Failed to update job.');
            }
        });
    });
});

function loadAllJobs() {
    $.ajax({
        url: `${api}/all`,
        method: 'GET',
        success: function (jobs) {
            let rows = '';
            jobs.forEach((job, index) => {
                rows += `
                    <tr>
                        <td>${index + 1}</td>
                        <td>${job.jobTitle}</td>
                        <td>${job.company}</td>
                        <td>${job.location}</td>
                        <td>${job.type}</td>
                        <td>${job.jobDescription}</td>
                        <td>
                            <button class="btn btn-sm ${job.status === 'Active' ? 'btn-success' : 'btn-secondary'} change-status-btn" data-id="${job.id}">
                                ${job.status}
                            </button>
                        </td>
                        <td>
                            <button class="btn btn-sm btn-warning me-1" onclick="editJob('${job.id}')">Edit</button>
                        </td>
                    </tr>`;
            });
            $('#jobsTableBody').html(rows);
        },
        error: function () {
            alert('Failed to load jobs.');
        }
    });
}

function editJob(id) {
    console.log("Fetching job with id:", id);
    $.ajax({
        url: `${api}/get/${id}`,
        method: 'GET',
        success: function (job) {
            console.log("Fetched job:", job);
            $('#editJobId').val(job.id);
            $('#editJobTitle').val(job.jobTitle);
            $('#editCompanyName').val(job.company);
            $('#editJobLocation').val(job.location);
            $('#editJobType').val(job.type);
            $('#editJobDescription').val(job.jobDescription);
            $('#editJobModal').modal('show');
        },
        error: function () {
            alert('Failed to fetch job data.');
        }
    });
}

$(document).on('click', '.change-status-btn', function () {
    const id = $(this).data('id');

    $.ajax({
        url: `${api}/status/${id}`,
        method: 'PATCH',
        success: function () {
            loadAllJobs();
        },
        error: function () {
            alert('Failed to change status.');
        }
    })
})

$('#searchInput').on('input', function () {
    const keyword = $(this).val().trim();

    if (keyword=== ''){
        loadAllJobs();
    }else{
        searchJobs(keyword);
    }
})

function searchJobs(keyword) {
    $.ajax({
        url: `${api}/search/${keyword}`,
        method:'GET',
        success: function (jobs) {
            let rows = '';
            jobs.forEach((job, index) => {
                rows += `
                    <tr>
                        <td>${index + 1}</td>
                        <td>${job.jobTitle}</td>
                        <td>${job.company}</td>
                        <td>${job.location}</td>
                        <td>${job.type}</td>
                        <td>${job.description}</td>
                        <td>${job.status}</td>
                        <td>
                            <button class="btn btn-sm btn-warning me-1" onclick="editJob('${job.id}')">Edit</button>
                        </td>
                    </tr>`;
            });
            $('#jobsTableBody').html(rows);
        },
        error: function () {
            alert('Failed to load jobs.');
        }
    })
}

function clearAddJobForm() {
    $('#jobTitle').val('');
    $('#companyName').val('');
    $('#jobLocation').val('');
    $('#jobType').val('Full-time');
    $('#jobDescription').val('');
}

async function loadJobPage(page = 0) {
    currentPage = page;

    $.ajax({
        url: `${api}?page=${page}&size=${size}`,
        method: "GET",
        data: { page: page, size: size },
        dataType: "json",
        success: function (data) {
            renderJobs(data.content);
            createPagination(data.totalPages, data.number);
        },
        error: function (xhr, status, err) {
            console.error("loadEventPage error:", status, err, xhr);
            const msg = `Failed to load page ${page}: ${xhr.status} ${xhr.statusText}`;
            showToast(msg, 'danger');
        }
    });
}

function renderJobs(jobs) {
    const tbody = $('#jobsTableBody');
    tbody.empty();

    jobs.forEach(job => {
        const row = `
            <tr>
                <td>${job.id ?? ''}</td>
                <td>${job.jobTitle ?? ''}</td>
                <td>${job.company ?? ''}</td>
                <td>${job.location ?? ''}</td>
                <td>${job.type ?? ''}</td>
                <td>${job.jobDescription ?? ''}</td>
                <td>
                    <button class="btn btn-sm ${job.status === 'Active' ? 'btn-success' : 'btn-secondary'} change-status-btn" data-id="${job.id}">
                        ${job.status}
                    </button>
                </td>
                <td>
                    <button class="btn btn-sm btn-warning me-1" onclick="editJob('${job.id}')">Edit</button>
                </td>
            </tr>`;
        tbody.append(row);
    });
}


function createPagination(totalPages, currentPage) {
    const pagination = $('#pagination');
    pagination.empty();

    // Previous Button
    const prevLi = $(`<li class="page-item ${currentPage === 0 ? 'disabled' : ''}">
                        <a class="page-link" href="#">Previous</a>
                      </li>`);
    prevLi.click(function (e) {
        e.preventDefault();
        if (currentPage > 0) loadJobPage(currentPage - 1);
    });
    pagination.append(prevLi);

    // Page Numbers
    for (let i = 0; i < totalPages; i++) {
        const pageItem = $(`<li class="page-item ${i === currentPage ? 'active' : ''}">
                                <a class="page-link" href="#">${i + 1}</a>
                             </li>`);
        pageItem.click(function (e) {
            e.preventDefault();
            loadJobPage(i);
        });
        pagination.append(pageItem);
    }

    // Next Button
    const nextLi = $(`<li class="page-item ${currentPage === totalPages - 1 ? 'disabled' : ''}">
                        <a class="page-link" href="#">Next</a>
                      </li>`);
    nextLi.click(function (e) {
        e.preventDefault();
        if (currentPage < totalPages - 1) loadJobPage(currentPage + 1);
    });
    pagination.append(nextLi);
}




