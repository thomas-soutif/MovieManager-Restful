function updateMenu(isConnected,username) {
	const menu = document.querySelector('.menu');

    // Efface le contenu actuel du menu
    menu.innerHTML = '';
	
	const links = [
        { href: '/movie.manager/', text: 'Accueil', active: true },
        { href: '/movie.manager/list_movie.html', text: 'Liste des Films' },
        { href: '/movie.manager/create_movie.html', text: 'Ajouter un Film' },
        { href: isConnected ? '#' : '/movie.manager/login_form.html', text: isConnected ? "Connecté en tant que " + username : 'Se Connecter' },
        { href: isConnected ? '/movie.manager/rest/users/disconnect' : '#', text: isConnected ? "Déconnexion" : '' }
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
        menu.appendChild(menuItem);
    });
}
        

 function checkLoginStatus() {
    fetch("../movie.manager/rest/users/is_login")
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

window.addEventListener('load', checkLoginStatus);