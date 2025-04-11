<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .green-text {
            color: green;
        }
        .green-button {
            background-color: green;
            color: white;
        }
        .green-text-dark {
            color: black;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center green-text">Register</h2>
    <form action="Register" method="post" class="w-50 mx-auto">
        <div class="mb-3">
            <label for="login" class="form-label green-text-dark">Username</label>
            <input type="text" class="form-control" id="login" name="login" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label green-text-dark">Password</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <button type="submit" class="btn green-button w-100">Register</button>
        <a href="index.jsp" class="d-block text-center mt-3 green-text">Already have an account? Login</a>
    </form>
</div>
</body>
</html>
