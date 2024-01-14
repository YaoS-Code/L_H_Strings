<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hubery</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .hero-section {
            height: 50vh;
            width: 100vw;
            /*background-image: url('./resources/images/cell&violin.jpeg');*/
            background-color: black;
            background-size: contain;
            background-repeat: no-repeat; /* Prevent the image from repeating */
            background-position: center;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            text-align: center;
            position: relative; /* Add position relative */
        }

        .hero-text {
            position: absolute; /* Position the text absolutely */
            bottom: 100px; /* 100px from the bottom */
            left: 100px; /* 100px from the left */
            color: white; /* Adjust text color as needed */
        }

        .footer {
            background-color: #2f4f4f;
            color: white;
            text-align: center;
            padding: 10px 0;
        }

        /* Custom styles */
    </style>
</head>
<body class="d-flex flex-column min-vh-100">
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
<%--    <a class="navbar-brand" href="https://hubery.me">Hubery Song</a>--%>
    <button class="navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">About Me</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">My Cello</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">My Piano</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">My Snowboarding</a>
            </li>
            <!-- Add other menu items here -->
        </ul>
        <%
            // Assuming 'loggedInUser' is the session attribute you set upon successful login
            String loggedInUser = (String) session.getAttribute("loggedInUser");
        %>

        <% if (loggedInUser != null) { %>
        <!-- User is logged in, show 'Portal' button -->
        <a href="./member/member.jsp" class="btn btn-primary">Welcome, <%=loggedInUser%></a>
        <a href="<%=request.getContextPath()%>/logout" class="btn btn-primary">Logout</a>


        <% } else { %>
        <!-- User is not logged in, show 'Login' button -->
        <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#loginModal">Login</button>

        <% } %>

    </div>
</nav>

<!-- Login Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginModalLabel">Login</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Login Form -->
                <form method="post" action="login">
                    <div class="form-group">
                        <label for="email">Username</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Enter Email">
                    </div>
                    <div class="form-group">
                        <label for="passwordL">Password</label>
                        <input type="password" class="form-control" id="passwordL" name="password" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>
            </div>
            <div class="modal-footer">
                <span>Don't have an account? </span>
                <button type="button" class="btn btn-secondary" data-dismiss="modal" data-toggle="modal" data-target="#registerModal">Register</button>
            </div>
        </div>
    </div>
</div>

<!-- Register Modal -->
<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="registerModalLabel">Register</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="register" method="post">
                <div class="modal-body">

                    <div id="errorContainer" style="color:red;"></div>
                    <!-- First Name -->
                    <div class="form-group">
                        <label for="firstName">First Name</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter first name" required>
                    </div>
                    <!-- Last Name -->
                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter last name" required>
                    </div>
                    <!-- Email -->
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" required>
                    </div>
                    <!-- Birthday -->
                    <div class="form-group">
                        <label for="birthday">Birthday</label>
                        <input type="date" class="form-control" id="birthday" name="birthday" required>
                    </div>
                    <!-- Password -->
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                    </div>
                    <!-- Confirm Password -->
                    <div class="form-group">
                        <label for="confirmPassword">Confirm Password</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Register</button>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- Hero Section -->
<div class="hero-section">
    <div class="hero-text">
        <h1>Welcome to L & H Duet Strings</h1>
        <p>Join Lilian and Hubery in their musical journey for a cause.</p>
    </div>
</div>

<!-- Main Content -->
<div class="container my-5 flex-grow-1">
    <h2>About the Duo</h2>
    <p>Hubery and Lilian, both 7 years old, are talented young musicians who perform duet strings at public locations like Fishman's Markets and Safeway. They play the cello and violin respectively, bringing joy through music and raising donations for the Children's Hospital in Vancouver.</p>
    <!-- Add more content here -->
</div>

<!-- Footer -->
<div class="footer mt-auto">
    <p>&copy; 2024 L_H_Duet_Strings. All Rights Reserved.</p>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
    window.onload = function() {
        var showModal = "<%= session.getAttribute("showModal") %>";
        var errorMessage = "<%= session.getAttribute("errorMessage") %>";
        if (showModal === "true" && errorMessage !== null && errorMessage !== "") {
            document.getElementById("errorContainer").innerHTML = errorMessage;
            $('#registerModal').modal('show');
            // Clear the session attributes
            <%
            session.removeAttribute("errorMessage");
            session.removeAttribute("showModal");
            %>
        }
    };
</script>


</body>
</html>
