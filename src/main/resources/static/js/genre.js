(() => {

    let container = document.querySelector("#flex");



    function createCard(header, result){

        var div = document.createElement("div");
        div.setAttribute("class", "card");
        var head = document.createElement("p");
        head.innerText = header[0];
        var para = document.createElement("a");
        para.innerText = result[1];
        para.setAttribute('href', "http://localhost:81/artists/read/" + result[0]);

        // var a = document.createElement('a');
        // var linkText = document.createTextNode("my title text");
        // a.appendChild(linkText);
        // a.title = "my title text";
        // a.href = "http://example.com";
        // document.body.appendChild(a);

        div.appendChild(head);
        div.appendChild(para);
    
        var head = document.createElement("p");
        head.innerText = header[1];
        div.appendChild(head);

        for(var i = 0; i < result[2].length; i ++){
            var para = document.createElement("a");
            para.innerText = result[2][i].name;
            para.setAttribute('href', "http://localhost:81/tracks/read/" + result[2][i].id);
            div.appendChild(para);
        }



      return div;
    }

    function simpleFetch(){
        const a = ["http://localhost:81/genres/read"];
            fetch(a)
            .then((response => {
              if(response.status !== 200){
                console.error(`status: ${response.status})`);
                return;
              }
              return response.json();
            })) 
            .then(data => {
                const result = data.map(({id, name, tracks}) => [id, name, tracks]);
                for (data of result) {
                const table = createCard(header, data);
                container.appendChild(table);
                }
            })
            .catch(error => console.error(error))
            .finally(() => { console.log("All OK!"); });
  
        }
    const header = ["NAME", "TRACKS"];
    simpleFetch();

})();