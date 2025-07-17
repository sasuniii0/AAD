const api = 'http://localhost:8080/api/v1/job'

$(document).ready(function() {
    loadAllJobs();

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

