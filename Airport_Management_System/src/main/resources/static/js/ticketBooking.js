// Generate a random seat number like "12C"
function generateSeatNumber() {
    const row = Math.floor(Math.random() * 30) + 1;
    const seatLetter = String.fromCharCode(65 + Math.floor(Math.random() * 6)); // A to F
    return `${row}${seatLetter}`;
}

// When 'Book Now' is clicked
$(document).on('click', '.edit-btn', function () {
    const flightId = $(this).data('id');
    $('#bookingForm').data('flight-id', flightId); // Store flight ID

    $('#seatNumber').val(generateSeatNumber());
    $('#bookingDate').val(new Date().toISOString().split('T')[0]); // today's date

    $('#bookingModal').modal('show');
});
