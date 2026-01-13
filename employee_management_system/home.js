let eid=document.getElementById("eid")
let ename=document.getElementById("name")
let email=document.getElementById("email")
let password=document.getElementById("password")
let salary=document.getElementById("salary")
let address=document.getElementById("address")
let contact=document.getElementById("contact")
let image=document.getElementById("imgElement")

let leid=localStorage.getItem("eid")
let lname=localStorage.getItem("name")
let lemail=localStorage.getItem("email")
let lpassword=localStorage.getItem("password")
let lsalary=localStorage.getItem("salary")
let laddress=localStorage.getItem("address")
let lcontact=localStorage.getItem("contact")


eid.innerText=leid
ename.innerText=lname
email.innerText=lemail
password.innerText=lpassword
salary.innerText=lsalary
address.innerText=laddress
contact.innerText=lcontact


let update=document.getElementById("update-btn")
update.addEventListener("click",() => {
    window.location.href="update.html"
})

// let deletebtn=document.getElementById("delete-btn")
// let eId=leid;
// deletebtn.addEventListener("click",() => {
//     const confirmation = confirm("Are you sure you want to delete your account?");
//             if (confirmation) {
//                 let x=fetch(`http://localhost:8080/delete/${eId}`, {
//                     method : "DELETE",
//                     headers : {
//                         "Content-Type" : "application/json"
//                     }
//                 })
//                 alert("Account deleted successfully.");
//                 window.location.href="login.html";
//                 // Add logic for deleting the account, such as a DELETE request to the server
//                 // Example:
//                 // fetch('http://localhost:8080/delete', { method: 'DELETE' })
//                 //     .then(response => response.json())
//                 //     .then(data => console.log(data));
//             } else {
//                 alert("Account deletion cancelled.");
//                 window.location.href="index.html";
//             }
// })

// References
const deleteBtn = document.getElementById("delete-btn");
const deleteModal = document.getElementById("delete-modal");
const confirmDeleteBtn = document.getElementById("confirm-delete");
const cancelDeleteBtn = document.getElementById("cancel-delete");

// Show the modal when "Delete Account" button is clicked
deleteBtn.addEventListener("click", () => {
    deleteModal.classList.remove("hidden");
});

// Handle "OK" button click
confirmDeleteBtn.addEventListener("click", () => {
    const eId = localStorage.getItem("eid"); // Assuming the employee ID is stored in localStorage
    fetch(`http://localhost:8080/delete/${eId}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => {
            if (response.ok) {
                alert("Account deleted successfully.");
                window.location.href = "login.html"; // Redirect to login page
            } else {
                throw new Error("Failed to delete account.");
            }
        })
        .catch((error) => {
            console.error("Error deleting account:", error);
            alert("An error occurred while deleting the account.");
        })
        .finally(() => {
            deleteModal.classList.add("hidden"); // Hide the modal
        });
});

// Handle "Cancel" button click
cancelDeleteBtn.addEventListener("click", () => {
    deleteModal.classList.add("hidden"); // Hide the modal
    window.location.href="index.html"
});


// JavaScript
document.addEventListener("DOMContentLoaded", () => {
    let uploadBtn = document.getElementById("upload-btn");
    let imgElement = document.getElementById("imgElement");

    // Create hidden input for file selection
    const fileInput = document.createElement("input");
    fileInput.type = "file";
    fileInput.accept = "image/*"; // Only images
    fileInput.style.display = "none";
    document.body.appendChild(fileInput);

    let selectedFile = null;

    // Load the image when the page is loaded
    const eid1 = localStorage.getItem("eid"); // Mock ID
    if (eid1) {
        // Fetch and display the stored image
        fetch(`http://localhost:8080/fetchimage/${eid1}`, {
            method: "GET",
        })
            .then((response) => {
                if (response.ok) {
                    return response.blob(); // Convert response to a Blob
                } 
                else {
                    throw new Error("Failed to fetch image.");
                }
            })
            .then((blob) => {
                const imageUrl = URL.createObjectURL(blob);
                imgElement.setAttribute("src", imageUrl); // Set image src dynamically
            })
            // .catch((error) => {
            //     console.error("Error fetching image on page load:", error);
            //     alert("Failed to display the image on load.");
            // });
    }

    uploadBtn.addEventListener("click", () => {
        fileInput.click(); // Open file picker when button is clicked
    });

    fileInput.addEventListener("change", () => {
        selectedFile = fileInput.files[0]; // Get the selected file
        if (!selectedFile) {
            alert("No file selected.");
            return;
        }

        // Send image data to backend
        const formData = new FormData();
        formData.append("file", selectedFile); // Append the selected file

        fetch(`http://localhost:8080/updateimage/${eid1}`, {
            method: "POST",
            body: formData,
        })
            .then((response) => response.json())
            .then((data) => {
                if (data.statusCode === 200) {
                    alert('Image uploaded successfully');
                    // Fetch and display the updated image
                    fetch(`http://localhost:8080/fetchimage/${eid1}`, {
                        method: "GET",
                    })
                        .then((response) => {
                            if (response.ok) {
                                return response.blob(); // Convert response to a Blob
                            } else {
                                throw new Error("Failed to fetch image.");
                            }
                        })
                        .then((blob) => {
                            const imageUrl = URL.createObjectURL(blob);
                            imgElement.setAttribute("src", imageUrl); // Set image src dynamically
                        })
                        .catch((error) => {
                            console.error("Error fetching image:", error);
                            alert("Failed to display image.");
                        });
                } else {
                    alert('Error: ' + data.msg);
                }
            })
            .catch((error) => {
                console.error('Error:', error);
                alert('An error occurred.');
            });
    });
});
