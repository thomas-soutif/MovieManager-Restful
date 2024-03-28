function updateMenu(isConnected,username) {
	const menu = document.querySelector('.menu');

    // Efface le contenu actuel du menu
    menu.innerHTML = '';
	
	const links = [
        { href: '/movie.manager/', text: 'Accueil', active: true },
        { href: '/movie.manager/list_movie.html', text: 'Liste des Films' },
        { href: '/movie.manager/create_movie.html', text: 'Ajouter un Film' },
        { href: isConnected ? (username == 'staff' ? '/movie.manager/admin.html' : '#') : '/movie.manager/login_form.html', text: isConnected ? "Connecté en tant que " + username : 'Se Connecter' },
        { href: isConnected ? '#' : '#', text: isConnected ? "Déconnexion" : '', id:"disconnectLink" }
    ];
    
	links.forEach(link => {
        const menuItem = document.createElement('a');
        menuItem.href = link.href;
        menuItem.textContent = link.text;
        menuItem.classList.add('menu-item');
        link.active = window.location.pathname === link.href;
        if (link.active) {
            menuItem.classList.add('active');
        }
        if (link.id){
			menuItem.id = link.id
		}
        menu.appendChild(menuItem);
    });
    const disconnectLink = document.getElementById('disconnectLink');
	console.log(disconnectLink);
	if (disconnectLink) {
    	disconnectLink.addEventListener('click', handleDisconnectClick);
	}
}
        

 function checkLoginStatus() {
	 
	 // Récupérer le JWT depuis le stockage local
	const jwt = localStorage.getItem('jwt');
	let headers = {"Content-Type": "application/x-www-form-urlencoded"};
	if (jwt) {
    	// Inclure le JWT dans l'en-tête Authorization
    	headers.Authorization = `Bearer ${jwt}`;
	}
    fetch("../movie.manager/rest/users/is_login",{
		method:"GET",
		headers: headers
	})
        .then(response => {
            if (!response.ok) {
                throw new Error("Impossible de vérifier l'état de connexion.");
            }
            return response.json();
        })
        .then(data => {
            const isConnected = data.connected;
            const username = data.username;
            console.log(data.connected);
            updateMenu(isConnected,username);
        })
        .catch(error => {
            console.error(error);
        });
}

function handleDisconnectClick(event) {
    event.preventDefault();
    // Récupérer le JWT depuis le stockage local
    const jwt = localStorage.getItem('jwt');
    let headers = {"Content-Type": "application/x-www-form-urlencoded"};
	if (jwt) {
    	// Inclure le JWT dans l'en-tête Authorization
    	headers.Authorization = `Bearer ${jwt}`;
	}
	
	fetch("../movie.manager/rest/users/disconnect",{
		method:"GET",
		headers: headers
	})
        .then(response => {
            if (!response.ok) {
                throw new Error("Impossible de vérifier la bonne déconnexion");
            }
            return response.json();
        })
        .then(data => {
            localStorage.removeItem('jwt');
        })
        .catch(error => {
            console.error(error);
        });
	
    window.location.href = '/movie.manager/'
}
window.addEventListener('load', checkLoginStatus);