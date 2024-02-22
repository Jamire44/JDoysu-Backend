function validateForm() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var errorMessage = document.getElementById("errorMessage");

    // Basic email validation
    var emailRegex = /^\S+@\S+\.\S+$/;
    if (!emailRegex.test(email)) {
        errorMessage.innerHTML = "Invalid email address";
        return false;
    }

    // Password length check
    if (password.length < 6) {
        errorMessage.innerHTML = "Password must be at least 6 characters long";
        return false;
    }

    // Clear error message if validation passes
    errorMessage.innerHTML = "";
    return true;
}
