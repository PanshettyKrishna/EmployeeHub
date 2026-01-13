// const form=document.getElementById("form");
// form.addEventListener("submit",(e) => {
//     e.preventDefault()
//     let name=document.getElementById("name").value;
//     let email=document.getElementById("email").value;
//     let password=document.getElementById("password").value;
//     let salary=document.getElementById("salary").value;
//     let address=document.getElementById("address").value;
//     let contact=document.getElementById("contact").value;
//     let img=document.getElementById("img").value;
    

//     let employee={
//         name:name,
//         email:email,
//         password:password,
//         salary:salary,
//         address:address,
//         contact:contact,
//         img:img
//     };

//     fetch("http://localhost:8080/register", {
        
//         method:'POST',
//         headers:{"Content-Type":"application/json"},
//         body:JSON.stringify(employee)
//     })

//     .then(response => {
//         if(!response.ok) {
//             throw new Error('Network response was not ok');
//         }
//         return response.json();
//     })
//     .then(data => {
//         console.log('success:',data);
//     })
//     .catch((error) => {
//         console.error('Error:',error);
//     });
// })
// let submit=document.getElementById("submit");
// submit.onclick=(e)=>{
//     e.preventDefault()
//     let name=document.getElementById("name").value;
//     let email=document.getElementById("email").value;
//     let password=document.getElementById("password").value;
//     let salary=document.getElementById("salary").value;
//     let address=document.getElementById("address").value;
//     let contact=document.getElementById("contact").value;
//     let img=document.getElementById("img").files[0];
    

//     let employee={
//         name:name,
//         email:email,
//         password:password,
//         salary:salary,
//         address:address,
//         contact:contact,
//         img:img
//     };

//     fetch("http://localhost:8080/register", {
        
//         method:'POST',
//         headers:{"Content-Type":"application/json"},
//         body:JSON.stringify(employee)
//     })

//     .then(response => {
//         if(!response.ok) {
//             throw new Error('Network response was not ok');
//         }
//         return response.json();
//     })
//     .then(data => {
//         console.log('success:',data);
//     })
//     .catch((error) => {
//         console.error('Error:',error);
//     });

// };

let submit = document.getElementById("submit");
submit.onclick = (e) => {
  e.preventDefault();
  let name = document.getElementById("name").value;
  let address = document.getElementById("address").value;
  let email = document.getElementById("email").value;
  let password = document.getElementById("password").value;
  let salary = document.getElementById("salary").value;
  let contact = document.getElementById("contact").value;
  // let imageInput = document.getElementById("img");

  // if (imageInput.files.length > 0) {
  //   let file = imageInput.files[0];
  //   let reader = new FileReader();

  //   reader.onloadend = function () {
  //     let base64String = reader.result.split(',')[1];

      let employee = {
        name: name,
        address: address,
        email: email,
        password: password,
        salary: salary,
        contact: contact,
        // img: base64String,
      };

      fetch("http://localhost:8080/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(employee)
      })
      .then((response) => {
        console.log(response)
        if (response.status === 208) {
          return response.json().then(errData =>{
            console.log(errData);
            throw errData;
           });
        }       
        return response.json();
      })
      .then((data) => {
        console.log("Success:", data);
        alert("Registered Successfully");
        window.location.href="login.html";
        // console.log("SIGN UP");
      })
      .catch((error) => {
        console.log(error);
        if(error.msg === "Email Already Exists!") {
          alert("Email Already Exists!")
        }
      });
    };

let regEx = /^(?=.\d)(?=.[a-z])(?=.[A-Z])(?=.[^\w\s]).{8,}$/;

let passworderoor=document.getElementById("passworderror")
password.addEventListener("keyup",()=>{
  let password=document.getElementById("password").value
  
  let upper=/[A-Z]/
  let lower=/[a-z]/
  let specialchar=/[^\w\s]/
  if(!upper.test(password))
  {
     passworderoor.innerText=("*password must contain uppercase");
     passworderoor.style.color="red";
     document.getElementById("password").style.outlineColor="red"

  }
  else if(!lower.test(password))
  {
    passworderoor.innerText=("*password must contain lowercase");
    passworderoor.style.color="red";
    document.getElementById("password").style.outlineColor="red"

  }    
  else if(!specialchar.test(password))
    {
      passworderoor.innerText=("*password must contain special characters");
      passworderoor.style.color="red";
      document.getElementById("password").style.outlineColor="red"

    }
  else if(password.length<8)
    {
      passworderoor.innerText=("*password must have 8 characters");
      passworderoor.style.color="red";
      document.getElementById("password").style.outlineColor="red"

    }      
  else
  {
    passworderoor.innerText="";
    document.getElementById("password").style.outlineColor="black"
  } 
})

// function check(password)
// {
//   let regEx = /^(?=.\d)(?=.[a-z])(?=.[A-Z])(?=.[^\w\s]).{8,}$/;
//   let bool=regEx.test(password);
//   if(bool)
//   {
//     console.log("password is matching the criteria"); 
//   }
//   else
//   {
//     console.log("password is not matching the criteria"); 
//   }

// }

// check();