$(document).ready(function() {
    // Load all packages
    loadPackages();

    // Apply filters button click
    $('#applyFilters').click(function() {
        loadPackages();
    });

    // Reset filters button click
    $('#resetFilters').click(function() {
        $('#destination').val('');
        $('#priceRange').val('');
        $('#duration').val('');
        loadPackages();
    });
});

// Load packages from the API
function loadPackages() {
    showSpinner();

    // Get filter values
    const destination = $('#destination').val();
    const priceRange = $('#priceRange').val();
    const duration = $('#duration').val();

    // In a real application, you would include these filters in the API call
    // For now, we'll just fetch all packages

    $.ajax({
        url: 'http://localhost:8082/api/v1/package/getAll',
        type: 'GET',
        success: function(response) {
            hideSpinner();
            $('#loadingMessage').hide();
            console.log('Packages loaded successfully:', response.data);
            displayPackages(response.data);
        },
        error: function(error) {
            hideSpinner();
            $('#loadingMessage').html('<div class="alert alert-danger">Failed to load packages. Please try again later.</div>');
            console.error('Error loading packages:', error);
        }
    });
}

// Display packages in cards
function displayPackages(packages) {
    const container = $('#packageContainer');
    container.empty();

    if (packages.length === 0) {
        container.html(`
                    <div class="col-12">
                        <div class="empty-state">
                            <i class="fas fa-search"></i>
                            <h4>No packages found</h4>
                            <p>Try adjusting your search filters or check back later for new travel packages.</p>
                        </div>
                    </div>
                `);
        return;
    }

    packages.forEach(function(pkg) {
        // Create a card for each package
        const card = $(`
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card package-card h-100">
                            <img src="${pkg.imagePath}" class="card-img-top" alt="${pkg.packageName}" onerror="this.src='https://via.placeholder.com/400x250?text=Explore+${pkg.packageName}'">
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title">${pkg.packageName}</h5>
                                <p class="card-text flex-grow-1">${pkg.description}</p>
                                <div class="price-tag mb-3">$${pkg.price.toFixed(2)}</div>
                                <a href="../about.html=${pkg.id}" class="btn btn-view-details">Book Now</a>
                            </div>
                        </div>
                    </div>
                `);

        container.append(card);
    });
}

function showSpinner() {
    $('#spinnerOverlay').fadeIn(200);
}

// Hide spinner overlay
function hideSpinner() {
    $('#spinnerOverlay').fadeOut(200);
}