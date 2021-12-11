<?php
    include_once("connection.php"); 

    $response = array();

    if (isset($_POST["function"])) {
        $func = $_POST["function"];
        if ($func == "login") {
            login($conn);
        } else if ($func == "register") {
            adduser($conn);
        } else if($func == "addsong")
        {
            addsong($conn);
        }
        else if($func == "getsong")
        {
            getsong($conn);
        }
    } else {
        $response["code"] = -1;
        $response["message"] = "No function data found";        
        echo json_encode($response);
    }


    function addsong($conn)
    {
        if(isset($_POST['instrument']) && isset($_POST['songmap']) && isset($_POST['email']))
        {
            $instrument = $_POST['instrument'];
            $songmap = $_POST['songmap'];
            $email = $_POST['email'];
            $sql_query = "INSERT INTO savedsongs(INSTRUMENT, SONGMAP, EMAIL) VALUES('$instrument', '$songmap', '$email')";
            $query = mysqli_query($conn, $sql_query);
            if ($query) {
                $response["code"] = 1;
                $response["message"] = "Data Inserted!";
            } else {
                $response["code"] = -3;
                $response["message"] = "Insert Data Failed!";
            }
            echo json_encode($response);

        }
    }

    function getsong($conn)
    {
        if(isset($_POST['instrument']))
        {
            $instrument = $_POST['instrument'];
            $sql_query = "SELECT SONGMAP FROM savedsongs WHERE INSTRUMENT = '$instrument'";
            $sql_query2 = "SELECT EMAIL FROM savedsongs WHERE INSTRUMENT = '$instrument'";

            $result = mysqli_query($conn, $sql_query);
            $result2 = mysqli_query($conn, $sql_query2);

            $response["songmap"] = json_encode(mysqli_fetch_all($result, MYSQLI_ASSOC));
            $response["email"] = json_encode(mysqli_fetch_all($result2, MYSQLI_ASSOC));
            $response["code"] = 1;
            echo json_encode($response);
            

        }
    }

    function login($conn) {
        if (isset($_POST["email"]) && isset($_POST["password"])) {
            $email = $_POST["email"];
            $password = $_POST["password"];
            $sql_query = "SELECT * FROM user WHERE email = '$email' AND password = '$password'";
                $result = mysqli_query($conn, $sql_query);
                if (mysqli_num_rows($result) > 0) {
                    $response["code"] = 1;
                    $values = $result->fetch_row();
                    $response["message"] = "Login Successful";
                    $response["user"] = $values[2];
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

