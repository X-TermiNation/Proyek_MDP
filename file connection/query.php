<?PHP 
    include_once("connection.php"); 

    $response = array();

    if (isset($_POST["function"])) {
        $func = $_POST["function"];
        if ($func == "login") {
            login($conn);
        } else if ($func == "register") {
            adduser($conn);
        }
    } else {
        $response["code"] = -1;
        $response["message"] = "No function data found";        
        echo json_encode($response);
    }



    function login($conn) {
        if (isset($_POST["email"]) && isset($_POST["password"])) {
            $email = $_POST["email"];
            $password = $_POST["password"];
            $sql_query = "SELECT * FROM user WHERE email = '$email' AND password = '$password'";
                $result = mysqli_query($conn, $sql_query);
                if (mysqli_num_rows($result) > 0) {
                    $response["code"] = 1;
                    $response["message"] = "Login Successful";
                } else {
                    $response["code"] = -3;
                    $response["message"] = "Invalid Username or Password";
                }         
        } else {
            $response["code"] = -2;
            $response["message"] = "Invalid Data";
        }
        echo json_encode($response);
    }

    function adduser($conn) {
        if (isset($_POST["username"]) && isset($_POST["email"]) && isset($_POST["password"])) {
            $username = $_POST["username"];
            $email = $_POST["email"];
            $password = $_POST["password"];
            $sql_insert = "INSERT INTO user(NAME,EMAIL,PASSWORD) VALUES ('$username', '$email', '$password')";
            $query = mysqli_query($conn, $sql_insert);
            if ($query) {
                $response["code"] = 1;
                $response["message"] = "Data Inserted!";
            } else {
                $response["code"] = -3;
                $response["message"] = "Insert Data Failed!";
            }
        } else {
            $response["code"] = -2;
            $response["message"] = "Invalid Data";
        }
        echo json_encode($response);
    }
?>

