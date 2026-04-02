const listaCategorias = document.getElementById("lista-categorias");

const cargarCategorias = () => {
    categorias.forEach((categoria) => {
        const li = document.createElement("li");
        li.innerHTML = `<a href="#" class="nav-link">${categoria}</a>`;
        listaCategorias.appendChild(li);
    });
}

cargarCategorias();

const contenedorProductos = document.getElementById("contenedor-productos");

const cargarProductos = () => {
    productos.forEach((producto) => {
        const article = document.createElement("article");

        article.innerHTML = `
            <img src="${producto.imagen}" alt="${producto.nombre}">
            <h3>${producto.nombre}</h3>
            <p>${producto.descripcion}</p>
            <p><strong>$${producto.precio}</strong></p>
            <button type="button" aria-label="Agregar ${producto.nombre} al carrito">Agregar</button>
        `;

        const botonAgregar = article.querySelector("button");
        botonAgregar.addEventListener("click", () => {
            alert(`Agregaste ${producto.nombre} al carrito`);
        });

        contenedorProductos.appendChild(article);
    });
}

cargarProductos();
