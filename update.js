//Targeting the input tags

let eid=document.getElementById("eid")
let ename=document.getElementById("name")
let email=document.getElementById("email")
let password=document.getElementById("password")
let salary=document.getElementById("salary")
let address=document.getElementById("address")
let contact=document.getElementById("contact")
let fileInput=document.getElementById("img")



//Fetching details from the local storage

let leid=localStorage.getItem("eid")
let lname=localStorage.getItem("name")
let lemail=localStorage.getItem("email")
let lpassword=localStorage.getItem("password")
let lsalary=localStorage.getItem("salary")
let laddress=localStorage.getItem("address")
let lcontact=localStorage.getItem("contact")

//giving value in input fields

eid.value=leid
ename.value=lname
email.value=lemail
password.value=lpassword
salary.value=lsalary
address.value=laddress
contact.value=lcontact

//adding event to updatebtn

let updatebtn=document.getElementById("update-btn")
updatebtn.addEventListener("click",(e)=>{
    e.preventDefault();
    let employee={
        eid : leid,
        name : ename.value,
        email : email.value,
        password : password.value,
        address : address.value,
        salary : salary.value,
        contact : contact.value
    }
    console.log(employee)
    let x=fetch("http://localhost:8080/update", {
        method : "PUT",
        headers : {
            "Content-Type" : "application/json"
        },
        body : JSON.stringify(employee)
    })
   console.log(x);
   x.then((response)=>{
    console.log(response);
    if(response.ok)
    {
        localStorage.setItem("name",ename.value)
        localStorage.setItem("email",email.value)
        localStorage.setItem("password",password.value)
        localStorage.setItem("address",address.value)
        localStorage.setItem("salary",salary.value)
        localStorage.setItem("contact",contact.value)
        alert("Updated Successfully")
        window.location.href="home.html"
    }
   })
   

})