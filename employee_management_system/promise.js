console.log("start");
let p1=new Promise((resolve,reject)=>{
    let day="mrng"
    if(day=="mrng")
    {
        resolve("Goood morning")
    }
    else
    {
        reject("It is not morning")
    }
})

p1.then((data)=>{
    console.log(data);
    document.write(data);
})
.catch((err)=>{
    console.log(err);
    document.write(err)
})
.finally(()=>{
    console.log("Finally executed");
    
})