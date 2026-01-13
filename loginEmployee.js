let submit=document.getElementById("submit");
submit.onclick = (e) => {
    e.preventDefault();
    let email=document.getElementById("email").value;
    let password=document.getElementById("password").value;
 
    if(email === "" || password === "")
    {
        alert("Enter Password or Email");
        return;
    }

    let employee={

        email: email,
        password: password,
    };

    fetch("http://localhost:8080/login", {
        method:"POST",
        headers: {
            "Content-Type":"application/json"
        },
        body:JSON.stringify(employee)
    })
    .then(response => {
        if (!response.ok) {
           return response.json().then(errData =>{
            throw errData;
           })
        }
        return response.json();
      })
      .then((data) => {
        console.log("Success:",data);
        // console.log(data.)
        localStorage.setItem("eid",data.data.eid)
        localStorage.setItem("name",data.data.name)
        localStorage.setItem("email",data.data.email)
        localStorage.setItem("password",data.data.password)
        localStorage.setItem("address",data.data.address)
        localStorage.setItem("contact",data.data.contact)
        localStorage.setItem("salary",data.data.salary)

        alert("Login Successfully");
        window.location.href="home.html";
      })
      .catch(error => {
        console.log("Error:",error);
        if(error.msg === "Email not found") {
            alert("Invalid Email")
        }
        else if(error.msg === "Give correct Password") {
            alert("Ivalid Password")
        }
        else {
            alert("Invalid Email or Password");
        }
      });
    };
