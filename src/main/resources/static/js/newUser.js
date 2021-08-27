//create new user
document.querySelector("form.create-user").addEventListener("submit", function (stop) {
    stop.preventDefault();
    let formElement = document.querySelector("form.create-user").elements;
    let full_name = formElement["full_name"].value;
    let user_name = formElement["user_name"].value;
    let password = formElement["password"].value;
    newUser(full_name, user_name, password);
    let msg = document.getElementById("success-create");
    msg.innerHTML = "New user created successfully!"
  })
  
  //log in user
  document.querySelector("form.log-in").addEventListener("submit", function (stop) {
    stop.preventDefault();
    let formElement = document.querySelector("form.log-in").elements;
    let user_name = formElement["user_name"].value;
    let password = formElement["password"].value;
    userLogIn(user_name, password);
  })
  
  
  function newUser(full_name, user_name, password) {
  
    let dataToPost = {
      "full_name": full_name,
      "user_name": user_name,
      "password": password
    }
    fetch("http://localhost:8082/users/create", {
        method: 'post',
        headers: {
          "Content-type": "application/json"
        },
        body: JSON.stringify(dataToPost)
      })
      .then(function (data) {
        console.log('Request succeeded with JSON response', data);
      })
      .catch(function (error) {
        console.log('Request failed', error);
      });
  }
  
  function userLogIn(user_name, password) {
    fetch('http://localhost:8082/users/read/' + user_name)
      .then(
        function (response) {
          if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            document.getElementById("login-msg").innerHTML = "Bad Attempt"
            return;
          }
          // Examine the text in the response
          response.json().then(function (data1) {
            if (data1.length == 0) {
              document.getElementById("login-msg").innerHTML = "No user found"
              return
            }
            var name;
            for (var key in data1) {
              if (key == 'user_name') {
                name = data1[key];
                console.log(name);
              }
              if (key == 'password') {
                if (password == data1[key]) {
                  console.log("Successful log in");
                  document.cookie = "user_name ="+ user_name;
                  console.log(name)
                  // var successBtn = document.getElementById("myProfile");
                  // successBtn.className = "btn btn-success";
                  // successBtn.innerHTML = name;
                  document.getElementById("login-msg").innerHTML = "Login Successfully!"
                  window.location.href= "index.html";
                  return;
                } else {
                  console.log("Error log in")
                  document.getElementById("login-msg").innerHTML = "Login FAILED!"
                  return;
                }
              }
            }
          });
        }
      )
      .catch(function (err) {
        console.log('Fetch Error :-S', err);
      });
  }
  