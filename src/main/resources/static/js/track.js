(() => {

    let container = document.querySelector("#flex");



    function createCard(header, result){
        console.log(result);
        var div = document.createElement("div");
        div.setAttribute("class", "card");
        var head = document.createElement("p");
        head.innerText = header[0];
        var para = document.createElement("a");
        para.innerText = result[1];
        para.setAttribute('href', "http://localhost:81/tracks/read/" + result[0]);
        div.appendChild(head);
        div.appendChild(para);
    
        var head = document.createElement("p");
        head.innerText = header[1];
        var para = document.createElement("p");
        para.innerText = result[4];
        div.appendChild(head);
        div.appendChild(para);

    if(result[3]!= null){
        var head2 = document.createElement("p");
        head2.innerText = header[2];
        var para2 = document.createElement("p");
        para2.innerText = result[3].name;
        div.appendChild(head2);
        div.appendChild(para2);
    }

    if(result[2]!= null){    
        var head3 = document.createElement("p");
        head3.innerText = header[3];
        var para3 = document.createElement("a");
        para3.innerText = result[2].name;
        para3.href = "album.html?id=" + result[2].id;
        // para3.setAttribute('href', "http://localhost:81/albums/read/" + result[2].id);
        div.appendChild(head3);
        div.appendChild(para3);
    
        var head = document.createElement("p");
        head.innerText = header[4];
        var para = document.createElement("a");
        para.innerText = result[2].artist.name;
        para.setAttribute('href', "artist.html?id=" + result[2].artist.id);
        div.appendChild(head);
        div.appendChild(para);
    }


      return div;
    }

    function simpleFetch(){
        const a = ["http://localhost:81/tracks/read"];
            fetch(a)
            .then((response => {
              if(response.status !== 200){
                console.error(`status: ${response.status})`);
                return;
              }
              return response.json();
            })) 
            .then(data => {
                const result = data.map(({id, name, album, genre, lyrics}) => [id, name, album, genre, lyrics]);
                for (data of result) {
                const table = createCard(header, data);
                // console.log(data);
                container.appendChild(table);
                }
            })
            .catch(error => console.error(error))
            .finally(() => { console.log("All OK!"); });
  
        }
    const header = ["NAME", "LYRCIS", "GENRE", "ALBUM", "ARTIST"];
    simpleFetch();

})();