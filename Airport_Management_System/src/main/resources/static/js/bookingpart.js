// Booking AJAX functionality
$(document).ready(function() {
    $('#bookingForm').submit(function (event) {
        event.preventDefault();
        createBooking();
    });

    // Function to create a booking
    function createBooking() {
        console.log('Creating booking...');
        // Collect form data
        const bookingData = {
            bookingDate: $('#bookingDate').val(),
            passengerName: $('#pasName').val(),
            flightClass: $('#flightClass').val(),
            paymentMethod: $('#paymentMethod').val(),
            seatNumber: $('#seatNumber').val(),
            packageName: $('#packageNameHidden').val(),
            email: $('#Email').val(),
        };



        console.log('Booking data:', bookingData);

        $.ajax({
            url: 'http://localhost:8082/api/v1/bookings/create',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(bookingData),
            success: function (response) {
               /* // Close the modal
                $('#bookingModal').modal('hide');
                Swal.fire({
                    title: "Booking Successful!",
                    icon: "success",
                    draggable: true
                });

                // Reset the form
                $('#bookingForm')[0].reset();
            },*/
                // Close the modal
                $('#bookingModal').modal('hide');

                // Show success message
                Swal.fire({
                    title: "Booking Created!",
                    text: "Please proceed to payment to confirm your booking.",
                    icon: "success",
                    showCancelButton: true,
                    confirmButtonColor: "#3085d6",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "Proceed to Payment",
                    cancelButtonText: "Pay Later"
                }).then((result) => {
                    if (result.isConfirmed) {
                        // Extract package price from the hidden field or response
                        const packagePrice = $('#packagePriceHidden').val() || 50.00; // Default to 50.00 if not found

                        // Redirect to payment page with booking information
                        window.location.href = `payment.html?bookingId=${response.bookingId}&package=${bookingData.packageName}&passenger=${bookingData.passengerName}&amount=${packagePrice}`;
                    } else {
                        // Reset the form
                        $('#bookingForm')[0].reset();
                    }
                });
            },
            error: function (xhr, status, error) {
                console.error('Error creating booking:', error);
                Swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text: "Something went wrong!",
                    footer: '<a href="#">Why do I have this issue?</a>'
                });
            }
        });
    }

    function showBookingDetails(booking) {
        // This is a placeholder function - implement according to your UI needs
        console.log('Booking details:', booking);

        // Example: You could update a bookings table
        updateBookingsTable();

        // Or redirect to a booking details page
        // window.location.href = '/bookings/' + booking.id;
    }

    // Function to update bookings table (if you have one)
    function updateBookingsTable() {
        $.ajax({
            url: '/api/bookings',
            type: 'GET',
            contentType: 'application/json',
            success: function (bookings) {
                // Clear existing table rows except the header
                $('#bookingsTable tbody').empty();

                // Add new rows for each booking
                bookings.forEach(function (booking) {
                    const row = `
                        <tr>
                            <td>${booking.id}</td>
                            <td>${booking.bookingDate}</td>
                            <td>${booking.passengerName}</td>
                            <td>${booking.flightClass}</td>
                            <td>${booking.seatNumber}</td>
                            <td>${booking.bookingStatus}</td>
                            <td>
                                <button class="btn btn-sm btn-info view-booking" data-id="${booking.id}">View</button>
                                <button class="btn btn-sm btn-warning edit-booking" data-id="${booking.id}">Edit</button>
                                <button class="btn btn-sm btn-danger delete-booking" data-id="${booking.id}">Delete</button>
                            </td>
                        </tr>
                    `;
                    $('#bookingsTable tbody').append(row);
                });

                // Attach event handlers to new buttons
                attachButtonHandlers();
            },
            error: function (xhr, status, error) {
                console.error('Error fetching bookings:', error);
            }
        });
    }

    // Function to attach event handlers to booking table buttons
    function attachButtonHandlers() {
        // View booking details
        $('.view-booking').click(function () {
            const bookingId = $(this).data('id');
            viewBookingDetails(bookingId);
        });

        // Edit booking
        $('.edit-booking').click(function () {
            const bookingId = $(this).data('id');
            editBooking(bookingId);
        });

        // Delete booking
        $('.delete-booking').click(function () {
            const bookingId = $(this).data('id');
            deleteBooking(bookingId);
        });
    }
});

    // Function to view booking details
   /* function viewBookingDetails(bookingId) {
        $.ajax({
            url: '/api/bookings/' + bookingId,
            type: 'GET',
            contentType: 'application/json',
            success: function(booking) {};*/
// Show booking details in a modal or details panel
// This is a placeholder - implement according to your UI