/**
 * 
 */
 window.onload = function(){
	getSessUser();
	getReims();
}

function getSessUser() {
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status==200){
			let user = JSON.parse(xhttp.responseText);
			//console.log(user);
			document.getElementById("welcomeHeader").innerText=`Welcome ${user.fname} ${user.lname}`;
		}
	}
	
	xhttp.open("GET", "http://localhost:8080/Ass1/getsessionuser.json");

	xhttp.send();
}


function getReims(){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status==200){
			let reims = JSON.parse(xhttp.responseText);
			console.log("Should be reims: "+reims);
			
			let table = document.getElementById("tbod");
			
			for (let i = 0; i < reims.length; i++) {
        		let date = new Date (reims[i].submit);
                let tr = document.createElement("tr");
  				let date2 = " ";
				if (reims[i].resolved != null){
					date2 = new Date (reims[i].resolved);
				}
                tr.innerHTML = `
                    <td>${reims[i].num}</td>
                    <td>${reims[i].amount}</td>
                    <td>${reims[i].typ}</td>
                    <td>${reims[i].stat}</td>
                    <td>${reims[i].description}</td>
                    <td>${date}</td>
                    <td>${date2}</td>
                    <td>${reims[i].resolver}</td>`;
                table.appendChild(tr);
			}
			
		}
	}
	
	xhttp.open("GET", "http://localhost:8080/Ass1/getsessionreims.json");
	xhttp.send();
	
}

