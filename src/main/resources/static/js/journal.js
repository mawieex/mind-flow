// Function to load journal entries from local storage
function loadEntries() {
    const entries = JSON.parse(localStorage.getItem('journalEntries')) || [];
    const entryList = document.getElementById('entryList');
    entryList.innerHTML = ''; // Clear existing entries

    entries.forEach((entry, index) => {
        const newEntry = document.createElement('li');
        newEntry.className = 'list-group-item';
        newEntry.innerHTML = `
            <strong>${entry.timestamp}</strong>: ${entry.journalEntry} <br>
            <em>Mood: ${entry.mood}</em> <br>
            <small>Tags: ${entry.tags}</small>
            <button class="btn btn-danger btn-sm float-right" onclick="deleteEntry(${index})">Delete</button>
        `;
        entryList.appendChild(newEntry);
    });
}

// Function to delete an entry
function deleteEntry(index) {
    const entries = JSON.parse(localStorage.getItem('journalEntries')) || [];
    entries.splice(index, 1); // Remove the entry at the specified index
    // Update local storage
    localStorage.setItem('journalEntries', JSON.stringify(entries));
    loadEntries(); // Refresh the displayed entries
}

// Load entries when the page loads
window.onload = loadEntries;

document.getElementById('journalForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the form from submitting normally

    // Get the values from the form
    const journalEntry = document.getElementById('journalEntry').value;
    const mood = document.getElementById('moodSelect').value;
    const tags = document.getElementById('tags').value;

    // Get the current date and time
    const now = new Date();
    const timestamp = now.toLocaleString(); // Format the date and time

    // Create a new journal entry object
    const newEntry = {
        timestamp: timestamp,
        journalEntry: journalEntry,
        mood: mood,
        tags: tags
    };

    // Retrieve existing entries from local storage
    const entries = JSON.parse(localStorage.getItem('journalEntries')) || [];
    entries.push(newEntry); // Add the new entry to the array

    // Save the updated entries back to local storage
    localStorage.setItem('journalEntries', JSON.stringify(entries));

    // Clear the form fields
    document.getElementById('journalEntry').value = '';
    document.getElementById('moodSelect').value = '';
    document.getElementById('tags').value = '';

    // Reload the entries to display the new one
    loadEntries();
});
