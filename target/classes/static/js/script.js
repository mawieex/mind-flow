//community

//insights
document.addEventListener("DOMContentLoaded", function () {
    const ctx = document.getElementById('moodChart').getContext('2d');
    const moodChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
            datasets: [{
                label: 'Mood Over Time',
                data: [3, 2, 5, 4, 6, 5, 7],
                borderColor: '#d1e1d4',
                backgroundColor: 'rgba(209, 225, 212, 0.2)',
                borderWidth: 2,
                fill: true
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
});

//profile